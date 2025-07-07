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
    private static FirstFloor firstFloor;

    @Override
    public void create() {
        this.w = (float) Gdx.graphics.getWidth();
        this.h = (float) Gdx.graphics.getHeight();
        (this.camera = new OrthographicCamera(this.w, this.h)).setToOrtho(false);
        stage = new Stage(new ScreenViewport(camera));
        Gdx.input.setInputProcessor(stage);

        init();
        Elevator.render();
        Door.render();
    }

    private void init() {
        Door.initialize(w, h);
        Arrows.initialize(w, h);
        Elevator.initialize(w, h, stage);
        Buttons.initialize(w, h);
        firstFloor = new FirstFloor(w, h);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        float delta = Gdx.graphics.getDeltaTime();
        if (Door.isAnimation()){
            Door.update(delta);
        }
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {

    }

    public static Stage getStage() {
        return stage;
    }

    public static Floor getFloor() {
        return switch (Elevator.getFloorIndex()) {
            case 1 -> firstFloor;
            default -> firstFloor;
        };
    }
}
