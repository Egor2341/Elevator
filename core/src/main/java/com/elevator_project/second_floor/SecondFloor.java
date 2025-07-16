package com.elevator_project.second_floor;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.elevator_project.game.App;
import com.elevator_project.game.Floor;
import com.elevator_project.game.RoomPart;

import java.util.ArrayList;
import java.util.List;

public class SecondFloor implements Floor {

    private final List<RoomPart> parts;
    private final List<Group> groups;
    private SecondFloorFirstSide firstSide;

    public SecondFloor() {
        parts = new ArrayList<>();
        groups = new ArrayList<>();
        initParts();
    }

    private void initParts() {
        firstSide = new SecondFloorFirstSide();
        parts.add(firstSide);
    }

    private void initGroups() {
        for (RoomPart part : parts) {
            groups.add(part.initGroup());
        }
    }

    @Override
    public void right() {

    }

    @Override
    public void left() {

    }

    @Override
    public void forward() {

    }

    @Override
    public void back() {

    }

    @Override
    public void render() {
        initGroups();
        for (Group group : groups) {
            App.getStage().addActor(group);
        }
    }

    @Override
    public void dispose() {
        for (Group group : groups) {
            group.remove();
        }
    }

    @Override
    public void hide() {
        for (Group group : groups) {
            group.setVisible(false);
        }
    }

    @Override
    public void show() {
        for (Group group : groups) {
            group.setVisible(true);
        }
    }
}
