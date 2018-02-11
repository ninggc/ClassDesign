package com.ning.enterprise.info;

import com.google.gson.Gson;

public class User implements IUser {

	private String id;
	private String account;
	private String mark;
	private String password;
	private String nickname;
	private String telephone;
	private String birthday;
	private String birType;
	private String portrait;
	private String introduce;

	public User() {
		this("", "", "", "", "", "0000-00-00", "1", "", "");
	}

	public User(String account, String mark, String password,
				String nickname, String telephone, String birthday,
				String birType, String portrait, String introduce) {
		setAccount(account);
		setMark(mark);
		setPassword(password);
		setNickname(nickname);
		setTelephone(telephone);
		setBirthday(birthday);
		setBirType(birType);
		setPortrait(portrait);
		setIntroduce(introduce);
	}

	@Override
	public String toString() {
		return this.toJSON();
	}

	@Override
	public String toJSON() {
		return new Gson().toJson(this).toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getBirType() {
		return birType;
	}

	public void setBirType(String birType) {
		this.birType = birType;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

}
