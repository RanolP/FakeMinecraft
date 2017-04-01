package me.ranol.fakemc.minecraft.panorama;

import assets.ResourceManager;

import java.awt.image.BufferedImage;

public class PanoramaGetter {
	private static final BufferedImage[] PANORAMA = {
			ResourceManager.getImage("panorama/1.png"),
			ResourceManager.getImage("panorama/2.png"),
			ResourceManager.getImage("panorama/3.png"),
			ResourceManager.getImage("panorama/4.png"),
			ResourceManager.getImage("panorama/5.png"),
			ResourceManager.getImage("panorama/6.png")
	};

	public static BufferedImage getPanorama() {
		return PANORAMA[(int) (System.currentTimeMillis() / 10000) % 6];
	}
}
