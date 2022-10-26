package com.lastcruise.controller;

import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class TitleScreen {

  private JLayeredPane titleScreen;
  private JLabel title;
  private JButton startBtn;
  private JButton loadBtn;
  private JButton settingsBtn;

  public TitleScreen() {
    buildTitleScreen();
  }

  private void buildTitleScreen() {
    titleScreen = new JLayeredPane();

    ImageIcon titleScreenImg = new ImageIcon(getClass().getClassLoader().getResource("images/homescreen.jpg"));
    Image image = titleScreenImg.getImage();
    Image newImage = image.getScaledInstance(1500, 800, Image.SCALE_SMOOTH);
    titleScreenImg = new ImageIcon(newImage);
    JLabel imgSection = new JLabel(titleScreenImg);
    imgSection.setSize(1500,800);

    title = new JLabel("The Last Cruise");
    title.setBounds(500, 90, 700, 150);
    title.setFont(new Font("Serif", Font.PLAIN, 85));

    startBtn = new JButton("Start");
    startBtn.setBounds(500, 575, 225, 75);

    loadBtn = new JButton("Load Game");
    loadBtn.setBounds(825, 575, 225, 75);

    ImageIcon settings = new ImageIcon(getClass().getClassLoader().getResource("images/settings.png"));
    settingsBtn = new JButton(settings);
    settingsBtn.setBounds(1400, 15, 100, 100);
    settingsBtn.setOpaque(false);
    settingsBtn.setContentAreaFilled(false);
    settingsBtn.setBorderPainted(false);
    settingsBtn.setBorder(null);

    titleScreen.add(imgSection, Integer.valueOf(0));
    titleScreen.add(title, Integer.valueOf(2));
    titleScreen.add(startBtn, Integer.valueOf(2));
    titleScreen.add(loadBtn, Integer.valueOf(2));
    titleScreen.add(settingsBtn, Integer.valueOf(2));
  }

  public JLayeredPane getTitleScreen() {
    return titleScreen;
  }

  public JButton getStartBtn() {
    return startBtn;
  }

  public JButton getLoadBtn() {
    return loadBtn;
  }

  public JLabel getTitle() {
    return title;
  }
}
