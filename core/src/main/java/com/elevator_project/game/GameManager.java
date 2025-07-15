package com.elevator_project.game;

import com.elevator_project.extra_elements.DownArrow;
import com.elevator_project.independent_elements.Door;
import com.elevator_project.elevator.ElevatorManager;
import com.elevator_project.independent_elements.InsulatingTape;
import com.elevator_project.extra_elements.Arrows;
import com.elevator_project.extra_elements.Inventory;
import com.elevator_project.first_floor.BoxQuest;
import com.elevator_project.first_floor.FirstFloor;
import lombok.Getter;
import lombok.Setter;

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
    private static final Inventory inventory;
    @Getter
    private static final InsulatingTape insulatingTape;
    @Getter
    @Setter
    private static boolean elevator;
    @Getter
    private static final DownArrow downArrow;

    static {
        elevator = true;
        atlasses = new Atlasses();
        door = new Door();
        inventory = new Inventory();
        arrows = new Arrows();
        downArrow = new DownArrow();
        elevatorManager = new ElevatorManager();
        firstFloor = new FirstFloor();
        insulatingTape = new InsulatingTape();
    }


    public static Floor getFloor() {
        return switch (elevatorManager.getFloorIndex()) {
            case 1 -> firstFloor;
            default -> firstFloor;
        };
    }
}
