package eu.crazycast.motd.velocity.feature;

import com.velocitypowered.api.event.proxy.ProxyPingEvent;
import com.velocitypowered.api.proxy.server.ServerPing;
import com.velocitypowered.api.proxy.server.ServerPing.SamplePlayer;
import com.velocitypowered.api.proxy.server.ServerPing.Version;
import eu.crazycast.motd.shared.configuration.Configuration;
import eu.crazycast.motd.shared.configuration.section.MessageOfTheDayConfiguration;
import eu.crazycast.motd.shared.configuration.section.PlayerListConfiguration;
import eu.crazycast.motd.shared.configuration.section.VersionConfiguration;
import eu.crazycast.motd.shared.feature.MessageOfTheDay;
import eu.crazycast.motd.shared.feature.MessageOfTheDayService;
import eu.crazycast.motd.shared.placeholer.PluginPlaceholders;
import eu.crazycast.motd.shared.util.ChatUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.JoinConfiguration;

public class VelocityMessageOfTheDayService extends MessageOfTheDayService<ProxyPingEvent, Component, SamplePlayer[], Version> {

  public VelocityMessageOfTheDayService(Configuration configuration, PluginPlaceholders pluginPlaceholders) {
    super(configuration, pluginPlaceholders);
  }

  @Override
  protected Optional<Component> buildDescription() {
    MessageOfTheDayConfiguration messageOfTheDayConfiguration = this.configuration.messageOfTheDayConfiguration;
    if (!messageOfTheDayConfiguration.enable) {
      return Optional.empty();
    }

    List<MessageOfTheDay> messageOfTheDays = new ArrayList<>(messageOfTheDayConfiguration.messageOfTheDay);
    if (messageOfTheDays.isEmpty()) {
      return Optional.of(Component.empty());
    }

    this.setDescriptionIndex(index -> index + 1);

    if (this.descriptionIndex >= messageOfTheDays.size()) {
      this.descriptionIndex = 0;
    }

    MessageOfTheDay messageOfTheDay = messageOfTheDays.get(this.descriptionIndex);
    return Optional.of(Component.join(
        JoinConfiguration.separator(Component.newline()),
        messageOfTheDay.getLines()
            .stream()
            .map(this.pluginPlaceholders::append)
            .map(ChatUtil::deserialize)
            .collect(Collectors.toList())
    ));
  }

  @Override
  protected Optional<ServerPing.SamplePlayer[]> buildPlayers() {
    PlayerListConfiguration playerListConfiguration = this.configuration.playerListConfiguration;
    if (!playerListConfiguration.enable) {
      return Optional.empty();
    }

    return Optional.of(playerListConfiguration.playerList.stream()
        .map(this.pluginPlaceholders::append)
        .map(ChatUtil::deserialize)
        .map(ChatUtil::serializeSection)
        .map(name -> new SamplePlayer(name, UUID.randomUUID()))
        .toArray(SamplePlayer[]::new));
  }

  @Override
  protected Optional<ServerPing.Version> buildVersion() {
    VersionConfiguration versionConfiguration = this.configuration.versionConfiguration;
    if (!versionConfiguration.enable) {
      return Optional.empty();
    }

    String text = this.pluginPlaceholders.append(versionConfiguration.text);
    return Optional.of(new ServerPing.Version(-1, ChatUtil.serializeSection(ChatUtil.deserialize(text))));
  }

  @Override
  public void handle(ProxyPingEvent event) {
    ServerPing.Builder builder = event.getPing().asBuilder();
    this.description.ifPresent(builder::description);
    this.players.ifPresent(builder::samplePlayers);
    this.version.ifPresent(builder::version);

    event.setPing(builder.build());
  }
}