package com.ham.app.vo;

public class TestVO extends FavVO{

	public String uid;
	public int aid;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	
	@Override
	public String toString() {
		return "TestVO [uid=" + uid + ", aid=" + aid + "]";
	}
	
}
