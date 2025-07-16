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

    private void initGroups() {
        for (RoomPart part : parts) {
            groups.add(part.initGroup());
        }
        for (Group group : groups) {
            group.setVisible(false);
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

    public void right() {
        move(partIndex, (partIndex + 1) % 4);
    }

    public void left() {
        move(partIndex, (partIndex + 3) % 4);
    }

    public void back() {
        move(partIndex, partIndex);
    }

    protected void move (int hide, int show) {
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

    public void render () {
        initGroups();
        for (Group group : groups) {
            App.getStage().addActor(group);
        }
        door.render();
        arrows.render();
        inventory.render();
        downArrow.render();
        downArrow.hide();
        move(2, 0);
    }

    public void dispose () {
        for (Group group : groups) {
            group.remove();
        }
        door.dispose();
        arrows.dispose();
        inventory.dispose();
        downArrow.dispose();
    }

    public void hide () {
        groups.get(partIndex).setVisible(false);
        if (partIndex == 2) {
            door.hide();
        }
    }

    public void show() {
        groups.get(partIndex).setVisible(true);
        if (partIndex == 2) {
            door.show();
        }
    }
}
