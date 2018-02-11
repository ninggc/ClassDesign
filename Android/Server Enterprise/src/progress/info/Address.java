package progress.info;

import com.google.gson.Gson;

public class Address implements IInfo {
	private String user_id;
	private String homeOrLocal;
	private String country;
	private String province;
	private String city;

	@Override
	public String toString() {
		return this.toJSON();
	}

	@Override
	public String toJSON() {
		return new Gson().toJson(this).toString();
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getHomeOrLocal() {
		return homeOrLocal;
	}

	public void setHomeOrLocal(String homeOrLocal) {
		this.homeOrLocal = homeOrLocal;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
