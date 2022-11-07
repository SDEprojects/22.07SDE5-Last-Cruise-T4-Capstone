package com.lastcruise.model;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PuzzleClient {

  private final int MAX;
  private final Map<String, List> PUZZLE_TEXT;
  private int randQuestionNumber;


  public PuzzleClient() {
    Puzzle puzzle = new Puzzle();
    PUZZLE_TEXT = puzzle.getPuzzleText();
    // set random number to number of available puzzles
    MAX = PUZZLE_TEXT.size();
  }

  // generates a random puzzle
  public String puzzleGenerator(){
    Random r =  new Random();
    randQuestionNumber = r.nextInt(MAX);
    String stringValueOfRandomQuestionNumber = String.valueOf(randQuestionNumber);
    String question = (String) PUZZLE_TEXT.get(stringValueOfRandomQuestionNumber).get(0);

    return question;
  }

  public Boolean checkPuzzleAnswer(String answer) {
    String stringValueOfRandomQuestionNumber = String.valueOf(randQuestionNumber);
    String answerWord = (String) PUZZLE_TEXT.get(stringValueOfRandomQuestionNumber).get(1);
    String answerLetter = (String) PUZZLE_TEXT.get(stringValueOfRandomQuestionNumber).get(2);
    String answer2 = answer.toUpperCase().trim();
    // user can respond with the word or the letter for the answer, if it is correct, correctAnswer is true, if not, it is false
    Boolean correctAnswer = answer2.equalsIgnoreCase(answerWord) || answer2.equalsIgnoreCase( answerLetter);
    return correctAnswer;
  }

  public void puzzlePunishmentSound() throws InterruptedException {
    URL grabSoundUrl = getClass().getResource(
        AllSounds.ALL_SOUNDS.get("pitFall"));
    SoundEffect.runAudio(grabSoundUrl);
  }
}
