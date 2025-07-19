package com.elevator_project.independent_elements;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.elevator_project.game.*;
import lombok.Getter;
import lombok.Setter;

public class InsulatingTape implements InventoryObject {
    private final Sprite sprite;
    private final float spriteResize;
    private final Group mainGroup;
    @Getter
    @Setter
    private int indexInInventory;

    public InsulatingTape () {
        mainGroup = new Group();
        indexInInventory = -1;
        float horiz = 2.5f;
        float vert = 2.2f;
        float resize = 200f;
        spriteResize = 720f;
        sprite = GameManager.getAtlasses().getExtraElementsAtlas().createSprite("InsulatingTape");
        Image insulatingTape = new Image(sprite);
        ImageProcessing.process(insulatingTape, resize, horiz, vert);
        insulatingTape.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {

                addToInventory();
                GameManager.getGameState().getObjectsInInventory().set(indexInInventory, "InsulatingTape");
                SaveManager.saveAutosave();
            }
        });
        mainGroup.addActor(insulatingTape);
    }

    public void addToInventory () {
        indexInInventory = GameManager.getInventory().addObject(sprite, spriteResize);
        dispose();
    }

    public void render () {
        App.getStage().addActor(mainGroup);
    }

    public void dispose () {
        mainGroup.remove();
    }

    public void hide () {
        mainGroup.setVisible(false);
    }

    public void show () {
        mainGroup.setVisible(true);
    }
}
