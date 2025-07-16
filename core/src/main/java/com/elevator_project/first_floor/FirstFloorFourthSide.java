package com.elevator_project.first_floor;

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

public class FirstFloorFourthSide extends RoomPart {

    private final TextureAtlas atlas;
    private Image window;
    private int windowIndex;
    private final List<Sprite> windowSprites;
    private final List<Image> elements;
    private boolean screamer;

    public FirstFloorFourthSide() {
        atlas = GameManager.getAtlasses().getFirstFloorAtlas();
        windowSprites = new ArrayList<>();
        elements = new ArrayList<>();
        screamer = false;
        initElements();
    }

    private void initElements() {
        elements.add(initWall());
        elements.add(initWindow());
        elements.add(initLocker());
        elements.add(initBox());
    }

    private Image initWall() {
        float WALL_RESIZE_FACTOR = 158f;

        Image wall = new Image(atlas.createSprite("Wall", 4));
        ImageProcessing.process(wall, WALL_RESIZE_FACTOR, w, h);

        return wall;
    }

    private Image initWindow() {
        final float WINDOW_RESIZE_FACTOR = 220f;
        final float WINDOW_VERT_FACTOR = 2f;
        final float WINDOW_HORIZ_FACTOR = 2.5f;

        windowIndex = 0;

        for (int i = 1; i < 4; i++) {
            windowSprites.add(atlas.createSprite("Window", i));
        }

        System.out.println(windowSprites);

        window = new Image(windowSprites.get(windowIndex));
        ImageProcessing.process(window, WINDOW_RESIZE_FACTOR, WINDOW_HORIZ_FACTOR, WINDOW_VERT_FACTOR);

        return window;
    }

    private Image initLocker() {
        final float LOCKER_RESIZE_FACTOR = 600f;
        final float LOCKER_HORIZ_FACTOR = 2.32f;
        final float LOCKER_VERT_FACTOR = 3.19f;

        Image locker = new Image(atlas.createSprite("Locker", 1));
        ImageProcessing.process(locker, LOCKER_RESIZE_FACTOR, LOCKER_HORIZ_FACTOR, LOCKER_VERT_FACTOR);

        return locker;
    }

    private Image initBox() {
        final float BOX_RESIZE_FACTOR = 1600f;
        final float BOX_HORIZ_FACTOR = 2.1f;
        final float BOX_VERT_FACTOR = 2.19f;

        Image box = new Image(atlas.createSprite("Box", 1));
        ImageProcessing.process(box, BOX_RESIZE_FACTOR, BOX_HORIZ_FACTOR, BOX_VERT_FACTOR);
        box.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameManager.getFirstFloor().moveToBox();
                GameManager.getArrows().hide();
                if (GameManager.getFirstFloor().isBoxQuestSolved()) {
                    GameManager.getInsulatingTape().show();
                }
            }
        });

        return box;
    }

    public void changeWindow() {
        if (GameManager.getFirstFloor().isBoxQuestSolved()) {
            window.setDrawable(new SpriteDrawable(windowSprites.get(2)));
            if (!screamer) {
                App.getSoundManager().playWindow();
                screamer = true;
            }
        } else {
            window.setDrawable(new SpriteDrawable(windowSprites.get(windowIndex)));
            windowIndex = windowIndex == 0 ? 1 : 0;
        }
    }

    public Group initGroup() {
        for (Image element : elements) {
            mainGroup.addActor(element);
        }
        return mainGroup;
    }
}
