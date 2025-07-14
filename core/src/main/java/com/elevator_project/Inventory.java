package com.elevator_project;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final TextureAtlas atlas;
    private final List<Image> cells;
    private final Sprite[] cellsSprites;
    private final Group mainGroup;
    @Getter
    private int chosen;
    private final List<Image> objects;
    private final int[] inventory;

    public Inventory () {
        atlas = GameManager.getAtlasses().getExtraElementsAtlas();
        cells = new ArrayList<>();
        mainGroup = new Group();
        cellsSprites = new Sprite[2];
        chosen = -1;
        objects = new ArrayList<>();
        inventory = new int[6];
        initInventory();
    }

    private void initInventory() {
        final float CELL_RESIZE_FACTOR = 500f;
        final float[] CELL_VERT_FACTOR = new float[] {1.15f, 1.3f, 1.5f, 1.78f, 2.18f, 2.78f};
        final float CELL_HORIZ_FACTOR = 1.08f;

        cellsSprites[0] = atlas.createSprite("Inventory", 1);
        cellsSprites[1] = atlas.createSprite("Inventory", 2);

        for (int i = 0; i < 6; i++) {
            Image cell = new Image(cellsSprites[0]);
            ImageProcessing.process(cell, CELL_RESIZE_FACTOR, CELL_HORIZ_FACTOR, CELL_VERT_FACTOR[i]);
            objects.add(initObject(i));
            cells.add(cell);
        }
    }

    private void initGroup () {
        for (int i = 0; i < cells.size(); i++) {
            mainGroup.addActor(cells.get(i));
            mainGroup.addActor(objects.get(i));
        }
    }

    public void render () {
        initGroup();
        App.getStage().addActor(mainGroup);
    }

    public void dispose () {
        mainGroup.remove();
    }

    public int addObject (Sprite object, float resize) {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == 0) {
                objects.get(i).setDrawable(new SpriteDrawable(
                    object
                ));
                objects.get(i).setSize(object.getWidth() * App.getDimensions()[0] / resize,
                    object.getHeight() * App.getDimensions()[0] / resize);
                inventory[i] = 1;
                return i;
            }
        }
        return -1;
    }

    public void removeObject (int index) {
        inventory[index] = 0;
        objects.get(index).setDrawable(new SpriteDrawable(atlas.createSprite("Inventory", 3)));
        cells.get(index).setDrawable(new SpriteDrawable(cellsSprites[0]));
    }

    private Image initObject (int i) {
        final float OBJECT_HORIZ_FACTOR = 1.08f;
        final float[] OBJECT_VERT_FACTOR = new float[] {1.15f, 1.3f, 1.5f, 1.78f, 2.18f, 2.78f};
        Image object = new Image();
        ImageProcessing.process(object, 1, OBJECT_HORIZ_FACTOR, OBJECT_VERT_FACTOR[i]);
        object.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                if (inventory[objects.indexOf(object)] != 1){
                    return;
                }
                Image cell = cells.get(objects.indexOf(object));
                if (chosen == cells.indexOf(cell)) {
                    chosen = -1;
                    cell.setDrawable(new SpriteDrawable(cellsSprites[0]));
                } else {
                    if (chosen != -1) {
                        cells.get(chosen).setDrawable(new SpriteDrawable(cellsSprites[0]));
                    }
                    chosen = cells.indexOf(cell);
                    cell.setDrawable(new SpriteDrawable(cellsSprites[1]));
                }
            }
        });
        return object;
    }
}
