package com.lastcruise.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PreludeScreen {
  JPanel mainPanel;
  JTextArea mainLabel;
  JFrame testFrame;
  JButton continueBtn, preludeSettingsBtn;

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
    mainLabel.setFont(new Font("Monospace", Font.PLAIN, 24));
    mainLabel.setBackground(Color.BLACK);
    mainLabel.setLineWrap(true);
    mainLabel.setBounds(375, 200, 750, 440);
    mainPanel.add(mainLabel);

    continueBtn = new JButton("Continue");
    continueBtn.setBounds(750, 650, 100, 50);

    ImageIcon preludeSettingsBtnImg = new ImageIcon(getClass().getClassLoader().getResource("images/settings.png"));
    preludeSettingsBtn = new JButton(preludeSettingsBtnImg);
    preludeSettingsBtn.setBounds(1400, 15, 65 ,65);
    preludeSettingsBtn.addActionListener(e -> new SettingsScreen());
    preludeSettingsBtn.setOpaque(false);
    preludeSettingsBtn.setFocusPainted(false);
    preludeSettingsBtn.setContentAreaFilled(false);
    preludeSettingsBtn.setBorderPainted(false);
    preludeSettingsBtn.setBorder(null);


    mainPanel.add(preludeSettingsBtn);
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
