package com.elevator_project.game;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import lombok.Getter;

@Getter
public class Atlasses {

    private final TextureAtlas extraElementsAtlas;

    private final TextureAtlas doorAtlas;

    private final TextureAtlas elevatorAtlas;
    private final TextureAtlas buttonsAtlas;

    private final TextureAtlas firstFloorAtlas;
    private final TextureAtlas secondFloorAtlas;
    private final TextureAtlas thirdFloorAtlas;
    private final TextureAtlas fourthFloorAtlas;
    private final TextureAtlas fifthFloorAtlas;
    private final TextureAtlas sixthFloorAtlas;

    public Atlasses() {
        extraElementsAtlas = new TextureAtlas("ExtraElements.atlas");

        doorAtlas = new TextureAtlas("Door.atlas");

        elevatorAtlas = new TextureAtlas("Elevator.atlas");
        buttonsAtlas = new TextureAtlas("Buttons.atlas");

        firstFloorAtlas = new TextureAtlas("FirstFloor.atlas");
        secondFloorAtlas = new TextureAtlas("secondFloor.atlas");
        thirdFloorAtlas = new TextureAtlas("thirdFloor.atlas");
        fourthFloorAtlas = new TextureAtlas("fourthFloor.atlas");
        fifthFloorAtlas = new TextureAtlas("fifthFloor.atlas");
        sixthFloorAtlas = new TextureAtlas("sixthFloor.atlas");
    }
}
