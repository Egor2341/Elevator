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

        return back;
    }

    private List<Image> initButtons () {
        final float BUTTON_RESIZE = 200f;
        final float[] BUTTON_HORIZ = new float[] {2.9f, 2.2f, 1.8f};
        final float[] BUTTON_VERT = new float[] {1.5f, 1.8f, 2.7f, 3.9f};
        List<Image> buttons = new ArrayList<>();

        Image buttonOne = new Image(atlas.createSprite("Button", 1));
        ImageProcessing.process(buttonOne, BUTTON_RESIZE, BUTTON_HORIZ[1], BUTTON_VERT[0]);
        buttons.add(buttonOne);

        Image buttonTwo = new Image(atlas.createSprite("Button", 2));
        ImageProcessing.process(buttonTwo, BUTTON_RESIZE, BUTTON_HORIZ[2], BUTTON_VERT[1]);
        buttons.add(buttonTwo);

        Image buttonThree = new Image(atlas.createSprite("Button", 3));
        ImageProcessing.process(buttonThree, BUTTON_RESIZE, BUTTON_HORIZ[2], BUTTON_VERT[2]);
        buttons.add(buttonThree);

        Image buttonFour = new Image(atlas.createSprite("Button", 4));
        ImageProcessing.process(buttonFour, BUTTON_RESIZE, BUTTON_HORIZ[1], BUTTON_VERT[3]);
        buttons.add(buttonFour);

        Image buttonFive = new Image(atlas.createSprite("Button", 5));
        ImageProcessing.process(buttonFive, BUTTON_RESIZE, BUTTON_HORIZ[0], BUTTON_VERT[2]);
        buttons.add(buttonFive);

        Image buttonSix = new Image(atlas.createSprite("Button", 6));
        ImageProcessing.process(buttonSix, BUTTON_RESIZE, BUTTON_HORIZ[0], BUTTON_VERT[1]);
        buttons.add(buttonSix);

        for (Image button : buttons) {
            button.addListener(new ClickListener() {
                @Override
                public void clicked (InputEvent event, float x, float y) {
                    GameManager.getElevatorManager().getElevator().move(
                        GameManager.getGameState().getFloorIndex(),
                        buttons.indexOf(button) + 1);
                    GameManager.getGameState().setFloorIndex(buttons.indexOf(button) + 1);
                    GameManager.getElevatorManager().back();
                }
            });
        }
        return buttons;
    }

    public Group initGroup () {
        elements.forEach(mainGroup::addActor);
        return mainGroup;
    }

    public void hide () {
        mainGroup.setVisible(false);
    }

    public void show () {
        mainGroup.setVisible(true);
    }
}
