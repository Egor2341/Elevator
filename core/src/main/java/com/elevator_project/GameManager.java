package com.elevator_project;

import com.elevator_project.first_floor.BoxQuest;
import com.elevator_project.first_floor.FirstFloor;
import lombok.Getter;

public class GameManager {
    @Getter
    private static final Atlasses atlasses;
    @Getter
    private static final ElevatorManager elevatorManager;
    @Getter
    private static final Door door;
    @Getter
    private static final Arrows arrows;
    @Getter
    private static final FirstFloor firstFloor;
    @Getter
    private static final BoxQuest boxQuest;
    @Getter
    private static final Inventory inventory;
    @Getter
    private static final InsulatingTape insulatingTape;

    static {
        atlasses = new Atlasses();
        elevatorManager = new ElevatorManager();
        door = new Door();
        arrows = new Arrows();
        firstFloor = new FirstFloor();
        boxQuest = new BoxQuest();
        inventory = new Inventory();
        insulatingTape = new InsulatingTape();
    }


    public static Floor getFloor() {
        return switch (elevatorManager.getFloorIndex()) {
            case 1 -> firstFloor;
            default -> firstFloor;
        };
    }
}
