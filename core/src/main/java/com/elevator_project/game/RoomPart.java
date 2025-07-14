package com.elevator_project.game;


import com.badlogic.gdx.scenes.scene2d.Group;

public class RoomPart {
    protected final float w;
    protected final float h;
    protected final Group mainGroup;


    public RoomPart() {
        this.w = App.getDimensions()[0];
        this.h = App.getDimensions()[1];
        mainGroup = new Group();
    }

    public Group initGroup () {
        return mainGroup;
    }
}
