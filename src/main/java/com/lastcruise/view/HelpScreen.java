package com.lastcruise.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class HelpScreen {

  private static JTextArea helpTextArea;

  static View view = new View();

  public HelpScreen() {
    buildHelpScreen();
  }

  private void buildHelpScreen() {

    helpTextArea = new JTextArea();
    helpTextArea.setText(view.getHelpCommands());
    helpTextArea.setMargin(new Insets(10, 10, 10, 10));
    helpTextArea.setSize(600, 300);
    helpTextArea.setBackground(Color.CYAN);
    helpTextArea.setVisible(true);
    helpTextArea.setLineWrap(true);

    JFrame frame = new JFrame();
    ImageIcon image = new ImageIcon(getClass().getClassLoader().getResource("images/help.png"));
    frame.setIconImage(image.getImage());
    frame.setLayout(null);
    frame.setTitle("Help");
    frame.setSize(600, 300);
    frame.setVisible(true);

    frame.add(helpTextArea);
    frame.setLocationRelativeTo(null);
  }
}











