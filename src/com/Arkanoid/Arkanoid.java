package com.Arkanoid;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Arkanoid extends StateBasedGame {

    public Arkanoid(String title) {
        super(title);
    }

    public static void main(String[] args) {

        try
        {
            Settings gg = new Settings();
            AppGameContainer app;
            app = new AppGameContainer(new Arkanoid("Arkanoid"));
            app.setDisplayMode(gg.get_width(), gg.get_height(), false);
            app.setAlwaysRender(true);
            app.setShowFPS(false);
            app.setTargetFrameRate(60);
            app.start();
        }
        catch (SlickException ex)
        {
            Logger.getLogger(Arkanoid.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initStatesList(GameContainer gameContainer) {
        this.addState(new GameMenu());
        this.addState(new GameActive());
    }

}
