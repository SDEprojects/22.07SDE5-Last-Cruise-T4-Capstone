package com.lastcruise.view;

import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class TitleScreen {

  private JLayeredPane titleScreen;
  private JLabel title;
  private JButton startBtn, helpBtn;
  private JButton loadBtn;
  private JButton settingsBtn;
  private JButton exitButton;
  private ClassLoader loader = getClass().getClassLoader();

  public TitleScreen() {
    buildTitleScreen();
  }

  private void buildTitleScreen() {
    titleScreen = new JLayeredPane();

    ImageIcon titleScreenImg = new ImageIcon(loader.getResource("images/homescreen.jpg"));
    Image image = titleScreenImg.getImage();
    Image newImage = image.getScaledInstance(1500, 800, Image.SCALE_SMOOTH);
    titleScreenImg = new ImageIcon(newImage);
    JLabel imgSection = new JLabel(titleScreenImg);
    imgSection.setSize(1500,800);

    title = new JLabel("The Last Cruise");
    title.setBounds(500, 90, 700, 150);
    title.setFont(new Font("Serif", Font.PLAIN, 85));

    startBtn = new JButton("New Game");
    startBtn.setBounds(500, 575, 225, 75);
    startBtn.setFont(new Font("Monospace", Font.PLAIN, 20));

    loadBtn = new JButton("Load Game");
    loadBtn.setBounds(825, 575, 225, 75);
    loadBtn.setFont(new Font("Monospace", Font.PLAIN, 20));

    exitButton = new JButton("Exit");
    exitButton.setBounds(724,675,100,50);
    exitButton.addActionListener(e -> System.exit(0));
    exitButton.setFont(new Font("Monospace", Font.PLAIN, 15));

    ImageIcon settings = new ImageIcon(loader.getResource("images/settings.png"));
    settingsBtn = new JButton(settings);
    settingsBtn.setBounds(1400, 15, 65, 65);
    settingsBtn.setOpaque(false);
    settingsBtn.setFocusPainted(false);
    settingsBtn.setContentAreaFilled(false);
    settingsBtn.setBorderPainted(false);
    settingsBtn.setBorder(null);
    settingsBtn.addActionListener(e -> new SettingsScreen());

    helpBtn = new JButton(new ImageIcon(loader.getResource("images/help.png")));
    helpBtn.setBounds(1400, 100, 65, 65);
    helpBtn.setOpaque(false);
    helpBtn.setFocusPainted(false);
    helpBtn.setContentAreaFilled(false);
    helpBtn.setBorderPainted(false);
    helpBtn.setBorder(null);
    helpBtn.addActionListener(e -> new HelpScreen());

    titleScreen.add(imgSection, Integer.valueOf(0));
    titleScreen.add(title, Integer.valueOf(2));
    titleScreen.add(startBtn, Integer.valueOf(2));
    titleScreen.add(loadBtn, Integer.valueOf(2));
    titleScreen.add(exitButton, Integer.valueOf(2));
    titleScreen.add(settingsBtn, Integer.valueOf(2));
    titleScreen.add(helpBtn, Integer.valueOf(2));
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
}
