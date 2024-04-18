package at.fhv.lab1.eventbus.events;

import java.io.Serializable;

public class Event implements Serializable {
  private final String type = this.getClass().getSimpleName();
  private final long timestamp = System.currentTimeMillis();

  public String getType() {
    return type;
  }

  public long getTimestamp() {
    return timestamp;
  }

  @Override
  public String toString() {
    return "Event{" + "type='" + type + "timestamp=" + timestamp + '}';
  }
}
