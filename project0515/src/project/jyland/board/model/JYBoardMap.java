package project.jyland.board.model;

import java.io.Serializable;
import java.sql.Date;

public class JYBoardMap implements Serializable {

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
	
	private double lat;
	private double lng;
	
	private String rmbk1(String msg) {
		return msg.substring(msg.indexOf("(")+1);
	}
	
	private String rmbk2(String msg) {
		return msg.substring(0, msg.indexOf(")"));
	}
	
	private void latLng(String location) {
		if(!location.equals("none")) {
			String temp=rmbk2(rmbk1(location));
			String temps[]=temp.split(",");
			this.lat=Double.parseDouble(temps[0].trim());
			this.lng=Double.parseDouble(temps[1].trim());
		}else {
			this.lat=1111.1111;
			this.lng=1111.1111;
		}
	}
	
	public JYBoardMap(JYBoard board) {
		super();
		this.seq = board.getSeq();
		this.id = board.getId();
		this.title = board.getTitle();
		this.content = board.getContent();
		this.location = board.getLocation();
		this.readcount = board.getReadcount();
		this.likecount = board.getLikecount();
		this.hatecount = board.getHatecount();
		this.wdate = board.getWdate();
		this.upload = board.getUpload();
		this.catid = board.getCatid();
		this.del = board.getDel();
		this.ip = board.getIp();
		this.regionid = board.getRegionid();
		latLng(board.getLocation());
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
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	@Override
	public String toString() {
		return "JYBoardMap [seq=" + seq + ", id=" + id + ", title=" + title + ", content=" + content + ", location="
				+ location + ", readcount=" + readcount + ", likecount=" + likecount + ", hatecount=" + hatecount
				+ ", wdate=" + wdate + ", upload=" + upload + ", catid=" + catid + ", del=" + del + ", ip=" + ip
				+ ", regionid=" + regionid + ", lat=" + lat + ", lng=" + lng + "]";
	}
	
}
