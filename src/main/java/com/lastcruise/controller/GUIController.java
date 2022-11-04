package com.lastcruise.controller;

import com.lastcruise.model.AllSounds;
import com.lastcruise.model.Commands;
import com.lastcruise.model.CraftingLocation;
import com.lastcruise.model.Game;
import com.lastcruise.model.GameMap.InvalidLocationException;
import com.lastcruise.model.Inventory.InventoryEmptyException;
import com.lastcruise.model.Music;
import com.lastcruise.model.Item;
import com.lastcruise.model.Player;
import com.lastcruise.model.Player.ItemNotEdibleException;
import com.lastcruise.model.Player.NoEnoughStaminaException;
import com.lastcruise.model.PuzzleClient;
import com.lastcruise.model.SoundEffect;
import com.lastcruise.view.GameScreen;
import com.lastcruise.view.PitLayout;
import com.lastcruise.view.PreludeScreen;
import com.lastcruise.view.TitleScreen;
import com.lastcruise.view.View;
import com.lastcruise.view.WinScreen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class GUIController {

  // PRIMARY WINDOW PANEL
  private JFrame mainFrame;

  // CHANGING PANELS
  private final TitleScreen titleScreen = new TitleScreen();
  private PreludeScreen intermission = new PreludeScreen();
  private GameScreen mainGameScreen = new GameScreen();
  // PITSCREEN INSTANTIATED WHEN PLAYER ENTERS PIT FOR NEW PUZZLE EACH TIME
  private PitLayout pitScreen;
  private WinScreen winScreen = new WinScreen();

  // CONTROLLER RELATED FIELDS
  private final View view = new View();
  private PuzzleClient puzzleClient = new PuzzleClient();
  private String message;
  private final GameLoader gameLoader = new GameLoader();
  private Game game;
  private ClassLoader loader = getClass().getClassLoader();

  // CONSTRUCTOR
  public GUIController() {
    // CREATE GAME WINDOW/FRAME
    mainFrame = new JFrame("The Last Cruise");
    mainFrame.setPreferredSize(new Dimension(1500, 800));
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setResizable(false);
    ImageIcon image = new ImageIcon(loader.getResource("images/logo.jpg"));
    mainFrame.setIconImage(image.getImage());
    // LOAD TITLE SCREEN
    loadTitleScreen();
    // ACTION CALLBACK TO PROCESS MAIN GAME COMMANDS
    mainGameScreen.setActionCallback((commands) -> {
      try {
        processCommand(commands);
        // make sure to attach with each event listener to update view
        updateView();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });
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
      try {
        this.gameSetUp();
      } catch (InterruptedException ex) {
        throw new RuntimeException(ex);
      }
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
    ActionListener updateText = e -> {
      intermission.changeText(view.printStoryIntro());
      intermission.getContinueBtn().setEnabled(true);
    };
    // use java swing timer to change prelude text, calls new action listener above after 10 seconds
    Timer timer = new Timer(10000, updateText);
    timer.setRepeats(false);
    timer.start();

    intermission.getContinueBtn().addActionListener(e -> {
      // sets prelude panel to false
      intermission.getMainPanel().setVisible(false);
      JPanel gamePanel = mainGameScreen.getMainGamePanel();
      // loads up game panel
      mainFrame.add(gamePanel);
      mainFrame.pack();
      mainFrame.setVisible(true);
    });
  }

  public void gameSetUp() throws InterruptedException {
    game = new Game("Sailor");
    updateView();
  }

  private void addGameText(String message) {
    mainGameScreen.getDialogueTextArea().append(message);
  }

  public void updateView() {
    String location = game.getCurrentLocationName();
    String inventory = game.getPlayerInventory().getInventory().keySet().toString();
    String stamina = game.getPlayerStamina();
    String locationDesc = game.getCurrentLocationDesc();
    String locationItems = game.getCurrentLocationItems().keySet().toString();

    // update GUI View with current location and Stamina
    mainGameScreen.getLocationLabel().setText(location);
    mainGameScreen.getStaminaLabel().setText(stamina);
    mainGameScreen.getDialogueTextArea().setText(locationDesc);

    // Updates GameScreen Location image
    updateLocationImg(location);
    updateLocationItems();

    // append additional game messages to the scroll screen
    addGameText(message);
  }

  public void updateLocationImg(String location) {
    if (!location.equalsIgnoreCase("pit")) {
      ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("images/" + location +".jpg"));
      Image image = icon.getImage();
      Image resizeImg = image.getScaledInstance(1150, 455, Image.SCALE_SMOOTH);
      icon = new ImageIcon(resizeImg);
      mainGameScreen.getBgImgLabel().setIcon(icon);
    }
  }

  public void updateLocationItems() {
    mainGameScreen.getItemsPanel().removeAll();
    if (game.getCurrentLocationItems().isEmpty()) {
      mainGameScreen.getItemsPanel().removeAll();
      mainGameScreen.getItemsPanel().revalidate();
      mainGameScreen.getItemsPanel().repaint();
    } else {
      if (game.getCurrentLocationItems().containsKey("raft")) {
        ImageIcon img = new ImageIcon(loader.getResource("images/raft.png"));
        JButton btn = new JButton(img);
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              processCommand(new String[]{"escape"});
            } catch (InterruptedException ex) {
              throw new RuntimeException(ex);
            }
          }
        });
        mainGameScreen.getItemsPanel().add(btn);
        mainGameScreen.getItemsPanel().revalidate();
        mainGameScreen.getItemsPanel().repaint();
      } else {
        game.getCurrentLocationItems().keySet().forEach(item -> {
          // Creating item panel buttons
          ImageIcon img = new ImageIcon(loader.getResource("images/" + item + ".png"));
          JButton btn = new JButton(img);
          btn.setOpaque(false);
          btn.setContentAreaFilled(false);
          btn.setBorderPainted(false);
          btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              String[] command = new String[]{"grab", item};
              try {
                if (game.getPlayerStaminaInt() < 25) {
                  updateView();
                  mainGameScreen.getDialogueTextArea().append(view.getNoPickUpStamina());
                } else if (item.equals("log") && !game.getPlayerInventory().getInventory()
                    .containsKey("machete")) {
                  updateView();
                  mainGameScreen.getDialogueTextArea().append(view.cantGrabItem());
                } else {
                  // duplicate item panel btns to inventory panel btns to add additional event listeners
                  processCommand(command);
                  ImageIcon img = new ImageIcon(loader.getResource("images/" + item + ".png"));
                  JButton btn = new JButton(img);
                  btn.setOpaque(false);
                  btn.setContentAreaFilled(false);
                  btn.setBorderPainted(false);
                  btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                      // create new frame that houses item description, dropBtn and if applicable, eatBtn
                      String desc = game.getPlayerInventory().getInventory().get(item)
                          .getDescription();
                      JFrame frame = new JFrame();
                      // Make Text Area
                      JDialog jd = new JDialog(frame, "Inventory Item", true);
                      jd.setLayout(null);
                      JTextArea itemDesc = new JTextArea();
                      itemDesc.setText("Item Description: \n" + desc);
                      itemDesc.setLineWrap(true);
                      itemDesc.setEditable(false);
                      itemDesc.setBounds(10, 10, 280, 100);
                      jd.add(itemDesc);
                      jd.setSize(300, 300);

                      // Make drop button
                      JButton dropBtn = new JButton("Drop");
                      dropBtn.setBounds(0, 130, 75, 75);
                      dropBtn.setBackground(Color.darkGray);
                      dropBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                          String[] command = new String[]{"drop", item};
                          if (game.getPlayerStaminaInt() < 25) {
                            updateView();
                            mainGameScreen.getDialogueTextArea().append(view.getNoDropStamina());
                          } else {
                            try {
                              processCommand(command);
                              frame.setVisible(false);
                              if (btn != null) {
                                btn.getParent().remove(btn);
                                mainGameScreen.getInventoryPanel().revalidate();
                                mainGameScreen.getInventoryPanel().repaint();
                                updateView();
                              }
                            } catch (InterruptedException ex) {
                              throw new RuntimeException(ex);
                            }
                          }
                        }
                      });

                      // make eat button if item is edible
                      if (game.getPlayerInventory().getInventory().get(item).getEdible()) {
                        JButton eatBtn = new JButton("Eat");
                        eatBtn.setBounds(140, 130, 75, 75);
                        eatBtn.setBackground(Color.darkGray);
                        eatBtn.addActionListener(new ActionListener() {
                          String[] command = new String[]{"eat", item};

                          @Override
                          public void actionPerformed(ActionEvent e) {
                            try {
                              processCommand(command);
                              frame.setVisible(false);
                              if (btn != null) {
                                btn.getParent().remove(btn);
                                mainGameScreen.getInventoryPanel().revalidate();
                                mainGameScreen.getInventoryPanel().repaint();
                                updateView();
                              }
                            } catch (InterruptedException ex) {
                              throw new RuntimeException(ex);
                            }
                          }
                        });
                        jd.add(eatBtn);
                      }
                      jd.add(dropBtn);
                      jd.setLocationRelativeTo(mainGameScreen.getInventoryPanel());
                      jd.setVisible(true);
                    }
                  });
                  mainGameScreen.getInventoryPanel().add(btn);
                  mainGameScreen.getItemsPanel().revalidate();
                  mainGameScreen.getItemsPanel().repaint();
                  updateView();
                }
              } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
              }
            }
          });
          mainGameScreen.getItemsPanel().add(btn);
          mainGameScreen.getItemsPanel().revalidate();
          mainGameScreen.getItemsPanel().repaint();
        });
      }
    }}

  public void processCommand(String[] command) throws InterruptedException {
    Commands c = Commands.valueOf(command[0].toUpperCase());

    switch (c) {
      //---- GO -------//
      case GO: {
        try {
          game.moveLocation(command);
          // TODO: Adjust PIT to print message elsewhere once panel is decided
          if (game.getCurrentLocationName().equals("PIT")) {
            // If location is pit, load up pit panel screen
            mainGameScreen.getMainGamePanel().setVisible(false);
            pitScreen = new PitLayout();
            JPanel pit = pitScreen.getPrimaryPanel();
            mainFrame.add(pit);
            mainFrame.pack();
            mainFrame.setVisible(true);

            URL fallSoundUrl = getClass().getResource(
                AllSounds.ALL_SOUNDS.get("pitFall"));
            SoundEffect.runAudio(fallSoundUrl);

            // Show pitFallPrompt to User
            pitScreen.changePuzzleTextArea(view.pitFallPrompt());
            pitScreen.appendToPuzzleTextArea(view.puzzleMessagePrompt());
            pitScreen.appendToPuzzleTextArea(puzzleClient.puzzleGenerator());
            ActionListener pitfall = new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                pitScreen.appendToPuzzleTextArea("\n\n\nYou are going to sleep for a thousand years. Beginning...");
                pitScreen.appendToPuzzleTextArea("\n\nPlease wait 15 seconds.");
                pitScreen.getSubmitButton().removeActionListener(pitScreen.getSubmit());
              }
            };
            ActionListener leavePit = new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                String[] command = new String[]{"GO", "EAST"};
                try {
                  processCommand(command);
                } catch (InterruptedException ex) {
                  throw new RuntimeException(ex);
                }
                // Change the screen back to mainGameScreen
                pitScreen.getPrimaryPanel().setVisible(false);
                mainGameScreen.getMainGamePanel().setVisible(true);
                updateView();
              }
            };
            pitScreen.setActionCallback((playerAnswer) -> {
                Boolean answer = puzzleClient.checkPuzzleAnswer(playerAnswer);
                if (answer) {
                  pitScreen.appendToPuzzleTextArea(view.solvedPuzzleMessage());
                  Timer timer = new Timer(3000, leavePit);
                  timer.setRepeats(false);
                  timer.start();
                } else {
                  pitScreen.appendToPuzzleTextArea(view.unSolvedPuzzleMessage());
                  pitScreen.getSubmitButton().removeActionListener(pitScreen.getSubmit());
                  try {
                    puzzleClient.puzzlePunishmentSound();
                  } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                  }
                  // Wait three seconds and tell player they were wrong
                  Timer timer = new Timer(3000, pitfall);
                  timer.setRepeats(false);
                  timer.start();
                  // Wait 15 seconds before continue with game
                  timer = new Timer(15000, leavePit);
                  timer.setRepeats(false);
                  timer.start();
                }
            });
          } else {
            URL runSoundUrl = getClass().getResource(
                AllSounds.ALL_SOUNDS.get("run"));
            SoundEffect.runAudio(runSoundUrl);
          }

        } catch (InvalidLocationException e) {
          message = view.getInvalidLocationMessage();

        }
        catch (NoEnoughStaminaException e) {
          message = view.getNoStaminaToMove();

        }
        break;
      }
      //---- HELP -------//
      case HELP: {
        message = view.getHelpCommands();
        break;
      }
      //---- INSPECT -------//
      case INSPECT: {
        if (game.inspectItem(command) != null) {
          message = view.getItemDescription(game.inspectItem(command));
        }
        break;
      }
      //---- GRAB -------//
      case PICKUP:
      case TAKE:
      case GRAB: {
        var currentLocationInventory = game.getCurrentLocationInventory();
        var playerInventory = game.getPlayerInventory();
        // GRABBING LOG
        if (command[1].equals("log") && !playerInventory.getInventory()
            .containsKey("machete")) {
          message = view.cantGrabItem();
        } else {
          try {
            game.transferItemFromTo(currentLocationInventory, playerInventory,
                command[1]);
            URL grabSoundUrl = getClass().getResource(
                AllSounds.ALL_SOUNDS.get("pickup"));
            SoundEffect.runAudio(grabSoundUrl);
          } catch (InventoryEmptyException e) {
            message = view.getInvalidItemMessage();
          } catch (NoEnoughStaminaException e) {
            message = view.getNoPickUpStamina();
          }

        }
        break;
      }
      //--- DROP ------//
      case DROP: {
        var currentLocationInventory = game.getCurrentLocationInventory();
        var playerInventory = game.getPlayerInventory();
        try {
          game.transferItemFromTo(playerInventory,
              currentLocationInventory,
              command[1]);
          URL dropSoundUrl = getClass().getResource(AllSounds.ALL_SOUNDS.get("drop"));
          SoundEffect.runAudio(dropSoundUrl);
        } catch (InventoryEmptyException e) {
          message = view.getInvalidItemMessage();
        } catch (NoEnoughStaminaException e) {
          message = view.getNoDropStamina();
        }
        break;
      }
      //--- CRAFT ---//
      case BUILD:
      case CRAFT: {
        if (command[1].equals("raft")) {
          if (game.getCurrentLocation() instanceof CraftingLocation) {
            //Craft raft
            if (game.craftRaft()) {
              message = view.getSuccessfulRaftBuildMessage();
            } else {
              message = view.getNotSuccessfulRaftBuildMessage();
            }

          } else {
            message = view.getNotInRaftLocationBuildMessage();
          }

        } else {
          message = view.getItemNotCraftable();
        }
        break;
      }
      case EAT: {
        try {
          game.eatItem(command[1]);
          message = view.getEating();
          URL eatSoundUrl = getClass().getResource(AllSounds.ALL_SOUNDS.get("eat"));
          SoundEffect.runAudio(eatSoundUrl);
        } catch (InventoryEmptyException e) {
          message = view.getInvalidItemMessage();
        } catch (ItemNotEdibleException e) {
          message = view.getCantEatThat();
        }
        break;
      }
      case SLEEP: {
        game.playerSleep();
        message = view.getSleeping();
        Thread.sleep(4000);
        break;
      }
      case ESCAPE: {
        if (game.getCurrentLocationItems().containsKey("raft")) {
          mainGameScreen.getMainGamePanel().setVisible(false);
          JPanel win = winScreen.getPrimaryPanel();
          mainFrame.add(win);
          mainFrame.pack();
          mainFrame.setVisible(true);
          // Play Again Feature
          winScreen.getYesPlay().addActionListener(e -> {
            mainFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            GUIController gui = new GUIController();
            try {
              gui.gameSetUp();
            } catch (InterruptedException ex) {
              throw new RuntimeException(ex);
            }
          });
        } else {
          message = view.getCantEscape();
        }
        break;
      }
      case QUIT: {
        break;
      }
      // MUSIC and SOUND EFFECTS CONTROLS
      case VOLUME:
      case MUSIC: {
        if (command[1].equals("up")) {
          Music.increaseMusic();
          break;
        } else if (command[1].equals("down")) {
          Music.decreaseMusic();
          break;
        } else if (command[1].equals("off") || command[1].equals("mute") || command[1].equals(
            "stop")) {
          Music.muteMusic();
          break;
        } else if (command[1].equals("on") || command[1].equals("unmute")) {
          Music.unMuteMusic();
          break;
        }
      }
      case SOUND: {
        if (command[1].equals("up")) {
          SoundEffect.increaseFxVolume();
          break;
        } else if (command[1].equals("down")) {
          SoundEffect.decreaseFxVolume();
          break;
        } else if (command[1].equals("off") || command[1].equals("mute")) {
          SoundEffect.muteSoundFx();
          break;
        } else if (command[1].equals("on") || command[1].equals("unmute")) {
          SoundEffect.unMuteSoundFx();
          break;
        }
      }

      // SAVING THE GAME
      case SAVE: {
        if (command[1].equals("game")) {
          try {
            gameLoader.saveGame(game);
            message = view.getGameSaved();
          } catch (IOException e) {
            message = view.getGameSaveFailed();
          }
          break;
        }
      }
      default:
        message = view.getInvalidCommandMessage();
        break;
    }
  }

  public JFrame getMainFrame() {
    return mainFrame;
  }

  public TitleScreen getTitleScreen() {
    return titleScreen;
  }

}

