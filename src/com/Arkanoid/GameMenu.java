package com.Arkanoid;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import java.io.IOException;

public class GameMenu extends BasicGameState {

    private Image bg = null;
    private Image buttontexture = null;

    private TrueTypeFont ff;

    private Rectangle button1;
    private Rectangle button2;
    private Rectangle field;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        bg = new Image("img/bg 640x856.jpg");

        ff = new TrueTypeFont(new java.awt.Font("Comic Sans MS", java.awt.Font.PLAIN, 32), true);

        buttontexture = new Image("img/buttonDefault.png");
        button1 = new Rectangle(320-95, 425, 190,50 );
        button2 = new Rectangle(320-95, 500, 190,50 );

        field = new Rectangle(0, 0, 640, 850);
    }

    private void play_button(StateBasedGame sbg){
        sbg.enterState(2);
    }

    private void rules_button() throws IOException {
        window wd = new window();
        wd.init();
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame sbg, int i) {
        if(gameContainer.getInput().isMouseButtonDown(0)) {
            int mouseX = gameContainer.getInput().getMouseX();
            int mouseY = gameContainer.getInput().getMouseY();
            if(mouseX > button1.getX() && mouseX < button1.getX()+button1.getWidth() && mouseY > button1.getY() && mouseY < button1.getY()+button1.getHeight()) {
                play_button(sbg);
            }
            if(mouseX > button2.getX() && mouseX < button2.getX()+button2.getWidth() && mouseY > button2.getY() && mouseY < button2.getY()+button2.getHeight()) {
                try {
                    rules_button();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        bg.draw(0, 0);

        g.setColor(new Color(63, 55, 173, 180));
        g.fill(field);
        g.setColor(Color.white);

        g.texture(button1, buttontexture, 1, 1, true);
        g.texture(button2, buttontexture, 1, 1, true);
        g.drawImage(new Image("img/name.png"), 20, 100);

        g.setColor(new Color(122, 1, 116));
        g.setFont(ff);
        g.drawString("Game", 278, 425);
        g.drawString("Rules", 278, 500);
        g.setColor(Color.white);

    }
    @Override
    public int getID() {
        return 1;
    }
}
