package com.lastcruise;

import com.lastcruise.controller.Controller;
import com.lastcruise.controller.GUIController;
import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException, InterruptedException {

      Controller controller = new Controller();
      // creates guiController instance in Controller, Prompts the user to start game or quit
      controller.gameSetUp();

      boolean runGame = true;
      // TODO: Goal to enter while loop and not change too much logic here
//      URL backgroundMusic = Main.class.getResource(AllSounds.ALL_SOUNDS.get("main"));
//      Music.runAudio(backgroundMusic);
//
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
