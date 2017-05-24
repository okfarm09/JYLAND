package project.jyland.book.model;

import java.io.Serializable;
import java.sql.Date;

public class JYBook implements Serializable {
	
	private int seq;
	private int titleseq;
	private String id;
	private String title;
	private String content;
	private int readcount;
	private int rate;
	private int ratecount;
	private Date wdate;
	public JYBook() {
		super();
	}
	public JYBook(int seq, int titleseq, String id, String title, String content, int readcount, int rate,
			int ratecount, Date wdate) {
		super();
		this.seq = seq;
		this.titleseq = titleseq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.readcount = readcount;
		this.rate = rate;
		this.ratecount = ratecount;
		this.wdate = wdate;
	}
	public JYBook(int seq, String id, String title, String content, int readcount, int rate, int ratecount,
			Date wdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.readcount = readcount;
		this.rate = rate;
		this.ratecount = ratecount;
		this.wdate = wdate;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getTitleseq() {
		return titleseq;
	}
	public void setTitleseq(int titleseq) {
		this.titleseq = titleseq;
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
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public int getRatecount() {
		return ratecount;
	}
	public void setRatecount(int ratecount) {
		this.ratecount = ratecount;
	}
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}
	@Override
	public String toString() {
		return "JYBook [seq=" + seq + ", titleseq=" + titleseq + ", id=" + id + ", title=" + title + ", content="
				+ content + ", readcount=" + readcount + ", rate=" + rate + ", ratecount=" + ratecount + ", wdate="
				+ wdate + "]";
	}

}
