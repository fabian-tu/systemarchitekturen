package at.fhv.lab1.commandclient.commands;

//import at.fhv.lab1.queryclient.EventHandler;
import at.fhv.lab1.eventbus.EventHandler;

public class RestoreEventsCommand {
    private final EventHandler eventHandler;

    public RestoreEventsCommand(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public void execute() {
        eventHandler.handleRestoreEvents();
    }
}
