package com.elevator_project.extra_elements;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.elevator_project.game.App;
import com.elevator_project.game.GameManager;
import com.elevator_project.game.ImageProcessing;


public class DownArrow {

    private Image downArrow;
    private final TextureAtlas atlas;

    public DownArrow () {
        atlas = GameManager.getAtlasses().getExtraElementsAtlas();
        initArrow();
    }

    private void initArrow () {
        float ARROW_RESIZE_FACTOR = 400f;
        float ARROW_HORIZ_FACTOR = 2f;
        float ARROW_VERT_FACTOR = 15f;


        downArrow = new Image(atlas.createSprite("DownArrow"));
        ImageProcessing.process(downArrow, ARROW_RESIZE_FACTOR, ARROW_HORIZ_FACTOR, ARROW_VERT_FACTOR);
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
        App.getStage().addActor(downArrow);
    }

    public void dispose() {
        downArrow.remove();
    }

    public void hide () {
        downArrow.setVisible(false);
    }

    public void show () {
        downArrow.setVisible(true);
    }
}
