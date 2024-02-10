package eu.crazycast.motd.shared.configuration.section;

import eu.crazycast.motd.shared.configuration.section.slots.PlayersConfiguration;
import eu.crazycast.motd.shared.configuration.section.slots.SlotsConfiguration;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.CustomKey;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;

@Names(strategy = NameStrategy.SNAKE_CASE, modifier = NameModifier.TO_LOWER_CASE)
public class VersionConfiguration extends OkaeriConfig {

  @Comment({
      "PL: Czy funkcja ma być włączona?",
      "EN: Shout the function be enabled?"
  })
  @CustomKey("enable")
  public boolean enable = true;

  @Comment({
      "PL: Tekst wyświetlany zamiast liczby graczy online",
      "    Dostępne zmienne: {players}, {slots}",
      "EN: Text displayed instead of number of online players",
      "    Placeholders: {players}, {slots}"
  })
  @CustomKey("text")
  public String text = "&7Online players: &f{players}&e/&c{slots}";

  @Comment({
      "PL: Konfiguracja liczby graczy online",
      "EN: Configuration online players amount"
  })
  @CustomKey("players-configuration")
  public PlayersConfiguration playersConfiguration = new PlayersConfiguration();

  @Comment({
      "PL: Konfiguracja liczby slotów",
      "EN: Configuration slot amount"
  })
  @CustomKey("slots-configuration")
  public SlotsConfiguration slotsConfiguration = new SlotsConfiguration();
}