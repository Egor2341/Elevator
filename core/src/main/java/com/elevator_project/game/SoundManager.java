package com.elevator_project.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
    private final Music backgroundMusic;
    private final Sound elevatorDoors;
    private final Sound steps;
    private final Sound window;
    private final Sound box;
    private final Sound rune;
    private final Sound elevatorMotor;
    private final Sound elevatorDing;
    private final Sound unavailableButton;
    private final Sound availableButton;

    public SoundManager () {
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music/background_music.mp3"));
        elevatorDoors = Gdx.audio.newSound(Gdx.files.internal("sounds/elevator_doors.mp3"));
        steps = Gdx.audio.newSound(Gdx.files.internal("sounds/footsteps.mp3"));
        window = Gdx.audio.newSound(Gdx.files.internal("sounds/window_2.wav"));
        box = Gdx.audio.newSound(Gdx.files.internal("sounds/box.mp3"));
        rune = Gdx.audio.newSound(Gdx.files.internal("sounds/rune.mp3"));
        elevatorMotor = Gdx.audio.newSound(Gdx.files.internal("sounds/elevator_motor.mp3"));
        elevatorDing = Gdx.audio.newSound(Gdx.files.internal("sounds/elevator_ding.mp3"));
        unavailableButton = Gdx.audio.newSound(Gdx.files.internal("sounds/unavailable_button.mp3"));
        availableButton = Gdx.audio.newSound(Gdx.files.internal("sounds/available_button.mp3"));
    }

    public void playBackgroundMusic () {
        backgroundMusic.play();
        backgroundMusic.setVolume(0.5f);
        backgroundMusic.setLooping(true);
    }

    public void playElevatorDoors () {
        elevatorDoors.play(1f, 3f, 0f);
    }

    public void playSteps () {
        steps.play(1f, 1f, -0.5f);
    }

    public void playWindow () {
        window.play();
    }

    public void playBox () {
        box.play();
    }

    public void playRune () {
        rune.play();
    }

    public void playElevatorMotor() {
        elevatorMotor.play();
    }

    public void stopElevatorMotor() {
        elevatorMotor.stop();
        elevatorDing.play();
    }

    public void playUnavailableButton() {
        unavailableButton.play();
    }

    public void playAvailableButton() {
        availableButton.play();
    }
}
