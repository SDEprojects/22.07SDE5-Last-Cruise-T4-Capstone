package com.lastcruise.controller;

import com.lastcruise.model.Music;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class SettingsScreen {

  private static JDialog dialog;

  public SettingsScreen() {
    buildSettingScreen();
  }

  private void buildSettingScreen() {
    JFrame frame = new JFrame();
    dialog = new JDialog(frame, "Volume Control", true);
    dialog.setLayout(new FlowLayout());

    JLabel musicControls = new JLabel("Music Controls");
    JButton mute = new JButton("Mute");
    mute.addActionListener(e -> Music.muteMusic());

    JButton unmute = new JButton("Unmute");
    unmute.addActionListener(e -> Music.unMuteMusic());

    dialog.add(musicControls);
    dialog.add(mute);
    dialog.add(unmute);

    dialog.setSize(300, 100);

    dialog.setVisible(true);


  }

}
