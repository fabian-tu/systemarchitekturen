package at.fhv.lab1.commandclient.commands;

import at.fhv.lab1.queryclient.EventHandler;

public class ClearQueryModelsCommand {
    private final EventHandler eventHandler;

    public ClearQueryModelsCommand(EventHandler eventHandler){
        this.eventHandler = eventHandler;
    }

    public void execute(){
        eventHandler.handleDeleteQueryModels();
    }
}