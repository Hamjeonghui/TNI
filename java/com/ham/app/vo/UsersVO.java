package com.ham.app.vo;

public class UsersVO {

	// 멤버변수
	private String uid;
	private String pw;
	private String uname;
	private String ubirth;
	private int uauth;
	
	// getter & setter
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUbirth() {
		return ubirth;
	}
	public void setUbirth(String ubirth) {
		this.ubirth = ubirth;
	}
	public int getUauth() {
		return uauth;
	}
	public void setUauth(int uauth) {
		this.uauth = uauth;
	}
	
	// to String
	@Override
	public String toString() {
		return "UsersVO [uid=" + uid + ", pw=" + pw + ", uname=" + uname + ", ubirth=" + ubirth + ", uauth=" + uauth
				+ "]";
	}
	
}
