package at.fhv.lab1.eventbus.events;

import java.io.Serializable;

public class Event implements Serializable {
    private final long timestamp = System.currentTimeMillis();

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Event{" +
                "timestamp=" + timestamp +
                '}';
    }
}
