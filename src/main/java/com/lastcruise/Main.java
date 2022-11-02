package com.lastcruise;

import com.lastcruise.controller.GUIController;
import com.lastcruise.model.AllSounds;
import com.lastcruise.model.Music;
import java.io.IOException;
import java.net.URL;

public class Main {

  public static void main(String[] args) throws IOException, InterruptedException {

    GUIController gui = new GUIController();
    // creates guiController instance in Controller, Prompts the user to start game or quit
    gui.gameSetUp();

//    boolean runGame = gui.getKeepPlaying();
    // TODO: Goal to enter while loop and not change too much logic here
//    URL backgroundMusic = Main.class.getResource(AllSounds.ALL_SOUNDS.get("main"));
//    Music.runAudio(backgroundMusic);

//    while (runGame) {
//      runGame = controller.getCommand();
//      controller.updateView();
//    }
//
//    Music.muteMusic();
//
//    // prevents cmd/terminal window from closing right away after game ends when playing JAR file
//    System.out.println("Thanks for playing! This window will soon close.");
//    Thread.sleep(5000);
  }
}
