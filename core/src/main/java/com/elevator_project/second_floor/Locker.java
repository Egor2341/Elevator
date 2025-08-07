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
import com.elevator_project.game.SaveManager;

import java.util.ArrayList;
import java.util.List;

public class Locker extends RoomPart {
    private final TextureAtlas atlas;
    private Sprite[] runesSprites;
    private Image[] runes;
    private Image locker;
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
        elements.add(initLocker());
        initRunes();
    }

    private Image initLocker() {
        final float LOCKER_RESIZE = 150;
        final float LOCKER_HORIZ = 4.5f;
        final float LOCKER_VERT = 4f;

        locker = new Image(atlas.createSprite("Locker", 1));
        ImageProcessing.process(locker, LOCKER_RESIZE, LOCKER_HORIZ, LOCKER_VERT);
        locker.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                checkSubsequence();
            }
        });

        return locker;
    }

    private void checkSubsequence() {
        runesIndexes = GameManager.getGameState().getRunesOnSecondFloorLocker();
        if (rightRunesIndexes.equals(runesIndexes)) {
            GameManager.getGameState().setLockerOnSecondFloorQuestSolved(true);
            for (Image rune : runes) {
                rune.remove();
            }
            GameManager.getSecondFloor().disposeRuneImagesOnLocker();
            SaveManager.saveAutosave();
        }
    }

    private void initRunes() {
        final float RUNE_RESIZE = 400f;
        final float RUNE_HORIZ = 1.42f;
        final float[] RUNE_VERT = new float[] {1.35f, 1.52f, 1.74f, 2.04f};

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
