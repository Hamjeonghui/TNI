package com.ham.app.vo;

import java.io.Serializable;

public class FavVO implements Serializable{

	// 멤버변수
	private String fid;
	private String uid;
	private int aid;
	
	// getter & setter
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
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
	
	// to String
	@Override
	public String toString() {
		return "Fav [fid=" + fid + ", uid=" + uid + ", aid=" + aid + "]";
	}
	
}
