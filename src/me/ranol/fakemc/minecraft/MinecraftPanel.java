package me.ranol.fakemc.minecraft;

import assets.ResourceManager;
import me.ranol.fakemc.minecraft.panorama.PanoramaGetter;
import me.ranol.percentswing.Percentage;
import me.ranol.percentswing.PercentageLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.net.URI;
import java.util.Timer;
import java.util.TimerTask;

public class MinecraftPanel extends JPanel {
	MinecraftPanel(MinecraftGame owner) {
		setOpaque(false);
		setLayout(new PercentageLayout());
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				if (isVisible()) {
					repaint();
				}
			}
		}, 0, 1000);
		MinecraftButton single = new MinecraftButton();
		single.setText("싱글플레이");
		single.addActionListener(e -> {
			if (Desktop.isDesktopSupported()) {
				try {
					Desktop.getDesktop().browse(new URI("https://minecraft.net/"));
					return;
				} catch (Exception ex) {
				}
			}
			JOptionPane.showMessageDialog(this, "마인크래프트 4만원밖에 안합니다. 그냥 구매하세요.\n ps. 배포날짜가 이상하다고 생각 안 하셨습니까?", "경고",
			                              JOptionPane.ERROR_MESSAGE);
		});
		add(new ImageRenderer(ResourceManager.getImage("logo.png")),
		    Percentage.builder().x(15).width(70).y(10).height(25));
		add(single, Percentage.builder().x(25).width(50).y(40).height(10));
		MinecraftButton multi = new MinecraftButton();
		multi.setText("멀티플레이");
		multi.addActionListener(e -> {
			if (Desktop.isDesktopSupported()) {
				try {
					Desktop.getDesktop().browse(new URI("http://kr.battle.net/heroes/ko/"));
					return;
				} catch (Exception ex) {
				}
			}
			JOptionPane.showMessageDialog(this,
			                              "♚♚히어로즈 오브 더 스☆톰♚♚가입시$$전원 카드팩☜☜뒷면100%증정※ ♜월드오브 워크래프트♜펫 무료증정￥ 특정조건 §§디아블로3§§★공허의유산★초상화획득기회@@@ 즉시이동http://kr.battle.net/heroes/ko/\n\n ps. 배포날짜가 이상하다고 생각 안 하셨습니까?",
			                              "경고", JOptionPane.ERROR_MESSAGE);
		});
		add(multi, Percentage.builder().x(25).width(50).y(55).height(10));
		MinecraftButton setting = new MinecraftButton();
		setting.setText("설정");
		setting.addActionListener(e -> JOptionPane.showMessageDialog(this,
		                                                             "개발자의 나태함으로 인해 설정은 비활성화 되어 있습니다.\n\n ps. 배포날짜가 이상하다고 생각 안 하셨습니까?",
		                                                             "경고", JOptionPane.ERROR_MESSAGE));
		add(setting, Percentage.builder().x(25).width(24.5).y(80).height(10));
		MinecraftButton exit = new MinecraftButton();
		exit.setText("게임 종료");
		exit.addActionListener(e -> owner.dispose());
		add(exit, Percentage.builder().x(50.5).width(24.5).y(80).height(10));
	}

	private float[] blur = {
			0.0625f,
			0.125f,
			0.0625f,
			0.125f,
			0.25f,
			0.125f,
			0.0625f,
			0.125f,
			0.0625f
	};

	@Override
	public void paint(Graphics g) {
		BufferedImage db = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g2d = db.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		Kernel kernel = new Kernel(3, 3, blur);
		BufferedImage pano = PanoramaGetter.getPanorama();
		BufferedImage dest = new BufferedImage(pano.getWidth(), pano.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
		ConvolveOp convolve = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
		convolve.filter(pano, dest);
		g2d.drawImage(dest, 0, 0, getWidth(), getHeight(), null);
		g2d.setColor(new Color(0, 0, 0, 100));
		g2d.fillRect(0, 0, getWidth(), getHeight());
		super.paint(g2d);
		g.drawImage(db, 0, 0, null);
		g2d.dispose();
		db.flush();
	}
}