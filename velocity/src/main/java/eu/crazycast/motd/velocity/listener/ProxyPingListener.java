package eu.crazycast.motd.velocity.listener;

import com.velocitypowered.api.event.EventTask;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyPingEvent;
import eu.crazycast.motd.velocity.feature.VelocityMessageOfTheDayService;

public class ProxyPingListener {

  private final VelocityMessageOfTheDayService velocityMessageOfTheDayService;

  public ProxyPingListener(VelocityMessageOfTheDayService velocityMessageOfTheDayService) {
    this.velocityMessageOfTheDayService = velocityMessageOfTheDayService;
  }

  @Subscribe
  public EventTask onPing(ProxyPingEvent event) {
    return EventTask.async(() -> this.velocityMessageOfTheDayService.handle(event));
  }
}