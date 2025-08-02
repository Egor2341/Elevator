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
    private Image button;

    private Sprite[] tvSprites;

    public Tv() {
        atlas = GameManager.getAtlasses().getSecondFloorAtlas();
        elements = new ArrayList<>();
        initElements();
    }

    private void initElements() {
        elements.add(initBack());
        elements.add(initTv());
        elements.add(initButton());
    }

    private Image initTv() {
        final float TV_RESIZE = 200f;
        final float TV_HORIZ = 3f;
        final float TV_VERT = 2.5f;
        tvSprites = new Sprite[7];
        for (int i = 0; i < 7; i++) {
            tvSprites[i] = atlas.createSprite("TV", i);
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

    private Image initButton() {
        final float BUTTON_RESIZE = 700f;
        final float BUTTON_HORIZ = 1.55f;
        final float BUTTON_VERT = 1.4f;

        button = new Image(atlas.createSprite("TV_Button"));
        ImageProcessing.process(button, BUTTON_RESIZE, BUTTON_HORIZ, BUTTON_VERT);
        button.setOrigin(Align.center);

        button.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                button.rotateBy(72);
                GameManager.getGameState().setChannelIndex(
                    (GameManager.getGameState().getChannelIndex() + 1) % 5
                );
                tv.setDrawable(new SpriteDrawable(tvSprites[GameManager.getGameState().getChannelIndex()]));
            }
        });

        return button;
    }

    @Override
    public Group initGroup() {
        button.rotateBy((GameManager.getGameState().getChannelIndex() - 1)*72);
        tv.setDrawable(new SpriteDrawable(tvSprites[GameManager.getGameState().getChannelIndex()]));

        for (Image element : elements) {
            mainGroup.addActor(element);
        }
        return mainGroup;
    }
}
