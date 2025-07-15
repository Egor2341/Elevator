package com.elevator_project.elevator;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.elevator_project.game.App;
import com.elevator_project.game.GameManager;
import com.elevator_project.game.GroupElements;
import com.elevator_project.game.ImageProcessing;

import java.util.ArrayList;
import java.util.List;

public class Elevator implements GroupElements {
    private final float h;
    private final float w;
    private final TextureAtlas atlas;
    private final Group mainGroup;
    private final List<Image> elements;

    public Elevator () {
        this.w = App.getDimensions()[0];
        this.h = App.getDimensions()[1];
        atlas = GameManager.getAtlasses().getElevatorAtlas();
        mainGroup = new Group();
        elements = new ArrayList<>();
        initElements();
    }

    private void initElements () {
        elements.add(initElevator());
        elements.add(initBack());
        elements.add(initButtons());
        elements.add(initDisplay());
    }

    private Image initElevator () {
        final float ELEVATOR_RESIZE_FACTOR = 158f;
        Image elevator = new Image(atlas.createSprite("Elevator"));
        ImageProcessing.process(elevator, ELEVATOR_RESIZE_FACTOR, w, h);
        return elevator;
    }

    private Image initBack () {
        final float BACK_RESIZE_FACTOR = 240f;
        final float BACK_VERT_FACTOR = 4.65f;
        final float BACK_HORIZ_FACTOR = 2.6f;
        Image back = new Image(atlas.createSprite("Back", 1));
        ImageProcessing.process(back, BACK_RESIZE_FACTOR, BACK_HORIZ_FACTOR, BACK_VERT_FACTOR);
        return back;
    }

    private Image initButtons () {
        final float BUTTONS_RESIZE_FACTOR = 900f;
        final float BUTTONS_VERT_FACTOR = 2.35f;
        final float BUTTONS_HORIZ_FACTOR = 1.53f;
        Image buttons = new Image(atlas.createSprite("Buttons"));
        ImageProcessing.process(buttons, BUTTONS_RESIZE_FACTOR, BUTTONS_HORIZ_FACTOR, BUTTONS_VERT_FACTOR);
        buttons.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                GameManager.getElevatorManager().forward();
            }
        });
        return buttons;
    }

    private Image initDisplay () {
        final float DISPLAY_RESIZE_FACTOR = 700f;
        final float DISPLAY_VERT_FACTOR = 1.8f;
        final float DISPLAY_HORIZ_FACTOR = 1.5f;
        Image display = new Image(atlas.createSprite("Display", 1));
        ImageProcessing.process(display, DISPLAY_RESIZE_FACTOR, DISPLAY_HORIZ_FACTOR, DISPLAY_VERT_FACTOR);
        return display;
    }

    public Group initGroup() {
        for (Image element : elements) {
            mainGroup.addActor(element);
        }
        return mainGroup;
    }

    public void hide () {
        mainGroup.setVisible(false);
    }

    public void show () {
        mainGroup.setVisible(true);
    }
}
