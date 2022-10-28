package com.lastcruise.controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class GUIController implements ActionListener {

  private JFrame mainFrame;
  private final TitleScreen titleScreen = new TitleScreen();
  private GameScreen game = new GameScreen();

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
    mainFrame.add(menu);
    mainFrame.pack();
    // Set location to null after 'pack()'
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setVisible(true);
    titleScreen.getStartBtn().addActionListener(this);
  }

  public JFrame getMainFrame() {
    return mainFrame;
  }

  public TitleScreen getTitleScreen() {
    return titleScreen;
  }


  public static void main(String[] args) {
    GUIController gui = new GUIController();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    titleScreen.getTitleScreen().setVisible(false);
    JPanel gamePanel = game.getMainGamePanel();
    mainFrame.add(gamePanel);
    mainFrame.pack();
    mainFrame.setVisible(true);
  }
}
