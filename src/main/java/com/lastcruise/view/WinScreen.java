package com.lastcruise.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class WinScreen {

  private JPanel primaryPanel;
  private JPanel contentPanel;
  private JLabel winBanner;
  private JLabel playAgainBanner;
  private JRadioButton yesPlay;
  private JRadioButton noPLay;
  private JLabel photoLabel;
  ActionListener endGame = e -> System.exit(0);
  private ClassLoader loader = getClass().getClassLoader();


  public WinScreen() {
    JPanel primary = primaryPanel;
    primary.setSize(1500, 800);
    primaryPanel.setBackground(new Color(29, 110, 20));

    // CONTENT PANE STYLING
    JPanel content = contentPanel;
    content.setBackground(Color.BLACK);

    // PHOTO LABEL STLYING
    JLabel photoLb = photoLabel;
    // BACKGROUND PHOTO
    ImageIcon photo1 = new ImageIcon(loader.getResource("images/homescreen.jpg"));
    Image image = photo1.getImage();
    Image newPhoto = image.getScaledInstance(900, 400, Image.SCALE_SMOOTH);
    photo1 = new ImageIcon(newPhoto);
    // SET ICON
    photoLb.setIcon(photo1);
    photoLb.setSize(900, 400);
    photoLb.setBounds(50, 100, 900, 400);
    // WIN BANNER STYLING
    winBanner.setForeground(Color.WHITE);
    winBanner.setFont(new Font("Serif", Font.BOLD, 40));

    // PLAY AGAIN BANNER STYLING & RADIO BUTTONS
    playAgainBanner.setForeground(Color.WHITE);
    playAgainBanner.setFont(new Font("Serif", Font.BOLD, 25));
    yesPlay.setForeground(Color.BLACK);
    noPLay.setForeground(Color.BLACK);

    // MAYBE ADD TIMER AND GOODBYE MESSAGE
    noPLay.addActionListener(endGame);

  }

  public JPanel getPrimaryPanel() {
    return primaryPanel;
  }

  public JRadioButton getYesPlay() {
    return yesPlay;
  }

  public JRadioButton getNoPLay() {
    return noPLay;
  }


}
