package com.lastcruise.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PitLayout {

  private JTextArea puzzleTextArea;
  private JTextField userInput;
  private JButton submitButton;
  private JPanel primaryPanel;

  // Temporary Fields for testing
  private JFrame frame = new JFrame();
  private static View view = new View();

  public PitLayout() {
    JPanel primary = primaryPanel;
    primary.setBackground(Color.BLACK);
    primary.setSize(1500, 800);
    // PUZZLE TEXT DISPLAY
    puzzleTextArea.setLineWrap(true);
    puzzleTextArea.setForeground(Color.white);
    puzzleTextArea.setBackground(Color.black);
    puzzleTextArea.setMargin(new Insets(10, 10, 10, 10));
    // USER INPUT BAR
    userInput.setMargin(new Insets(10, 30, 10, 10));
    userInput.setFont(new Font("SansSerif", Font.BOLD, 20));
    userInput.setBackground(Color.GRAY);
    userInput.setForeground(Color.WHITE);
//    userInput.setBorder(BorderFactory.createLineBorder(Color.white));
    submitButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // captures text successfully
        String answer = userInput.getText();
        System.out.println(answer);
      }
    });

//    // Temporary Test Frame
//    frame.setSize(1500, 800);
//    frame.add(primary);
//    frame.setVisible(true);
//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  // getter to set the text in text Area
  public JTextArea getPuzzleTextArea() {
    return puzzleTextArea;
  }

  //grabTextArea and upload Puzzle Text to that
  public void changePuzzleTextArea(String puzzle) {
    getPuzzleTextArea().setText(puzzle);
  }

  public void appendToPuzzleTextArea(String incorrectAnswer) {
    getPuzzleTextArea().append(incorrectAnswer);
  }

  // getter to grab user input and process in process command (?)
  public JTextField getUserInput() {
    return userInput;
  }

  public JPanel getPrimaryPanel() {
    return primaryPanel;
  }

  public static void main(String[] args) {
    PitLayout layout = new PitLayout();
    layout.changePuzzleTextArea(view.puzzleMessagePrompt());
  }
}
