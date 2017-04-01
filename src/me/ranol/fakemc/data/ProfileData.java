package me.ranol.fakemc.data;

public class ProfileData {
	public static final ProfileData STANDARD = new ProfileData("latest-version", "1.11.2", "");
	private String name;
	private String version;
	private String javaArg = "";

	public ProfileData(String name, String version, String javaArg) {
		this.name = name;
		this.version = version;
		this.javaArg = javaArg;
	}

	public ProfileData(String name, String version) {
		this(name, version, "");
	}

	public ProfileData(String name) {
		this(name, "1.11.2");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getJavaArg() {
		return javaArg;
	}

	public void setJavaArg(String javaArg) {
		this.javaArg = javaArg;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		ProfileData that = (ProfileData) o;

		return name != null ? name.equals(that.name) : that.name == null;
	}

	@Override
	public int hashCode() {
		return name != null ? name.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "ProfileData{" + "name='" + name + '\'' + ", version='" + version + '\'' + ", javaArg='" + javaArg + '\'' + '}';
	}
}