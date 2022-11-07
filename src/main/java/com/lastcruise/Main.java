package com.lastcruise;

import com.lastcruise.controller.GUIController;
import com.lastcruise.model.AllSounds;
import com.lastcruise.model.Music;
import java.io.IOException;
import java.net.URL;


public class Main {

  public static void main(String[] args) throws IOException, InterruptedException {

    new GUIController();

    URL backgroundMusic = Main.class.getResource(AllSounds.ALL_SOUNDS.get("main"));
    Music.runAudio(backgroundMusic);
    Music.decreaseMusic();
  }
}
