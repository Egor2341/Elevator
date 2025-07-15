package com.elevator_project.first_floor;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.elevator_project.extra_elements.Arrows;
import com.elevator_project.extra_elements.Inventory;
import com.elevator_project.game.App;
import com.elevator_project.game.Floor;
import com.elevator_project.game.GameManager;
import com.elevator_project.game.RoomPart;
import com.elevator_project.independent_elements.Door;

import java.util.ArrayList;
import java.util.List;

public class FirstFloor implements Floor {

    private final List<Group> groups;
    private int partIndex;
    private final Door door;
    private final Inventory inventory;
    private final Arrows arrows;
    private final List<RoomPart> parts;
    private FirstFloorFirstSide firstSide;
    private FirstFloorSecondSide secondSide;
    private FirstFloorThirdSide thirdSide;
    private FirstFloorFourthSide fourthSide;
    private BoxQuest boxQuest;
    private int boxQuestIndex;

    public FirstFloor() {
        door = GameManager.getDoor();
        inventory = GameManager.getInventory();
        arrows = GameManager.getArrows();
        groups = new ArrayList<>();
        parts = new ArrayList<>();
        partIndex = 0;
        initElements();
    }

    private void initElements () {
        firstSide = new FirstFloorFirstSide();
        secondSide = new FirstFloorSecondSide();
        thirdSide = new FirstFloorThirdSide();
        fourthSide = new FirstFloorFourthSide();
        boxQuest = new BoxQuest();
        parts.add(firstSide);
        parts.add(secondSide);
        parts.add(thirdSide);
        parts.add(fourthSide);
        parts.add(boxQuest);
        boxQuestIndex = 4;
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
        move(partIndex, (partIndex + 1) % 4);
    }

    public void left() {
        move(partIndex, (partIndex + 3) % 4);
    }

    public void forward() {move(partIndex, boxQuestIndex);}

    public void back() {move(boxQuestIndex, partIndex);}

    private void move (int hide, int show) {
        App.getSoundManager().playSteps();
        if (show != boxQuestIndex) {
            arrows.show();
            partIndex = show;
        }
        if (hide == 2) {
            door.hide();
        }
        groups.get(hide).setVisible(false);
        groups.get(show).setVisible(true);
        if (show == 2) {
            door.show();
        } else if (show == 3) {
            fourthSide.changeWindow();
        }
    }

    public boolean isBoxQuestSolved() {
        return boxQuest.isOpen();
    }

    public void render() {
        initGroups();
        for (Group group : groups) {
            App.getStage().addActor(group);
        }
        inventory.render();
        door.render();
        arrows.render();
        move(2, 0);
    }

    public void dispose() {
        for (Group group : groups) {
            group.remove();
        }
        inventory.dispose();
        door.dispose();
        arrows.dispose();
    }

    public void hide () {
        groups.get(partIndex).setVisible(false);
        door.hide();
    }

    public void show () {
        groups.get(partIndex).setVisible(true);
        if (partIndex == 2) {
            door.show();
        }
    }
}
