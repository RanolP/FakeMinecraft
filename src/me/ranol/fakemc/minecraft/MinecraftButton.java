package me.ranol.fakemc.minecraft;

import assets.ResourceManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MinecraftButton extends JButton {
	private static final Image HOVER = ResourceManager.getImage("btn/btn_hover.png");
	private static final Image NONE = ResourceManager.getImage("btn/btn_none.png");
	private Image current;
	private static Font apply;

	static {
		int size = 20;
		Font temp = null;
		String[] array = {"맑은 고딕"};
		for (int i = 0; temp == null && i < array.length; i++) {
			temp = new Font(array[i], Font.PLAIN, size);
		}
		apply = temp != null ? temp : Font.getFont(Font.DIALOG).deriveFont((float) size);
	}

	public MinecraftButton() {
		current = NONE;
		setFont(apply);
		setForeground(Color.WHITE);
		getModel().addChangeListener(e -> {
			if (getModel().isRollover()) {
				current = HOVER;
			} else {
				current = NONE;
			}
			repaint();
		});
	}

	@Override
	public void paint(Graphics g) {
		BufferedImage buf = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g2d = buf.createGraphics();
		if (current != null) {
			g2d.drawImage(current, 0, 0, getWidth(), getHeight(), null);
		}
		FontMetrics metrics = g.getFontMetrics(getFont());
		Insets i = getInsets();
		int x = (getWidth() - metrics.stringWidth(getText())) / 2 + i.left;
		int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent() - i.bottom;
		g2d.setColor(getForeground());
		g2d.drawString(getText(), x, y);
		g.drawImage(buf, 0, 0, null);
		buf.flush();
		g2d.dispose();
	}
}
