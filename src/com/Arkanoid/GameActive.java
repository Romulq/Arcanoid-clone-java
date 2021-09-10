package com.Arkanoid;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Input;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameActive extends BasicGameState {

    private Image bg = null;
    private Image paddle = null;
    private Image balltexture = null;
    private Image heart = null;

    private boolean left,right,space;

    private int paddleX,paddleY;
    private paddle pd;

    private int ballX,ballY;
    private ball ball;

    private Settings gg;
    private scoreboard sb;

    private ArrayList<Rectangle> bricks;
    private ArrayList<Image> textures;

    Random random = new Random();
    private TrueTypeFont ff;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        paddleX = gameContainer.getWidth() / 2 - 52;
        paddleY = gameContainer.getHeight() - 34;
        ballX = gameContainer.getWidth() / 2;
        ballY = gameContainer.getHeight() - 45;

        pd = new paddle(paddleX, paddleY);
        ball = new ball(ballX, ballY);
        gg = new Settings();
        sb = new scoreboard();
        bricks = new ArrayList<Rectangle>();
        textures = new ArrayList<Image>();

        ff = new TrueTypeFont(new java.awt.Font("Alien Encounters", java.awt.Font.BOLD, 36), true);

        bg = new Image("img/bg 640x856.jpg");
        paddle = new Image("img/paddleBlu.png");
        balltexture = new Image("img/ballBlue.png");
        heart = new Image("img/32x32.png");

        create_bricks();
    }

    private void create_bricks() throws SlickException {
        for (int j = 0; j < 9; j++) {
            for (int k = 0; k < 6; k++) {
                bricks.add(new Rectangle(50 + (k * 95),47+(j*48), 64, 32));
                textures.add(new Image(getName()));
            }
        }
    }

    private String getName() {
        int i = random.nextInt(6);
        return "img/brick_" + i + ".png";
    }

    private void high_score() {
        if (sb.getScore() > sb.getHigh_score()) {
            sb.setHigh_score(sb.getScore());;
        }
    }

    private int readFile() {
        int high_score = 0;
        try{
            Scanner sc = new Scanner(new File("score.txt"));
            String score = sc.nextLine();
            sc.close();
            high_score = Integer.parseInt(score);

        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return high_score;
    }

    private void writeFile(int x) throws IOException {
        Writer wr = new FileWriter("score.txt");
        wr.write(String.valueOf(x));
        wr.flush();
        wr.close();
    }

    private boolean check_high_score() {
        int score = sb.getHigh_score();
        int check_score = readFile();
        if (score > check_score) {
            return true;
        }else {
            return false;
        }
    }

    private void save_high_score() throws IOException {
        if(check_high_score()) {
            int score = sb.getHigh_score();
            writeFile(score);
        }
    }

    private void check_ball_bottom(StateBasedGame sbg) throws InterruptedException, IOException, SlickException {
        if(ball.getY() + ball.getHeight() >= gg.get_height()) {
            ball.set_BALL(false);
            ball.set_moveX((int) gg.get_speed_factorX());
            ball.set_moveY((int) gg.get_speed_factorY());
            ball_out(sbg);
        }
    }

    private void ball_out(StateBasedGame sbg) throws InterruptedException, IOException, SlickException {
        sb.setHeart(sb.getHeart() - 1);
        ball.ball_center();
        pd.paddle_center();
        if (sb.getHeart() > 0) {
            Thread.sleep(500);
        }else {
            bricks.clear();
            save_high_score();
            Thread.sleep(300);
            sb.setHeart(3);
            sb.setScore(0);
            ball.set_BALL(false);
            sbg.enterState(1);
            create_bricks();
        }
    }

    private void control() {
        if(left) {
            pd.set_left(true);
        }else {
            pd.set_left(false);
        }
        if(right) {
            pd.set_right(true);
        }else {
            pd.set_right(false);
        }
        if(space) {
            if (!ball.get_BALL()) {
                if(!pd.get_left() && !pd.get_right()) {
                    ball.set_moveX(0);
                }
                if(pd.get_left()) {
                    ball.set_moveX((int)(ball.get_moveX()*-1));
                }
                ball.set_BALL(true);
            }
        }
    }

    private void collisions(StateBasedGame sbg) throws InterruptedException, SlickException, IOException {
        float blx = ball.getX();  float bly = ball.getY();  float blw = ball.getWidth();  float blh = ball.getHeight();
        float pdy = pd.getY();  float pdx = pd.getX();  float pdw = pd.getX()+104;
        boolean collidePaddle = bly+blh - pdy >=4.9 && pdx - (blx+blw) <=4 && blx - pdw <=4;

        if(bricks.size() == 0) {
            Thread.sleep(300);
            ball.ball_center();
            pd.paddle_center();
            ball.set_moveX((int) gg.get_speed_factorX());
            ball.set_moveY((int) gg.get_speed_factorY());
            save_high_score();
            sb.setHeart(3);
            sb.setScore(0);
            ball.set_BALL(false);
            sbg.enterState(1);
            create_bricks();
        }

        for (int j = 0; j< bricks.size(); j++) {
            boolean collide = ball.intersects(bricks.get(j));
            float brx = bricks.get(j).getX();   float bry = bricks.get(j).getY();
            float brw = bricks.get(j).getWidth();   float brh = bricks.get(j).getHeight();
            if(collide) {
                if(bry+brh - bly == 2) {
                    ball.set_moveY((int) (ball.get_moveY() * -1));
                }
                if(bly+blh - bry <= 2) {
                    ball.set_moveY((int) (ball.get_moveY() * -1));
                }
                if(brx+brw - blx <= 4) {
                    ball.set_moveX((int) (ball.get_moveX() * -1));
                }
                if(blx+blw - brx <= 4) {
                    ball.set_moveX((int) (ball.get_moveX() * -1));
                }
                bricks.remove(j);
                textures.remove(j);

                sb.setScore(sb.getScore() + gg.get_points());
                high_score();

            }

        }

        if(collidePaddle) {
            if ((int) (bly+blh - pdy) == 4) {
                ball.set_moveY((int) (ball.get_moveY() * -1));
            }else {
                ball.set_moveX((int) (ball.get_moveX() * -1));
            }
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame sbg, int i) throws SlickException {
        left = gameContainer.getInput().isKeyDown(Input.KEY_LEFT);
        right = gameContainer.getInput().isKeyDown(Input.KEY_RIGHT);
        space = gameContainer.getInput().isKeyDown(Input.KEY_SPACE);
        control();

        try {
            collisions(sbg);
            check_ball_bottom(sbg);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

        pd.update();
        if (!ball.get_BALL()) {
            ball.setCenterX(pd.getX()+52);
        }
        ball.update();

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) {
        bg.draw(0, 0);

        for(int i = 0; i < sb.getHeart(); i++) {
            heart.draw(5+(i*32), gameContainer.getHeight() - 36); }

        paddle.draw(pd.getX(), pd.getY());
        g.texture(ball, balltexture, 1, 1, true);

        for (int j=0; j < bricks.size(); j++){
            Rectangle r = bricks.get(j);
            Image texture = textures.get(j);
            g.texture(r,texture, 1, 1, true);
        }

        g.setColor(new Color(122, 1, 116));
        g.setFont(ff);
        g.drawString(String.valueOf(sb.getHigh_score()), 275, 5);
        g.drawString(String.valueOf(sb.getScore()), 10, 5);
        g.setColor(Color.white);
    }

    @Override
    public int getID() {
        return 2;
    }
}
