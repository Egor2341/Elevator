package com.elevator_project.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.elevator_project.independent_elements.InsulatingTape;

import java.util.List;

public class SaveManager {

    private static final FileHandle autosave = Gdx.files.local("saves/autosave.sav");

    public static void loadAutosave() {
        Json json = new Json();
        String loadedData = autosave.readString();
        if (loadedData.isEmpty()) {
            GameManager.setGameState(new GameState());
        } else {
            GameManager.setGameState(json.fromJson(GameState.class, loadedData));
        }
    }

    public static void  fillInventory () {
        List<String> objectsInInventory = GameManager.getGameState().getObjectsInInventory();
        if (!objectsInInventory.isEmpty()) {
            for (String name : objectsInInventory) {
                System.out.println(name);
                InventoryObject object = switch (name) {
                    case "InsulatingTape" -> GameManager.getInsulatingTape();
                    default -> null;
                };
                if (object != null) {
                    object.addToInventory();
                }
            }
        }
    }

    public static void saveAutosave() {
        Json json = new Json();
        String saveData = json.toJson(GameManager.getGameState());
        autosave.writeString(saveData, false);
    }

}
