package com.elevator_project.elevator;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.elevator_project.game.App;
import com.elevator_project.game.GameManager;
import com.elevator_project.game.GroupElements;
import com.elevator_project.game.ImageProcessing;

import java.util.ArrayList;
import java.util.List;

public class Buttons implements GroupElements {
    private final float h;
    private final float w;
    private final TextureAtlas atlas;
    private final Group mainGroup;
    private final List<Image> elements;

    public Buttons () {
        this.w = App.getDimensions()[0];
        this.h = App.getDimensions()[1];
        atlas = GameManager.getAtlasses().getButtonsAtlas();
        mainGroup = new Group();
        elements = new ArrayList<>();
        initElements();
    }

    private void initElements () {
        elements.add(initBack());
        elements.addAll(initButtons());
    }

    private Image initBack () {
        final float BACK_RESIZE_FACTOR = 110f;
        Image back = new Image(atlas.createSprite("Back"));
        ImageProcessing.process(back, BACK_RESIZE_FACTOR, w, h);
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hide();
                GameManager.getElevatorManager().getElevator().show();
                GameManager.getDoor().render();
            }
        });
        return back;
    }

    private List<Image> initButtons () {
        final float BUTTON_RESIZE_FACTOR = 200f;
        final float FIRST_HORIZ_ROW_FACTOR = 2.9f;
        final float SECOND_HORIZ_ROW_FACTOR = 2.2f;
        final float THIRD_HORIZ_ROW_FACTOR = 1.8f;
        final float FIRST_VERT_ROW_FACTOR = 1.5f;
        final float SECOND_VERT_ROW_FACTOR = 1.8f;
        final float THIRD_VERT_ROW_FACTOR = 2.7f;
        final float FOURTH_VERT_ROW_FACTOR = 3.9f;
        List<Image> buttons = new ArrayList<>();

        Image buttonOne = new Image(atlas.createSprite("Button", 1));
        ImageProcessing.process(buttonOne, BUTTON_RESIZE_FACTOR, SECOND_HORIZ_ROW_FACTOR, FIRST_VERT_ROW_FACTOR);
        buttons.add(buttonOne);

        Image buttonTwo = new Image(atlas.createSprite("Button", 2));
        ImageProcessing.process(buttonTwo, BUTTON_RESIZE_FACTOR, THIRD_HORIZ_ROW_FACTOR, SECOND_VERT_ROW_FACTOR);
        buttons.add(buttonTwo);

        Image buttonThree = new Image(atlas.createSprite("Button", 3));
        ImageProcessing.process(buttonThree, BUTTON_RESIZE_FACTOR, THIRD_HORIZ_ROW_FACTOR, THIRD_VERT_ROW_FACTOR);
        buttons.add(buttonThree);

        Image buttonFour = new Image(atlas.createSprite("Button", 4));
        ImageProcessing.process(buttonFour, BUTTON_RESIZE_FACTOR, SECOND_HORIZ_ROW_FACTOR, FOURTH_VERT_ROW_FACTOR);
        buttons.add(buttonFour);

        Image buttonFive = new Image(atlas.createSprite("Button", 5));
        ImageProcessing.process(buttonFive, BUTTON_RESIZE_FACTOR, FIRST_HORIZ_ROW_FACTOR, THIRD_VERT_ROW_FACTOR);
        buttons.add(buttonFive);

        Image buttonSix = new Image(atlas.createSprite("Button", 6));
        ImageProcessing.process(buttonSix, BUTTON_RESIZE_FACTOR, FIRST_HORIZ_ROW_FACTOR, SECOND_VERT_ROW_FACTOR);
        buttons.add(buttonSix);

        return buttons;
    }

    public Group initGroup () {
        for (Image element : elements) {
            mainGroup.addActor(element);
        }
        return mainGroup;
    }

    public void hide () {
        mainGroup.setVisible(false);
    }

    public void show () {
        mainGroup.setVisible(true);
    }
}
