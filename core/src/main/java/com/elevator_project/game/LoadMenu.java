package com.elevator_project.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;
import lombok.Getter;

public class LoadMenu {

    private final Group group;
    private final FileHandle[] loads;
    private final Image[] cells;
    private final Label[] labels;
    private final TextureAtlas atlas;
    private Image back;
    private final float w;
    private final float h;
    @Getter
    private boolean visible;

    public LoadMenu () {
        loads = new FileHandle[4];
        cells = new Image[4];
        labels = new Label[4];

        atlas = GameManager.getAtlasses().getExtraElementsAtlas();
        group = new Group();
        w = App.getDimensions()[0];
        h = App.getDimensions()[1];
        initLoads();
        initCells();
        initBack();
        initLabels();
    }

    private void initLoads () {
        loads[0] = Gdx.files.local("saves/autosave.sav");
        loads[1] = Gdx.files.local("saves/save1.sav");
        loads[2] = Gdx.files.local("saves/save2.sav");
        loads[3] = Gdx.files.local("saves/save3.sav");
    }

    private void initGroup () {
        group.addActor(back);
        for (Image cell : cells) {
            group.addActor(cell);
        }
        for (Label label : labels) {
            group.addActor(label);
        }
    }

    private void initBack () {
        final float BACK_RESIZE = 158f;

        back = new Image(atlas.createSprite("Back"));
        ImageProcessing.process(back, BACK_RESIZE, w, h);
    }

    private void initCells () {
        final float CELL_RESIZE = 70f;
        final float CELL_HORIZ = 2.5f;
        final float[] CELL_VERT = new float[] {1.32f, 1.95f, 3.7f, 35f};

        for (int i = 0; i < 4; i++) {
            int index = i;
            Image cell = new Image(atlas.createSprite("SaveCell"));
            ImageProcessing.process(cell, CELL_RESIZE, CELL_HORIZ, CELL_VERT[i]);
            cell.addListener(new ClickListener() {
                @Override
                public void clicked (InputEvent event, float x, float y) {
                    if (GameManager.getMainMenu().isVisible()) {
                        GameManager.getMainMenu().dispose();
                    }
                    Json json = new Json();
                    GameState loadedData = json.fromJson(GameState.class, loads[index].readString());
                    if (loadedData != null) {
                        SaveManager.load(loads[index]);
                    }
                }
            });

            cells[i] = cell;
        }
    }

    private void initLabels () {
        final float LABEL_RESIZE = 50f;
        final float LABEL_HORIZ = 2.4f;
        final float[] LABEL_VERT = new float[] {1.13f, 1.58f, 2.5f, 6.5f};

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Norse-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int) (w / LABEL_RESIZE);
        parameter.color = Color.WHITE;

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = generator.generateFont(parameter);

        for (int i = 0; i < 4; i++) {
            Json json = new Json();
            String saveInfo;
            GameState loadedData = json.fromJson(GameState.class, loads[i].readString());
            if (loadedData == null || loadedData.getSaveName().isEmpty()) {
                saveInfo = "Empty slot";
            } else {
                saveInfo = loadedData.getSaveName();
            }
            if (i == 0) {
                saveInfo = "Autosave";
            }
            Label label = new Label("save"+(i+1)+"\n"+saveInfo, labelStyle);
            label.setPosition(w / LABEL_HORIZ, h / LABEL_VERT[i]);
            labels[i] = label;
        }
    }

    public void changeLabel (int index, String saveName) {
        labels[index].setText("save"+(index+1)+"\n"+saveName);
    }

    public void render () {
        visible = true;
        initGroup();
        App.getStage().addActor(group);
    }

    public void dispose () {
        visible = false;
        group.remove();
    }
}
