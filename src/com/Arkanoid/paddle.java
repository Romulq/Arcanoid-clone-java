package com.Arkanoid;

public class paddle {

    Settings gg = new Settings();

    private int x,y,center;
    private double move = gg.get_paddle_move();

    private boolean moving_right, moving_left;


    public paddle(int x, int y) {
        this.x = x;
        this.y = y;
        this.center = x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean get_right() {
        return moving_right;
    }

    public boolean get_left() {
        return moving_left;
    }

    public void set_right(boolean x) {
        this.moving_right = x;
    }

    public void set_left(boolean x) {
        this.moving_left = x;
    }

    public void paddle_center() {
        x = center;
    }

    public void update() {
        if (moving_right && ((x + 122) <= gg.get_width())) {
            x += move;
        }
        if (moving_left && (x >= 2)) {
            x -= move;
        }
    }
}
