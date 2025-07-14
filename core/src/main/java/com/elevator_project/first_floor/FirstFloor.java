package com.elevator_project.first_floor;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.elevator_project.game.App;
import com.elevator_project.game.Floor;
import com.elevator_project.game.GameManager;
import com.elevator_project.game.RoomPart;
import com.elevator_project.independent_elements.Door;

import java.util.ArrayList;
import java.util.List;

public class FirstFloor implements Floor {

    private final List<Group> groups;
    private int wallIndex;
    private final Door door;
    private final List<RoomPart> parts;

    public FirstFloor() {
        door = GameManager.getDoor();
        groups = new ArrayList<>();
        parts = new ArrayList<>();
        wallIndex = 0;
        initElements();
    }

    private void initElements () {
        parts.add(new FirstFloorFirstSide());
        parts.add(new FirstFloorSecondSide());
        parts.add(new FirstFloorThirdSide());
        parts.add(new FirstFloorFourthSide());
        parts.add(new BoxQuest());
    }

    private void initGroups () {
        for (RoomPart part : parts) {
            groups.add(part.initGroup());
        }
        for (int i = 1; i < groups.size(); i++) {
            groups.get(i).setVisible(false);
        }
    }

    public void right() {
        move(wallIndex, (wallIndex + 1) % 4);
    }

    public void left() {
        move(wallIndex, (wallIndex + 3) % 4);
    }

    public void back() {}

    private void move (int hide, int show) {
        App.getSoundManager().playSteps();
        if (hide == 2) {
            door.dispose();
        }
        groups.get(hide).setVisible(false);
        groups.get(show).setVisible(true);
        wallIndex = show;
        if (show == 2) {
            door.render();
            if (thirdSide.isButtonAvailable()) {
                door.setAvailable(true);
            }
        } else if (show == 3) {
            if (!GameManager.getBoxQuest().isOpen()) {
                fourthSide.changeWindow();
            }
        }
    }

    public void render() {
        initGroups();
        for (Group group : groups) {
            App.getStage().addActor(group);
        }
        GameManager.getInventory().render();
        move(2, 0);
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
