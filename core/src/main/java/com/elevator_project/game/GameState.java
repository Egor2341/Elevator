package com.elevator_project.game;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GameState {

    private boolean elevator;

    private boolean doorAvailable;

    private boolean boxQuestSolved;
    private boolean screamerOnFirstFloorPlayed;
    private boolean buttonAvailable;

    private List<String> objectsInInventory;

    private int floorIndex;

    private Integer partIndex;

    private String saveName;

    public GameState () {
        elevator = true;

        doorAvailable = true;

        boxQuestSolved = false;
        screamerOnFirstFloorPlayed = false;
        buttonAvailable = false;

        objectsInInventory = new ArrayList<>();
        objectsInInventory.add("");
        objectsInInventory.add("");
        objectsInInventory.add("");
        objectsInInventory.add("");
        objectsInInventory.add("");
        objectsInInventory.add("");

        floorIndex = 1;

        partIndex = 0;

        saveName = "autosave";
    }
}
