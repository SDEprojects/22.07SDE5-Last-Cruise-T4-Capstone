package com.lastcruise.view;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PreludeScreen {
  JPanel mainPanel;
  JTextArea mainLabel;
  JFrame testFrame;
  JButton continueBtn;

  public PreludeScreen() {
    buildPreludeScreen();
  }

  public void buildPreludeScreen() {
    mainPanel = new JPanel();
    mainPanel.setSize(1500, 800);
    mainPanel.setLayout(null);
    mainPanel.setBackground(Color.BLACK);

    mainLabel = new JTextArea("Testing");
    mainLabel.setForeground(Color.white);
    mainLabel.setBackground(Color.BLACK);
    mainLabel.setLineWrap(true);
    mainLabel.setBounds(375, 200, 750, 440);
    mainPanel.add(mainLabel);

    continueBtn = new JButton("Continue");
    continueBtn.setBounds(750, 650, 100, 50);
    mainPanel.add(continueBtn);
  }

  public void changeText(String gameText) {
    mainLabel.setText(gameText);
  }

  public JTextArea getMainLabel() {
    return mainLabel;
  }
  public JPanel getMainPanel() {
    return mainPanel;
  }

  public JButton getContinueBtn() {
    return continueBtn;
  }

}
