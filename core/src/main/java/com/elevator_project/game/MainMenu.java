package com.elevator_project.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenu {

//    private final TextureAtlas atlas;
    private final float w;
    private final float h;
    private final Group mainGroup;
    private Image back;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    public MainMenu () {
//        atlas = GameManager.getAtlasses().getMainMenuAtlass();
        mainGroup = new Group();
        w = App.getDimensions()[0];
        h = App.getDimensions()[1];

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Norse-Bold.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 44;
        parameter.color = Color.WHITE;
    }

    public void initBack () {
        final float BACK_RESIZE_FACTOR = 158f;

//        back = new Image(atlas.createSprite("Back"));
        ImageProcessing.process(back, BACK_RESIZE_FACTOR, w, h);
    }

    public Label initLabelStart () {
        Label.LabelStyle labelStyle = new Label.LabelStyle();

        labelStyle.font = generator.generateFont(parameter);
        Label label = new Label("NEW GAME", labelStyle);
        label.setPosition(w / 2, h / 2);
        label.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
            }
        });

        return label;
    }

    public Label initLabelContinue () {
        Label.LabelStyle labelStyle = new Label.LabelStyle();

        labelStyle.font = generator.generateFont(parameter);
        Label label = new Label("CONTINUE", labelStyle);
        label.setPosition(w / 2, h / 3);
        label.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                SaveManager.loadAutosave();
            }
        });

        return label;
    }
}
