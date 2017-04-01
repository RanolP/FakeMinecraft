package me.ranol.fakemc.view;

import java.awt.*;

public class Util {
	public static Dimension sizeBy(double wPerc, double hPerc) {
		return sizeBy(screen(), wPerc, hPerc);
	}

	public static Dimension sizeBy(Dimension origin, double wPerc, double hPerc) {
		Dimension size = new Dimension(origin);
		size.width = (int) (size.width * wPerc / 100);
		size.height = (int) (size.height * hPerc / 100);
		return size;
	}

	public static Dimension screen(){
		return Toolkit.getDefaultToolkit().getScreenSize();
	}

	public static Point center(Component origin, Component self) {
		return new Point(origin.getX() + (origin.getWidth() - self.getWidth()) / 2,
		                 origin.getY() + (origin.getHeight() - self.getHeight()) / 2);
	}

	public static Point center(Dimension origin, Component self) {
		return new Point((origin.width - self.getWidth()) / 2, (origin.height - self.getHeight()) / 2);
	}
}
