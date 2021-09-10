package com.Arkanoid;

public class Settings {

    private int WIDTH = 640;
    private int HEIGHT = 850;
    private float speed_factorX = 4;
    private float speed_factorY = -4;
    private double paddle_move = 4.5;
    private int heart = 3;
    private int points = 200;

    public int get_points() {
        return points;
    }

    public int get_heart() {
        return heart;
    }

    public int get_width() {
        return WIDTH;
    }

    public int get_height() {
        return HEIGHT;
    }

    public float get_speed_factorX() {
        return speed_factorX;
    }

    public float get_speed_factorY() {
        return speed_factorY;
    }

    public double get_paddle_move() { return paddle_move; }

}
