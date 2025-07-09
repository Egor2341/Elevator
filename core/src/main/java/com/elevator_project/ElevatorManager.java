package com.elevator_project;

import com.badlogic.gdx.scenes.scene2d.Group;
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

    public void initGroups () {
        groups.add(elevator.initGroup());
        groups.add(buttons.initGroup());
    }

    public void render () {
        initGroups();
        for (Group group : groups) {
            App.getStage().addActor(group);
        }
    }

    public void dispose () {
        for (Group group : groups) {
            group.remove();
        }
    }
}
