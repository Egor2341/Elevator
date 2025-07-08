package com.elevator_project;


import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ImageProcessing {

    public static void process (Image image, float resize, float horiz, float vert) {
        float w = App.getDimensions()[0];
        float h = App.getDimensions()[1];
        image.setSize(image.getWidth() * w / resize,
            image.getHeight() * w / resize);
        image.setPosition(w / horiz, h / vert);
    }
}
