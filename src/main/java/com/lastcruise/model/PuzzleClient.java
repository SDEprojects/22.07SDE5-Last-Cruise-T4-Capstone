package com.lastcruise.model;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

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

//    Scanner playerResponse = new Scanner(System.in);
//    System.out.print("Type your response here: ");
//    String answer = playerResponse.nextLine().toUpperCase().trim();
//    // user can respond with the word or the letter for the answer, if it is correct, correctAnswer is true, if not, it is false
//    correctAnswer = answer.equalsIgnoreCase(answerWord) || answer.equalsIgnoreCase( answerLetter);
//    System.out.println(correctAnswer);

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
//    puzzleFailureTimer();
//    for (int a = 1; a< 11; a++){
//      System.out.println(a +"0"+ " Years later..");
//      URL grabSoundUrl1 = getClass().getResource(
//          AllSounds.ALL_SOUNDS.get("bell"));
//      SoundEffect.runAudio(grabSoundUrl1);
//      puzzleFailureTimer();
//    }
  }

//  public void puzzleFailureTimer() throws InterruptedException {
//    Thread.sleep(1500);
//  }

}
