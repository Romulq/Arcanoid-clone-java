package com.Arkanoid;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class window {
    public void init() throws IOException {
        JFrame frame = new JFrame("Rules");
        JLabel label = new JLabel();

        frame.getContentPane().add(label);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(650,350));
        frame.pack();
        frame.setVisible(true);

        label.setIcon(new ImageIcon(ImageIO.read(new File("img/rule.png"))));
    }
}
