package com.ham.app.vo;

import org.springframework.web.multipart.MultipartFile;

public class ArticleVO {

	// 멤버변수
	private int aid;
	private String uid;
	private String uname;
	private String title;
	private String acontent;
	private String part;
	private String leadTime;
	private int cnt;
	private int fav;
	private String adate;
	private String addr;
	private int rcnt;
	private int faid; // 로그인 상태의 id가 해당 글을 좋아하는지의 유무(0이면 좋아요아닌상태, 1이 좋아요인상태)
	private String condition; //검색 조건
	private String search; //검색어
	private MultipartFile uploadFile; //타투 이미지
	private String filename; //타투 이미지 파일 이름
	
	// getter & setter
	public int getAid() {
		return aid;
	}
	public int getFaid() {
		return faid;
	}
	public void setFaid(int faid) {
		this.faid = faid;
	}
	public int getRcnt() {
		return rcnt;
	}
	public void setRcnt(int rcnt) {
		this.rcnt = rcnt;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAcontent() {
		return acontent;
	}
	public void setAcontent(String acontent) {
		this.acontent = acontent;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public String getLeadTime() {
		return leadTime;
	}
	public void setLeadTime(String leadTime) {
		this.leadTime = leadTime;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getFav() {
		return fav;
	}
	public void setFav(int fav) {
		this.fav = fav;
	}
	public String getAdate() {
		return adate;
	}
	public void setAdate(String adate) {
		this.adate = adate;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	// to string
	@Override
	public String toString() {
		return "ArticleVO [aid=" + aid + ", uid=" + uid + ", uname=" + uname + ", title=" + title + ", acontent="
				+ acontent + ", part=" + part + ", leadTime=" + leadTime + ", cnt=" + cnt + ", fav=" + fav + ", adate="
				+ adate + ", addr=" + addr + ", rcnt=" + rcnt + ", faid=" + faid + ", condition=" + condition
				+ ", search=" + search + ", uploadFile=" + uploadFile + ", filename=" + filename + "]";
	}
	
}