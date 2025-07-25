package com.elevator_project.fifth_floor;

import com.elevator_project.game.Floor;

public class FifthFloor extends Floor {

    private FifthFloorSecondSide secondSide;
    private FifthFloorFirstSide firstSide;
    private FifthFloorThirdSide thirdSide;
    private FifthFloorFourthSide fourthSide;

    public FifthFloor() {
        partIndex = 0;
        initParts();
    }

    public void initParts() {
        firstSide = new FifthFloorFirstSide();
        parts.add(firstSide);
        secondSide = new FifthFloorSecondSide();
        parts.add(secondSide);
        thirdSide = new FifthFloorThirdSide();
        parts.add(thirdSide);
        fourthSide = new FifthFloorFourthSide();
        parts.add(fourthSide);
    }

    @Override
    public void back() {
    }
}
