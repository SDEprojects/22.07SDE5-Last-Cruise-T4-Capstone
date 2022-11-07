package com.lastcruise.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.function.Consumer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GameScreen {

  private JPanel mainGamePanel, itemsPanel, inventoryPanel, dialogueImgPanel, bgImgPanel;
  private JLabel locationLabel, staminaLabel, bgImgLabel, playerStatusImg;
  private JLayeredPane dialoguePanel, backgroundImgPane, playerStatusPane;
  private ImageIcon bgImg, stoneImg;
  private JTextArea dialogueTextArea;

  private JButton sleepBtn, gameSettingsBtn, saveGameBtn, helpBtn, craftBtn, northBtn, southBtn, eastBtn, westBtn;

  private Consumer<String[]> actionCallback;
  private ClassLoader loader = getClass().getClassLoader();



  public GameScreen() {
    buildGameScreen();
  }

  public void buildGameScreen() {

    // Create main panel that houses all game screen related containers
    mainGamePanel = new JPanel();
    mainGamePanel.setSize(1500, 800);
    mainGamePanel.setLayout(null);

    // Create ImageIcon for dialogue container
    ImageIcon scroll = new ImageIcon(loader.getResource("images/scroll.png"));
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

    // Create TextArea to overlay the scroll image
    dialogueTextArea = new JTextArea();
    dialogueTextArea.setBounds(70, 70, 1103, 175);
    dialogueTextArea.setOpaque(false);
    dialogueTextArea.setEditable(false);
    dialogueTextArea.setLineWrap(true);
    dialogueTextArea.setFont(new Font("Serif", Font.BOLD, 16));
    dialoguePanel.add(dialogueTextArea, Integer.valueOf(2));

    // Create items in location display panel
    itemsPanel = new JPanel(new GridLayout(1, 4));
    itemsPanel.setBounds(17, 470, 1145, 75);
    itemsPanel.setBackground(Color.LIGHT_GRAY);
    itemsPanel.setBorder(BorderFactory.createLineBorder(Color.black, 4));

    // Image to be used for background image
    bgImg = new ImageIcon(loader.getResource("images/BEACH.jpg"));
    Image image = bgImg.getImage();
    Image newBgImg = image.getScaledInstance(1150, 455, Image.SCALE_SMOOTH);
    bgImg = new ImageIcon(newBgImg);
    bgImgLabel = new JLabel(bgImg);

    // Create pane to house the location background image and directional arrows
    backgroundImgPane = new JLayeredPane();
    backgroundImgPane.setBounds(10, 10, 1150, 455);

    // Add background image to panel
    bgImgPanel = new JPanel();
    bgImgPanel.setBounds(10, 10, 1150, 455);
    bgImgPanel.add(bgImgLabel);
    backgroundImgPane.add(bgImgPanel, Integer.valueOf(0));

    // Create pane to house stone background, stamina, current location, sleep and eat btns
    playerStatusPane = new JLayeredPane();
    playerStatusPane.setBounds(1180,90,300,230);

    // Create and resize stone img
    stoneImg = new ImageIcon(loader.getResource("images/stone.png"));
    Image stone = stoneImg.getImage();
    Image resizeStone = stone.getScaledInstance(300,230, Image.SCALE_SMOOTH);
    stoneImg= new ImageIcon(resizeStone);

    // Label that houses stone img
    playerStatusImg = new JLabel(stoneImg);
    playerStatusImg.setSize(300,230);
    playerStatusPane.add(playerStatusImg, Integer.valueOf(0));

    // Create label to display current location; dynamically updated in GUIController
    locationLabel = new JLabel();
    locationLabel.setBounds(30, 10, 300, 50);
    locationLabel.setFont(new Font("Serif", Font.BOLD, 18));
    locationLabel.setForeground(Color.white);
    playerStatusPane.add(locationLabel, Integer.valueOf(1));

    // Create label to display players stamina; dynamically updated in GUIController
    staminaLabel = new JLabel();
    staminaLabel.setBounds(30, 60, 300, 50);
    staminaLabel.setFont(new Font("Serif", Font.BOLD, 18));
    staminaLabel.setForeground(Color.white);
    playerStatusPane.add(staminaLabel, Integer.valueOf(1));

    // Create btn for sleep logic
    sleepBtn = new JButton(new ImageIcon(loader.getResource("images/sleep.png")));
    sleepBtn.setOpaque(false);
    sleepBtn.setContentAreaFilled(false);
    sleepBtn.setBorderPainted(false);
    sleepBtn.setBorder(null);
    sleepBtn.setBounds(60, 140, 64, 60);
    sleepBtn.addActionListener(e -> {
      String[] commands = new String[]{"sleep"};
      actionCallback.accept(commands);
    });
    playerStatusPane.add(sleepBtn, Integer.valueOf(1));

    // Create btn for eat logic
    craftBtn = new JButton(new ImageIcon(loader.getResource("images/craft.png")));
    craftBtn.setOpaque(false);
    craftBtn.setContentAreaFilled(false);
    craftBtn.setBorderPainted(false);
    craftBtn.setBorder(null);
    craftBtn.setBounds(160, 140, 64, 60);
    craftBtn.addActionListener(e -> {
      String[] commands = new String[]{"craft", "raft"};
      actionCallback.accept(commands);
    });
    playerStatusPane.add(craftBtn, Integer.valueOf(1));

    // Create btn for settings screen
    gameSettingsBtn = new JButton(new ImageIcon(loader.getResource("images/settings.png")));
    gameSettingsBtn.setBounds(1400, 15, 65, 65);
    gameSettingsBtn.setOpaque(false);
    gameSettingsBtn.setFocusPainted(false);
    gameSettingsBtn.setContentAreaFilled(false);
    gameSettingsBtn.setBorderPainted(false);
    gameSettingsBtn.setBorder(null);
    gameSettingsBtn.addActionListener(e -> new SettingsScreen());

    // Create btn for save game logic
    saveGameBtn = new JButton(new ImageIcon(loader.getResource("images/save.png")));
    saveGameBtn.setBounds(1300, 15, 65, 65);
    saveGameBtn.setOpaque(false);
    saveGameBtn.setFocusPainted(false);
    saveGameBtn.setContentAreaFilled(false);
    saveGameBtn.setBorderPainted(false);
    saveGameBtn.setBorder(null);
    saveGameBtn.addActionListener(e -> {
      String[] commands = new String[]{"save", "game"};
      actionCallback.accept(commands);

    });

    // Create btn for help screen
    helpBtn = new JButton(new ImageIcon(loader.getResource("images/help.png")));
    helpBtn.setBounds(1200, 15, 65, 65);
    helpBtn.setOpaque(false);
    helpBtn.setFocusPainted(false);
    helpBtn.setContentAreaFilled(false);
    helpBtn.setBorderPainted(false);
    helpBtn.setBorder(null);
    helpBtn.addActionListener(e -> new HelpScreen());


    // Create panel to display players inventory
    inventoryPanel = new JPanel(new GridLayout(4, 3));
    inventoryPanel.setBounds(1180, 320, 300, 430);
    inventoryPanel.setBackground(Color.darkGray);

    // Adding directional buttons w/ event listeners (connected in GUIController)
    northBtn = new JButton(new ImageIcon(loader.getResource("images/up.png")));
    northBtn.setOpaque(false);
    northBtn.setContentAreaFilled(false);
    northBtn.setBorderPainted(false);
    backgroundImgPane.add(northBtn, Integer.valueOf(2));
    northBtn.setBounds(555, 20, 100, 50);
    northBtn.addActionListener(e -> {
      String[] commands = new String[]{"go", "north"};
      actionCallback.accept(commands);
    });

    southBtn = new JButton(new ImageIcon(loader.getResource("images/down.png")));
    southBtn.setOpaque(false);
    southBtn.setContentAreaFilled(false);
    southBtn.setBorderPainted(false);
    backgroundImgPane.add(southBtn, Integer.valueOf(2));
    southBtn.setBounds(555, 400, 100, 50);
    southBtn.addActionListener(e -> {
      String[] commands = new String[]{"go", "south"};
      actionCallback.accept(commands);
    });

    eastBtn = new JButton(new ImageIcon(loader.getResource("images/right.png")));
    eastBtn.setOpaque(false);
    eastBtn.setContentAreaFilled(false);
    eastBtn.setBorderPainted(false);
    backgroundImgPane.add(eastBtn, Integer.valueOf(2));
    eastBtn.setBounds(1070, 227, 100, 57);
    eastBtn.addActionListener(e -> {
      String[] commands = new String[]{"go", "east"};
      actionCallback.accept(commands);
    });

    westBtn = new JButton(new ImageIcon(loader.getResource("images/left.png")));
    westBtn.setOpaque(false);
    westBtn.setContentAreaFilled(false);
    westBtn.setBorderPainted(false);
    backgroundImgPane.add(westBtn, Integer.valueOf(2));
    westBtn.setBounds(-10, 227, 100, 57);
    westBtn.addActionListener(e -> {
      String[] commands = new String[]{"go", "west"};
      actionCallback.accept(commands);
    });

    mainGamePanel.add(gameSettingsBtn);
    mainGamePanel.add(saveGameBtn);
    mainGamePanel.add(helpBtn);
    mainGamePanel.add(backgroundImgPane);
    mainGamePanel.add(itemsPanel);
    mainGamePanel.add(dialoguePanel);
    mainGamePanel.add(inventoryPanel);
    mainGamePanel.add(playerStatusPane);

  }


  public JLabel getBgImgLabel() {
    return bgImgLabel;
  }

  public JTextArea getDialogueTextArea() {
    return dialogueTextArea;
  }

  public JPanel getMainGamePanel() {
    return mainGamePanel;
  }


  public JPanel getItemsPanel() {
    return itemsPanel;
  }


  public JPanel getInventoryPanel() {
    return inventoryPanel;
  }

  public JLabel getLocationLabel() {
    return locationLabel;
  }

  public JLabel getStaminaLabel() {
    return staminaLabel;
  }

  public void setActionCallback(Consumer<String[]> actionCallback) {
    this.actionCallback = actionCallback;
  }

}

