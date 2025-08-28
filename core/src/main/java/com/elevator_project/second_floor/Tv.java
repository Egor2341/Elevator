package com.elevator_project.second_floor;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
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
import java.util.Arrays;
import java.util.List;

public class Tv extends RoomPart {
    private final TextureAtlas atlas;
    private final List<Image> elements;

    private Image tv;
    private Image switchButton;
    private Image powerButton;

    private Sprite[] tvSprites;

    private Sprite[] runesSprites;
    private Image[] runes;

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
        initRunes();
    }

    private Image initTv() {
        final float TV_RESIZE = 160f;
        final float TV_HORIZ = 3.8f;
        final float TV_VERT = 5.5f;
        tvSprites = new Sprite[6];
        for (int i = 1; i < 7; i++) {
            tvSprites[i-1] = atlas.createSprite("TV", i);
        }
        tv = new Image(tvSprites[1]);
        ImageProcessing.process(tv, TV_RESIZE, TV_HORIZ, TV_VERT);

        return tv;
    }

    private Image initBack() {
        final float BACK_RESIZE = 800;

        Image back = new Image(atlas.createSprite("TV_Back"));
        ImageProcessing.process(back, BACK_RESIZE, w, h);

        return back;
    }

    private Image initSwitchButton() {
        final float SWITCH_BUTTON_RESIZE = 500f;
        final float SWITCH_BUTTON_HORIZ = 1.54f;
        final float SWITCH_BUTTON_VERT = 1.8f;

        switchButton = new Image(atlas.createSprite("TV_Button"));
        ImageProcessing.process(switchButton, SWITCH_BUTTON_RESIZE, SWITCH_BUTTON_HORIZ, SWITCH_BUTTON_VERT);
        switchButton.setOrigin(Align.center);

        switchButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if (GameManager.getGameState().isLockerOnSecondFloorQuestSolved()){
                    return;
                }
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
        final float POWER_BUTTON_RESIZE = 500f;
        final float POWER_BUTTON_HORIZ = 1.535f;
        final float POWER_BUTTON_VERT = 3.5f;

        powerButton = new Image(atlas.createSprite("TV_PowerButton"));
        ImageProcessing.process(powerButton, POWER_BUTTON_RESIZE, POWER_BUTTON_HORIZ, POWER_BUTTON_VERT);

        powerButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if (GameManager.getGameState().isLockerOnSecondFloorQuestSolved()){
                    return;
                }
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

    private void initRunes() {
        final float RUNE_RESIZE = 360f;
        final float RUNE_HORIZ = 1.45f;
        final float[] RUNE_VERT = new float[] {12f, h};

        runesSprites = new Sprite[6];
        for (int i = 0; i < 6; i++) {
            runesSprites[i] = atlas.createSprite("Rune", i);
        }
        runes = new Image[2];

        for (int i = 0; i < 2; i++){
            Image rune = new Image(runesSprites[0]);
            ImageProcessing.process(rune, RUNE_RESIZE, RUNE_HORIZ, RUNE_VERT[i]);
            runes[i] = rune;
        }
    }

    public void updateRunesImage() {
        List<Integer> runesIndexes = GameManager.getGameState().getRunesOnSecondFloorLocker();
        for (int i = 0; i < 2; i++) {
            runes[i].setDrawable(new SpriteDrawable(runesSprites[runesIndexes.get(i)]));
        }
    }

    public void disposeRunesImage(){
        Arrays.stream(runes).forEach(Actor::remove);
    }

    public void setScreamerImage(){
        tv.setDrawable(new SpriteDrawable(tvSprites[5]));
    }

    @Override
    public Group initGroup() {
        switchButton.setRotation(0);
        switchButton.rotateBy((GameManager.getGameState().getChannelIndex()) * 72);

        elements.forEach(mainGroup::addActor);
        if (!GameManager.getGameState().isLockerOnSecondFloorQuestSolved()) {
            tv.setDrawable(new SpriteDrawable(
                GameManager.getGameState().isTvOn() ?
                    tvSprites[GameManager.getGameState().getChannelIndex()] :
                    atlas.createSprite("TV", 0)
            ));
            updateRunesImage();
            Arrays.stream(runes).forEach(mainGroup::addActor);
        } else {
            setScreamerImage();
        }
        return mainGroup;
    }
}
