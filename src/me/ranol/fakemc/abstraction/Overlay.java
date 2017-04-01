package me.ranol.fakemc.abstraction;

import java.awt.*;

@FunctionalInterface
public interface Overlay {
	void paintBefore(Graphics2D g, Component c);

	default void paintAfter(Graphics2D g, Component c) {
	}
}
