package com.elevator_project;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Door {

    private static Animation<TextureRegion> doorAnimation;
    private static Image door;
    private static float stateTime;

    private static final float DOOR_RESIZE_FACTOR = 240f;
    private static final float DOOR_VERT_FACTOR = 4.65f;
    private static final float DOOR_HORIZ_FACTOR = 2.6f;

    private static boolean openDoor;

    public static void initialize(float w, float h) {
        TextureAtlas atlas = new TextureAtlas("Door.atlas");

        door = new Image();
        doorAnimation = new Animation<>(0.15f, atlas.findRegions("Door"));
        door = new Image(new TextureRegionDrawable(doorAnimation.getKeyFrame(0)));
        door.setSize(door.getWidth() * w / DOOR_RESIZE_FACTOR, door.getHeight() * w / DOOR_RESIZE_FACTOR);
        door.setPosition(w / DOOR_HORIZ_FACTOR, h / DOOR_VERT_FACTOR);
        door.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!openDoor) {
                    stateTime = 0;
                    openDoor = true;
                } else if (doorAnimation.isAnimationFinished(stateTime)){
                    dispose();
                    Elevator.dispose();
                    App.getFloor(Elevator.getFloorIndex()).render();
                }
            }
        });
        App.getStage().addActor(door);
        openDoor = false;
    }

    public static void update(float delta) {
        if (openDoor) {
            stateTime += delta;
            TextureRegion frame = doorAnimation.getKeyFrame(stateTime, false);
            door.setDrawable(new TextureRegionDrawable(frame));
        }
    }

    private static void dispose() {
        door.remove();
    }
}
