package com.elevator_project.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class SaveManager {
//    private static final Preferences auto = Gdx.app.getPreferences("Auto Preferences");

    private static final FileHandle autosave = Gdx.files.local("saves/autosave.sav");

    public static void loadAll () {
        Json json = new Json();
        String loadedData = autosave.readString();
        if (loadedData.isEmpty()) {
            GameManager.setGameState(new GameState());
        } else {
            GameManager.setGameState(json.fromJson(GameState.class, loadedData));
        }
    }

    public static void saveAll () {
        Json json = new Json();
        String saveData = json.toJson(GameManager.getGameState());
        autosave.writeString(saveData, false);
    }

//    public static void saveAll () {
//        auto.putBoolean("Is elevator", SavingData.isElevator());
//        auto.putBoolean("Is door available", SavingData.isDoorAvailable());
//        auto.putBoolean("Is box quest solved", SavingData.isBoxQuestSolved());
//        auto.putBoolean("Is button on first floor available", SavingData.isButtonOnFirstFloorAvailable());
//        auto.putBoolean("Is screamer on first floor played", SavingData.isScreamerOnFirstFloorPlayed());
//
//        auto.putInteger("Floor index", SavingData.getFloorIndex());
//        auto.putInteger("Part index", SavingData.getPartIndex());
//
//        auto.flush();
//    }
//
//    public static void loadAll () {
//        SavingData.setElevator(auto.getBoolean("Is elevator"));
//        SavingData.setDoorAvailable(auto.getBoolean("Is door available"));
//        SavingData.setBoxQuestSolved(auto.getBoolean("Is box quest solved"));
//        SavingData.setButtonOnFirstFloorAvailable(auto.getBoolean("Is button on first floor available"));
//        SavingData.setScreamerOnFirstFloorPlayed(auto.getBoolean("Is screamer on first floor played"));
//
//        SavingData.setFloorIndex(auto.getInteger("Floor index"));
//        SavingData.setPartIndex(auto.getInteger("Part index"));
//    }
//
//    public static void saveIsElevator (boolean value) {
//        auto.putBoolean("Is elevator", value);
//        SavingData.setElevator(value);
//        auto.flush();
//    }
//
//    public static void saveIsDoorAvailable (boolean value) {
//        auto.putBoolean("Is elevator", value);
//        SavingData.setElevator(value);
//        auto.flush();
//    }
//
//    public static void loadIsElevator () {
//        SavingData.setElevator(auto.getBoolean("Is elevator"));
//    }
}
