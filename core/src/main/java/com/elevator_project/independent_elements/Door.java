package com.elevator_project.independent_elements;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.elevator_project.game.App;
import com.elevator_project.game.GameManager;
import com.elevator_project.game.ImageProcessing;

public class Door {
    private final float h;
    private final TextureAtlas atlas;

    Image door;
    private static Animation<TextureRegion> doorAnimation;
    private static float stateTime;

    private static boolean animation;
    private static boolean available;
    private static boolean open;
    private static boolean closingDoors;

    public Door () {
        this.h = App.getDimensions()[1];
        atlas = GameManager.getAtlasses().getDoorAtlas();
        initDoor();
        available = true;
        open = false;
        closingDoors = true;
    }

    private void initDoor () {
        final float DOOR_RESIZE_FACTOR = 240f;
        final float DOOR_VERT_FACTOR_IN_ELEVATOR = 4.65f;
        final float DOOR_VERT_FACTOR_IN_ROOM = 3.15f;
        final float DOOR_HORIZ_FACTOR = 2.6f;

        doorAnimation = new Animation<>(0.3f, atlas.findRegions("Door"));
        door = new Image(new TextureRegionDrawable(doorAnimation.getKeyFrame(0)));
        ImageProcessing.process(door, DOOR_RESIZE_FACTOR, DOOR_HORIZ_FACTOR, DOOR_VERT_FACTOR_IN_ELEVATOR);
        door.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (available){
                        if (open) {
                            App.getSoundManager().playSteps();
                            if (GameManager.isElevator()) {
                                dispose();
                                GameManager.getElevatorManager().dispose();
                                GameManager.getInventory().dispose();
                                GameManager.getFloor().render();
                                available = false;
                                GameManager.setElevator(false);
                                App.getSoundManager().playElevatorDoors();
                            } else {
                                dispose();
                                GameManager.getFloor().dispose();
                                GameManager.getElevatorManager().render();
                                GameManager.setElevator(true);
                                close();
                            }
                            TextureRegion frame = doorAnimation.getKeyFrame(0, false);
                            door.setDrawable(new TextureRegionDrawable(frame));
                            if (GameManager.isElevator()) {
                                door.setY(h / DOOR_VERT_FACTOR_IN_ELEVATOR);
                            } else {
                                door.setY(h / DOOR_VERT_FACTOR_IN_ROOM);
                            }
                            open = false;

                        } else {
                            App.getSoundManager().playElevatorDoors();
                            stateTime = 0;
                            animation = true;
                            available = false;
                            closingDoors = false;
                            doorAnimation.setPlayMode(Animation.PlayMode.NORMAL);
                        }
                }
            }
        });
    }

    public void close () {
        if (open) {
            App.getSoundManager().playElevatorDoors();
            doorAnimation.setPlayMode(Animation.PlayMode.REVERSED);
            stateTime = 0;
            available = false;
            animation = true;
            closingDoors = true;
        } else {
            GameManager.getElevatorManager().setMoving(true);
            App.getSoundManager().playElevatorMotor();
        }
    }

    public void update(float delta) {
        if (animation) {
            stateTime += delta;
            TextureRegion frame = doorAnimation.getKeyFrame(stateTime, false);
            door.setDrawable(new TextureRegionDrawable(frame));
            if (doorAnimation.isAnimationFinished(stateTime)) {
                animation = false;
                if (closingDoors) {
                    GameManager.getElevatorManager().setMoving(true);
                    App.getSoundManager().playElevatorMotor();
                    open = false;
                } else {
                    open = true;
                    available = true;
                }
            }
        }
    }

    public void setAvailable(boolean value) {
        available = value;
    }

    public boolean isAnimation () {
        return animation;
    }

    public void dispose() {
        door.remove();
    }

    public void render() {
        App.getStage().addActor(door);
    }

    public void hide() {
        door.setVisible(false);
    }

    public void show() {
        door.setVisible(true);
    }
}
