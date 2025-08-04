package com.elevator_project.second_floor;

import com.elevator_project.game.*;

public class SecondFloor extends Floor {

    private SecondFloorFirstSide firstSide;
    private SecondFloorSecondSide secondSide;
    private SecondFloorThirdSide thirdSide;
    private SecondFloorFourthSide fourthSide;
    private Tv tv;

    private final int moveToTvPart = 4;
    private final int moveFromTvPart = 0;

    private Locker locker;
    private final int moveToLockerPart = 5;
    private final int moveFromLockerPart = 0;

    public SecondFloor() {
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
        tv = new Tv();
        parts.add(tv);
        locker = new Locker();
        parts.add(locker);
    }

    public void moveToTv() {
        move(moveFromTvPart, moveToTvPart);
    }

    public void moveToLocker() {
        move(moveFromLockerPart, moveToLockerPart);
    }

    @Override
    public void move(int hide, int show) {
        super.move(hide, show);
        if (hide == moveToTvPart || hide == moveToLockerPart) {
            SaveManager.saveAutosave();
            arrows.show();
            downArrow.hide();
        }
        if (hide == moveToTvPart) {
            firstSide.updateTvImage();
        }
        if (show == moveToTvPart || show == moveToLockerPart) {
            arrows.hide();
            downArrow.show();
        }
    }

    @Override
    public void back() {
        int show = switch (GameManager.getGameState().getPartIndex()) {
            case moveToTvPart -> moveFromTvPart;
            case moveToLockerPart -> moveFromLockerPart;
            default -> 0;
        };
        move(GameManager.getGameState().getPartIndex(), show);
    }
}
