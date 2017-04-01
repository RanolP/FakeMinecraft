package me.ranol.fakemc.abstraction;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Set;

public interface Overlayable {
	void paintSuper(Graphics g);

	Set<Overlay> getOverlays();

	Component getSelf();

	void addOverlay(Overlay over);

	void removeOverlay(Overlay over);

	int getWidth();

	int getHeight();

	default void update(Graphics g) {
		paint(g);
	}

	default void paint(Graphics g) {
		BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2d = img.createGraphics();
		Set<Overlay> overlays = getOverlays();
		Color std = g2d.getColor();
		for (Overlay over : overlays) {
			over.paintBefore(g2d, getSelf());
			g2d.setColor(std);
		}
		paintSuper(g2d);
		for (Overlay over : overlays) {
			over.paintAfter(g2d, getSelf());
			g2d.setColor(std);
		}
		g.drawImage(img, 0, 0, null);
		img.flush();
	}
}
