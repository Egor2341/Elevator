package com.elevator_project.fourth_floor;

import com.elevator_project.game.Floor;

public class FourthFloor extends Floor {

    private FourthFloorFirstSide firstSide;
    private FourthFloorSecondSide secondSide;
    private FourthFloorThirdSide thirdSide;
    private FourthFloorFourthSide fourthSide;

    public FourthFloor() {
        partIndex = 0;
        initParts();
    }

    public void initParts() {
        firstSide = new FourthFloorFirstSide();
        parts.add(firstSide);
        secondSide = new FourthFloorSecondSide();
        parts.add(secondSide);
        thirdSide = new FourthFloorThirdSide();
        parts.add(thirdSide);
        fourthSide = new FourthFloorFourthSide();
        parts.add(fourthSide);
    }

    @Override
    public void back() {
    }
}
