package com.elevator_project.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class LoadMenu {

    private final Group group;
    private final FileHandle[] loads;
    private final Image[] cells;
    private final TextureAtlas atlas;
    private Image back;
    private final float w;
    private final float h;

    public LoadMenu () {
        loads = new FileHandle[4];
        cells = new Image[4];
        atlas = GameManager.getAtlasses().getExtraElementsAtlas();
        group = new Group();
        w = App.getDimensions()[0];
        h = App.getDimensions()[1];
        initLoads();
        initCells();
        initBack();
    }

    private void initLoads () {
        loads[0] = Gdx.files.local("saves/save1.sav");
    }

    private void initGroup () {
        group.addActor(back);
        group.addActor(cells[0]);
    }

    private void initBack () {
        final float BACK_RESIZE = 158f;

        back = new Image(atlas.createSprite("Back"));
        ImageProcessing.process(back, BACK_RESIZE, w, h);
    }

    private void initCells () {
        final float CELL_RESIZE = 50f;
        final float CELL_HORIZ = 2f;
        final float CELL_1_VERT = 1.5f;

        Image cell = new Image(atlas.createSprite("SaveCell"));
        ImageProcessing.process(cell, CELL_RESIZE, CELL_HORIZ, CELL_1_VERT);
        cell.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                SaveManager.loadUser(loads[0]);
            }
        });

        cells[0] = cell;
    }

    public void render () {
        initGroup();
        App.getStage().addActor(group);
    }

    public void dispose () {
        group.remove();
    }
}
