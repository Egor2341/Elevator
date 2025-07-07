package com.elevator_project;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Arrows {

    private static Image rightArrow;
    private static Image leftArrow;

    public static void initialize(float w, float h) {

        TextureAtlas atlas = new TextureAtlas("ExtraElements.atlas");

        float ARROW_RESIZE_FACTOR = 400f;
        float RIGHT_ARROW_HORIZ_FACTOR = 1.08f;
        float LEFT_ARROW_HORIZ_FACTOR = 15f;
        float ARROW_VERT_FACTOR = 2f;

        rightArrow = new Image(atlas.createSprite("RightArrow"));
        rightArrow.setSize(rightArrow.getWidth() * w / ARROW_RESIZE_FACTOR,
            rightArrow.getHeight() * w / ARROW_RESIZE_FACTOR);
        rightArrow.setPosition(w / RIGHT_ARROW_HORIZ_FACTOR, h / ARROW_VERT_FACTOR);
        rightArrow.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                App.getFloor().right();
            }
        });


        leftArrow = new Image(atlas.createSprite("LeftArrow"));
        leftArrow.setSize(leftArrow.getWidth() * w / ARROW_RESIZE_FACTOR,
            leftArrow.getHeight() * w / ARROW_RESIZE_FACTOR);
        leftArrow.setPosition(w / LEFT_ARROW_HORIZ_FACTOR, h / ARROW_VERT_FACTOR);
        leftArrow.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                App.getFloor().left();
            }
        });
    }

    public static void render() {
        App.getStage().addActor(leftArrow);
        App.getStage().addActor(rightArrow);
    }

    public static void dispose() {
        rightArrow.remove();
        leftArrow.remove();
    }
}
