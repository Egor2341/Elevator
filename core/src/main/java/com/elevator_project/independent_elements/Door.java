package com.elevator_project.independent_elements;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.elevator_project.game.*;
import lombok.Getter;

public class Door {
    private final float h;
    private final TextureAtlas atlas;

    Image door;
    private Animation<TextureRegion> doorAnimation;
    private float stateTime;

    @Getter
    private boolean animation;
    private boolean open;
    @Getter
    private boolean close;

    public Door () {
        this.h = App.getDimensions()[1];
        atlas = GameManager.getAtlasses().getDoorAtlas();
        initDoor();
        open = false;
        close = true;
    }

    private void initDoor () {
        final float DOOR_RESIZE_FACTOR = 240f;
        final float DOOR_VERT_FACTOR_IN_ELEVATOR = 4.65f;
        final float DOOR_VERT_FACTOR_IN_ROOM = 3.15f;
        final float DOOR_HORIZ_FACTOR = 2.6f;

        doorAnimation = new Animation<>(0.3f, atlas.findRegions("Door"));
        door = new Image(new TextureRegionDrawable(doorAnimation.getKeyFrame(0)));
        if (GameManager.getGameState().isElevator()){
            ImageProcessing.process(door, DOOR_RESIZE_FACTOR, DOOR_HORIZ_FACTOR, DOOR_VERT_FACTOR_IN_ELEVATOR);
        } else {
            ImageProcessing.process(door, DOOR_RESIZE_FACTOR, DOOR_HORIZ_FACTOR, DOOR_VERT_FACTOR_IN_ROOM);
        }
        door.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (GameManager.getGameState().isDoorAvailable()){
                        if (open) {
                            App.getSoundManager().playSteps();
                            if (GameManager.getGameState().isElevator()) {
                                dispose();
                                GameManager.getElevatorManager().dispose();
                                GameManager.getInventory().dispose();
                                GameManager.getFloor().render();

                                App.getSoundManager().playElevatorDoors();
                            } else {
                                dispose();
                                GameManager.getFloor().dispose();
                                GameManager.getElevatorManager().render();
                                close();
                            }

                        } else {
                            App.getSoundManager().playElevatorDoors();
                            stateTime = 0;
                            animation = true;
                            GameManager.getGameState().setDoorAvailable(false);
                            close = false;
                            doorAnimation.setPlayMode(Animation.PlayMode.NORMAL);
                        }
                }
            }
        });
    }

    public void close () {
            App.getSoundManager().playElevatorDoors();
            doorAnimation.setPlayMode(Animation.PlayMode.REVERSED);
            stateTime = 0;
            GameManager.getGameState().setDoorAvailable(false);
            animation = true;
            close = true;
    }

    public void update(float delta) {
        if (animation) {
            stateTime += delta;
            TextureRegion frame = doorAnimation.getKeyFrame(stateTime, false);
            door.setDrawable(new TextureRegionDrawable(frame));
            if (doorAnimation.isAnimationFinished(stateTime)) {
                animation = false;
                if (close) {
                    open = false;
                    if (GameManager.getElevatorManager().isWait()) {
                        GameManager.getElevatorManager().getElevator().initMoving();
                        GameManager.getElevatorManager().setWait(false);
                    } else {
                        GameManager.getGameState().setDoorAvailable(true);
                    }
                } else {
                    open = true;
                    GameManager.getGameState().setDoorAvailable(true);
                }
            }
        }
    }

    public void dispose() {
        door.remove();
    }

    public void render() {
        final float DOOR_VERT_FACTOR_IN_ELEVATOR = 4.65f;
        final float DOOR_VERT_FACTOR_IN_ROOM = 3.15f;
        doorAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        TextureRegion frame = doorAnimation.getKeyFrame(0, false);
        door.setDrawable(new TextureRegionDrawable(frame));

        if (GameManager.getGameState().isElevator()) {
            door.setY(h / DOOR_VERT_FACTOR_IN_ELEVATOR);
        } else {
            door.setY(h / DOOR_VERT_FACTOR_IN_ROOM);
        }
        open = false;
        App.getStage().addActor(door);
    }

    public void hide() {
        door.setVisible(false);
    }

    public void show() {
        door.setVisible(true);
    }
}
