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
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

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

    private static Group elevatorGroup;

    private static List<Image> elements = new ArrayList<>();


    public static void initialize(float width, float height, Stage stage) {
        elevatorGroup = new Group();

        atlas = new TextureAtlas(Gdx.files.internal("Elevator.atlas"));

        elevator = atlas.createSprite("Elevator");
        elevatorImage = new Image(elevator);
        elevatorImage.setSize(elevator.getWidth()*width/ELEVATOR_RESIZE_FACTOR,
            elevator.getHeight()*width/ELEVATOR_RESIZE_FACTOR);
        elements.add(elevatorImage);

        door = atlas.createSprite("Door");
        doorImage = new Image(door);
        doorImage.setSize(door.getWidth()*width/DOOR_RESIZE_FACTOR,
            door.getHeight()*width/DOOR_RESIZE_FACTOR);
        doorImage.setPosition(width / DOOR_HORIZ_FACTOR, height / DOOR_VERT_FACTOR);
        elements.add(doorImage);

        display = atlas.createSprite("Display", 1);
        displayImage = new Image(display);
        displayImage.setSize(display.getWidth()*width/DISPLAY_RESIZE_FACTOR,
            display.getHeight()*width/DISPLAY_RESIZE_FACTOR);
        displayImage.setPosition(width / DISPLAY_HORIZ_FACTOR,
            height / DISPLAY_VERT_FACTOR);
        elements.add(displayImage);

        buttons = atlas.createSprite("Buttons");
        buttonsImage = new Image(buttons);
        buttonsImage.setSize(buttons.getWidth() * width / BUTTONS_RESIZE_FACTOR,
            buttons.getHeight() * width / BUTTONS_RESIZE_FACTOR);
        buttonsImage.setPosition(width / BUTTONS_HORIZ_FACTOR, height / BUTTONS_VERT_FACTOR);
        buttonsImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                Buttons.render();
            }
        });
        elements.add(buttonsImage);


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
}
