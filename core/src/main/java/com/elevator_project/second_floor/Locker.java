package com.elevator_project.second_floor;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.elevator_project.game.*;

import java.util.ArrayList;
import java.util.List;

public class Locker extends RoomPart {
    private final TextureAtlas atlas;
    private Sprite[] runesSprites;
    private Image[] runes;
    private Image closeLocker;
    private Image openLocker;
    private final List<Image> elements;
    private List<Integer> runesIndexes;
    private final List<Integer> rightRunesIndexes;

    public Locker() {
        atlas = GameManager.getAtlasses().getSecondFloorAtlas();
        elements = new ArrayList<>();
        rightRunesIndexes = List.of(4, 1, 5, 3);
        initElements();
    }

    private void initElements() {
        elements.add(initBack());
        elements.add(initLocker());
        elements.add(openLocker);
        initRunes();
    }

    private Image initBack() {
        final float BACK_RESIZE = 960f;

        Image back = new Image(atlas.createSprite("LockerBack"));
        ImageProcessing.process(back, BACK_RESIZE, w, h);

        return back;
    }

    private Image initLocker() {
        final float CLOSE_LOCKER_RESIZE = 180;
        final float CLOSE_LOCKER_HORIZ = 4.5f;
        final float CLOSE_LOCKER_VERT = 1.92f;

        final float OPEN_LOCKER_RESIZE = 182f;
        final float OPEN_LOCKER_HORIZ = 19f;
        final float OPEN_LOCKER_VERT = 3.2f;

        closeLocker = new Image(atlas.createSprite("Locker", 1));
        ImageProcessing.process(closeLocker, CLOSE_LOCKER_RESIZE, CLOSE_LOCKER_HORIZ, CLOSE_LOCKER_VERT);
        closeLocker.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                checkSubsequence();
            }
        });


        openLocker = new Image(atlas.createSprite("Locker", 2));
        ImageProcessing.process(openLocker, OPEN_LOCKER_RESIZE, OPEN_LOCKER_HORIZ, OPEN_LOCKER_VERT);

        return closeLocker;
    }

    private void checkSubsequence() {
        runesIndexes = GameManager.getGameState().getRunesOnSecondFloorLocker();
        if (rightRunesIndexes.equals(runesIndexes)) {
            GameManager.getGameState().setLockerOnSecondFloorQuestSolved(true);
            for (Image rune : runes) {
                rune.remove();
            }
            App.getSoundManager().playOpen();
            GameManager.getSecondFloor().disposeRuneImagesOnLocker();
            closeLocker.setVisible(false);
            openLocker.setVisible(true);
            SaveManager.saveAutosave();
        }
    }

    private void initRunes() {
        final float RUNE_RESIZE = 400f;
        final float RUNE_HORIZ = 1.63f;
        final float[] RUNE_VERT = new float[] {1.095f, 1.2f, 1.33f, 1.49f};

        runesSprites = new Sprite[6];
        for (int i = 0; i < 6; i++) {
            runesSprites[i] = atlas.createSprite("Rune", i);
        }

        runes = new Image[4];
        runesIndexes = GameManager.getGameState().getRunesOnSecondFloorLocker();
        for (int i = 0; i < 4; i++) {
            Image rune = new Image(runesSprites[0]);
            ImageProcessing.process(rune, RUNE_RESIZE, RUNE_HORIZ, RUNE_VERT[i]);
            int number = i;
            rune.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    App.getSoundManager().playSwitch();
                    int runeIndex = (runesIndexes.get(number) + 1) % 6;
                    runesIndexes.set(number, runeIndex);
                    rune.setDrawable(new SpriteDrawable(runesSprites[runeIndex]));
                    GameManager.getGameState().setRunesOnSecondFloorLocker(runesIndexes);
                }
            });
            runes[i] = rune;
        }
    }

    @Override
    public Group initGroup() {
        closeLocker.setVisible(true);
        openLocker.setVisible(false);
        elements.forEach(mainGroup::addActor);
        if (!GameManager.getGameState().isLockerOnSecondFloorQuestSolved()) {
            runesIndexes = GameManager.getGameState().getRunesOnSecondFloorLocker();
            for (int i = 0; i < 4; i++) {
                runes[i].setDrawable(new SpriteDrawable(runesSprites[runesIndexes.get(i)]));
                mainGroup.addActor(runes[i]);
            }
        }
        return mainGroup;
    }
}
