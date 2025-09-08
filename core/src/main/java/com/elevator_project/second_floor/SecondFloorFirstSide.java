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

import java.util.*;

public class SecondFloorFirstSide extends RoomPart {

    private final TextureAtlas atlas;
    private final List<Image> elements;

    private Sprite[] tvSprites;
    private Image tv;
    private Image switchButton;
    private Image powerButton;

    private Sprite[] runesSprites;
    private Image[] runes;

    private Image closeLocker;
    private Image openLocker;

    public SecondFloorFirstSide () {
        atlas = GameManager.getAtlasses().getSecondFloorAtlas();
        elements = new ArrayList<>();
        initElements();
    }

    private void initElements() {
        elements.add(initWall());
        elements.add(initLocker());
        elements.add(initTV());
        elements.add(switchButton);
        elements.add(powerButton);
        elements.add(openLocker);
    }

    private Image initWall () {
        float WALL_RESIZE_FACTOR = 158f;

        Image wall = new Image(atlas.createSprite("Wall", 1));
        ImageProcessing.process(wall, WALL_RESIZE_FACTOR, w, h);

        return wall;
    }

    private Image initLocker () {
        final float CLOSE_LOCKER_RESIZE = 350f;
        final float CLOSE_LOCKER_HORIZ  = 2.55f;
        final float CLOSE_LOCKER_VERT = 3.19f;

        final float OPEN_LOCKER_RESIZE = 360f;
        final float OPEN_LOCKER_HORIZ = 3.2f;
        final float OPEN_LOCKER_VERT = 4.7f;

        final float RUNE_RESIZE = 800f;
        final float RUNE_HORIZ = 1.68f;
        final float[] RUNE_VERT = new float[] {1.945f, 2.1f, 2.29f, 2.52f};

        runesSprites = new Sprite[6];
        for (int i = 0; i < 6; i++) {
            runesSprites[i] = atlas.createSprite("Rune", i);
        }
        runes = new Image[4];

        for (int i = 0; i < 4; i++){
            Image rune = new Image(runesSprites[0]);
            ImageProcessing.process(rune, RUNE_RESIZE, RUNE_HORIZ, RUNE_VERT[i]);
            runes[i] = rune;
        }

        closeLocker = new Image(atlas.createSprite("Locker", 1));
        ImageProcessing.process(closeLocker, CLOSE_LOCKER_RESIZE, CLOSE_LOCKER_HORIZ, CLOSE_LOCKER_VERT);
        closeLocker.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                GameManager.getSecondFloor().moveToLocker();
            }
        });

        openLocker = new Image(atlas.createSprite("Locker", 2));
        ImageProcessing.process(openLocker, OPEN_LOCKER_RESIZE, OPEN_LOCKER_HORIZ, OPEN_LOCKER_VERT);

        return closeLocker;
    }

    private Image initTV () {
        final float TV_RESIZE = 400f;
        final float TV_HORIZ = 2.35f;
        final float TV_VERT = 1.78f;

        final float SWITCH_BUTTON_RESIZE = 1300f;
        final float SWITCH_BUTTON_HORIZ = 1.72f;
        final float SWITCH_BUTTON_VERT = 1.4f;

        final float POWER_BUTTON_RESIZE = 1300f;
        final float POWER_BUTTON_HORIZ = 1.72f;
        final float POWER_BUTTON_VERT = 1.65f;

        tvSprites = new Sprite[6];
        for (int i = 1; i < 7; i++) {
            tvSprites[i-1] = atlas.createSprite("TV", i);
        }
        tv = new Image(tvSprites[1]);
        ImageProcessing.process(tv, TV_RESIZE, TV_HORIZ, TV_VERT);

        tv.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                GameManager.getSecondFloor().moveToTv();
            }
        });

        switchButton = new Image(atlas.createSprite("TV_Button"));
        ImageProcessing.process(switchButton, SWITCH_BUTTON_RESIZE, SWITCH_BUTTON_HORIZ, SWITCH_BUTTON_VERT);
        switchButton.setOrigin(Align.center);

        powerButton = new Image(atlas.createSprite("TV_PowerButton"));
        ImageProcessing.process(powerButton, POWER_BUTTON_RESIZE, POWER_BUTTON_HORIZ, POWER_BUTTON_VERT);

        return tv;
    }

    public void updateTvImage() {
        if (GameManager.getGameState().isLockerOnSecondFloorQuestSolved()) {
            tv.setDrawable(new SpriteDrawable(tvSprites[5]));
        } else {
            tv.setDrawable(new SpriteDrawable(
                GameManager.getGameState().isTvOn() ?
                    tvSprites[GameManager.getGameState().getChannelIndex()] :
                    atlas.createSprite("TV", 0)
            ));
        }
        switchButton.setRotation(0);
        switchButton.rotateBy((GameManager.getGameState().getChannelIndex()) * 72);
    }

    public void updateRunesImage() {
        List<Integer> runesIndexes = GameManager.getGameState().getRunesOnSecondFloorLocker();
        for (int i = 0; i < 4; i++) {
            runes[i].setDrawable(new SpriteDrawable(runesSprites[runesIndexes.get(i)]));
        }
    }

    public void disposeRunesImage(){
        for (Image rune : runes) {
            rune.remove();
        }
        closeLocker.setVisible(false);
        openLocker.setVisible(true);
    }

    public Group initGroup() {
        openLocker.setVisible(false);
        closeLocker.setVisible(true);
        updateTvImage();
        for (Image element : elements) {
            mainGroup.addActor(element);
        }
        if (!GameManager.getGameState().isLockerOnSecondFloorQuestSolved()) {
            updateRunesImage();
            for (Image rune : runes) {
                mainGroup.addActor(rune);
            }
        }
        return mainGroup;
    }
}
