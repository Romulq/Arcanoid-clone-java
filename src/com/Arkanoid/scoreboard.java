package com.Arkanoid;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class scoreboard {

    Settings gg = new Settings();

    private int score = 0;
    private int high_score = readFile();
    private int heart = gg.get_heart();

    void setScore(int x) {
        this.score = x;
    }

    void setHigh_score(int x) {
        this.high_score = x;
    }

    void setHeart(int x) {
        this.heart = x;
    }

    int getScore() {
        return score;
    }

    int getHigh_score() {
        return high_score;
    }

    int getHeart() {
        return heart;
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
}
