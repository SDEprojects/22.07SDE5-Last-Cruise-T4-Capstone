package com.lastcruise.view;

import com.lastcruise.model.GameText;
import java.util.Map;

public class View {

    private final Map<String, String> GAME_TEXT;

    public View() {
        GameText gameText = new GameText();
        GAME_TEXT = gameText.getGameText();
    }

//    public void printGameBannerConsole() {
//        System.out.println(Colors.GREEN + GAME_TEXT.get("Banner") + Colors.RESET);
//    }

    public String printGameBanner() {
       return GAME_TEXT.get("Banner");
    }

    public String printStory() {
        return GAME_TEXT.get("Intro");
    }

    public void printHelpCommands() {
        System.out.println(GAME_TEXT.get("Help"));
    }

    public String printInstructions() {
        return GAME_TEXT.get("Instructions");
    }

    public String printStoryIntro() {
        return GAME_TEXT.get("StoryIntro");
    }

    public void printNamePrompt() {
        System.out.print(GAME_TEXT.get("NamePrompt"));
    }

    public String printStartGamePrompt() {
        return GAME_TEXT.get("StartGamePrompt");
    }

    public void printStatusBanner(String location, String stamina, String inventory,
        String locationDesc,
        String locationItems, String message) {
        System.out.printf(GAME_TEXT.get("Status"), location, stamina, inventory, locationDesc,
            locationItems,
            message);
    }

    //------------VIEW MESSAGES------------------------------------------
    public String getItemDescription(String description) {
        return String.format(GAME_TEXT.get("ItemDescription"),
            description);
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
        return Colors.RED + GAME_TEXT.get("CantGrabItem") + Colors.RESET;
    }

    public String getItemNotCraftable() {
        return GAME_TEXT.get("ItemNotCraftable");
    }

    public String getItemNotEdible() {
        return GAME_TEXT.get("YouCantEatThat");
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

    public String printCantLoadGame() {
        return GAME_TEXT.get("NoSavedGame");
    }

    public String getNoStaminaToMove() {
        return GAME_TEXT.get("CantMove");
    }

    public String getCantEscape() {
        return GAME_TEXT.get("CantEscape");
    }

    public String getYouWonMessage() {
        return GAME_TEXT.get("Win");
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

    public String pitFallEscapePrompt() {
        return GAME_TEXT.get("PitFallEscape");
    }

    public void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}