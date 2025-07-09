package com.elevator_project.first_floor;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.elevator_project.App;
import com.elevator_project.GameManager;
import com.elevator_project.GroupElements;
import com.elevator_project.ImageProcessing;

import java.util.ArrayList;
import java.util.List;

public class FirstFloorThirdSide implements GroupElements {
    private final float w;
    private final float h;
    private final TextureAtlas atlas;
    private final Group mainGroup;
    private final List<Image> elements;

    public FirstFloorThirdSide() {
        this.w = App.getDimensions()[0];
        this.h = App.getDimensions()[1];
        atlas = GameManager.getAtlasses().getFirstFloorAtlas();
        mainGroup = new Group();
        elements = new ArrayList<>();
        initElements();
    }

    private void initElements () {
        elements.add(initWall());
        elements.add(initBack());
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

        Image back = new Image(atlas.createSprite("Back"));
        ImageProcessing.process(back, BACK_RESIZE_FACTOR, BACK_HORIZ_FACTOR, BACK_VERT_FACTOR);

        return back;
    }

    private Image initButton() {
        final float BUTTON_RESIZE_FACTOR = 350f;
        final float BUTTON_VERT_FACTOR = 2f;
        final float BUTTON_HORIZ_FACTOR = 1.6f;

        Image button = new Image(atlas.createSprite("Button"));
        ImageProcessing.process(button, BUTTON_RESIZE_FACTOR, BUTTON_HORIZ_FACTOR, BUTTON_VERT_FACTOR);

        return button;
    }

    public Group initGroup() {
        for (Image element : elements) {
            mainGroup.addActor(element);
        }
        return mainGroup;
    }
}
