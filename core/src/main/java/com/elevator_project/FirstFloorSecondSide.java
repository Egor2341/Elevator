package com.elevator_project;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class FirstFloorSecondSide implements GroupElements{
    private final float w;
    private final float h;
    private final TextureAtlas atlas;
    private final Group mainGroup;

    public FirstFloorSecondSide() {
        this.w = App.getDimensions()[0];
        this.h = App.getDimensions()[1];
        atlas = App.getAtlasses().getFirstFloorAtlas();
        mainGroup = new Group();
    }

    private Image initWall () {
        float WALL_RESIZE_FACTOR = 158f;

        Image wall = new Image(atlas.createSprite("Wall", 2));
        ImageProcessing.process(wall, WALL_RESIZE_FACTOR, w, h);

        return wall;
    }

    public Group initGroup() {
        mainGroup.addActor(initWall());
        return mainGroup;
    }
}
