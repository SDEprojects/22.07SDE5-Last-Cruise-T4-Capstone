package com.lastcruise.controller;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class GameScreen {

  JPanel mainGamePanel, bgImgPanel, itemsPanel, mapPanel, inventoryPanel, dialogueImgPanel, dialogueTextPanel;
  JLabel dialoguePanelText, dialogueText;
  JLayeredPane dialoguePanel;
  JFrame frame;

  public GameScreen() {
    buildGameScreen();
  }

  public void buildGameScreen() {

    // Create main panel that houses all game screen related containers
    mainGamePanel = new JPanel();
    mainGamePanel.setSize(1500, 800);
    mainGamePanel.setLayout(null);

    // Create ImageIcon for dialogue container
    ImageIcon scroll = new ImageIcon(getClass().getClassLoader().getResource("images/scroll.jpg"));
    // Convert to awt image to resize
    Image image1 = scroll.getImage();
    // Set new dimensions
    Image scrollImg = image1.getScaledInstance(1150, 200, Image.SCALE_SMOOTH);
    // Convert back to ImageIcon
    scroll = new ImageIcon(scrollImg);
    // Create label with scroll image
    JLabel dialogueImg = new JLabel(scroll);

    // Create initial layered pane to display text on top of the scroll image
    dialoguePanel = new JLayeredPane();
    dialoguePanel.setBounds(10, 505, 1150, 275);

    // Create container/ panel to hold scroll img
    dialogueImgPanel = new JPanel();
    dialogueImgPanel.setBounds(10, 50, 1150, 275);

    // Add image to the img panel, set z-index to 0 "bottom"
    dialogueImgPanel.add(dialogueImg);
    dialoguePanel.add(dialogueImgPanel, Integer.valueOf(0));

    // Create new panel to hold text on top of scroll img
    dialogueTextPanel = new JPanel();
    dialogueTextPanel.setOpaque(false);
    dialogueTextPanel.setBounds(15, 50, 900, 125);

    // Create label to actually hold text content
    dialogueText = new JLabel("Hello world");
    // Add text label to text panel/ container
    dialogueTextPanel.add(dialogueText);
    // Add text container to dialogue img panel, set z-index to 1 to sit above scroll img
    dialoguePanel.add(dialogueTextPanel, Integer.valueOf(1));

    // Adding dialogue container to mainGamePanel
    mainGamePanel.add(dialoguePanel);

    // Create items in location display panel
    itemsPanel = new JPanel(new GridLayout(1, 4));
    itemsPanel.setBounds(10, 470, 1150, 75);
    itemsPanel.setBorder(BorderFactory.createLineBorder(Color.black, 4));
//    itemsPanel.setBackground(Color.black);
    // Temporary images
    ImageIcon img1 = new ImageIcon(getClass().getClassLoader().getResource("images/banana.png"));
    ImageIcon img2 = new ImageIcon(getClass().getClassLoader().getResource("images/paddle.png"));
    ImageIcon img3 = new ImageIcon(getClass().getClassLoader().getResource("images/mushroom.png"));
    ImageIcon img4 = new ImageIcon(getClass().getClassLoader().getResource("images/berries.png"));

    JLabel img1Label = new JLabel(img1);
    JLabel img2Label = new JLabel(img2);
    JLabel img3Label = new JLabel(img3);
    JLabel img4Label = new JLabel(img4);
    itemsPanel.add(img1Label);
    itemsPanel.add(img2Label);
    itemsPanel.add(img3Label);
    itemsPanel.add(img4Label);

    // add location items to main panel
    mainGamePanel.add(itemsPanel);

    ImageIcon bgImg = new ImageIcon(getClass().getClassLoader().getResource("images/jungle1.jpg"));
    Image image = bgImg.getImage();
    Image newBgImg = image.getScaledInstance(1150, 455, Image.SCALE_SMOOTH);
    bgImg = new ImageIcon(newBgImg);
    // Image to be used for background image
    JLabel bgImgLabel = new JLabel(bgImg);

    // Create panel to house the location background image
    bgImgPanel = new JPanel();
    bgImgPanel.setBounds(10, 10, 1150, 455);
    bgImgPanel.add(bgImgLabel);

    // Add background image panel to main panel
    mainGamePanel.add(bgImgPanel);

    // Create panel to eventually house map/ compass or player info
    mapPanel = new JPanel();
    mapPanel.setBounds(1180, 10, 300, 300);
    mapPanel.setBackground(Color.black);

    // Add map to main panel
    mainGamePanel.add(mapPanel);

    // Create panel to display players inventory
    inventoryPanel = new JPanel(new GridLayout(4, 3));
    inventoryPanel.setBounds(1180, 320, 300, 430);

    inventoryPanel.setBackground(Color.darkGray);

    //Temporary images
    ImageIcon img5 = new ImageIcon(getClass().getClassLoader().getResource("images/fish.png"));
    ImageIcon img6 = new ImageIcon(getClass().getClassLoader().getResource("images/cloth.png"));
    ImageIcon img7 = new ImageIcon(getClass().getClassLoader().getResource("images/log.png"));
    ImageIcon img8 = new ImageIcon(getClass().getClassLoader().getResource("images/machete.png"));
    ImageIcon img9 = new ImageIcon(getClass().getClassLoader().getResource("images/pipe.png"));
    ImageIcon img10 = new ImageIcon(getClass().getClassLoader().getResource("images/rope.png"));

    JLabel img5Label = new JLabel(img5);
    JLabel img6Label = new JLabel(img6);
    JLabel img7Label = new JLabel(img7);
    JLabel img8Label = new JLabel(img8);
    JLabel img9Label = new JLabel(img9);
    JLabel img10Label = new JLabel(img10);

    inventoryPanel.add(img5Label);
    inventoryPanel.add(img6Label);
    inventoryPanel.add(img7Label);
    inventoryPanel.add(img8Label);
    inventoryPanel.add(img9Label);
    inventoryPanel.add(img10Label);

    // Add inventory panel to main panel
    mainGamePanel.add(inventoryPanel);

  }

  public JFrame getFrame() {
    return frame;
  }

  public JPanel getMainGamePanel() {
    return mainGamePanel;
  }

  public JPanel getBgImgPanel() {
    return bgImgPanel;
  }

  public JPanel getItemsPanel() {
    return itemsPanel;
  }

//  public JPanel getDialoguePanel() {
//    return dialoguePanel;
//  }

  public JPanel getMapPanel() {
    return mapPanel;
  }

  public JPanel getInventoryPanel() {
    return inventoryPanel;
  }

//  public static void main(String[] args) {
//    new GameScreen();
//  }


}

