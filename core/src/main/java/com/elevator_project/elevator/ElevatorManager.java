package com.elevator_project.elevator;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.elevator_project.game.App;
import com.elevator_project.game.GameManager;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ElevatorManager {
    private final List<Group> groups;
    @Getter
    private final Elevator elevator;
    @Getter
    private final Buttons buttons;
    @Getter
    private int floorIndex;

    public ElevatorManager() {
        groups = new ArrayList<>();
        buttons = new Buttons();
        elevator = new Elevator();
        floorIndex = 1;
        buttons.hide();
    }

    public void forward() {
        elevator.hide();
        GameManager.getDoor().hide();
        buttons.show();
        GameManager.getDownArrow().show();
    }

    public void back() {
        buttons.hide();
        elevator.show();
        GameManager.getDoor().show();
        GameManager.getDownArrow().hide();
    }

    public void initGroups () {
        groups.add(elevator.initGroup());
        groups.add(buttons.initGroup());
    }

    public void render () {
        initGroups();
        for (Group group : groups) {
            App.getStage().addActor(group);
        }
        GameManager.getInventory().render();
        GameManager.getDoor().render();
        GameManager.getDownArrow().render();
        GameManager.getDownArrow().hide();
    }

    public void dispose () {
        for (Group group : groups) {
            group.remove();
        }
        GameManager.getDownArrow().dispose();
    }
}
