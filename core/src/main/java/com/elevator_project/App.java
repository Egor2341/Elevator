package com.elevator_project;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class App extends ApplicationAdapter {
    private SpriteBatch batch;
    private float w;
    private float h;
    private OrthographicCamera camera;
    private static Stage stage;

    @Override
    public void create() {
        this.w = (float) Gdx.graphics.getWidth();
        this.h = (float) Gdx.graphics.getHeight();
        (this.camera = new OrthographicCamera(this.w, this.h)).setToOrtho(false);
        stage = new Stage(new ScreenViewport(camera));
        Gdx.input.setInputProcessor(stage);
        Elevator.initialize(w, h, stage);
        Buttons.initialize(w, h);
        Elevator.render();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {

    }

    public static Stage getStage() {
        return stage;
    }
}
