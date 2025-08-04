package com.elevator_project.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import lombok.Getter;



/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class App extends ApplicationAdapter {
    private static float w;
    private static float h;
    @Getter
    private static Stage stage;
    @Getter
    private static SoundManager soundManager;

    @Override
    public void create() {
        w = (float) Gdx.graphics.getWidth();
        h = (float) Gdx.graphics.getHeight();
        OrthographicCamera camera;
        (camera = new OrthographicCamera(w, h)).setToOrtho(false);
        stage = new Stage(new ScreenViewport(camera));
        Gdx.input.setInputProcessor(stage);
        init();
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown (InputEvent event, int keycode) {
                switch (keycode) {
                    case Input.Keys.ESCAPE:
                        if (GameManager.getSaveMenu().isVisible()){
                            GameManager.getSaveMenu().dispose();
                        }
                        if (GameManager.getLoadMenu().isVisible()) {
                            GameManager.getLoadMenu().dispose();
                        }
                        if (!GameManager.getMainMenu().isVisible()) {
                            GameManager.getPauseMenu().render();
                        }
                        return true;
                }
                return false;
            }

        });
    }

    private void init() {
        soundManager = new SoundManager();
        soundManager.playBackgroundMusic();
        GameManager.getMainMenu().render();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        float delta = Gdx.graphics.getDeltaTime();
        if (GameManager.getDoor().isAnimation()){
            GameManager.getDoor().update(delta);
        }
        if (GameManager.getElevatorManager().isMoving()) {
            GameManager.getElevatorManager().moveElevator(delta);
        }
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {

    }

    public static float[] getDimensions () {
        return new float[] {w, h};
    }
}
