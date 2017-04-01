package me.ranol.fakemc.minecraft;

import javax.swing.*;
import java.awt.*;

public class ImageRenderer extends JPanel{
	private final Image img;

	public ImageRenderer(Image img) {
		this.img = img;
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
	}
}
