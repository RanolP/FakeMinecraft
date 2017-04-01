package me.ranol.percentswing;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;

import static me.ranol.percentswing.Percentage.Type.values;

public class PercentageLayout implements LayoutManager2 {
	private HashMap<Component, Percentage> percentageMap = new HashMap<>();
	private static boolean DEBUG = false;

	public static void debug(boolean val) {
		DEBUG = val;
	}

	@Override
	public void addLayoutComponent(String name, Component comp) {
		throw new UnsupportedOperationException("Not supported yet");
	}

	@Override
	public void addLayoutComponent(Component comp, Object constraints) {
		if (constraints != null) {
			if (constraints instanceof Percentage) {
				percentageMap.put(comp, (Percentage) constraints);
			} else if (constraints instanceof Percentage.Builder) {
				percentageMap.put(comp, ((Percentage.Builder) constraints).build());
			} else {
				throw new IllegalArgumentException("constraints parameter must be of type Percentage!");
			}
		} else {
			percentageMap.put(comp, Percentage.EMPTY);
		}
	}

	@Override
	public Dimension maximumLayoutSize(Container target) {
		return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
	}

	@Override
	public float getLayoutAlignmentX(Container target) {
		return 0.5f;
	}

	@Override
	public float getLayoutAlignmentY(Container target) {
		return 0.5f;
	}

	@Override
	public void invalidateLayout(Container target) {
		// do nothing
	}

	@Override
	public void removeLayoutComponent(Component comp) {

	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		return parent.getSize();
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		return null;
	}

	@Override
	public void layoutContainer(Container parent) {
		synchronized (parent.getTreeLock()) {
			Component[] comp = parent.getComponents();
			Dimension pdim = parent.getSize();
			Insets insets = parent.getInsets();
			if (DEBUG) {
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Parent's Class     :: " + parent.getClass().getName());
				System.out.println("Parent's Dimension :: " + pdim);
				System.out.println("Parent's Inset     :: " + insets);
			}
			for (Component c : comp) {
				Percentage got = percentageMap.getOrDefault(c, Percentage.EMPTY);
				int[] xywh = new int[4];
				for (int i = 3; i >= 0; i--) {
					xywh[i] = got.from(values()[i], pdim, 100d, insets);
				}
				if (DEBUG) {
					System.out.println("Component Class    :: " + c.getClass().getName());
					System.out.println("Got Percentage     :: " + got);
					System.out.println("x,y,width,height   :: " + Arrays.toString(xywh));
				}
				c.setBounds(xywh[0], xywh[1], xywh[2], xywh[3]);
			}
		}
	}
}
