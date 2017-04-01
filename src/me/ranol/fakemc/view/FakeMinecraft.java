package me.ranol.fakemc.view;

import assets.ResourceManager;
import me.ranol.percentswing.Percentage;
import me.ranol.percentswing.PercentageLayout;

import javax.swing.*;

public class FakeMinecraft extends JFrame {
	/*
	 * 만우절 기념 프로젝트입니다.
	 */

	public static final FakeMinecraft fake;

	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		fake = new FakeMinecraft();
	}

	public static void main(String[] args) {
		fake.setVisible(true);
	}

	private FakeMinecraft() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		setSize(Util.sizeBy(47, 54));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setIconImage(ResourceManager.getImage("favicon.png"));
		setTitle("FakeMinecraft - by Ranol_");
		setLayout(new PercentageLayout());
		add(new TabMaster(), Percentage.builder().width(100).height(89));
		add(new BottomPanel(), Percentage.builder().y(89).width(100).height(11));
	}
}