package com.elevator_project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;
import java.util.List;

public class Elevator {

    private static TextureAtlas atlas;

    private static final float ELEVATOR_RESIZE_FACTOR = 158f;

    private static final float BACK_RESIZE_FACTOR = 240f;
    private static final float BACK_VERT_FACTOR = 4.65f;
    private static final float BACK_HORIZ_FACTOR = 2.6f;

    private static final float DISPLAY_RESIZE_FACTOR = 700f;
    private static final float DISPLAY_VERT_FACTOR = 1.8f;
    private static final float DISPLAY_HORIZ_FACTOR = 1.5f;

    private static final float BUTTONS_RESIZE_FACTOR = 900f;
    private static final float BUTTONS_VERT_FACTOR = 2.35f;
    private static final float BUTTONS_HORIZ_FACTOR = 1.53f;

    private static Group elevatorGroup;

    private static final List<Image> elements = new ArrayList<>();

    private static int floorIndex;

    public static TextureAtlas getAtlas() {
        return atlas;
    }


    public static void initialize(float width, float height, Stage stage) {
        floorIndex = 1;

        elevatorGroup = new Group();

        atlas = new TextureAtlas(Gdx.files.internal("Elevator.atlas"));

        Image elevator = new Image(atlas.createSprite("Elevator"));
        elevator.setSize(elevator.getWidth() * width / ELEVATOR_RESIZE_FACTOR,
            elevator.getHeight() * width / ELEVATOR_RESIZE_FACTOR);
        elements.add(elevator);

        Image back = new Image(atlas.createSprite("Back", 1));
        back.setSize(back.getWidth() * width / BACK_RESIZE_FACTOR,
            back.getHeight() * width / BACK_RESIZE_FACTOR);
        back.setPosition(width / BACK_HORIZ_FACTOR, height / BACK_VERT_FACTOR);
        elements.add(back);

        Image display = new Image(atlas.createSprite("Display", 1));
        display.setSize(display.getWidth() * width / DISPLAY_RESIZE_FACTOR,
            display.getHeight() * width / DISPLAY_RESIZE_FACTOR);
        display.setPosition(width / DISPLAY_HORIZ_FACTOR,
            height / DISPLAY_VERT_FACTOR);
        elements.add(display);

        Image buttons = new Image(atlas.createSprite("Buttons"));
        buttons.setSize(buttons.getWidth() * width / BUTTONS_RESIZE_FACTOR,
            buttons.getHeight() * width / BUTTONS_RESIZE_FACTOR);
        buttons.setPosition(width / BUTTONS_HORIZ_FACTOR, height / BUTTONS_VERT_FACTOR);
        buttons.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hide();
                Buttons.render();
            }
        });
        elements.add(buttons);
    }

    public static void render() {
        for (Image element : elements) {
            elevatorGroup.addActor(element);
        }
        App.getStage().addActor(elevatorGroup);
    }

    public static void dispose() {
        for (Image element : elements) {
            elevatorGroup.removeActor(element);
        }
    }

    public static void show() {
        for (Image element : elements) {
            elevatorGroup.setVisible(true);
        }
    }

    public static void hide() {
        for (Image element : elements) {
            elevatorGroup.setVisible(false);
        }
    }

    public static int getFloorIndex () {
        return floorIndex;
    }
}
