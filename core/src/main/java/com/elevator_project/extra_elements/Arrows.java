package com.elevator_project.extra_elements;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.elevator_project.game.App;
import com.elevator_project.game.GameManager;
import com.elevator_project.game.ImageProcessing;

public class Arrows {

    private Image rightArrow;
    private Image leftArrow;
    private Image downArrow;
    private final TextureAtlas atlas;
    private final Group mainGroup;

    public Arrows () {
        atlas = GameManager.getAtlasses().getExtraElementsAtlas();
        mainGroup = new Group();
        initArrows();
    }

    private void initArrows () {
        float ARROW_RESIZE_FACTOR = 400f;
        float RIGHT_ARROW_HORIZ_FACTOR = 1.13f;
        float LEFT_ARROW_HORIZ_FACTOR = 9.5f;
        float DOWN_ARROW_HORIZ_FACTOR = 2f;
        float DOWN_ARROW_VERT_FACTOR = 15f;
        float ARROW_VERT_FACTOR = 2f;

        rightArrow = new Image(atlas.createSprite("RightArrow"));
        ImageProcessing.process(rightArrow, ARROW_RESIZE_FACTOR, RIGHT_ARROW_HORIZ_FACTOR, ARROW_VERT_FACTOR);
        rightArrow.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                GameManager.getFloor().right();
            }
        });


        leftArrow = new Image(atlas.createSprite("LeftArrow"));
        ImageProcessing.process(leftArrow, ARROW_RESIZE_FACTOR, LEFT_ARROW_HORIZ_FACTOR, ARROW_VERT_FACTOR);
        leftArrow.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                GameManager.getFloor().left();
            }
        });

        downArrow = new Image(atlas.createSprite("DownArrow"));
        ImageProcessing.process(downArrow, ARROW_RESIZE_FACTOR, DOWN_ARROW_HORIZ_FACTOR, DOWN_ARROW_VERT_FACTOR);
        downArrow.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                if (GameManager.getGameState().isElevator()){
                    GameManager.getElevatorManager().back();
                } else {
                    GameManager.getFloor().back();
                }
            }
        });
    }

    public void render() {
        mainGroup.addActor(rightArrow);
        mainGroup.addActor(leftArrow);
        App.getStage().addActor(mainGroup);
    }

    public void dispose() {
        mainGroup.remove();
    }

    public void hide () {
        mainGroup.setVisible(false);
    }

    public void show () {
        mainGroup.setVisible(true);
    }
}
