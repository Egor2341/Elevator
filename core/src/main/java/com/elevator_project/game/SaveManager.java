package com.elevator_project.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.elevator_project.extra_elements.Inventory;

import java.util.List;

public class SaveManager {

    private static final FileHandle autosave = Gdx.files.local("saves/autosave.sav");

    public static void save (FileHandle save, String saveName) {
        GameManager.getGameState().setSaveName(saveName);
        Json json = new Json();
        String saveData = json.toJson(GameManager.getGameState());
        save.writeString(saveData, false);
    }

    public static void load (FileHandle load) {
        Json json = new Json();
        String loadedData = load.readString();
        if (loadedData.isEmpty()) {
            GameManager.setGameState(new GameState());
        } else {
            GameManager.setGameState(json.fromJson(GameState.class, loadedData));
        }
    }

    public static void loadUser (FileHandle load) {
        if (GameManager.getGameState().isElevator()) {
            GameManager.getElevatorManager().dispose();
        } else {
            GameManager.getFloor().dispose();
        }
        Json json = new Json();
        String loadedData = load.readString();
        GameManager.setGameState(json.fromJson(GameState.class, loadedData));
        if (GameManager.getGameState().isElevator()) {
            GameManager.getElevatorManager().render();
        } else {
            GameManager.getFloor().render();
        }
        fillInventory();
        GameManager.getLoadMenu().dispose();
    }

    public static void loadAutosave() {
        load(autosave);
    }

    public static void  fillInventory () {
        List<String> objectsInInventory = GameManager.getGameState().getObjectsInInventory();
        if (!objectsInInventory.isEmpty()) {
            int index = 0;
            for (String name : objectsInInventory) {
                InventoryObject object = switch (name) {
                    case "InsulatingTape":
                        GameManager.getInsulatingTape().setIndexInInventory(-1);
                        yield GameManager.getInsulatingTape();
                    default:
                        yield null;
                };
                if (object != null) {
                    object.addToInventory();
                } else {
                    GameManager.getInventory().addEmptyObject(index);
                }
                index++;
            }
        }
    }

    public static void saveAutosave() {
        save(autosave, "autosave");
    }

}
