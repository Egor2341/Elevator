package com.elevator_project.third_floor;

import com.elevator_project.game.Floor;

public class ThirdFloor extends Floor {

    private ThirdFloorFirstSide firstSide;
    private ThirdFloorSecondSide secondSide;
    private ThirdFloorThirdSide thirdSide;
    private ThirdFloorForuthSide fourthSide;

    public ThirdFloor() {
        partIndex = 0;
        initParts();
    }

    private void initParts() {
        firstSide = new ThirdFloorFirstSide();
        parts.add(firstSide);
        secondSide = new ThirdFloorSecondSide();
        parts.add(secondSide);
        thirdSide = new ThirdFloorThirdSide();
        parts.add(thirdSide);
        fourthSide = new ThirdFloorForuthSide();
        parts.add(fourthSide);
    }

    @Override
    public void back() {
    }
}
