package com.elevator_project;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.graphics.g2d.freetype.*;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class App extends ApplicationAdapter {
    private SpriteBatch batch;
    private static float w;
    private static float h;
    private OrthographicCamera camera;
    private static Stage stage;
    private static FirstFloor firstFloor;
    private static Atlasses atlasses;

    @Override
    public void create() {
        w = (float) Gdx.graphics.getWidth();
        h = (float) Gdx.graphics.getHeight();
        (this.camera = new OrthographicCamera(w, h)).setToOrtho(false);
        stage = new Stage(new ScreenViewport(camera));
        Gdx.input.setInputProcessor(stage);

//        Label.LabelStyle labelStyle = new Label.LabelStyle();
//        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Norse-Regular.ttf"));
//        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
//            new FreeTypeFontGenerator.FreeTypeFontParameter();
//        parameter.size = 44;
//        parameter.color = Color.WHITE;
//        labelStyle.font = generator.generateFont(parameter);
//        Label label = new Label("RUN", labelStyle);
//        label.setPosition(w / 2, h / 2);
        atlasses = new Atlasses();
        init();
        Elevator.render();
        Door.render();
    }

    private void init() {
        Door.initialize(w, h);
        Arrows.initialize(w, h);
        Elevator.initialize(w, h, stage);
        Buttons.initialize(w, h);
        firstFloor = new FirstFloor();
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

    public static float[] getDimensions () {
        return new float[] {w, h};
    }

    public static Atlasses getAtlasses() {
        return atlasses;
    }
}
