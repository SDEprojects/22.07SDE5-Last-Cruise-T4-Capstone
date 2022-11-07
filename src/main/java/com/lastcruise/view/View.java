package com.lastcruise.view;

import com.lastcruise.model.GameText;
import java.util.Map;

public class View {

    private final Map<String, String> GAME_TEXT;

    public View() {
        GameText gameText = new GameText();
        GAME_TEXT = gameText.getGameText();
    }

    public String printStory() {
        return GAME_TEXT.get("Intro");
    }

    public String printStoryIntro() {
        return GAME_TEXT.get("StoryIntro");
    }

    //------------VIEW MESSAGES------------------------------------------
    public String getItemDescription(String description) {

        return String.format(GAME_TEXT.get("ItemDescription"), description);

    }

    public String getInvalidItemMessage() {
        return GAME_TEXT.get("ItemNotFound");
    }

    public String getInvalidCommandMessage() {
        return GAME_TEXT.get("InvalidCommand");
    }

    public String getInvalidLocationMessage() {
        return GAME_TEXT.get("InvalidLocation");
    }

    public String getSuccessfulRaftBuildMessage() {
        return GAME_TEXT.get("BuildSuccessful");
    }

    public String getNotSuccessfulRaftBuildMessage() {
        return GAME_TEXT.get("BuildNotSuccessful");
    }

    public String getNotInRaftLocationBuildMessage() {
        return GAME_TEXT.get("InvalidCraftingLocation");
    }

    public String getHelpCommands() {
        return GAME_TEXT.get("Help");
    }

    public String cantGrabItem() {
        return GAME_TEXT.get("CantGrabItem");
    }

    public String getItemNotCraftable() {
        return GAME_TEXT.get("ItemNotCraftable");
    }

    public String getSleeping() {
        return GAME_TEXT.get("Sleep");
    }

    public String getNoPickUpStamina() {
        return GAME_TEXT.get("NotEnoughPickUpStamina");
    }

    public String getNoDropStamina() {
        return GAME_TEXT.get("NotEnoughDropStamina");
    }

    public String getGameSaved() {
        return GAME_TEXT.get("GameSaved");
    }

    public String getGameSaveFailed() {
        return GAME_TEXT.get("GameSaveFailed");
    }

    public String getEating() {
        return GAME_TEXT.get("EatItem");
    }

    public String getCantEatThat() {
        return GAME_TEXT.get("YouCantEatThat");
    }

    public String getNoStaminaToMove() {
        return GAME_TEXT.get("CantMove");
    }

    public String getCantEscape() {
        return GAME_TEXT.get("CantEscape");
    }

    public String solvedPuzzleMessage() {
        return GAME_TEXT.get("SolvedPuzzle");
    }

    public String unSolvedPuzzleMessage() {

        return GAME_TEXT.get("UnSolvedPuzzle");
    }
    public String puzzleMessagePrompt() {
        return GAME_TEXT.get("PuzzlePrompt");
    }

    public String pitFallPrompt() {
        return GAME_TEXT.get("PitFall");
    }
}