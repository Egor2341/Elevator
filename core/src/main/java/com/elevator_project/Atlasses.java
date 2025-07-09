package com.elevator_project;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import lombok.Getter;

@Getter
public class Atlasses {

    private final TextureAtlas firstFloorAtlas;
    private final TextureAtlas elevatorAtlas;
    private final TextureAtlas buttonsAtlas;
    private final TextureAtlas doorAtlas;
    private final TextureAtlas extraElementsAtlas;

    public Atlasses() {
        firstFloorAtlas = new TextureAtlas("FirstFloor.atlas");
        elevatorAtlas = new TextureAtlas("Elevator.atlas");
        buttonsAtlas = new TextureAtlas("Buttons.atlas");
        doorAtlas = new TextureAtlas("Door.atlas");
        extraElementsAtlas = new TextureAtlas("ExtraElements.atlas");
    }
}
