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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoxQuest {
    private final TextureAtlas atlas;
    private final float w;
    private final float h;
    private final Group mainGroup;
    private final List<Image> elements;
    private final List<Sprite> runes;
    private final Map<Image, Integer> buttons;

    public BoxQuest () {
        w = App.getDimensions()[0];
        h = App.getDimensions()[1];
        atlas = GameManager.getAtlasses().getFirstFloorAtlas();
        mainGroup = new Group();
        elements = new ArrayList<>();
        runes = new ArrayList<>();
        buttons = new HashMap<>();
        initElements();
    }

    private void initElements () {
        elements.add(initFloor());
        elements.add(initBox());
        elements.addAll(initRunes().keySet());
    }

    private Image initBox () {
        final float BOX_RESIZE_FACTOR = 110f;
        final float BOX_HORIZ_FACTOR = 9.7f;
        final float BOX_VERT_FACTOR = 4.7f;

        Image box = new Image(atlas.createSprite("Box", 2));
        ImageProcessing.process(box, BOX_RESIZE_FACTOR, BOX_HORIZ_FACTOR, BOX_VERT_FACTOR);

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
                GameManager.getFloor().show();
                GameManager.getArrows().show();
            }
        });

        return floor;
    }

    private Map<Image, Integer> initRunes () {
        final float BUTTON_RESIZE_FACTOR = 220f;
        final float BUTTON_VERT_FACTOR = 2.19f;
        final float FIRST_BUTTON_HORIZ_FACTOR = 3.7f;
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
                    buttons.put(button, buttons.get(button) + 1);
                    button.setDrawable(new SpriteDrawable((runes.get((buttons.get(button)) % 6))));
                }
            });
            buttons.put(button, 0);
        }

        return buttons;
    }

    private Group initGroup () {
        for (Image element : elements) {
            mainGroup.addActor(element);
        }
        return mainGroup;
    }

    public void render () {
        App.getStage().addActor(initGroup());
    }

    public void dispose () {
        mainGroup.remove();
    }

    public void hide () {
        mainGroup.setVisible(false);
    }

    public void show () {
        mainGroup.setVisible(true);
    }
}
