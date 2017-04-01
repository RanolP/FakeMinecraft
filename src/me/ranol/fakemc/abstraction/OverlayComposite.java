package me.ranol.fakemc.abstraction;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class OverlayComposite<T extends Component & Overlayable> implements Overlayable {
	private final T component;
	private final Set<Overlay> overlays = new HashSet<>();

	public OverlayComposite(T component) {
		this.component = component;
	}

	@Override
	public void paintSuper(Graphics g) {
		component.paintSuper(g);
	}

	@Override
	public Set<Overlay> getOverlays() {
		return overlays;
	}

	@Override
	public Component getSelf() {
		return component;
	}

	@Override
	public void addOverlay(Overlay over) {
		overlays.add(over);
	}

	@Override
	public void removeOverlay(Overlay over) {
		overlays.remove(over);
	}

	@Override
	public int getWidth() {
		return component.getWidth();
	}

	@Override
	public int getHeight() {
		return component.getHeight();
	}
}
