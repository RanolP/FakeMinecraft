package me.ranol.fakemc.minecraft;

import assets.ResourceManager;
import me.ranol.fakemc.data.ProfileData;
import me.ranol.fakemc.view.Util;
import me.ranol.percentswing.Percentage;
import me.ranol.percentswing.PercentageLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class MinecraftGame extends JFrame {
	ProfileData data;
	private boolean loading = true;
	private Image mojang = ResourceManager.getImage("mojang.png");

	public MinecraftGame(ProfileData data) throws HeadlessException {
		this.data = data;
		setSize(Util.sizeBy(45, 46));
		setLocation(Util.center(Util.screen(), this));
		setTitle("Minecraft " + data.getVersion().replace("release", "").trim());
		setVisible(true);
		MinecraftPanel pan = new MinecraftPanel(this);
		pan.setVisible(false);
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				loading = false;
				cancel();
				pan.setVisible(true);
				repaint();
			}
		}, 3000);
		setLayout(new PercentageLayout());
		add(pan, Percentage.MAX);
	}

	@Override
	public void paint(Graphics g) {
		if (loading) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.drawImage(mojang, (getWidth() - getHeight()) / 2, 0, getHeight(), getHeight(), null);
		} else {
			super.paint(g);
		}
	}
}