package com.elevator_project.game;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.elevator_project.extra_elements.Arrows;
import com.elevator_project.extra_elements.DownArrow;
import com.elevator_project.extra_elements.Inventory;
import com.elevator_project.independent_elements.Door;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    protected final List<RoomPart> parts;
    protected final List<Group> groups;
    protected final Door door;
    protected final Inventory inventory;
    protected final Arrows arrows;
    protected final DownArrow downArrow;
    protected int partIndex;

    public void initGroups() {
        partIndex = GameManager.getGameState().getPartIndex();
        for (RoomPart part : parts) {
            groups.add(part.initGroup());
        }
        for (int i = 0; i < groups.size(); i++) {
            if (i != partIndex) {
                groups.get(i).setVisible(false);
            }
        }

    }

    public Floor() {
        parts = new ArrayList<>();
        groups = new ArrayList<>();
        door = GameManager.getDoor();
        inventory = GameManager.getInventory();
        arrows = GameManager.getArrows();
        downArrow = GameManager.getDownArrow();
        partIndex = 0;
    }

    public void initParts () {

    }

    public void right() {
        partIndex = GameManager.getGameState().getPartIndex();
        move(partIndex, (partIndex + 1) % 4);
    }

    public void left() {
        partIndex = GameManager.getGameState().getPartIndex();
        move(partIndex, (partIndex + 3) % 4);
    }

    public void back() {
        partIndex = GameManager.getGameState().getPartIndex();
        move(partIndex, partIndex);
    }

    protected void move (int hide, int show) {
        App.getSoundManager().playSteps();
        if (hide == 2) {
            door.hide();
        }
        GameManager.getGameState().setPartIndex(show);
        SaveManager.saveAutosave();
        groups.get(hide).setVisible(false);
        groups.get(show).setVisible(true);
        if (show == 2) {
            door.show();
        }
    }

    public void render () {
        GameManager.getGameState().setElevator(false);
        SaveManager.saveAutosave();
        initGroups();
        for (Group group : groups) {
            App.getStage().addActor(group);
        }
        door.render();
        arrows.render();
        inventory.render();
        downArrow.render();
        downArrow.hide();
        move(2, partIndex);
    }

    public void dispose () {
        for (Group group : groups) {
            group.remove();
        }
        door.dispose();
        arrows.dispose();
        arrows.show();
        inventory.dispose();
        downArrow.dispose();
        downArrow.show();
    }

    public void hide () {
        partIndex = GameManager.getGameState().getPartIndex();
        groups.get(partIndex).setVisible(false);
        if (partIndex == 2) {
            door.hide();
        }
    }

    public void show() {
        partIndex = GameManager.getGameState().getPartIndex();
        groups.get(partIndex).setVisible(true);
        if (partIndex == 2) {
            door.show();
        }
    }
}
