package project.jyland.book.model;

import java.io.Serializable;
import java.sql.Date;

public class JYBooktitle implements Serializable {
	
	private int seq;
	private String title;
	private String id;
	private Date wdate;
	private Date udate;
	public JYBooktitle() {
		super();
	}
	public JYBooktitle(int seq, String title, String id, Date wdate, Date udate) {
		super();
		this.seq = seq;
		this.title = title;
		this.id = id;
		this.wdate = wdate;
		this.udate = udate;
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
	@Override
	public String toString() {
		return "JYBooktitle [seq=" + seq + ", title=" + title + ", id=" + id + ", wdate=" + wdate + ", udate=" + udate
				+ "]";
	}

}
