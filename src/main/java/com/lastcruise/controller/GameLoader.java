package com.lastcruise.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lastcruise.model.Game;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import javax.swing.Timer;

public class GameLoader {

    // save game method
    public void saveGame(Game game) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // ObjectMapper writes game data into a new file called saved-data.json
        mapper.writeValue(new File("saved-data.json"), game);
        System.out.println("saved");
         ActionListener closeGame = e -> {
            System.exit(0);
        };
         Timer timer = new Timer(3000, closeGame);
         timer.setRepeats(false);
         timer.start();
    }

    // load game method
    public Game loadGame() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // ObjectMapper reads game data from the created saved-data.json
        Game savedGame = mapper.readValue(Paths.get("saved-data.json").toFile(), Game.class);
        System.out.println("Game Loaded");
        // returns Game
        return savedGame;
    }
}