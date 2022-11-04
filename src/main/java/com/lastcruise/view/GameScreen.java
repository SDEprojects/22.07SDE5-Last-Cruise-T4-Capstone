package com.lastcruise.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.function.Consumer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GameScreen {

  // TODO: Enforce encapsulation (making things private),
  //  Create update screen method to update status stuff
  private JPanel mainGamePanel, itemsPanel, mapPanel, inventoryPanel, dialogueImgPanel, dialogueTextPanel, bgImgPanel;
  private JLabel dialoguePanelText, dialogueText, locationLabel, staminaLabel, bgImgLabel;
  private JLayeredPane dialoguePanel, backgroundImgPane;
  private ImageIcon bgImg;
  private JTextArea dialogueTextArea;
  private JFrame frame = new JFrame();
  private JScrollPane text;
  private JButton sleepBtn, gameSettingsBtn, saveGameBtn, helpBtn;

  private View view = new View();
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
    ImageIcon scroll = new ImageIcon(loader.getResource("images/scroll.jpg"));
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
//    dialoguePanel.setBackground(Color.magenta);

    // Create container/ panel to hold scroll img
    dialogueImgPanel = new JPanel();
    dialogueImgPanel.setBounds(10, 50, 1150, 275);


    // Add image to the img panel, set z-index to 0 "bottom"
    dialogueImgPanel.add(dialogueImg);
    dialoguePanel.add(dialogueImgPanel, Integer.valueOf(0));


    dialogueTextArea = new JTextArea();
    dialogueTextArea.setBounds(65, 65, 1103, 175);
    dialogueTextArea.setOpaque(false);
    dialogueTextArea.setEditable(false);
    dialogueTextArea.setLineWrap(true);

    dialoguePanel.add(dialogueTextArea, Integer.valueOf(2));

    // Adding dialogue container to mainGamePanel
    mainGamePanel.add(dialoguePanel);

    // Create items in location display panel
    itemsPanel = new JPanel(new GridLayout(1, 4));
    itemsPanel.setBounds(10, 470, 1150, 75);
    itemsPanel.setBorder(BorderFactory.createLineBorder(Color.black, 4));
//    itemsPanel.setBackground(Color.black);

    // add location items to main panel
    mainGamePanel.add(itemsPanel);

    bgImg = new ImageIcon(getClass().getClassLoader().getResource("images/BEACH.jpg"));
    Image image = bgImg.getImage();
    Image newBgImg = image.getScaledInstance(1150, 455, Image.SCALE_SMOOTH);
    bgImg = new ImageIcon(newBgImg);
    // Image to be used for background image
    bgImgLabel = new JLabel(bgImg);
//    bgImgLabel.setIcon(jungle);


    // Create panel to house the location background image
    backgroundImgPane = new JLayeredPane();
    backgroundImgPane.setBounds(10, 10, 1150, 455);

    bgImgPanel = new JPanel();
    bgImgPanel.setBounds(10, 10, 1150, 455);
    bgImgPanel.add(bgImgLabel);
    backgroundImgPane.add(bgImgPanel, Integer.valueOf(0));

    // Add background image panel to main panel
    mainGamePanel.add(backgroundImgPane);

    // Create panel to eventually house map/ compass or player info
    mapPanel = new JPanel();
    mapPanel.setLayout(null);
    mapPanel.setBounds(1180, 90, 300, 210);
//     mapPanel.setBackground(Color.black);
    // TODO: Add labels to panel
    locationLabel = new JLabel();
    locationLabel.setBackground(Color.PINK);
    locationLabel.setBounds(10, 10, 300, 50);
    locationLabel.setText("Location!");
    mapPanel.add(locationLabel);

    staminaLabel = new JLabel();
    staminaLabel.setBounds(10, 60, 300, 50);
    staminaLabel.setText("Stamina!");
    mapPanel.add(staminaLabel);

    sleepBtn = new JButton(new ImageIcon(loader.getResource("images/sleep.png")));
    sleepBtn.setOpaque(false);
//    sleepBtn.setFocusPainted(false);
    sleepBtn.setContentAreaFilled(false);
    sleepBtn.setBorderPainted(false);
    sleepBtn.setBorder(null);
    sleepBtn.setBounds(34, 150, 250, 60);
    sleepBtn.addActionListener(e -> {
      String[] commands = new String[]{"sleep"};
      actionCallback.accept(commands);
    });

    gameSettingsBtn = new JButton(new ImageIcon(loader.getResource("images/settings.png")));
    gameSettingsBtn.setBounds(1400, 15, 65, 65);
    gameSettingsBtn.setOpaque(false);
    gameSettingsBtn.setFocusPainted(false);
    gameSettingsBtn.setContentAreaFilled(false);
    gameSettingsBtn.setBorderPainted(false);
    gameSettingsBtn.setBorder(null);
    gameSettingsBtn.addActionListener(e -> new SettingsScreen());

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

    helpBtn = new JButton(new ImageIcon(loader.getResource("images/help.png")));
    helpBtn.setBounds(1200, 15, 65, 65);
    helpBtn.setOpaque(false);
    helpBtn.setFocusPainted(false);
    helpBtn.setContentAreaFilled(false);
    helpBtn.setBorderPainted(false);
    helpBtn.setBorder(null);
    helpBtn.addActionListener(e -> new HelpScreen());


    mainGamePanel.add(gameSettingsBtn);
    mainGamePanel.add(saveGameBtn);
    mapPanel.add(sleepBtn);
    mainGamePanel.add(helpBtn);


    // Add map to main panel
    mainGamePanel.add(mapPanel);

    // Create panel to display players inventory
    inventoryPanel = new JPanel(new GridLayout(4, 3));
    inventoryPanel.setBounds(1180, 320, 300, 430);

    inventoryPanel.setBackground(Color.darkGray);

    // Adding directional buttons w/ event listeners (connected in GUIController)
    JButton northBtn = new JButton(new ImageIcon(loader.getResource("images/up.png")));
    northBtn.setOpaque(false);
    northBtn.setContentAreaFilled(false);
    northBtn.setBorderPainted(false);
    backgroundImgPane.add(northBtn, Integer.valueOf(2));
    northBtn.setBounds(555, 20, 100, 50);
    northBtn.addActionListener(e -> {
      String[] commands = new String[]{"go", "north"};
      actionCallback.accept(commands);
    });

    JButton southBtn = new JButton(new ImageIcon(loader.getResource("images/down.png")));
    southBtn.setOpaque(false);
    southBtn.setContentAreaFilled(false);
    southBtn.setBorderPainted(false);
    backgroundImgPane.add(southBtn, Integer.valueOf(2));
    southBtn.setBounds(555, 400, 100, 50);
    southBtn.addActionListener(e -> {
      String[] commands = new String[]{"go", "south"};
      actionCallback.accept(commands);
    });

    JButton eastBtn = new JButton(new ImageIcon(loader.getResource("images/right.png")));
    eastBtn.setOpaque(false);
    eastBtn.setContentAreaFilled(false);
    eastBtn.setBorderPainted(false);
    backgroundImgPane.add(eastBtn, Integer.valueOf(2));
    eastBtn.setBounds(1070, 227, 100, 57);
    eastBtn.addActionListener(e -> {
      String[] commands = new String[]{"go", "east"};
      actionCallback.accept(commands);
    });

    JButton westBtn = new JButton(new ImageIcon(loader.getResource("images/left.png")));
    westBtn.setOpaque(false);
    westBtn.setContentAreaFilled(false);
    westBtn.setBorderPainted(false);
    backgroundImgPane.add(westBtn, Integer.valueOf(2));
    westBtn.setBounds(-10, 227, 100, 57);
    westBtn.addActionListener(e -> {
      String[] commands = new String[]{"go", "west"};
      actionCallback.accept(commands);
    });

    // Add inventory panel to main panel
    mainGamePanel.add(inventoryPanel);
  }


  public JLabel getBgImgLabel() {
    return bgImgLabel;
  }

  public JTextArea getDialogueTextArea() {
    return dialogueTextArea;
  }

  public ImageIcon getBgImg() {
    return bgImg;
  }

  public void setBgImg(ImageIcon bgImg) {
    this.bgImg = bgImg;
  }

  public JPanel getMainGamePanel() {
    return mainGamePanel;
  }

  public JLayeredPane getBgImgPanel() {
    return backgroundImgPane;
  }

  public JPanel getItemsPanel() {
    return itemsPanel;
  }

  public JPanel getMapPanel() {
    return mapPanel;
  }

  public JPanel getInventoryPanel() {
    return inventoryPanel;
  }

  public JLabel getLocationLabel() {
    return locationLabel;
  }

  public void setLocationLabel(JLabel locationLabel) {
    this.locationLabel = locationLabel;
  }

  public JLabel getStaminaLabel() {
    return staminaLabel;
  }

  public void setStaminaLabel(JLabel staminaLabel) {
    this.staminaLabel = staminaLabel;
  }

  public Consumer<String[]> getActionCallback() {
    return actionCallback;
  }

  public void setActionCallback(Consumer<String[]> actionCallback) {
    this.actionCallback = actionCallback;
  }

  public JButton getSleepBtn() {
    return sleepBtn;
  }

}

