package com.elevator_project.game;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameState {

    private boolean elevator;

    public GameState () {
        elevator = true;
    }
}
