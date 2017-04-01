package me.ranol.fakemc.view.tabs;

import javax.swing.*;

public class NewsTab extends JScrollPane {
	public NewsTab(JEditorPane pane) {
		super(pane);
		try {
			pane.setPage("http://mcupdate.tumblr.com");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public NewsTab() {
		this(new JEditorPane());
	}
}
