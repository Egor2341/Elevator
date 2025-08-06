package com.elevator_project.elevator;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.elevator_project.game.App;
import com.elevator_project.game.GameManager;
import com.elevator_project.game.SaveManager;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ElevatorManager {
    private final List<Group> groups;
    @Getter
    private final Elevator elevator;
    @Getter
    private final Buttons buttons;
    @Getter
    @Setter
    private boolean moving;
    @Getter
    @Setter
    private boolean wait;

    public ElevatorManager() {
        groups = new ArrayList<>();
        buttons = new Buttons();
        elevator = new Elevator();
        buttons.hide();
        moving = false;
        wait = false;
    }

    public void moveToButtons() {
        GameManager.getDoor().hide();
        buttons.show();
        GameManager.getDownArrow().show();
    }

    public void back() {
        buttons.hide();
        GameManager.getDoor().show();
        GameManager.getDownArrow().hide();
    }

    public void moveElevator (float delta) {
        elevator.update(delta);
    }

    public List<Group> initGroups () {
        groups.add(elevator.initGroup());
        groups.add(buttons.initGroup());
        return groups;
    }

    public void render () {
        initGroups().forEach(App.getStage()::addActor);

        GameManager.getGameState().setElevator(true);

        GameManager.getInventory().render();

        GameManager.getDoor().render();
        GameManager.getDoor().show();

        GameManager.getDownArrow().render();
        GameManager.getDownArrow().hide();

        GameManager.getGameState().setPartIndex(0);

        SaveManager.saveAutosave();
    }

    public void dispose () {
        groups.forEach(Actor::remove);
        GameManager.getDownArrow().dispose();
        GameManager.getDoor().dispose();
        GameManager.getInventory().dispose();
    }
}
