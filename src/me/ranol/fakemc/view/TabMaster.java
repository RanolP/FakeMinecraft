package me.ranol.fakemc.view;

import me.ranol.fakemc.view.tabs.NewsTab;

import javax.swing.*;

public class TabMaster extends JTabbedPane {
	public TabMaster() {
		add("News", new NewsTab());
	}
}
