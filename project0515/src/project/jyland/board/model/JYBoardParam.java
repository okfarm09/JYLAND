package project.jyland.board.model;

import java.io.Serializable;
import java.sql.Date;

public class JYBoardParam implements Serializable {
	
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
	
	private int recordCountPerPage=30;
	private int pageNumber=0;
	
	private int start=1;
	private int end=30;
	
	private String keyword;
	public JYBoardParam() {
		super();
	}
	public JYBoardParam(int seq, String id, String title, String content, String location, int readcount, int likecount,
			int hatecount, Date wdate, String upload, int catid, int del, String ip, int regionid,
			int recordCountPerPage, int pageNumber, int start, int end, String keyword) {
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
		this.recordCountPerPage = recordCountPerPage;
		this.pageNumber = pageNumber;
		this.start = start;
		this.end = end;
		this.keyword = keyword;
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
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	@Override
	public String toString() {
		return "JYBoardParam [seq=" + seq + ", id=" + id + ", title=" + title + ", content=" + content + ", location="
				+ location + ", readcount=" + readcount + ", likecount=" + likecount + ", hatecount=" + hatecount
				+ ", wdate=" + wdate + ", upload=" + upload + ", catid=" + catid + ", del=" + del + ", ip=" + ip
				+ ", regionid=" + regionid + ", recordCountPerPage=" + recordCountPerPage + ", pageNumber=" + pageNumber
				+ ", start=" + start + ", end=" + end + ", keyword=" + keyword + "]";
	}
}
