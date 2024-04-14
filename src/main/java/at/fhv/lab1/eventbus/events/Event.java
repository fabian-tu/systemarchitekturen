package at.fhv.lab1.eventbus.events;

public class Event {
    private final long timestamp = System.currentTimeMillis();
    private final String type = this.getClass().getSimpleName();

    public String getType() {
        return type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Event{" +
                "type=" + type +
                "timestamp=" + timestamp +
                '}';
    }
}
