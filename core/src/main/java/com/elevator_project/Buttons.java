package com.elevator_project;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;
import java.util.List;

public class Buttons {
    private static final Group buttonsGroup = new Group();

    private static final float BUTTON_RESIZE_FACTOR = 200f;
    private static final float FIRST_HORIZ_ROW_FACTOR = 2.9f;
    private static final float SECOND_HORIZ_ROW_FACTOR = 2.2f;
    private static final float THIRD_HORIZ_ROW_FACTOR = 1.8f;
    private static final float FIRST_VERT_ROW_FACTOR = 1.5f;
    private static final float SECOND_VERT_ROW_FACTOR = 1.8f;
    private static final float THIRD_VERT_ROW_FACTOR = 2.7f;
    private static final float FOURTH_VERT_ROW_FACTOR = 3.9f;

    private static List<Image> elements = new ArrayList<>();

    private static final float BACK_RESIZE_FACTOR = 110f;

    public static void initialize(float w, float h) {
        TextureAtlas atlas = new TextureAtlas("Buttons.atlas");

        Image back = new Image(atlas.createSprite("Back"));
        back.setSize(back.getWidth() * w / BACK_RESIZE_FACTOR, back.getHeight() * w / BACK_RESIZE_FACTOR);
        back.setPosition(0, 0);
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                Elevator.show();
            }
        });
        elements.add(back);

        Image buttonOne = new Image(atlas.createSprite("Button", 1));
        buttonOne.setSize(buttonOne.getWidth() * w / BUTTON_RESIZE_FACTOR,
            buttonOne.getHeight() * w / BUTTON_RESIZE_FACTOR);
        buttonOne.setPosition(w / SECOND_HORIZ_ROW_FACTOR, h / FIRST_VERT_ROW_FACTOR);
        elements.add(buttonOne);

        Image buttonTwo = new Image(atlas.createSprite("Button", 2));
        buttonTwo.setSize(buttonTwo.getWidth() * w / BUTTON_RESIZE_FACTOR,
            buttonTwo.getHeight() * w / BUTTON_RESIZE_FACTOR);
        buttonTwo.setPosition(w / THIRD_HORIZ_ROW_FACTOR, h / SECOND_VERT_ROW_FACTOR);
        elements.add(buttonTwo);

        Image buttonThree = new Image(atlas.createSprite("Button", 3));
        buttonThree.setSize(buttonThree.getWidth() * w / BUTTON_RESIZE_FACTOR,
            buttonThree.getHeight() * w / BUTTON_RESIZE_FACTOR);
        buttonThree.setPosition(w / THIRD_HORIZ_ROW_FACTOR, h / THIRD_VERT_ROW_FACTOR);
        elements.add(buttonThree);

        Image buttonFour = new Image(atlas.createSprite("Button", 4));
        buttonFour.setSize(buttonFour.getWidth() * w / BUTTON_RESIZE_FACTOR,
            buttonFour.getHeight() * w / BUTTON_RESIZE_FACTOR);
        buttonFour.setPosition(w / SECOND_HORIZ_ROW_FACTOR, h / FOURTH_VERT_ROW_FACTOR);
        elements.add(buttonFour);

        Image buttonFive = new Image(atlas.createSprite("Button", 5));
        buttonFive.setSize(buttonFive.getWidth() * w / BUTTON_RESIZE_FACTOR,
            buttonFive.getHeight() * w / BUTTON_RESIZE_FACTOR);
        buttonFive.setPosition(w / FIRST_HORIZ_ROW_FACTOR, h / THIRD_VERT_ROW_FACTOR);
        elements.add(buttonFive);

        Image buttonSix = new Image(atlas.createSprite("Button", 6));
        buttonSix.setSize(buttonSix.getWidth() * w / BUTTON_RESIZE_FACTOR,
            buttonSix.getHeight() * w / BUTTON_RESIZE_FACTOR);
        buttonSix.setPosition(w / FIRST_HORIZ_ROW_FACTOR, h / SECOND_VERT_ROW_FACTOR);
        elements.add(buttonSix);
    }

    public static void render() {
        for (Image element : elements) {
            buttonsGroup.addActor(element);
        }
        App.getStage().addActor(buttonsGroup);
    }

    public static void dispose() {
        for (Image element : elements) {
            buttonsGroup.removeActor(element);
        }
    }
}
