package com.elevator_project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.awt.*;

public class Elevator {

    private static TextureAtlas atlas;

    private static Sprite elevator;
    private static final float ELEVATOR_RESIZE_FACTOR = 158f;
    private static Image elevatorImage;

    private static Sprite door;
    private static final float DOOR_RESIZE_FACTOR = 220f;
    private static final float DOOR_VERT_FACTOR = 4.7f;
    private static final float DOOR_HORIZ_FACTOR = 2.7f;
    private static Image doorImage;

    private static Sprite display;
    private static final float DISPLAY_RESIZE_FACTOR = 700f;
    private static final float DISPLAY_VERT_FACTOR = 1.8f;
    private static final float DISPLAY_HORIZ_FACTOR = 1.5f;
    private static Image displayImage;

    private static Sprite buttons;
    private static final float BUTTONS_RESIZE_FACTOR = 900f;
    private static final float BUTTONS_VERT_FACTOR = 2.35f;
    private static final float BUTTONS_HORIZ_FACTOR = 1.53f;
    private static Image buttonsImage;

    private static boolean viewButtons;

    private static Stage stage;

    public static Sprite getDoor() {
        return door;
    }

    public static boolean getViewButtons() {
        return viewButtons;
    }

    public static void changeViewButtons() {
        viewButtons = !viewButtons;
    }

    public static void initialize(float width, float height) {
        stage = App.getStage();

        atlas = new TextureAtlas(Gdx.files.internal("Elevator.atlas"));

        elevator = atlas.createSprite("Elevator");
        elevatorImage = new Image(elevator);
        elevatorImage.setSize(elevator.getWidth()*width/ELEVATOR_RESIZE_FACTOR,
            elevator.getHeight()*width/ELEVATOR_RESIZE_FACTOR);
        stage.addActor(elevatorImage);

        door = atlas.createSprite("Door");
        doorImage = new Image(door);
        doorImage.setSize(door.getWidth()*width/DOOR_RESIZE_FACTOR,
            door.getHeight()*width/DOOR_RESIZE_FACTOR);
        doorImage.setPosition(width / DOOR_HORIZ_FACTOR, height / DOOR_VERT_FACTOR);
        stage.addActor(doorImage);

        display = atlas.createSprite("Display", 1);
        displayImage = new Image(display);
        displayImage.setSize(display.getWidth()*width/DISPLAY_RESIZE_FACTOR,
            display.getHeight()*width/DISPLAY_RESIZE_FACTOR);
        displayImage.setPosition(width / DISPLAY_HORIZ_FACTOR,
            height / DISPLAY_VERT_FACTOR);
        stage.addActor(displayImage);

        buttons = atlas.createSprite("Buttons");
        buttonsImage = new Image(buttons);
        buttonsImage.setSize(buttons.getWidth() * width / BUTTONS_RESIZE_FACTOR,
            buttons.getHeight() * width / BUTTONS_RESIZE_FACTOR);
        buttonsImage.setPosition(width / BUTTONS_HORIZ_FACTOR, height / BUTTONS_VERT_FACTOR);

        buttonsImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println(1);
            }
        });
        stage.addActor(buttonsImage);

        viewButtons = false;
    }
}
