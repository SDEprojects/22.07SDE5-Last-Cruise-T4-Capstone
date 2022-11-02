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
  private JButton sleepBtn;

  private View view = new View();
  private Consumer<String[]> actionCallback;

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
//    dialoguePanel.setBackground(Color.magenta);

    // Create container/ panel to hold scroll img
    dialogueImgPanel = new JPanel();
    dialogueImgPanel.setBounds(10, 50, 1150, 275);

//    dialoguePanel.add(dialogueImgPanel, Integer.valueOf(0));

    // Add image to the img panel, set z-index to 0 "bottom"
    dialogueImgPanel.add(dialogueImg);
    dialoguePanel.add(dialogueImgPanel, Integer.valueOf(0));

    // Create new panel to hold text on top of scroll img
//    dialogueTextPanel = new JPanel();
//    dialogueTextPanel.setOpaque(false);
//    dialogueTextPanel.setBounds(15, 50, 900, 125);
//    dialogueTextPanel.setBorder(BorderFactory.createLineBorder(Color.black, 5));

    dialogueTextArea = new JTextArea();
    dialogueTextArea.setBounds(65, 65, 1103, 175);
//    dialogueTextArea.setOpaque(false);
//    dialogueTextArea.setText(view.printStory());
    dialogueTextArea.setOpaque(false);
    dialogueTextArea.setEditable(false);
    dialogueTextArea.setLineWrap(true);
//    dialogueTextArea.setBorder(BorderFactory.createLineBorder(Color.black, 2));
    dialoguePanel.add(dialogueTextArea, Integer.valueOf(2));

//    text = new JScrollPane(dialogueTextArea);
//    text.setBounds(15,50,900,125);
////    text.getViewport().setOpaque(false);
////    text.setOpaque(false);
////    dialogueTextArea.add(text);
//    dialoguePanel.add(dialogueTextArea, Integer.valueOf(2));

    // Create label to actually hold text content
//    dialogueText = new JLabel("Hello world");
//    dialogueText.setBorder(BorderFactory.createLineBorder(Color.black, 4));
//    // Add text label to text panel/ container
//    dialogueTextPanel.add(dialogueText);
//    // Add text container to dialogue img panel, set z-index to 1 to sit above scroll img
//    dialoguePanel.add(dialogueTextPanel, Integer.valueOf(1));

    // Adding dialogue container to mainGamePanel
    mainGamePanel.add(dialoguePanel);

    // Create items in location display panel
    itemsPanel = new JPanel(new GridLayout(1, 4));
    itemsPanel.setBounds(10, 470, 1150, 75);
    itemsPanel.setBorder(BorderFactory.createLineBorder(Color.black, 4));
//    itemsPanel.setBackground(Color.black);
    // Temporary images
    // TODO: Put getClassLoader stuff in a variable
    ImageIcon img1 = new ImageIcon(getClass().getClassLoader().getResource("images/banana.png"));
    ImageIcon img2 = new ImageIcon(getClass().getClassLoader().getResource("images/paddle.png"));
    ImageIcon img3 = new ImageIcon(getClass().getClassLoader().getResource("images/mushroom.png"));
    ImageIcon img4 = new ImageIcon(getClass().getClassLoader().getResource("images/berries.png"));

    JButton btn1 = new JButton(img1);
    btn1.setOpaque(false);
    btn1.setContentAreaFilled(false);
    btn1.setBorderPainted(false);
    // attach to button listener callback
    // action is button press

    JButton btn2 = new JButton(img2);
    btn2.setOpaque(false);
    btn2.setContentAreaFilled(false);
    btn2.setBorderPainted(false);

    JButton btn3 = new JButton(img3);
    btn3.setOpaque(false);
    btn3.setContentAreaFilled(false);
    btn3.setBorderPainted(false);

    JButton btn4 = new JButton(img4);
    btn4.setOpaque(false);
    btn4.setContentAreaFilled(false);
    btn4.setBorderPainted(false);

