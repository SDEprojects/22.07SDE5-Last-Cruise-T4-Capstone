package com.lastcruise.view;

import com.lastcruise.controller.Action;
import com.lastcruise.model.Commands;
import com.lastcruise.view.View;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

  // TODO: Enforce encapsulation (making things private)
  JPanel mainGamePanel, bgImgPanel, itemsPanel, mapPanel, inventoryPanel, dialogueImgPanel, dialogueTextPanel;
  JLabel dialoguePanelText, dialogueText;
  JLayeredPane dialoguePanel;
  ImageIcon bgImg;
  JTextArea dialogueTextArea;
  JScrollPane text;
  View view = new View();
  private Consumer<Action> actionCallback; // TODO: This is a callback to pass an action back to the controller

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
    dialogueTextArea.setBounds(65,65,1103,175);
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
    btn1.addActionListener(e -> {
      Action action = new Action(Commands.PICKUP);
      // set any other fields necessary
      // pass back to controller (translate low level user interaction to high level actions in game, pass back to controller)
      actionCallback.accept(action);
    });

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

    bgImg = new ImageIcon(getClass().getClassLoader().getResource("images/beach1.jpg"));
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

//
//    JLabel img5Label = new JLabel(img5);
//    JLabel img6Label = new JLabel(img6);
//    JLabel img7Label = new JLabel(img7);
//    JLabel img8Label = new JLabel(img8);
//    JLabel img9Label = new JLabel(img9);
//    JLabel img10Label = new JLabel(img10);
//
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


  public Consumer<Action> getActionCallback() {
    return actionCallback;
  }

  public void setActionCallback(
      Consumer<Action> actionCallback) {
    this.actionCallback = actionCallback;
  }
}

