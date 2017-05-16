package project.jyland.board.model;

import java.io.Serializable;

//project.jyland.board.model.JYBoard
import java.sql.Date;

public class JYBoard implements Serializable {

	private int seq;
	private String id;
	private String title;
	private String content;
	private String location;
	private int readcount;
	private int likecount;
	private int hatecount;
	private Date wdate;
	private String upload;
	private int catid;
	private int del;
	private String ip;
	private int regionid;
	private int commentcount;
	private String catname;
	private String dateForMain;
	
	public JYBoard() {
		super();
	}
	public JYBoard(int seq, String id, String title, String content, String location, int readcount, int likecount,
			int hatecount, Date wdate, String upload, int catid, int del, String ip, int regionid) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.location = location;
		this.readcount = readcount;
		this.likecount = likecount;
		this.hatecount = hatecount;
		this.wdate = wdate;
		this.upload = upload;
		this.catid = catid;
		this.del = del;
		this.ip = ip;
		this.regionid = regionid;
	}
	public JYBoard(int seq, String id, String title, String content, String location, int readcount, int likecount,
			int hatecount, Date wdate, String upload, int catid, int del, String ip, int regionid, int commentcount,
			String catname) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.location = location;
		this.readcount = readcount;
		this.likecount = likecount;
		this.hatecount = hatecount;
		this.wdate = wdate;
		this.upload = upload;
		this.catid = catid;
		this.del = del;
		this.ip = ip;
		this.regionid = regionid;
		this.commentcount = commentcount;
		this.catname = catname;
	}
	public JYBoard(String id, String title, String content, String location, String upload, int catid, String ip,
			int regionid) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.location = location;
		this.upload = upload;
		this.catid = catid;
		this.ip = ip;
		this.regionid = regionid;
	}
	public JYBoard(int seq, String id, String title, int readcount, Date wdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.readcount = readcount;
		this.wdate = wdate;
	}
	@Override
	public String toString() {
		return "JYBoard [seq=" + seq + ", id=" + id + ", title=" + title + ", content=" + content + ", location="
				+ location + ", readcount=" + readcount + ", likecount=" + likecount + ", hatecount=" + hatecount
				+ ", wdate=" + wdate + ", upload=" + upload + ", catid=" + catid + ", del=" + del + ", ip=" + ip
				+ ", regionid=" + regionid + ", commentcount=" + commentcount + ", catname=" + catname + "]";
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public int getLikecount() {
		return likecount;
	}
	public void setLikecount(int likecount) {
		this.likecount = likecount;
	}
	public int getHatecount() {
		return hatecount;
	}
	public void setHatecount(int hatecount) {
		this.hatecount = hatecount;
	}
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}
	public String getUpload() {
		return upload;
	}
	public void setUpload(String upload) {
		this.upload = upload;
	}
	public int getCatid() {
		return catid;
	}
	public void setCatid(int catid) {
		this.catid = catid;
	}
	public int getDel() {
		return del;
	}
	public void setDel(int del) {
		this.del = del;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getRegionid() {
		return regionid;
	}
	public void setRegionid(int regionid) {
		this.regionid = regionid;
	}
	public int getCommentcount() {
		return commentcount;
	}
	public void setCommentcount(int commentcount) {
		this.commentcount = commentcount;
	}
	public String getCatname() {
		return catname;
	}
	public void setCatname(String catname) {
		this.catname = catname;
	}
	public String getDateForMain() {
		return dateForMain;
	}
	public void setDateForMain(String dateForMain) {
		this.dateForMain = dateForMain;
	}
	
	
}
