package com.elevator_project.game;

import lombok.Getter;
import lombok.Setter;

public class SavingData {

    @Getter
    @Setter
    private static boolean elevator;

    @Getter
    @Setter
    private static boolean doorAvailable;

    @Getter
    @Setter
    private static boolean boxQuestSolved;

    @Getter
    @Setter
    private static boolean buttonOnFirstFloorAvailable;

    @Getter
    @Setter
    private static boolean screamerOnFirstFloorPlayed;

    @Getter
    @Setter
    private static  int floorIndex;

    @Getter
    @Setter
    private static  int partIndex;

    public static void initialize () {
        elevator = true;

        doorAvailable = true;

        boxQuestSolved = false;
        buttonOnFirstFloorAvailable = false;
        screamerOnFirstFloorPlayed = false;

        floorIndex = 1;

        partIndex = 0;
    }
}
