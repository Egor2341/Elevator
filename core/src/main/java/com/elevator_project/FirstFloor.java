package com.elevator_project;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import java.util.List;

public class FirstFloor implements Floor{

    private int wallIndex;
    private final List<Group> groups;
    private final TextureAtlas atlas;
    private final float w;
    private int windowIndex;
    private final Image window;
    private Sprite[] windowSprites;

    public FirstFloor(float w, float h) {
        atlas = new TextureAtlas("FirstFloor.atlas");
        this.w = w;
        Group firstSide = new Group();
        Group secondSide = new Group();
        Group thirdSide = new Group();
        Group fourthSide = new Group();

        groups = List.of(firstSide, secondSide, thirdSide, fourthSide);

        initWalls();


        final float BACK_RESIZE_FACTOR = 240f;
        final float BACK_VERT_FACTOR = 3.15f;
        final float BACK_HORIZ_FACTOR = 2.6f;
        Image back = new Image(atlas.createSprite("Back"));
        back.setSize(back.getWidth() * w / BACK_RESIZE_FACTOR,
            back.getHeight() * w / BACK_RESIZE_FACTOR);
        back.setPosition(w / BACK_HORIZ_FACTOR, h / BACK_VERT_FACTOR);
        thirdSide.addActor(back);

        final float BUTTON_RESIZE_FACTOR = 350f;
        final float BUTTON_VERT_FACTOR = 2f;
        final float BUTTON_HORIZ_FACTOR = 1.6f;
        Image button = new Image(atlas.createSprite("Button"));
        button.setSize(button.getWidth() * w / BUTTON_RESIZE_FACTOR,
            button.getHeight() * w / BUTTON_RESIZE_FACTOR);
        button.setPosition(w / BUTTON_HORIZ_FACTOR, h / BUTTON_VERT_FACTOR);
        thirdSide.addActor(button);

        windowSprites = new Sprite[3];
        for (int i = 1; i < 4; i++) {
            windowSprites[i-1] = atlas.createSprite("Window", i);
        }
        windowIndex = 0;
        final float WINDOW_RESIZE_FACTOR = 200f;
        final float WINDOW_VERT_FACTOR = 2.2f;
        final float WINDOW_HORIZ_FACTOR = 2.5f;
        window = new Image(windowSprites[windowIndex]);
        window.setSize(window.getWidth() * w / WINDOW_RESIZE_FACTOR,
            window.getHeight() * w / WINDOW_RESIZE_FACTOR);
        window.setPosition(w / WINDOW_HORIZ_FACTOR, h / WINDOW_VERT_FACTOR);
        fourthSide.addActor(window);


        secondSide.setVisible(false);
        thirdSide.setVisible(false);
        fourthSide.setVisible(false);

        wallIndex = 0;
    }

    private void initWalls() {
        for (int i = 0; i < 4; i++) {
            Image wall = new Image(atlas.createSprite("Wall", i + 1));
            float WALL_RESIZE_FACTOR = 158f;
            wall.setSize(wall.getWidth() * w / WALL_RESIZE_FACTOR,
                wall.getHeight() * w / WALL_RESIZE_FACTOR);
            wall.setPosition(0, 0);
            groups.get(i).addActor(wall);
        }
    }

    public void render() {
        for (Group group : groups) {
            App.getStage().addActor(group);
        }
    }

    public void right() {
        move(wallIndex, (wallIndex + 1) % 4);
    }

    public void left() {
        move(wallIndex, (wallIndex + 3) % 4);
    }

    private void move (int hide, int show) {
        if (hide == 2) {
            Door.dispose();
        }
        groups.get(hide).setVisible(false);
        groups.get(show).setVisible(true);
        wallIndex = show;
        if (show == 2) {
            Door.render();
        } else if (show == 3) {
            window.setDrawable(new SpriteDrawable(windowSprites[windowIndex]));
            windowIndex = windowIndex == 0 ? 1 : 0;
        }
    }

    public void dispose() {
        for (Group group : groups) {
            group.remove();
        }
    }
}
