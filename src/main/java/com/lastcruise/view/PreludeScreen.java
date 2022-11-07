package com.lastcruise.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PreludeScreen {
  JPanel mainPanel;
  JTextArea mainLabel;
  JButton continueBtn, preludeSettingsBtn, helpBtn;
  private ClassLoader loader = getClass().getClassLoader();

  public PreludeScreen() {
    buildPreludeScreen();
  }

  public void buildPreludeScreen() {
    mainPanel = new JPanel();
    mainPanel.setSize(1500, 800);
    mainPanel.setLayout(null);
    mainPanel.setBackground(Color.BLACK);

    mainLabel = new JTextArea();
    mainLabel.setForeground(Color.white);
    mainLabel.setFont(new Font("Monospace", Font.PLAIN, 24));
    mainLabel.setBackground(Color.BLACK);
    mainLabel.setWrapStyleWord(true);
    mainLabel.setLineWrap(true);
    mainLabel.setBounds(375, 200, 755, 440);
    mainPanel.add(mainLabel);

    continueBtn = new JButton("Continue");
    continueBtn.setBounds(720, 650, 100, 50);
    continueBtn.setEnabled(false);

    ImageIcon preludeSettingsBtnImg = new ImageIcon(loader.getResource("images/settings.png"));
    preludeSettingsBtn = new JButton(preludeSettingsBtnImg);
    preludeSettingsBtn.setBounds(1400, 15, 65 ,65);
    preludeSettingsBtn.addActionListener(e -> new SettingsScreen());
    preludeSettingsBtn.setOpaque(false);
    preludeSettingsBtn.setFocusPainted(false);
    preludeSettingsBtn.setContentAreaFilled(false);
    preludeSettingsBtn.setBorderPainted(false);
    preludeSettingsBtn.setBorder(null);

    helpBtn = new JButton(new ImageIcon(loader.getResource("images/help.png")));
    helpBtn.setBounds(1400, 100, 65, 65);
    helpBtn.setOpaque(false);
    helpBtn.setFocusPainted(false);
    helpBtn.setContentAreaFilled(false);
    helpBtn.setBorderPainted(false);
    helpBtn.setBorder(null);
    helpBtn.addActionListener(e -> new HelpScreen());


    mainPanel.add(preludeSettingsBtn);
    mainPanel.add(continueBtn);
    mainPanel.add(helpBtn);
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
