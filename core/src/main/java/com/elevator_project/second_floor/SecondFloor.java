package com.elevator_project.second_floor;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.elevator_project.extra_elements.Arrows;
import com.elevator_project.extra_elements.DownArrow;
import com.elevator_project.extra_elements.Inventory;
import com.elevator_project.game.*;
import com.elevator_project.independent_elements.Door;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class SecondFloor extends Floor {

    private SecondFloorFirstSide firstSide;
    private SecondFloorSecondSide secondSide;
    private SecondFloorThirdSide thirdSide;
    private SecondFloorFourthSide fourthSide;
    private Tv tv;

    private final int moveToTvPart = 4;
    private final int moveFromTvPart = 0;

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
    }

    public void moveToTv() {
        move(GameManager.getGameState().getPartIndex(), moveToTvPart);
    }

    @Override
    public void move(int hide, int show) {
        super.move(hide, show);
        if (hide == moveToTvPart) {
            SaveManager.saveAutosave();
            arrows.show();
            downArrow.hide();
            firstSide.updateTvImage();
        }
        if (show == moveToTvPart) {
            arrows.hide();
            downArrow.show();
        }
    }

    @Override
    public void back() {
        int show = switch (GameManager.getGameState().getPartIndex()) {
            case moveToTvPart -> moveFromTvPart;
            default -> moveFromTvPart;
        };
        move(GameManager.getGameState().getPartIndex(), show);
    }
}
