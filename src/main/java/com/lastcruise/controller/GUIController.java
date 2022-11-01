package com.lastcruise.controller;

import com.lastcruise.view.GameScreen;
import com.lastcruise.view.PreludeScreen;
import com.lastcruise.view.TitleScreen;
import com.lastcruise.view.View;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class GUIController {

  private JFrame mainFrame;
  private final TitleScreen titleScreen = new TitleScreen();
  private PreludeScreen intermission = new PreludeScreen();
  // Use callback instead of static methods
  private GameScreen game = new GameScreen();
  static View view = new View();

  public GUIController() {
    mainFrame = new JFrame("The Last Cruise");
    mainFrame.setPreferredSize(new Dimension(1500, 800));
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setResizable(false);

    ImageIcon image = new ImageIcon(getClass().getClassLoader().getResource("images/logo.jpg"));
    mainFrame.setIconImage(image.getImage());

    loadTitleScreen();
//    continueToGame();
    // This is a high level event listener, envoked by View to perform an action
//    game.setActionCallback((action) -> {
//      // would invoke process command in controller
//      // TODO: Handle action
//    });
  }

  private void loadTitleScreen() {
    JLayeredPane menu = titleScreen.getTitleScreen();
    mainFrame.add(menu);
    mainFrame.pack();
    // Set location to null after 'pack()'
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setVisible(true);
    // start game action listener
    titleScreen.getStartBtn().addActionListener(e -> {
      continueToGame();
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
    intermission.getContinueBtn().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // sets prelude panel to false
        intermission.getMainPanel().setVisible(false);
        JPanel gamePanel = game.getMainGamePanel();
        // loads up game panel
        mainFrame.add(gamePanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
      }
    });
  }

  private void setGameText(String message) {
    game.getDialogueTextArea().setText(message);
  }

  public JFrame getMainFrame() {
    return mainFrame;
  }

  public TitleScreen getTitleScreen() {
    return titleScreen;
  }

  public static void main(String[] args) throws InterruptedException {
    GUIController gui = new GUIController();
//    game.getDialogueTextArea().setText(view.printStory());
//    gui.setGameText(view.printStory());
  }

}
