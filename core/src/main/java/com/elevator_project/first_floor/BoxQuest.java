package com.elevator_project.first_floor;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.elevator_project.game.App;
import com.elevator_project.game.GameManager;
import com.elevator_project.game.ImageProcessing;
import com.elevator_project.game.RoomPart;
import lombok.Getter;

import java.lang.Integer;

import java.util.*;

public class BoxQuest extends RoomPart {
    private final TextureAtlas atlas;
    private final List<Image> elements;
    private final List<Sprite> runes;
    private final Map<Image, Integer> buttons;
    private final Integer[] combination;
    private Image box;
    @Getter
    private boolean open;
    private boolean sound;

    public BoxQuest () {
        atlas = GameManager.getAtlasses().getFirstFloorAtlas();
        elements = new ArrayList<>();
        runes = new ArrayList<>();
        buttons = new LinkedHashMap<>();
        combination = new Integer[] {2, 5, 1, 4};
        open = false;
        sound = false;
        initElements();
    }

    private void initElements () {
        elements.add(initFloor());
        elements.add(initLocker());
        elements.add(initBox());
        initRunes();
    }

    private Image initBox () {
        final float BOX_RESIZE_FACTOR = 100f;
        final float BOX_HORIZ_FACTOR = 4.4f;
        final float BOX_VERT_FACTOR = 3.4f;

        box = new Image(atlas.createSprite("Box", 2));
        ImageProcessing.process(box, BOX_RESIZE_FACTOR, BOX_HORIZ_FACTOR, BOX_VERT_FACTOR);
        box.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!open){
                    checkSubsequence();
                }
            }
        });

        return box;
    }

    private Image initFloor () {
        final float FLOOR_RESIZE_FACTOR = 10f;

        Image floor = new Image(atlas.createSprite("Floor"));
        ImageProcessing.process(floor, FLOOR_RESIZE_FACTOR, w, h);

        return floor;
    }

    private Image initLocker () {
        final float LOCKER_RESIZE_FACTOR = 100f;
        final float LOCKER_HORIZ_FACTOR = 18f;
        final float LOCKER_VERT_FACTOR = 7.5f;

        Image locker = new Image(atlas.createSprite("Locker", 2));
        ImageProcessing.process(locker, LOCKER_RESIZE_FACTOR, LOCKER_HORIZ_FACTOR, LOCKER_VERT_FACTOR);

        return locker;
    }

    private void initRunes () {
        final float BUTTON_RESIZE_FACTOR = 220f;
        final float BUTTON_VERT_FACTOR = 2.3f;
        final float FIRST_BUTTON_HORIZ_FACTOR = 3.8f;
        final float SECOND_BUTTON_HORIZ_FACTOR = 2.6f;
        final float THIRD_BUTTON_HORIZ_FACTOR = 1.87f;
        final float FOURTH_BUTTON_HORIZ_FACTOR = 1.5f;

        for (int i = 1; i < 7; i++) {
            runes.add(atlas.createSprite("Rune", i));
        }

        for (int i = 0; i < 4; i++) {
            Image button = new Image(runes.getFirst());
            float curHorizFactor = switch (i) {
                case 0 -> FIRST_BUTTON_HORIZ_FACTOR;
                case 1 -> SECOND_BUTTON_HORIZ_FACTOR;
                case 2 -> THIRD_BUTTON_HORIZ_FACTOR;
                case 3 -> FOURTH_BUTTON_HORIZ_FACTOR;
                default -> 0f;
            };
            ImageProcessing.process(button,
                BUTTON_RESIZE_FACTOR, curHorizFactor, BUTTON_VERT_FACTOR);
            button.addListener(new ClickListener() {
                @Override
                public void clicked (InputEvent event, float x, float y) {
                    App.getSoundManager().playRune();
                    buttons.put(button, (buttons.get(button) + 1) % 6);
                    button.setDrawable(new SpriteDrawable((runes.get((buttons.get(button))))));
                }
            });
            buttons.put(button, 0);
        }
    }

    private void checkSubsequence () {
        if (Arrays.equals(combination, buttons.values().toArray(new Integer[4]))){
            App.getSoundManager().playBox();
            box.setDrawable(new SpriteDrawable(atlas.createSprite("Box", 3)));
            for (Image button : buttons.keySet()) {
                button.remove();
            }
            GameManager.getInsulatingTape().render();
            open = true;
        }
    }

    public Group initGroup () {
        for (Image element : elements) {
            mainGroup.addActor(element);
        }
        if (!GameManager.getFirstFloor().isBoxQuestSolved()) {
            for (Image button : buttons.keySet()) {
                mainGroup.addActor(button);
            }
        }
        return mainGroup;
    }

}
