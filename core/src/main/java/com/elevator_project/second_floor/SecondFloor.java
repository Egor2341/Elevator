package com.elevator_project.second_floor;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.elevator_project.extra_elements.Arrows;
import com.elevator_project.extra_elements.DownArrow;
import com.elevator_project.extra_elements.Inventory;
import com.elevator_project.game.App;
import com.elevator_project.game.Floor;
import com.elevator_project.game.GameManager;
import com.elevator_project.game.RoomPart;
import com.elevator_project.independent_elements.Door;

import java.util.ArrayList;
import java.util.List;

public class SecondFloor extends Floor {

    private SecondFloorFirstSide firstSide;
    private SecondFloorSecondSide secondSide;
    private SecondFloorThirdSide thirdSide;
    private SecondFloorFourthSide fourthSide;

    public SecondFloor() {
        partIndex = 0;
        initParts();
    }

    public void initParts() {
        firstSide = new SecondFloorFirstSide();
        parts.add(firstSide);
        secondSide = new SecondFloorSecondSide();
        parts.add(secondSide);
        thirdSide = new SecondFloorThirdSide();
        parts.add(thirdSide);
        fourthSide = new SecondFloorFourthSide();
        parts.add(fourthSide);
    }

    @Override
    public void back() {
    }
}
