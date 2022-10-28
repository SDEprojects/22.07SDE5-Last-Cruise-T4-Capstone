package com.lastcruise.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class MainScreen {

  public static void main(String[] args) {

    JLayeredPane layeredPane = new JLayeredPane();
    layeredPane.setSize(1200, 1000);


    ImageIcon backgroundImage = new ImageIcon(MainScreen.class.getClassLoader().getResource("images/beach1.jpg"));
    JLabel bgImage = new JLabel(backgroundImage);
    bgImage.setBounds(10, 20, 750, 350);



    JPanel redPanel = new JPanel();
    redPanel.setBackground(Color.red);
    redPanel.setBounds(10, 550, 750, 300);

    JPanel orangePanel = new JPanel();
    orangePanel.setOpaque(true);
    orangePanel.setBackground(Color.ORANGE);
    orangePanel.setBounds(810, 30, 330, 400);
    orangePanel.setLayout(new GridLayout(2, 1));
    orangePanel.add(new JButton(new ImageIcon(MainScreen.class.getClassLoader().getResource("images/health.jpg"))));
    orangePanel.add(new JButton());

    JPanel magentaPanel = new JPanel();
    magentaPanel.setOpaque(true);
    magentaPanel.setBackground(Color.magenta);
    magentaPanel.setBounds(810, 440, 330, 400);
    magentaPanel.setLayout(new GridLayout(4, 4));

    magentaPanel.add(new JButton(new ImageIcon(MainScreen.class.getClassLoader().getResource("images/banana.png"))));
    magentaPanel.add(new JButton(new ImageIcon(MainScreen.class.getClassLoader().getResource("images/berries.png"))));
    magentaPanel.add(new JButton(new ImageIcon(MainScreen.class.getClassLoader().getResource("images/cloth.png"))));
    magentaPanel.add(new JButton(new ImageIcon(MainScreen.class.getClassLoader().getResource("images/fish.png"))));
    magentaPanel.add(new JButton(new ImageIcon(MainScreen.class.getClassLoader().getResource("images/log.png"))));
    magentaPanel.add(new JButton(new ImageIcon(MainScreen.class.getClassLoader().getResource("images/machete.png"))));
    magentaPanel.add(new JButton(new ImageIcon(MainScreen.class.getClassLoader().getResource("images/mushroom.png"))));
    magentaPanel.add(new JButton(new ImageIcon(MainScreen.class.getClassLoader().getResource("images/paddle.png"))));
    magentaPanel.add(new JButton(new ImageIcon(MainScreen.class.getClassLoader().getResource("images/pipe.png"))));
    magentaPanel.add(new JButton(new ImageIcon(MainScreen.class.getClassLoader().getResource("images/rope.png"))));


    JPanel greenPanel = new JPanel();
    greenPanel.setBackground(Color.GREEN);
    greenPanel.setBounds(800, 20, 350, 830);


    JPanel bluePanel = new JPanel();
    bluePanel.setBackground(Color.BLUE);
    bluePanel.setBounds(10, 20, 750, 350);


    JPanel pinkPanel = new JPanel();
    pinkPanel.setBackground(Color.PINK);
    pinkPanel.setBounds(10, 380, 750, 160);
    pinkPanel.setLayout(new GridLayout(1, 4));

    pinkPanel.add(new JButton(new ImageIcon(MainScreen.class.getClassLoader().getResource("images/banana.png"))));
    pinkPanel.add(new JButton(new ImageIcon(MainScreen.class.getClassLoader().getResource("images/berries.png"))));
    pinkPanel.add(new JButton(new ImageIcon(MainScreen.class.getClassLoader().getResource("images/cloth.png"))));
    pinkPanel.add(new JButton(new ImageIcon(MainScreen.class.getClassLoader().getResource("images/fish.png"))));

    JFrame frame = new JFrame();
    frame.setLayout(null);
    frame.setSize(1200, 1000);
    frame.setVisible(true);
    frame.setResizable(false);


    frame.add(layeredPane);
    bluePanel.add(bgImage);
    frame.add(bluePanel);
    frame.add(redPanel);
    layeredPane.add(orangePanel);
    layeredPane.add(magentaPanel);
    layeredPane.add(greenPanel);
    frame.add(pinkPanel);
    frame.setVisible(true);



  }

}
