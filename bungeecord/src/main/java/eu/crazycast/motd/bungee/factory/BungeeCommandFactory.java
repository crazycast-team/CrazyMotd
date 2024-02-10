package eu.crazycast.motd.bungee.factory;

import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.bungee.LiteBungeeFactory;
import eu.crazycast.motd.bungee.BungeeMotd;
import eu.crazycast.motd.shared.factory.CommandFactory;
import eu.crazycast.motd.shared.provider.LoggerProvider;
import net.md_5.bungee.api.CommandSender;

public class BungeeCommandFactory extends CommandFactory<Object> {

  private final BungeeMotd plugin;
  private LiteCommands<CommandSender> liteCommands;

  public BungeeCommandFactory(BungeeMotd plugin, LoggerProvider loggerProvider) {
    super(loggerProvider);
    this.plugin = plugin;
  }

  @Override
  public void register() {
    this.liteCommands = LiteBungeeFactory.builder(this.plugin)
        .commands(this.commands.toArray())
        .build();
  }

  @Override
  public void unregister() {
    if (this.liteCommands != null) {
      this.liteCommands.unregister();
    }
  }
}