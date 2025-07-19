package com.elevator_project.game;

import com.elevator_project.extra_elements.DownArrow;
import com.elevator_project.fifth_floor.FifthFloor;
import com.elevator_project.fourth_floor.FourthFloor;
import com.elevator_project.independent_elements.Door;
import com.elevator_project.elevator.ElevatorManager;
import com.elevator_project.independent_elements.InsulatingTape;
import com.elevator_project.extra_elements.Arrows;
import com.elevator_project.extra_elements.Inventory;
import com.elevator_project.first_floor.FirstFloor;
import com.elevator_project.second_floor.SecondFloor;
import com.elevator_project.sixth_floor.SixthFloor;
import com.elevator_project.third_floor.ThirdFloor;
import lombok.Getter;
import lombok.Setter;

public class GameManager {

    @Getter
    @Setter
    private static boolean playFootSteps;

    @Getter
    @Setter
    private static GameState gameState;

    @Getter
    private static final Atlasses atlasses;

    @Getter
    private static final Door door;
    @Getter
    private static final Arrows arrows;
    @Getter
    private static final DownArrow downArrow;
    @Getter
    private static final Inventory inventory;

    @Getter
    private static final InsulatingTape insulatingTape;

    @Getter
    private static final ElevatorManager elevatorManager;

    @Getter
    private static final FirstFloor firstFloor;
    @Getter
    private static final SecondFloor secondFloor;
    @Getter
    private static final ThirdFloor thirdFloor;
    @Getter
    private static final FourthFloor fourthFloor;
    @Getter
    private static final FifthFloor fifthFloor;
    @Getter
    private static final SixthFloor sixthFloor;

    static {
        SaveManager.loadAutosave();

        playFootSteps = true;

        atlasses = new Atlasses();

        door = new Door();
        arrows = new Arrows();
        downArrow = new DownArrow();
        inventory = new Inventory();

        insulatingTape = new InsulatingTape();

        elevatorManager = new ElevatorManager();

        firstFloor = new FirstFloor();
        secondFloor = new SecondFloor();
        thirdFloor = new ThirdFloor();
        fourthFloor = new FourthFloor();
        fifthFloor = new FifthFloor();
        sixthFloor = new SixthFloor();

        SaveManager.fillInventory();
    }


    public static Floor getFloor() {
        return switch (elevatorManager.getFloorIndex()) {
            case 1 -> firstFloor;
            case 2 -> secondFloor;
            case 3 -> thirdFloor;
            case 4 -> fourthFloor;
            case 5 -> fifthFloor;
            case 6 -> sixthFloor;
            default -> firstFloor;
        };
    }
}
