package com.ham.app.vo;

public class CommentVO {

	// 멤버변수
	private int cid;
	private String uid;
	private int aid;
	private String ccontent;
	private String cdate;
	private int ccheck;
	private int cgroup;
	
	// getter & setter
	public int getCid() {
		return cid;
	}
	public int getCcheck() {
		return ccheck;
	}
	public void setCcheck(int ccheck) {
		this.ccheck = ccheck;
	}
	public int getCgroup() {
		return cgroup;
	}
	public void setCgroup(int cgroup) {
		this.cgroup = cgroup;
	}
	public void setCid(int cid) {
		this.cid = cid;
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
	public String getCcontent() {
		return ccontent;
	}
	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdate) {
		this.cdate = cdate;
	}
	
	// to String
	@Override
	public String toString() {
		return "CommentVO [cid=" + cid + ", uid=" + uid + ", aid=" + aid + ", ccontent=" + ccontent + ", cdate=" + cdate
				+ ", ccheck=" + ccheck + ", cgroup=" + cgroup + "]";
	}
	

	
	
	
}
