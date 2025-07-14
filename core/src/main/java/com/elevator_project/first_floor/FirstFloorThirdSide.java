package com.elevator_project.first_floor;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.elevator_project.game.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class FirstFloorThirdSide extends RoomPart {

    private final TextureAtlas atlas;
    private final List<Image> elements;
    @Getter
    private boolean buttonAvailable;
    private Image back;

    public FirstFloorThirdSide() {
        atlas = GameManager.getAtlasses().getFirstFloorAtlas();
        elements = new ArrayList<>();
        buttonAvailable = false;
        initElements();
    }

    private void initElements () {
        elements.add(initWall());
        elements.add(initBack());
        elements.add(initWire());
        elements.add(initButton());
    }

    private Image initWall () {
        float WALL_RESIZE_FACTOR = 158f;

        Image wall = new Image(atlas.createSprite("Wall", 3));
        ImageProcessing.process(wall, WALL_RESIZE_FACTOR, w, h);

        return wall;
    }

    private Image initBack() {
        final float BACK_RESIZE_FACTOR = 240f;
        final float BACK_VERT_FACTOR = 3.15f;
        final float BACK_HORIZ_FACTOR = 2.6f;

        back = new Image(atlas.createSprite("Back", 1));
        ImageProcessing.process(back, BACK_RESIZE_FACTOR, BACK_HORIZ_FACTOR, BACK_VERT_FACTOR);

        return back;
    }

    private Image initButton() {
        final float BUTTON_RESIZE_FACTOR = 350f;
        final float BUTTON_VERT_FACTOR = 2f;
        final float BUTTON_HORIZ_FACTOR = 1.6f;

        Image button = new Image(atlas.createSprite("Button"));
        ImageProcessing.process(button, BUTTON_RESIZE_FACTOR, BUTTON_HORIZ_FACTOR, BUTTON_VERT_FACTOR);
        button.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                if (buttonAvailable) {
                    back.setDrawable(new SpriteDrawable(atlas.createSprite("Back", 2)));
                    GameManager.getDoor().setAvailable(true);
                }
            }
        });

        return button;
    }

    private Image initWire () {
        final float WIRE_RESIZE_FACTOR = 500f;
        final float WIRE_HORIZ_FACTOR = 1.54f;
        final float WIRE_VERT_FACTOR = 1.7f;

        Image wire = new Image(atlas.createSprite("Wire", 1));
        ImageProcessing.process(wire, WIRE_RESIZE_FACTOR, WIRE_HORIZ_FACTOR, WIRE_VERT_FACTOR);
        wire.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                int insulatingTapeIndexInventory = GameManager.getInsulatingTape().getIndexInInventory();
                if (insulatingTapeIndexInventory != -1 &&
                GameManager.getInventory().getChosen() == insulatingTapeIndexInventory) {
                    GameManager.getInventory().removeObject(insulatingTapeIndexInventory);
                    wire.setDrawable(new SpriteDrawable(atlas.createSprite("Wire", 2)));
                    buttonAvailable = true;
                }
            }
        });

        return wire;
    }

    public Group initGroup() {
        for (Image element : elements) {
            mainGroup.addActor(element);
        }
        return mainGroup;
    }
}
