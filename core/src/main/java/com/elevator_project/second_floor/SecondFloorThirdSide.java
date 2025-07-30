package com.elevator_project.second_floor;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.elevator_project.game.GameManager;
import com.elevator_project.game.ImageProcessing;
import com.elevator_project.game.RoomPart;

import java.util.ArrayList;
import java.util.List;

public class SecondFloorThirdSide extends RoomPart {

    private final TextureAtlas atlas;
    private final List<Image> elements;
    private Image back;

    public SecondFloorThirdSide () {
        atlas = GameManager.getAtlasses().getSecondFloorAtlas();
        elements = new ArrayList<>();
        initElements();
    }

    private void initElements() {
        elements.add(initWall());
        elements.add(initBack());
    }

    private Image initBack() {
        final float BACK_RESIZE_FACTOR = 240f;
        final float BACK_VERT_FACTOR = 3.15f;
        final float BACK_HORIZ_FACTOR = 2.6f;

        back = new Image(atlas.createSprite("DoorBack"));
        ImageProcessing.process(back, BACK_RESIZE_FACTOR, BACK_HORIZ_FACTOR, BACK_VERT_FACTOR);

        return back;
    }

    private Image initWall () {
        float WALL_RESIZE_FACTOR = 158f;

        Image wall = new Image(atlas.createSprite("Wall", 3));
        ImageProcessing.process(wall, WALL_RESIZE_FACTOR, w, h);

        return wall;
    }

    public Group initGroup() {
        for (Image element : elements) {
            mainGroup.addActor(element);
        }
        return mainGroup;
    }
}
