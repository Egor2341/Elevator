package com.elevator_project.first_floor;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.elevator_project.*;

import java.util.ArrayList;
import java.util.List;

public class FirstFloor implements Floor {

    private final List<Group> groups;
    private final FirstFloorFirstSide firstSide;
    private final FirstFloorSecondSide secondSide;
    private final FirstFloorThirdSide thirdSide;
    private final FirstFloorFourthSide fourthSide;
    private int wallIndex;
    private final Door door;

    public FirstFloor() {
        door = GameManager.getDoor();
        groups = new ArrayList<>();
        wallIndex = 0;
        firstSide = new FirstFloorFirstSide();
        secondSide = new FirstFloorSecondSide();
        thirdSide = new FirstFloorThirdSide();
        fourthSide = new FirstFloorFourthSide();
        initGroups();
    }

    private void initGroups () {
        groups.add(firstSide.initGroup());
        groups.add(secondSide.initGroup());
        groups.add(thirdSide.initGroup());
        groups.add(fourthSide.initGroup());
        groups.get(1).setVisible(false);
        groups.get(2).setVisible(false);
        groups.get(3).setVisible(false);
    }

    public void render() {
        initGroups();
        for (Group group : groups) {
            App.getStage().addActor(group);
        }
        GameManager.getBoxQuest().render();
        GameManager.getBoxQuest().hide();
    }

    public void right() {
        move(wallIndex, (wallIndex + 1) % 4);
    }

    public void left() {
        move(wallIndex, (wallIndex + 3) % 4);
    }

    private void move (int hide, int show) {
        if (hide == 2) {
            door.dispose();
        }
        groups.get(hide).setVisible(false);
        groups.get(show).setVisible(true);
        wallIndex = show;
        if (show == 2) {
            door.render();
        } else if (show == 3) {
            fourthSide.changeWindow();
        }
    }

    public void dispose() {
        for (Group group : groups) {
            group.remove();
        }
    }

    public void hide () {
        groups.get(wallIndex).setVisible(false);
    }

    public void show () {
        groups.get(wallIndex).setVisible(true);
    }
}
