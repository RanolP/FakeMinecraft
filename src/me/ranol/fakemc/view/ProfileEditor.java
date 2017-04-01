package me.ranol.fakemc.view;

import me.ranol.fakemc.data.ProfileData;
import me.ranol.fakemc.view.bottom.ProfileView;
import me.ranol.percentswing.Percentage;
import me.ranol.percentswing.PercentageLayout;

import javax.swing.*;
import java.util.Vector;

public class ProfileEditor extends JDialog {
	private static final Vector<String> VERSIONS = new Vector<String>() {
		{
			add("release 1.11.2");
			add("release 1.11.1");
			add("release 1.11");
			add("release 1.10.2");
			add("release 1.10.1");
			add("release 1.10");
			add("release 1.9.4");
			add("release 1.9.3");
			add("release 1.9.2");
			add("release 1.9.1");
			add("release 1.9");
			add("release 1.8.9");
			add("release 1.8.8");
			add("release 1.8.7");
			add("release 1.8.6");
			add("release 1.8.5");
			add("release 1.8.4");
			add("release 1.8.3");
			add("release 1.8.2");
			add("release 1.8.1");
			add("release 1.8");
			add("release 1.7.10");
			add("release 1.7.9");
			add("release 1.7.8");
			add("release 1.7.7");
			add("release 1.7.6");
			add("release 1.7.5");
			add("release 1.7.4");
			add("release 1.7.3");
			add("release 1.7.2");
			add("release 1.6.4");
			add("release 1.6.2");
			add("release 1.6.1");
			add("release 1.5.2");
			add("release 1.5.1");
			add("release 1.4.7");
			add("release 1.4.6");
			add("release 1.4.5");
			add("release 1.4.4");
			add("release 1.4.2");
			add("release 1.3.2");
			add("release 1.3.1");
			add("release 1.2.5");
			add("release 1.2.4");
			add("release 1.2.3");
			add("release 1.2.2");
			add("release 1.2.1");
			add("release 1.1");
			add("release 1.0");
		}
	};
	private ProfileData data;
	private JTextField profileName = new JTextField();
	private JComboBox<String> version = new JComboBox<>(VERSIONS);
	private JTextField javaArg = new JTextField();
	private ProfileView owner;

	public ProfileEditor(ProfileView prof, String sel) {
		this(prof, new ProfileData(sel + "의 복사본"));
	}

	public ProfileEditor(ProfileView prof, ProfileData data) {
		super(FakeMinecraft.fake);
		owner = prof;
		setLayout(new PercentageLayout());
		this.data = data;
		profileName.setText(data.getName());
		setTitle("프로필 수정");
		setSize(Util.sizeBy(30, 30));
		setLocation(Util.center(FakeMinecraft.fake, this));
		add(new JLabel("프로필 이름 :"), Percentage.builder().x(5).y(5).width(15).height(10));
		add(profileName, Percentage.builder().x(21).y(5).width(65).height(10));
		add(new JLabel("버전 :"), Percentage.builder().x(5).y(25).width(15).height(10));
		add(version, Percentage.builder().x(21).y(25).width(65).height(10));
		version.setSelectedItem(data.getVersion());
		add(new JLabel("자바 인수 :"), Percentage.builder().x(5).y(45).width(15).height(10));
		add(javaArg, Percentage.builder().x(21).y(45).width(65).height(10));
		JButton cancel = new JButton("취소");
		cancel.addActionListener(a -> cancel());
		add(cancel, Percentage.builder().x(5).y(85).width(20).height(6));
		JButton save = new JButton("저장");
		save.addActionListener(a -> save());
		add(save, Percentage.builder().x(65).y(85).width(20).height(6));
	}

	private void save() {
		if(profileName.getText().isEmpty()){
			JOptionPane.showMessageDialog(this, "프로필 이름은 공란일 수 없습니다!", "오류", JOptionPane.ERROR_MESSAGE);
			return;
		}
		data.setName(profileName.getText());
		data.setVersion(String.valueOf(version.getSelectedItem()));
		data.setJavaArg(javaArg.getText());
		owner.addData(data);
		cancel();
	}

	private void cancel() {
		dispose();
	}
}
