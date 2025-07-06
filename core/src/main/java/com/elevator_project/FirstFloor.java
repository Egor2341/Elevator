package com.elevator_project;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.List;

public class FirstFloor implements Floor{

    private int wallIndex;
    private final List<Group> groups;
    private final Group firstSide;
    private final Group secondSide;
    private final Group thirdSide;
    private final Group fourthSide;

    public FirstFloor(float w, float h) {
        TextureAtlas atlas = new TextureAtlas("FirstFloor.atlas");
        firstSide = new Group();
        secondSide = new Group();
        thirdSide = new Group();
        fourthSide = new Group();
        groups = List.of(firstSide, secondSide, thirdSide, fourthSide);

        float WALL_RESIZE_FACTOR = 158f;

        Image firstWall = new Image(atlas.createSprite("Wall", 1));
        firstWall.setSize(firstWall.getWidth() * w / WALL_RESIZE_FACTOR,
            firstWall.getHeight() * w / WALL_RESIZE_FACTOR);
        firstWall.setPosition(0, 0);
        firstSide.addActor(firstWall);

        firstSide.setVisible(true);
        secondSide.setVisible(false);
        thirdSide.setVisible(false);
        fourthSide.setVisible(false);

        wallIndex = 1;
    }

    public void render() {
        for (Group group : groups) {
            App.getStage().addActor(group);
        }
    }

    public void move (int from, int to) {
        Group hideWall = switch (from) {
            case 1 -> firstSide;
            case 2 -> secondSide;
            case 3 -> thirdSide;
            case 4 -> fourthSide;
            default -> firstSide;
        };
        hideWall.setVisible(false);
        Group showWall = switch (to) {
            case 1 -> firstSide;
            case 2 -> secondSide;
            case 3 -> thirdSide;
            case 4 -> fourthSide;
            default -> firstSide;
        };
        showWall.setVisible(true);
    }

    public void dispose() {
        for (Group group : groups) {
            group.remove();
        }
    }
}
