package com.lastcruise.controller;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class GUIController {

  private JFrame mainFrame;
  private final TitleScreen titleScreen = new TitleScreen();


  public GUIController() {
    mainFrame = new JFrame("The Last Cruise");
    mainFrame.setPreferredSize(new Dimension(1500, 800));
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setResizable(false);

    ImageIcon image = new ImageIcon(getClass().getClassLoader().getResource("images/logo.jpg"));
    mainFrame.setIconImage(image.getImage());

    loadTitleScreen();

  }

  private void loadTitleScreen() {
    JLayeredPane menu = titleScreen.getTitleScreen();
    JLabel title = titleScreen.getTitle();
    JButton startButton = titleScreen.getStartBtn();
    JButton loadButton = titleScreen.getLoadBtn();
    mainFrame.add(menu);
    mainFrame.pack();
    mainFrame.setVisible(true);
  }

  public static void main(String[] args) {
    GUIController gui = new GUIController();
  }
}
