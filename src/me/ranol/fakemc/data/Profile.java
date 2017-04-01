package me.ranol.fakemc.data;

import java.util.HashSet;
import java.util.Set;

public class Profile {
	public static final Profile INSTANCE = new Profile();
	private Set<ProfileData> dataSet = new HashSet<>();

	public Profile() {
		addData(ProfileData.STANDARD);
	}

	public boolean hasData(String name) {
		return dataSet.contains(new ProfileData(name));
	}

	public ProfileData getData(String name) {
		return dataSet.stream().filter(d -> d.getName().equals(name)).findFirst().orElse(null);
	}

	public void addData(ProfileData data) {
		dataSet.add(data);
	}

	public void removeData(ProfileData data) {
		dataSet.remove(data);
	}

	public Set<ProfileData> getDataSet() {
		return new HashSet<>(dataSet);
	}
}