package eu.crazycast.motd.shared.configuration;

import eu.crazycast.motd.shared.configuration.section.MessageOfTheDayConfiguration;
import eu.crazycast.motd.shared.configuration.section.PlayerListConfiguration;
import eu.crazycast.motd.shared.configuration.section.VersionConfiguration;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.CustomKey;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Header({
    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~",
    "~                                  ~",
    "~           crazymotd              ~",
    "~                                  ~",
    "~ Version: 1.0.1                   ~",
    "~ Author: kerpson                  ~",
    "~ Website: www.crazycast.eu        ~",
    "~                                  ~",
    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
})
@Names(strategy = NameStrategy.SNAKE_CASE, modifier = NameModifier.TO_LOWER_CASE)
public class Configuration extends OkaeriConfig {

  @Comment({
      "PL: Czas, co jaki motd ma się odświeżać",
      "    Ustaw na 0s aby motd zmieniało się co ping",
      "EN: The time that the motd should refresh every",
      "    Set to 0s for motd to change every ping"
  })
  @CustomKey("update-time")
  public Duration updateTime = Duration.ofSeconds(10L);

  @Comment({
      "PL: Konfiguracja wiadomości message of the day",
      "EN: Message of the day configuration"
  })
  @CustomKey("message-of-the-day-configuration")
  public MessageOfTheDayConfiguration messageOfTheDayConfiguration = new MessageOfTheDayConfiguration();

  @Comment({
      "PL: Konfiguracja listy gracz",
      "EN: Player list configuration"
  })
  @CustomKey("player-list-configuration")
  public PlayerListConfiguration playerListConfiguration = new PlayerListConfiguration();

  @Comment({
      "PL: Konfiguracja graczy online i slotów",
      "EN: Online players and slots configuration"
  })
  @CustomKey("version-configuration")
  public VersionConfiguration versionConfiguration = new VersionConfiguration();

  @Comment({
      "PL: Konfiguracja podstawowej komendy",
      "EN: Basic command configuration"
  })
  @CustomKey("help-command")
  public List<String> helpCommand = Arrays.asList(
      "",
      "        <gradient:#FBA600:#FDE400><bold>CrazyMotd",
      " &8» &6/crazymotd reload &7- &7Reload the configuration",
      ""
  );

  @Comment({
      "PL: Wiadomość po poprawnym przeładowaniu pluginu",
      "EN: Message after correct configuration reload"
  })
  @CustomKey("configuration-reload-success-message")
  public String configurationReloadSuccessMessage = "&a# Successfully plugin reload!";

  @Comment({
      "PL: Wiadomość po niepoprawnym przeładowaniu pluginu",
      "EN: Message after incorrect configuration reload"
  })
  @CustomKey("configuration-reload-fail-message")
  public String configurationReloadFailMessage = "&c# Incorrect reload configuration! Check console";
}