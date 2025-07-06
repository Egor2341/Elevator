package com.elevator_project;

public interface Floor {
    public void render();
    public void move(int from, int to);
    public void dispose();
}
