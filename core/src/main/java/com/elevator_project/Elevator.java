package com.elevator_project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Elevator {

    private static TextureAtlas atlas;

    private static Sprite elevator;
    private static final float ELEVATOR_RESIZE_FACTOR = 158f;

    private static Sprite door;
    private static final float DOOR_RESIZE_FACTOR = 220f;
    private static final float DOOR_VERT_FACTOR = 1.9f;

    private static Sprite display;
    private static final float DISPLAY_RESIZE_FACTOR = 700f;
    private static final float DISPLAY_VERT_FACTOR = 1.8f;
    private static final float DISPLAY_HORIZ_FACTOR = 1.5f;

    private static Sprite buttons;
    private static final float BUTTONS_RESIZE_FACTOR = 900f;
    private static final float BUTTONS_VERT_FACTOR = 2.35f;
    private static final float BUTTONS_HORIZ_FACTOR = 1.53f;


    public static void initialize(float width, float height) {
        atlas = new TextureAtlas(Gdx.files.internal("Elevator.atlas"));

        elevator = atlas.createSprite("Elevator");
        elevator.setSize(elevator.getWidth()*width/ELEVATOR_RESIZE_FACTOR,
            elevator.getHeight()*width/ELEVATOR_RESIZE_FACTOR);
        elevator.setPosition(width / 2 - elevator.getWidth() / 2, height / 2 - elevator.getHeight() /  2);

        door = atlas.createSprite("Door");
        door.setSize(door.getWidth()*width/DOOR_RESIZE_FACTOR,
            door.getHeight()*width/DOOR_RESIZE_FACTOR);
        door.setPosition(width / 2 - door.getWidth() / 2, height / DOOR_VERT_FACTOR - door.getHeight() / 2);

        display = atlas.createSprite("Display", 1);
        display.setSize(display.getWidth()*width/DISPLAY_RESIZE_FACTOR,
            display.getHeight()*width/DISPLAY_RESIZE_FACTOR);
        display.setPosition(width / DISPLAY_HORIZ_FACTOR,
            height / DISPLAY_VERT_FACTOR);

        buttons = atlas.createSprite("Buttons");
        buttons.setSize(buttons.getWidth() * width / BUTTONS_RESIZE_FACTOR,
            buttons.getHeight() * width / BUTTONS_RESIZE_FACTOR);
        buttons.setPosition(width / BUTTONS_HORIZ_FACTOR, height / BUTTONS_VERT_FACTOR);

    }

    public static void render(SpriteBatch batch) {
        elevator.draw(batch);
        door.draw(batch);
        display.draw(batch);
        buttons.draw(batch);
    }
}
