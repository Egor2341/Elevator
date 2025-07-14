package com.elevator_project.first_floor;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.elevator_project.game.*;

public class FirstFloorFirstSide extends RoomPart {

    private final TextureAtlas atlas;

    public FirstFloorFirstSide() {
        atlas = GameManager.getAtlasses().getFirstFloorAtlas();
    }

    private Image initWall () {
        float WALL_RESIZE_FACTOR = 158f;

        Image wall = new Image(atlas.createSprite("Wall", 1));
        ImageProcessing.process(wall, WALL_RESIZE_FACTOR, w, h);

        return wall;
    }

    public Group initGroup() {
        mainGroup.addActor(initWall());
        return mainGroup;
    }
}
