package com.elevator_project.second_floor;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.elevator_project.game.GameManager;
import com.elevator_project.game.ImageProcessing;
import com.elevator_project.game.RoomPart;

import java.util.ArrayList;
import java.util.List;

public class Tv extends RoomPart {
    private final TextureAtlas atlas;
    private final List<Image> elements;

    private Image tv;
    private Image switchButton;

    private Sprite[] tvSprites;

    public Tv() {
        atlas = GameManager.getAtlasses().getSecondFloorAtlas();
        elements = new ArrayList<>();
        initElements();
    }

    private void initElements() {
        elements.add(initBack());
        elements.add(initTv());
        elements.add(initSwitchButton());
        elements.add(initPowerButton());
    }

    private Image initTv() {
        final float TV_RESIZE = 200f;
        final float TV_HORIZ = 3f;
        final float TV_VERT = 2.5f;
        tvSprites = new Sprite[5];
        for (int i = 1; i < 6; i++) {
            tvSprites[i-1] = atlas.createSprite("TV", i);
        }
        tv = new Image(tvSprites[1]);
        ImageProcessing.process(tv, TV_RESIZE, TV_HORIZ, TV_VERT);

        return tv;
    }

    private Image initBack() {
        final float BACK_RESIZE = 158f;

        Image back = new Image(atlas.createSprite("TV_Back"));
        ImageProcessing.process(back, BACK_RESIZE, w, h);

        return back;
    }

    private Image initSwitchButton() {
        final float SWITCH_BUTTON_RESIZE = 700f;
        final float SWITCH_BUTTON_HORIZ = 1.55f;
        final float SWITCH_BUTTON_VERT = 1.4f;

        switchButton = new Image(atlas.createSprite("TV_Button"));
        ImageProcessing.process(switchButton, SWITCH_BUTTON_RESIZE, SWITCH_BUTTON_HORIZ, SWITCH_BUTTON_VERT);
        switchButton.setOrigin(Align.center);

        switchButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                switchButton.rotateBy(72);
                GameManager.getGameState().setChannelIndex(
                    (GameManager.getGameState().getChannelIndex() + 1) % 5
                );
                if (GameManager.getGameState().isTvOn()) {
                    tv.setDrawable(new SpriteDrawable(tvSprites[GameManager.getGameState().getChannelIndex()]));
                }
            }
        });

        return switchButton;
    }

    private Image initPowerButton() {
        final float POWER_BUTTON_RESIZE = 700f;
        final float POWER_BUTTON_HORIZ = 1.55f;
        final float POWER_BUTTON_VERT = 2f;

        Image powerButton = new Image(atlas.createSprite("TV_PowerButton"));
        ImageProcessing.process(powerButton, POWER_BUTTON_RESIZE, POWER_BUTTON_HORIZ, POWER_BUTTON_VERT);

        powerButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                GameManager.getGameState().setTvOn(!GameManager.getGameState().isTvOn());
                tv.setDrawable(new SpriteDrawable(
                    GameManager.getGameState().isTvOn() ?
                        tvSprites[GameManager.getGameState().getChannelIndex()] :
                        atlas.createSprite("TV", 0)
                ));
            }
        });

        return powerButton;
    }

    @Override
    public Group initGroup() {
        switchButton.setRotation(0);
        switchButton.rotateBy((GameManager.getGameState().getChannelIndex()) * 72);
        tv.setDrawable(new SpriteDrawable(
            GameManager.getGameState().isTvOn() ?
                tvSprites[GameManager.getGameState().getChannelIndex()] :
                atlas.createSprite("TV", 0)
        ));
        for (Image element : elements) {
            mainGroup.addActor(element);
        }
        return mainGroup;
    }
}
