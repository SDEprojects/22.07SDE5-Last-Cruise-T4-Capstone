package com.lastcruise.view;

import com.lastcruise.model.Music;
import com.lastcruise.model.SoundEffect;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
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
    ImageIcon image = new ImageIcon(getClass().getClassLoader().getResource("images/settings.png"));
    frame.setIconImage(image.getImage());
    dialog = new JDialog(frame, "Settings", true);
    dialog.setLayout(null);

    JLabel musicControls = new JLabel("Music Volume: ");
    musicControls.setBounds(10,10,200, 20);

    JButton mute = new JButton("Mute");
    mute.setBounds(10, 30, 100, 30);
    mute.addActionListener(e -> Music.muteMusic());

    JButton unmute = new JButton("Unmute");
    unmute.setBounds(110, 30, 100, 30);
    unmute.addActionListener(e -> Music.unMuteMusic());

    JButton musicDown = new JButton("-");
    musicDown.setBounds(10,60,100,30);
    musicDown.addActionListener(e -> Music.decreaseMusic());

    JButton musicUp = new JButton("+");
    musicUp.setBounds(110,60,100,30);
    musicUp.addActionListener(e -> Music.increaseMusic());

    JLabel soundFxControls = new JLabel("Sound FX Volume:");
    soundFxControls.setBounds(10,90, 200, 20);

    JButton fxMute = new JButton("Mute");
    fxMute.setBounds(10,110,100,30);
    fxMute.addActionListener(e -> SoundEffect.muteSoundFx());

    JButton fxUnmute = new JButton("Unmute");
    fxUnmute.setBounds(110,110,100,30);
    fxUnmute.addActionListener(e -> SoundEffect.unMuteSoundFx());

    JButton fxDown = new JButton("-");
    fxDown.setBounds(10,140,100,30);
    fxDown.addActionListener(e -> SoundEffect.decreaseFxVolume());

    JButton fxUp = new JButton("+");
    fxUp.setBounds(110,140,100,30);
    fxUp.addActionListener(e -> SoundEffect.increaseFxVolume());

    JButton quit = new JButton("Exit Game");
    quit.setBounds(90,230,100,30);
    quit.addActionListener(e -> System.exit(0));

    dialog.add(musicControls);
    dialog.add(mute);
    dialog.add(unmute);
    dialog.add(musicDown);
    dialog.add(musicUp);
    dialog.add(soundFxControls);
    dialog.add(fxMute);
    dialog.add(fxUnmute);
    dialog.add(fxUp);
    dialog.add(fxDown);
    dialog.add(quit);

    dialog.setSize(300, 300);

    dialog.setLocationRelativeTo(null);
    dialog.setVisible(true);
  }
}
