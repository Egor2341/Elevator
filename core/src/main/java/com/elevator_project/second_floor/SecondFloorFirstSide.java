package com.elevator_project.second_floor;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.elevator_project.game.GameManager;
import com.elevator_project.game.ImageProcessing;
import com.elevator_project.game.RoomPart;

import java.util.*;

public class SecondFloorFirstSide extends RoomPart {

    private final TextureAtlas atlas;
    private final List<Image> elements;

    private Sprite[] tvSprites;
    private Image tv;

    public SecondFloorFirstSide () {
        atlas = GameManager.getAtlasses().getSecondFloorAtlas();
        elements = new ArrayList<>();
        initElements();
    }

    private void initElements() {
        elements.add(initWall());
        elements.add(initLocker());
        elements.add(initTV());
    }

    private Image initWall () {
        float WALL_RESIZE_FACTOR = 158f;

        Image wall = new Image(atlas.createSprite("Wall", 1));
        ImageProcessing.process(wall, WALL_RESIZE_FACTOR, w, h);

        return wall;
    }

    private Image initLocker () {
        final float LOCKER_RESIZE_FACTOR = 350f;
        final float LOCKER_HORIZ_FACTOR = 2.55f;
        final float LOCKER_VERT_FACTOR = 3.19f;

        Image locker = new Image(atlas.createSprite("Locker", 1));
        ImageProcessing.process(locker, LOCKER_RESIZE_FACTOR, LOCKER_HORIZ_FACTOR, LOCKER_VERT_FACTOR);

        return locker;
    }

    private Image initTV () {
        final float TV_RESIZE = 400f;
        final float TV_HORIZ = 2.35f;
        final float TV_VERT = 1.78f;

        tvSprites = new Sprite[5];
        for (int i = 1; i < 6; i++) {
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

        return tv;
    }

    public void updateTvImage() {
        System.out.println(GameManager.getGameState().isTvOn());
        System.out.println(GameManager.getGameState().getChannelIndex());
        tv.setDrawable(new SpriteDrawable(
            GameManager.getGameState().isTvOn() ?
                tvSprites[GameManager.getGameState().getChannelIndex()] :
                atlas.createSprite("TV", 0)
        ));
    }

    public Group initGroup() {
        updateTvImage();
        for (Image element : elements) {
            mainGroup.addActor(element);
        }
        return mainGroup;
    }
}
