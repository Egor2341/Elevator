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

public class SecondFloor implements Floor {

    private final List<RoomPart> parts;
    private final List<Group> groups;
    private SecondFloorFirstSide firstSide;
    private SecondFloorSecondSide secondSide;
    private SecondFloorThirdSide thirdSide;
    private SecondFloorFourthSide fourthSide;
    private final Door door;
    private final Inventory inventory;
    private final Arrows arrows;
    private final DownArrow downArrow;
    private int partIndex;

    public SecondFloor() {
        parts = new ArrayList<>();
        groups = new ArrayList<>();
        door = GameManager.getDoor();
        inventory = GameManager.getInventory();
        arrows = GameManager.getArrows();
        downArrow = GameManager.getDownArrow();
        partIndex = 0;
        initParts();
    }

    private void initParts() {
        firstSide = new SecondFloorFirstSide();
        parts.add(firstSide);
        secondSide = new SecondFloorSecondSide();
        parts.add(secondSide);
        thirdSide = new SecondFloorThirdSide();
        parts.add(thirdSide);
        fourthSide = new SecondFloorFourthSide();
        parts.add(fourthSide);
    }

    private void initGroups() {
        for (RoomPart part : parts) {
            groups.add(part.initGroup());
        }
    }

    @Override
    public void right() {
        move(partIndex, (partIndex + 1) % 4);
    }

    @Override
    public void left() {
        move(partIndex, (partIndex + 3) % 4);
    }

    @Override
    public void forward() {

    }

    @Override
    public void back() {

    }

    private void move (int hide, int show) {
        App.getSoundManager().playSteps();
        if (hide == 2) {
            door.hide();
        }
        partIndex = show;
        groups.get(hide).setVisible(false);
        groups.get(show).setVisible(true);
        if (show == 2) {
            door.show();
        }
    }

    @Override
    public void render() {
        initGroups();
        for (Group group : groups) {
            App.getStage().addActor(group);
        }
        inventory.render();
        door.render();
        arrows.render();
        downArrow.render();
        downArrow.hide();
        move(2, 0);
    }

    @Override
    public void dispose() {
        for (Group group : groups) {
            group.remove();
        }
        inventory.dispose();
        door.dispose();
        arrows.dispose();
        downArrow.dispose();
    }

    @Override
    public void hide() {
        for (Group group : groups) {
            group.setVisible(false);
        }
        door.hide();
    }

    @Override
    public void show() {
        for (Group group : groups) {
            group.setVisible(true);
        }
        if (partIndex == 2) {
            door.show();
        }
    }
}
