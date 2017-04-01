package me.ranol.fakemc.abstraction;

import java.awt.*;
import java.util.Set;

public interface COverlayable<T extends Component & Overlayable> extends Overlayable {
	OverlayComposite<T> getComposite();

	@Override
	default Set<Overlay> getOverlays() {
		return getComposite().getOverlays();
	}

	@Override
	default Component getSelf() {
		return getComposite().getSelf();
	}

	@Override
	default void addOverlay(Overlay over) {
		getComposite().addOverlay(over);
	}

	@Override
	default void removeOverlay(Overlay over) {
		getComposite().removeOverlay(over);
	}

	@Override
	default int getWidth() {
		return getComposite().getWidth();
	}

	@Override
	default int getHeight() {
		return getComposite().getHeight();
	}
}
