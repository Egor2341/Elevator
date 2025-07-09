package com.elevator_project;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.elevator_project.first_floor.BoxQuest;
import com.elevator_project.first_floor.FirstFloor;
import lombok.Getter;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class App extends ApplicationAdapter {
    private static float w;
    private static float h;
    @Getter
    private static Stage stage;

    @Override
    public void create() {
        w = (float) Gdx.graphics.getWidth();
        h = (float) Gdx.graphics.getHeight();
        OrthographicCamera camera;
        (camera = new OrthographicCamera(w, h)).setToOrtho(false);
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

        init();
    }

    private void init() {
        GameManager.getElevatorManager().render();
        GameManager.getDoor().render();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        float delta = Gdx.graphics.getDeltaTime();
        if (GameManager.getDoor().isAnimation()){
            GameManager.getDoor().update(delta);
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
