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
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GUIController {

  // Primary Window Frame
  private JFrame mainFrame;

  // Changing Panels
  private final TitleScreen titleScreen = new TitleScreen();
  private PreludeScreen intermission = new PreludeScreen();
  // Use callback instead of static methods
  private GameScreen mainGameScreen = new GameScreen();
  private PitLayout pitScreen = new PitLayout();

  // Controller Related Fields
  private final View view = new View();
  PuzzleClient puzzleClient = new PuzzleClient();
  private String name;
  private String message;
  private final GameLoader gameLoader = new GameLoader();
  private Game game;
  private Controller controller = new Controller();
  private Boolean keepPlaying = true;

  // CONSTRUCTOR
  public GUIController() {
    // CREATE GAME WINDOW/FRAME
    mainFrame = new JFrame("The Last Cruise");
    mainFrame.setPreferredSize(new Dimension(1500, 800));
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setResizable(false);
    ImageIcon image = new ImageIcon(getClass().getClassLoader().getResource("images/logo.jpg"));
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

  private void addGameText(String message) {
    mainGameScreen.getDialogueTextArea().append(message);
  }

  public void updateView() {
//    view.clearConsole();
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
    System.out.println(locationItems);

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
            JPanel pit = pitScreen.getPrimaryPanel();
            mainFrame.add(pit);
            mainFrame.pack();
            mainFrame.setVisible(true);

            URL fallSoundUrl = getClass().getResource(
                AllSounds.ALL_SOUNDS.get("pitFall"));
            SoundEffect.runAudio(fallSoundUrl);

            // Show pitFallPrompt to User
            pitScreen.changePuzzleTextArea(view.pitFallPrompt());
//            updateView();
//            updateLocationTimer();
            pitScreen.appendToPuzzleTextArea(view.puzzleMessagePrompt());
//            message = view.puzzleMessagePrompt();
//            updateView();
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


//            if (puzzleClient.puzzleGenerator()) {
//              message = view.solvedPuzzleMessage();
//            } else {
//              message = view.unSolvedPuzzleMessage();
//              updateView();
//              puzzleClient.puzzlePunishment();
//              message = view.pitFallEscapePrompt();
//            }
          } else {
            URL runSoundUrl = getClass().getResource(
                AllSounds.ALL_SOUNDS.get("run"));
            SoundEffect.runAudio(runSoundUrl);
          }

        } catch (InvalidLocationException e) {
          message = view.getInvalidLocationMessage();

        }
//        catch (InterruptedException e) {
//          throw new RuntimeException(e);
//
//        }
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
          message = view.getYouWonMessage();
          keepPlaying = false;
        } else {
          message = view.getCantEscape();
        }
        break;
      }
      case QUIT: {
        keepPlaying = false;
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
        } else if (command[1].equals("off") || command[1].equals("mute") || command[1].equals("stop")) {
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

  public Boolean getKeepPlaying() {
    return keepPlaying;
  }

  public void updateLocationItems() {
    mainGameScreen.getItemsPanel().removeAll();
    game.getCurrentLocationItems().keySet().forEach(item -> {
      ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("images/" + item + ".png"));
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
            } else if (item.equals("log") && !game.getPlayerInventory().getInventory().containsKey("machete")) {
              updateView();
              mainGameScreen.getDialogueTextArea().append(view.cantGrabItem());
            } else {
              processCommand(command);
              ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("images/" + item + ".png"));
              JButton btn = new JButton(img);
              btn.setOpaque(false);
              btn.setContentAreaFilled(false);
              btn.setBorderPainted(false);
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

