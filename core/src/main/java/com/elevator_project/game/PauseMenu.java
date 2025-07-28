package com.elevator_project.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class PauseMenu {

    private final TextureAtlas atlas;
    private final float w;
    private final float h;
    private final Group mainGroup;
    private Image back;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private final float LABEL_HORIZ;
    private final float[] LABEL_VERT;

    public PauseMenu () {
        atlas = GameManager.getAtlasses().getExtraElementsAtlas();
        mainGroup = new Group();
        w = App.getDimensions()[0];
        h = App.getDimensions()[1];

        final float LABEL_RESIZE = 20f;
        LABEL_HORIZ = 2.3f;
        LABEL_VERT = new float[] {1.3f, 1.7f, 2.5f, 4.5f};

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Norse-Bold.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int) (w / LABEL_RESIZE);
        parameter.color = Color.WHITE;
        initBack();
    }

    private void initGroup () {
        mainGroup.addActor(back);
        mainGroup.addActor(initLabelContinue());
        mainGroup.addActor(initLabelSave());
        mainGroup.addActor(initLabelLoad());
        mainGroup.addActor(initLabelMainMenu());
    }

    private void initBack () {
        final float BACK_RESIZE_FACTOR = 158f;

        back = new Image(atlas.createSprite("Back"));
        ImageProcessing.process(back, BACK_RESIZE_FACTOR, w, h);
    }

    private Label initLabelContinue () {
        Label.LabelStyle labelStyle = new Label.LabelStyle();

        labelStyle.font = generator.generateFont(parameter);
        Label label = new Label("CONTINUE", labelStyle);
        label.setPosition(w / LABEL_HORIZ, h / LABEL_VERT[0]);
        label.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                dispose();
            }
        });

        return label;
    }

    private Label initLabelSave () {
        Label.LabelStyle labelStyle = new Label.LabelStyle();

        labelStyle.font = generator.generateFont(parameter);
        Label label = new Label("SAVE", labelStyle);
        label.setPosition(w / LABEL_HORIZ, h / LABEL_VERT[1]);
        label.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                dispose();
                GameManager.getSaveMenu().render();
            }
        });
        return label;
    }

    private Label initLabelLoad () {
        Label.LabelStyle labelStyle = new Label.LabelStyle();

        labelStyle.font = generator.generateFont(parameter);
        Label label = new Label("LOAD", labelStyle);
        label.setPosition(w / LABEL_HORIZ, h / LABEL_VERT[2]);
        label.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                dispose();
                GameManager.getLoadMenu().render();
            }
        });
        return label;
    }

    private Label initLabelMainMenu () {
        Label.LabelStyle labelStyle = new Label.LabelStyle();

        labelStyle.font = generator.generateFont(parameter);
        Label label = new Label("MAIN MENU", labelStyle);
        label.setPosition(w / LABEL_HORIZ, h / LABEL_VERT[3]);
        label.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                dispose();
                GameManager.getMainMenu().render();
            }
        });
        return label;
    }

    public void show () {
        mainGroup.setVisible(true);
    }

    public void hide () {
        mainGroup.setVisible(false);
    }

    public void render () {
        initGroup();
        App.getStage().addActor(mainGroup);
    }

    public void dispose () {
        mainGroup.remove();
    }
}
