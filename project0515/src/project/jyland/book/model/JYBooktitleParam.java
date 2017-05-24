package project.jyland.book.model;

import java.io.Serializable;
import java.sql.Date;

public class JYBooktitleParam implements Serializable {
	
	private int seq;
	private String title;
	private String id;
	private Date wdate;
	private Date udate;
	
	private int recordCountPerPage=30;
	private int pageNumber=0;
	
	private int start=1;
	private int end=30;
	public JYBooktitleParam() {
		super();
	}
	public JYBooktitleParam(int seq, String title, String id, Date wdate, Date udate, int recordCountPerPage,
			int pageNumber, int start, int end) {
		super();
		this.seq = seq;
		this.title = title;
		this.id = id;
		this.wdate = wdate;
		this.udate = udate;
		this.recordCountPerPage = recordCountPerPage;
		this.pageNumber = pageNumber;
		this.start = start;
		this.end = end;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}
	public Date getUdate() {
		return udate;
	}
	public void setUdate(Date udate) {
		this.udate = udate;
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
	@Override
	public String toString() {
		return "JYBooktitleParam [seq=" + seq + ", title=" + title + ", id=" + id + ", wdate=" + wdate + ", udate="
				+ udate + ", recordCountPerPage=" + recordCountPerPage + ", pageNumber=" + pageNumber + ", start="
				+ start + ", end=" + end + "]";
	}

}
