package me.ranol.fakemc.view;

import me.ranol.fakemc.minecraft.MinecraftGame;
import me.ranol.fakemc.view.bottom.ProfileView;
import me.ranol.percentswing.Percentage;
import me.ranol.percentswing.PercentageLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BottomPanel extends JPanel {
	public BottomPanel() {
		super(new PercentageLayout());
		add(ProfileView.INSTANCE, Percentage.builder().height(100).width(30));
		JButton startGame = new JButton("게임 시작");
		startGame.addActionListener(a -> {
			ProfileView.INSTANCE.getSelectedData().ifPresent(dat -> {
				MinecraftGame game = new MinecraftGame(dat);
			});
		});
		add(startGame, Percentage.builder().height(90).x(35).y(5).width(30));
		add(new JLabel("ID :"), Percentage.builder().height(90).x(75).y(5).width(5));
		add(new JTextField() {
			boolean ured = false;

			void a() {
				SwingUtilities.invokeLater(() -> {
					ured = getText().length() > 16 || getText().isEmpty();
					repaint();
				});
			}

			{
				addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						a();
					}

					@Override
					public void keyPressed(KeyEvent e) {
						a();
					}
				});
				addFocusListener(new FocusAdapter() {
					@Override
					public void focusLost(FocusEvent e) {
						a();
					}
				});
			}

			@Override
			public void update(Graphics g) {
				paint(g);
			}

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				if (ured) {
					g.setColor(Color.RED);
					g.fillRect(0, getHeight() - 2, getWidth(), getHeight());
				}
			}
		}, Percentage.builder().height(40).x(80).y(30).width(20));
	}
}