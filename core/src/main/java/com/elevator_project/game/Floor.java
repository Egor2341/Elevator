package com.elevator_project.game;

public interface Floor {
    public void render();
    public void right();
    public void left();
    public void forward();
    public void back();
    public void dispose();
    public void hide();
    public void show();
}
