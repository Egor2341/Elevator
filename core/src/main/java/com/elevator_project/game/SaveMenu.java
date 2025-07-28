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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaveMenu {

    private final Group group;
    private final FileHandle[] saves;
    private final Image[] cells;
    private final Label[] labels;
    private final TextureAtlas atlas;
    private Image back;
    private final float w;
    private final float h;
    @Getter
    private boolean visible;

    public SaveMenu () {
        saves = new FileHandle[3];
        cells = new Image[3];
        labels = new Label[3];
        atlas = GameManager.getAtlasses().getExtraElementsAtlas();
        group = new Group();
        w = App.getDimensions()[0];
        h = App.getDimensions()[1];
        initSaves();
        initCells();
        initBack();
        initLabels();
    }

    private void initSaves () {
        saves[0] = Gdx.files.local("saves/save1.sav");
        saves[1] = Gdx.files.local("saves/save2.sav");
        saves[2] = Gdx.files.local("saves/save3.sav");
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
        final float[] CELL_VERT = new float[] {1.5f, 2.6f, 10f};

        for (int i = 0; i < 3; i++) {
            int index = i;
            Image cell = new Image(atlas.createSprite("SaveCell"));
            ImageProcessing.process(cell, CELL_RESIZE, CELL_HORIZ, CELL_VERT[i]);
            cell.addListener(new ClickListener() {
                @Override
                public void clicked (InputEvent event, float x, float y) {
                    String saveName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    SaveManager.save(saves[index], saveName);
                    labels[index].setText("save"+(index+1)+"\n"+saveName);
                    GameManager.getLoadMenu().changeLabel(index+1, saveName);
                    dispose();
                }
            });
            cells[i] = cell;
        }
    }

    private void initLabels () {
        final float LABEL_RESIZE = 50f;
        final float LABEL_HORIZ = 2.4f;
        final float[] LABEL_VERT = new float[] {1.26f, 1.95f, 4.4f};

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Norse-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int) (w / LABEL_RESIZE);
        parameter.color = Color.WHITE;

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = generator.generateFont(parameter);

        for (int i = 0; i < 3; i++) {
            Json json = new Json();
            String saveInfo;
            GameState loadedData = json.fromJson(GameState.class, saves[i].readString());
            if (loadedData == null || loadedData.getSaveName().isEmpty()) {
                saveInfo = "Empty slot";
            } else {
                saveInfo = loadedData.getSaveName();
            }
            Label label = new Label("save"+(i+1)+"\n"+saveInfo, labelStyle);
            label.setPosition(w / LABEL_HORIZ, h / LABEL_VERT[i]);
            labels[i] = label;
        }
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
