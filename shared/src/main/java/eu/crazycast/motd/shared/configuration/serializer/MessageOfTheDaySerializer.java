package eu.crazycast.motd.shared.configuration.serializer;

import eu.crazycast.motd.shared.feature.MessageOfTheDay;
import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import org.jetbrains.annotations.NotNull;

public class MessageOfTheDaySerializer implements ObjectSerializer<MessageOfTheDay> {

  @Override
  public boolean supports(@NotNull Class<? super MessageOfTheDay> type) {
    return MessageOfTheDay.class.isAssignableFrom(type);
  }

  @Override
  public void serialize(
      @NotNull MessageOfTheDay object,
      @NotNull SerializationData data,
      @NotNull GenericsDeclaration generics
  ) {
    data.add("key", object.getKey());
    data.addCollection("lines", object.getLines(), String.class);
  }

  @Override
  public MessageOfTheDay deserialize(@NotNull DeserializationData data, @NotNull GenericsDeclaration generics) {
    return new MessageOfTheDay(
        data.get("key", String.class),
        data.getAsList("lines", String.class)
    );
  }
}