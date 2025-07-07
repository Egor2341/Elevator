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
    private static final float DOOR_VERT_FACTOR_IN_ELEVATOR = 4.65f;
    private static final float DOOR_VERT_FACTOR_IN_ROOM = 3.15f;
    private static final float DOOR_HORIZ_FACTOR = 2.6f;

    private static float h;

    private static boolean animation;
    private static boolean available;

    private static boolean elevator;

    public static void initialize(float w, float height) {
        TextureAtlas atlas = new TextureAtlas("Door.atlas");
        h = height;
        door = new Image();
        doorAnimation = new Animation<>(0.15f, atlas.findRegions("Door"));
        door = new Image(new TextureRegionDrawable(doorAnimation.getKeyFrame(0)));
        door.setSize(door.getWidth() * w / DOOR_RESIZE_FACTOR, door.getHeight() * w / DOOR_RESIZE_FACTOR);
        door.setPosition(w / DOOR_HORIZ_FACTOR, h / DOOR_VERT_FACTOR_IN_ELEVATOR);
        door.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (available){
                    if (!animation) {
                        if (doorAnimation.isAnimationFinished(stateTime)) {
                            if (elevator) {
                                dispose();
                                Elevator.dispose();
                                App.getFloor().render();
                                Arrows.render();
                                available = false;
                                TextureRegion frame = doorAnimation.getKeyFrame(0, false);
                                door.setDrawable(new TextureRegionDrawable(frame));
                                door.setY(h / DOOR_VERT_FACTOR_IN_ROOM);
                                elevator = false;
                            }
                        } else {
                            animation = true;
                            stateTime = 0;
                        }
                    }
                }
            }
        });
        available = true;
        animation = false;
        elevator = true;
    }

    public static void update(float delta) {
        if (animation) {
            stateTime += delta;
            TextureRegion frame = doorAnimation.getKeyFrame(stateTime, false);
            door.setDrawable(new TextureRegionDrawable(frame));
            if (doorAnimation.isAnimationFinished(stateTime)) {
                animation = false;
            }
        }
    }

    public static void dispose() {
        door.remove();
    }

    public static void render() {
        App.getStage().addActor(door);
    }

    public static boolean isAnimation() {
        return animation;
    }

    public static void setAvailable(boolean value) {
        available = value;
    }
}
