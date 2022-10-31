package com.lastcruise.controller;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class MainScreen extends JFrame{

  private JPanel backgroundPanel, availableItemsPanel, inventoryPanel, statusPanel;
  private JScrollPane gameDialogScrollPane;
  private TextArea dialogTa;

  public MainScreen() {
    initialize();
  }

  public void initialize() {

    ImageIcon backgroundImage = new ImageIcon(MainScreen.class.getClassLoader().getResource("images/beach1.jpg"));
    JLabel bgImage = new JLabel(backgroundImage);
    bgImage.setBounds(10, 20, 750, 350);

    dialogTa = new TextArea();
    gameDialogScrollPane = new JScrollPane(dialogTa);
    gameDialogScrollPane.setBounds(10, 550, 750, 300);

    statusPanel = new JPanel();
    statusPanel.setOpaque(true);
    statusPanel.setBackground(Color.ORANGE);
    statusPanel.setBounds(810, 30, 330, 400);
    statusPanel.setLayout(new GridLayout(2, 1));
    statusPanel.add(new JButton(new ImageIcon(MainScreen.class.getClassLoader().getResource("images/health.jpg"))));
    statusPanel.add(new JButton());

    inventoryPanel = new JPanel();
    inventoryPanel.setOpaque(true);
    inventoryPanel.setBackground(Color.magenta);
    inventoryPanel.setBounds(810, 440, 330, 400);
    inventoryPanel.setLayout(new GridLayout(4, 4));

    inventoryPanel.add(new JButton(new ImageIcon(MainScreen.class.getClassLoader().getResource("images/banana.png"))));
    inventoryPanel.add(new JButton(new ImageIcon(MainScreen.class.getClassLoader().getResource("images/berries.png"))));
    inventoryPanel.add(new JButton(new ImageIcon(MainScreen.class.getClassLoader().getResource("images/cloth.png"))));
    inventoryPanel.add(new JButton(new ImageIcon(MainScreen.class.getClassLoader().getResource("images/fish.png"))));
    inventoryPanel.add(new JButton(new ImageIcon(MainScreen.class.getClassLoader().getResource("images/log.png"))));
    inventoryPanel.add(new JButton(new ImageIcon(MainScreen.class.getClassLoader().getResource("images/machete.png"))));
    inventoryPanel.add(new JButton(new ImageIcon(MainScreen.class.getClassLoader().getResource("images/mushroom.png"))));
    inventoryPanel.add(new JButton(new ImageIcon(MainScreen.class.getClassLoader().getResource("images/paddle.png"))));
    inventoryPanel.add(new JButton(new ImageIcon(MainScreen.class.getClassLoader().getResource("images/pipe.png"))));
    inventoryPanel.add(new JButton(new ImageIcon(MainScreen.class.getClassLoader().getResource("images/rope.png"))));

    backgroundPanel = new JPanel();
    backgroundPanel.setBackground(Color.BLUE);
    backgroundPanel.setBounds(10, 20, 750, 350);

    availableItemsPanel = new JPanel();
    availableItemsPanel.setBackground(Color.PINK);
    availableItemsPanel.setBounds(10, 380, 750, 160);
    availableItemsPanel.setLayout(new GridLayout(1, 4));

    availableItemsPanel.add(new JButton(new ImageIcon(MainScreen.class.getClassLoader().getResource("images/banana.png"))));
    availableItemsPanel.add(new JButton(new ImageIcon(MainScreen.class.getClassLoader().getResource("images/berries.png"))));
    availableItemsPanel.add(new JButton(new ImageIcon(MainScreen.class.getClassLoader().getResource("images/cloth.png"))));
    availableItemsPanel.add(new JButton(new ImageIcon(MainScreen.class.getClassLoader().getResource("images/fish.png"))));

    JPanel actionPanel = new JPanel();
    actionPanel.setBounds(10, 860,  750, 100);
    actionPanel.setBackground(Color.red);

    this.setLayout(null);
    this.setSize(1200, 1500);
    this.setVisible(true);
    this.setResizable(false);

    backgroundPanel.add(bgImage);
    this.add(backgroundPanel);
    this.add(gameDialogScrollPane);
    this.add(actionPanel);
    this.add(statusPanel);
    this.add(inventoryPanel);
    this.add(availableItemsPanel);
    this.add(availableItemsPanel);
    this.setVisible(true);
  }

  public JPanel getBackgroundPanel() {
    return backgroundPanel;
  }

  public void setBackgroundPanel(JPanel backgroundPanel) {
    this.backgroundPanel = backgroundPanel;
  }

  public JPanel getAvailableItemsPanel() {
    return availableItemsPanel;
  }

  public void setAvailableItemsPanel(JPanel availableItemsPanel) {
    this.availableItemsPanel = availableItemsPanel;
  }

  public JPanel getInventoryPanel() {
    return inventoryPanel;
  }

  public void setInventoryPanel(JPanel inventoryPanel) {
    this.inventoryPanel = inventoryPanel;
  }

  public JPanel getStatusPanel() {
    return statusPanel;
  }

  public void setStatusPanel(JPanel statusPanel) {
    this.statusPanel = statusPanel;
  }

  public JScrollPane getGameDialogScrollPane() {
    return gameDialogScrollPane;
  }

  public void setGameDialogScrollPane(JScrollPane gameDialogScrollPane) {
    this.gameDialogScrollPane = gameDialogScrollPane;
  }

  public TextArea getDialogTa() {
    return dialogTa;
  }

  public void setDialogTa(TextArea dialogTa) {
    this.dialogTa = dialogTa;
  }
}
