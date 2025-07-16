package com.elevator_project.sixth_floor;

import com.elevator_project.game.Floor;

public class SixthFloor extends Floor {

    private SixthFloorFirstSide firstSide;
    private SixthFloorSecondSide secondSide;
    private SixthFloorThirdSide thirdSide;
    private SixthFloorFourthSide fourthSide;

    public SixthFloor() {
        partIndex = 0;
        initParts();
    }

    private void initParts() {
        firstSide = new SixthFloorFirstSide();
        parts.add(firstSide);
        secondSide = new SixthFloorSecondSide();
        parts.add(secondSide);
        thirdSide = new SixthFloorThirdSide();
        parts.add(thirdSide);
        fourthSide = new SixthFloorFourthSide();
        parts.add(fourthSide);
    }

    @Override
    public void back() {
    }
}
