package me.ranol.fakemc.view.bottom;

import me.ranol.fakemc.data.Profile;
import me.ranol.fakemc.data.ProfileData;
import me.ranol.fakemc.view.ProfileEditor;
import me.ranol.percentswing.Percentage;
import me.ranol.percentswing.PercentageLayout;

import javax.swing.*;
import java.util.Optional;

public class ProfileView extends JPanel {
	private JComboBox<String> profileBox = new JComboBox<>();
	public static final ProfileView INSTANCE = new ProfileView();

	private ProfileView() {
		super(new PercentageLayout());
		profileBox.setEditable(false);
		add(new JLabel("프로필:"), Percentage.builder().width(20).height(45).x(1));
		add(profileBox, Percentage.builder().width(55).height(45).x(30));
		JButton newProfile = new JButton("새 프로필");
		newProfile.addActionListener(a -> {
			ProfileEditor e = new ProfileEditor(this, String.valueOf(profileBox.getSelectedItem()));
			e.setVisible(true);
		});
		add(newProfile, Percentage.builder().width(40).y(55).height(35));
		JButton editProfile = new JButton("프로필 수정");
		editProfile.addActionListener(a -> {
			if (profileBox.getSelectedIndex() == -1) {
				JOptionPane.showMessageDialog(this, "수정할 프로필을 선택하세요.", "오류", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Optional<ProfileData> dat = getSelectedData();
			if (!dat.isPresent()) {
				JOptionPane.showMessageDialog(this, "프로필 선택에 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
				updateData();
				return;
			}
			ProfileEditor edit = new ProfileEditor(this, dat.get());
			edit.setVisible(true);
		});
		add(editProfile, Percentage.builder().width(40).y(55).height(35).x(45));
		updateData();
	}

	public Optional<ProfileData> getSelectedData() {
		if (profileBox.getSelectedIndex() == -1) {
			return Optional.empty();
		}
		return Optional.ofNullable(Profile.INSTANCE.getData(String.valueOf(profileBox.getSelectedItem())));
	}

	public void addData(ProfileData data) {
		Profile.INSTANCE.addData(data);
		updateData();
	}

	public void removeData(ProfileData data) {
		Profile.INSTANCE.removeData(data);
		updateData();
	}

	public void updateData() {
		Object object = profileBox.getSelectedItem();
		profileBox.removeAllItems();
		for (ProfileData dat : Profile.INSTANCE.getDataSet()) {
			profileBox.addItem(dat.getName());
		}
		profileBox.setSelectedItem(object);
		if (profileBox.getSelectedIndex() == -1 && profileBox.getItemCount() > 0) {
			profileBox.setSelectedIndex(0);
		}
	}
}