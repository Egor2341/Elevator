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

    private int channelIndex;
    private boolean tvOn;
    private List<Integer> runesOnSecondFloorLocker;
    private boolean lockerOnSecondFloorQuestSolved;

    public GameState () {
        elevator = true;

        doorAvailable = true;

        boxQuestSolved = false;
        screamerOnFirstFloorPlayed = false;
        buttonAvailable = false;

        objectsInInventory = new ArrayList<>();
        for (int i = 0; i < 6; i++){
            objectsInInventory.add("");
        }

        runesOnSecondFloorLocker = List.of(0, 0, 0, 0);
        lockerOnSecondFloorQuestSolved = false;

        floorIndex = 1;

        partIndex = 0;

        saveName = "autosave";

        channelIndex = 0;
        tvOn = false;
    }
}
