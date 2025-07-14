package com.elevator_project.first_floor;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.elevator_project.App;
import com.elevator_project.GameManager;
import com.elevator_project.ImageProcessing;
import lombok.Getter;

import java.lang.Integer;

import java.util.*;

public class BoxQuest {
    private final TextureAtlas atlas;
    private final float w;
    private final float h;
    private final Group mainGroup;
    private final List<Image> elements;
    private final List<Sprite> runes;
    private final Map<Image, Integer> buttons;
    private final Integer[] combination;
    private Image box;
    @Getter
    private boolean open;
    private final Group runesGroup;
    private boolean sound;

    public BoxQuest () {
        w = App.getDimensions()[0];
        h = App.getDimensions()[1];
        atlas = GameManager.getAtlasses().getFirstFloorAtlas();
        mainGroup = new Group();
        elements = new ArrayList<>();
        runes = new ArrayList<>();
        buttons = new LinkedHashMap<>();
        combination = new Integer[] {2, 5, 1, 4};
        open = false;
        runesGroup = new Group();
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
        floor.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                hide();
                if (open) {
                    GameManager.getInsulatingTape().hide();
                    if (!sound) {
                        App.getSoundManager().playWindow();
                        sound = true;
                    }
                }
                GameManager.getFloor().show();
                GameManager.getArrows().show();
            }
        });

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
            runesGroup.remove();
            GameManager.getInsulatingTape().render();
            open = true;
            GameManager.getFirstFloor().getFourthSide().changeWindow();
        }
    }

    private void initGroup () {
        for (Image element : elements) {
            mainGroup.addActor(element);
        }
        for (Image button : buttons.keySet()) {
            runesGroup.addActor(button);
        }
    }

    public void render () {
        initGroup();
        App.getStage().addActor(mainGroup);
        if (!open) {
            App.getStage().addActor(runesGroup);
        }
    }

    public void dispose () {
        mainGroup.remove();
        runesGroup.remove();
    }

    public void hide () {
        mainGroup.setVisible(false);
        runesGroup.setVisible(false);
    }

    public void show () {
        mainGroup.setVisible(true);
        runesGroup.setVisible(true);
    }

}
