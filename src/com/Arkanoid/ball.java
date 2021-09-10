package com.Arkanoid;

import org.newdawn.slick.geom.Circle;

public class ball extends Circle {

    Settings gg = new Settings();

    private float centerx;
    private float centery;
    private float moveX = gg.get_speed_factorX();
    private float moveY = gg.get_speed_factorY();
    private boolean BALL_IN_GAME;

    public ball(int x, int y){
        super(x,y,12);
        centerx = x;
        centery = y;
    }

    public boolean get_BALL() {
        return BALL_IN_GAME;
    }

    public void set_BALL(boolean x) {
        BALL_IN_GAME = x;
    }

    public double get_moveX() {
        return moveX;
    }

    public double get_moveY() {
        return moveY;
    }

    public void set_moveX(int x) {
        this.moveX = x;
    }

    public void set_moveY(int y) {
        this.moveY = y;
    }

    public void set_pd_x(int x) {
    }



    public void ball_center() {
        setCenterX(centerx);
        setCenterY(centery);
    }

    public void update() {
        if (BALL_IN_GAME) {
            if ((getCenterX() + 11) + moveX > gg.get_width() || getCenterX() - 11 + moveX < 0) {
                moveX *= -1;
            }
            if (getCenterY() - 11 + moveY < 0) {
                moveY *= -1;
            }
            setCenterX(getCenterX() + moveX);
            setCenterY(getCenterY() + moveY);
        }
    }

}