//    itemsPanel.add(btn1);
//    itemsPanel.add(btn2);
//    itemsPanel.add(btn3);
//    itemsPanel.add(btn4);

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
    mapPanel.setBounds(1180, 10, 300, 300);
    // mapPanel.setBackground(Color.black);
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

    sleepBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/sleep.png")));
    sleepBtn.setOpaque(false);
    sleepBtn.setContentAreaFilled(false);
    sleepBtn.setBorderPainted(false);
    sleepBtn.setBounds(0, 230, 150, 60);
    sleepBtn.addActionListener(e -> {
      String[] commands = new String[]{"sleep"};
      actionCallback.accept(commands);
    });


    JButton eatBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/eat.png")));
    eatBtn.setOpaque(false);
    eatBtn.setContentAreaFilled(false);
    eatBtn.setBorderPainted(false);
    eatBtn.setBounds(140, 235, 150, 60);

    mapPanel.add(sleepBtn);
    mapPanel.add(eatBtn);

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

    JButton btn5 = new JButton(img5);
    btn5.setOpaque(false);
    btn5.setContentAreaFilled(false);
    btn5.setBorderPainted(false);

    JButton btn6 = new JButton(img6);
    btn6.setOpaque(false);
    btn6.setContentAreaFilled(false);
    btn6.setBorderPainted(false);

    JButton btn7 = new JButton(img7);
    btn7.setOpaque(false);
    btn7.setContentAreaFilled(false);
    btn7.setBorderPainted(false);

    JButton btn8 = new JButton(img8);
    btn8.setOpaque(false);
    btn8.setContentAreaFilled(false);
    btn8.setBorderPainted(false);

    JButton btn9 = new JButton(img9);
    btn9.setOpaque(false);
    btn9.setContentAreaFilled(false);
    btn9.setBorderPainted(false);

    JButton btn10 = new JButton(img10);
    btn10.setOpaque(false);
    btn10.setContentAreaFilled(false);
    btn10.setBorderPainted(false);

    // Adding directional buttons w/ event listeners (connected in GUIController)
    JButton northBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/up.png")));
    northBtn.setOpaque(false);
    northBtn.setContentAreaFilled(false);
    northBtn.setBorderPainted(false);
    backgroundImgPane.add(northBtn, Integer.valueOf(2));
    northBtn.setBounds(555, 20, 100, 50);
    northBtn.addActionListener(e -> {
      String[] commands = new String[]{"go", "north"};
      actionCallback.accept(commands);
    });

    JButton southBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/down.png")));
    southBtn.setOpaque(false);
    southBtn.setContentAreaFilled(false);
    southBtn.setBorderPainted(false);
    backgroundImgPane.add(southBtn, Integer.valueOf(2));
    southBtn.setBounds(555, 400, 100, 50);
    southBtn.addActionListener(e -> {
      String[] commands = new String[]{"go", "south"};
      actionCallback.accept(commands);
    });

    JButton eastBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/right.png")));
    eastBtn.setOpaque(false);
    eastBtn.setContentAreaFilled(false);
    eastBtn.setBorderPainted(false);
    backgroundImgPane.add(eastBtn, Integer.valueOf(2));
    eastBtn.setBounds(1070, 227, 100, 57);
    eastBtn.addActionListener(e -> {
      String[] commands = new String[]{"go", "east"};
      actionCallback.accept(commands);
    });

    JButton westBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/left.png")));
    westBtn.setOpaque(false);
    westBtn.setContentAreaFilled(false);
    westBtn.setBorderPainted(false);
    backgroundImgPane.add(westBtn, Integer.valueOf(2));
    westBtn.setBounds(-10, 227, 100, 57);
    westBtn.addActionListener(e -> {
      String[] commands = new String[]{"go", "west"};
      actionCallback.accept(commands);
    });

    inventoryPanel.add(btn1);
    inventoryPanel.add(btn2);
    inventoryPanel.add(btn3);
    inventoryPanel.add(btn4);
    inventoryPanel.add(btn5);
    inventoryPanel.add(btn6);
    inventoryPanel.add(btn7);
    inventoryPanel.add(btn8);
    inventoryPanel.add(btn9);
    inventoryPanel.add(btn10);

    // Add inventory panel to main panel
    mainGamePanel.add(inventoryPanel);
//    frame.setSize(1500, 800);
//    frame.add(mainGamePanel);
//    frame.setVisible(true);

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

// test method
//  public static void main(String[] args) {
//    GameScreen screen = new GameScreen();
//    screen.updateStatus("Merry", "Christmas");
//  }

}

