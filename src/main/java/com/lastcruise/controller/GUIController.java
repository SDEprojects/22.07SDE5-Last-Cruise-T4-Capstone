package com.lastcruise.controller;

import com.lastcruise.model.Game;
import com.lastcruise.model.Player;
import com.lastcruise.view.GameScreen;
import com.lastcruise.view.PreludeScreen;
import com.lastcruise.view.TitleScreen;
import com.lastcruise.view.View;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GUIController {

  private JFrame mainFrame;
  private final TitleScreen titleScreen = new TitleScreen();
  private PreludeScreen intermission = new PreludeScreen();
  // Use callback instead of static methods
  private GameScreen mainGameScreen = new GameScreen();
  static View view = new View();
  private Controller controller = new Controller();
  private Game game;

  public GUIController() {
    mainFrame = new JFrame("The Last Cruise");
    mainFrame.setPreferredSize(new Dimension(1500, 800));
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setResizable(false);
    ImageIcon image = new ImageIcon(getClass().getClassLoader().getResource("images/logo.jpg"));
    mainFrame.setIconImage(image.getImage());
    loadTitleScreen();
    mainGameScreen.setActionCallback((commands) -> {
      try {
        controller.processCommand(commands, game);
        // make ure to attach with each event listener to update view
        updateView();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });
//    mainGameScreen.getSleepBtn().addActionListener(e -> game.playerSleep());
  }

  private void loadTitleScreen() {
    JLayeredPane menu = titleScreen.getTitleScreen();
    mainFrame.add(menu);
    mainFrame.pack();
    // Set location to null after 'pack()'
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setVisible(true);
    // start game action listener
    titleScreen.getStartBtn().addActionListener(e -> {continueToGame();
    });
  }

  private void continueToGame() {
    titleScreen.getTitleScreen().setVisible(false);
    JPanel prelude = intermission.getMainPanel();
    mainFrame.add(prelude);
    mainFrame.pack();
    mainFrame.setVisible(true);
    // TODO: Add scrolling text effect to printSTory in interlude screen
    intermission.changeText(view.printStory());
    ActionListener updateText = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        intermission.changeText(view.printStoryIntro());
      }
    };
    // use java swing timer to change prelude text, calls new action listener above after 10 seconds
    Timer timer = new Timer(10000, updateText);
    timer.setRepeats(false);
    timer.start();

    intermission.getContinueBtn().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // sets prelude panel to false
        intermission.getMainPanel().setVisible(false);
        JPanel gamePanel = mainGameScreen.getMainGamePanel();
        // loads up game panel
        mainFrame.add(gamePanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
      }
    });
  }

  public void gameSetUp() throws InterruptedException {
    game = new Game("Sailor");
    updateView();
  }

  private void setGameText(String message) {
    mainGameScreen.getDialogueTextArea().setText(message);
  }

  public void updateView() {
    view.clearConsole();
    String location = game.getCurrentLocationName();
    String inventory = game.getPlayerInventory().getInventory().keySet().toString();
    String stamina = game.getPlayerStamina();
    String locationDesc = game.getCurrentLocationDesc();
    String locationItems = game.getCurrentLocationItems().keySet().toString();
    // update GUI View with current location and Stamina
    mainGameScreen.getLocationLabel().setText(location);
    mainGameScreen.getStaminaLabel().setText(stamina);
    updateLocationImg(location);
  }


  public void updateLocationImg(String location) {
    ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("images/" + location +".jpg"));
    Image image = icon.getImage();
    Image resizeImg = image.getScaledInstance(1150, 455, Image.SCALE_SMOOTH);
    icon = new ImageIcon(resizeImg);
    mainGameScreen.getBgImgLabel().setIcon(icon);
  }


  public JFrame getMainFrame() {
    return mainFrame;
  }

  public TitleScreen getTitleScreen() {
    return titleScreen;
  }

//  public static void main(String[] args) throws InterruptedException {
//    GUIController gui = new GUIController();
//    gui.updateViewGUI("Beach", "25");

//    game.getDialogueTextArea().setText(view.printStory());
//    gui.setGameText(view.printStory());
  }

