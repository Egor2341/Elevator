package com.elevator_project;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Atlasses {

    private final TextureAtlas firstFloorAtlas;

    public Atlasses() {
        firstFloorAtlas = new TextureAtlas("FirstFloor.atlas");
    }

    public TextureAtlas getFirstFloorAtlas() {
        return firstFloorAtlas;
    }
}
