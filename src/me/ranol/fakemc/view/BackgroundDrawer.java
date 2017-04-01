package me.ranol.fakemc.view;

import assets.ResourceManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BackgroundDrawer {
	private static final Image bg = ResourceManager.getImage("background.png");

	public static BufferedImage draw(int wpx, int hpx) {
		int ipw = bg.getWidth(null);
		int iph = bg.getHeight(null);
		BufferedImage bi = new BufferedImage(wpx, hpx, BufferedImage.TYPE_3BYTE_BGR);
		BufferedImage l = new BufferedImage(wpx, iph, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D lg = l.createGraphics();
		Graphics2D big = bi.createGraphics();
		for (int x = 0; x < wpx; x += ipw) {
			lg.drawImage(bg, x, 0, null);
		}
		for (int y = 0; y < hpx; y += iph) {
			big.drawImage(l, 0, y, null);
		}
		return bi;
	}
}
