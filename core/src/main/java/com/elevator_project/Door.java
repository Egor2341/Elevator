package com.elevator_project;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Door {
    private final float h;
    private final TextureAtlas atlas;

    Image door;
    private static Animation<TextureRegion> doorAnimation;
    private static float stateTime;

    private static boolean animation;
    private static boolean available;
    private static boolean elevator;

    public Door () {
        this.h = App.getDimensions()[1];
        atlas = GameManager.getAtlasses().getDoorAtlas();
        initDoor();
        elevator = true;
        available = true;
        animation = false;
    }

    private void initDoor () {
        final float DOOR_RESIZE_FACTOR = 240f;
        final float DOOR_VERT_FACTOR_IN_ELEVATOR = 4.65f;
        final float DOOR_VERT_FACTOR_IN_ROOM = 3.15f;
        final float DOOR_HORIZ_FACTOR = 2.6f;

        door = new Image();
        doorAnimation = new Animation<>(0.15f, atlas.findRegions("Door"));
        door = new Image(new TextureRegionDrawable(doorAnimation.getKeyFrame(0)));
        ImageProcessing.process(door, DOOR_RESIZE_FACTOR, DOOR_HORIZ_FACTOR, DOOR_VERT_FACTOR_IN_ELEVATOR);
        door.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (available){
                    if (!animation) {
                        if (doorAnimation.isAnimationFinished(stateTime)) {
                            if (elevator) {
                                dispose();
                                GameManager.getElevatorManager().dispose();
                                GameManager.getFloor().render();
                                GameManager.getArrows().render();
                                available = false;
                                elevator = false;
                            } else {
                                GameManager.getFloor().dispose();
                                GameManager.getArrows().dispose();
                                elevator = true;
                            }
                            TextureRegion frame = doorAnimation.getKeyFrame(0, false);
                            door.setDrawable(new TextureRegionDrawable(frame));
                            door.setY(h / DOOR_VERT_FACTOR_IN_ROOM);
                        } else {
                            animation = true;
                            stateTime = 0;
                        }
                    }
                }
            }
        });
    }

    public void update(float delta) {
        if (animation) {
            stateTime += delta;
            TextureRegion frame = doorAnimation.getKeyFrame(stateTime, false);
            door.setDrawable(new TextureRegionDrawable(frame));
            if (doorAnimation.isAnimationFinished(stateTime)) {
                animation = false;
            }
        }
    }

    public void dispose() {
        door.remove();
    }

    public void render() {
        App.getStage().addActor(door);
    }

    public void setAvailable(boolean value) {
        available = value;
    }

    public boolean isAnimation () {
        return animation;
    }
}
