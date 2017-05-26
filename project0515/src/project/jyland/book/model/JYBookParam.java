package project.jyland.book.model;

import java.io.Serializable;
import java.sql.Date;

public class JYBookParam implements Serializable {
	
	private int seq;
	private int titleseq;
	private String id;
	private String title;
	private String content;
	private int readcount;
	private int rate;
	private int ratecount;
	private Date wdate;
	
	private int recordCountPerPage=30;
	private int pageNumber=0;
	
	private int start=1;
	private int end=30;
	
	public JYBookParam() {
		super();
	}
	public JYBookParam(int seq, int titleseq, String id, String title, String content, int readcount, int rate,
			int ratecount, Date wdate, int recordCountPerPage, int pageNumber, int start, int end) {
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
		this.recordCountPerPage = recordCountPerPage;
		this.pageNumber = pageNumber;
		this.start = start;
		this.end = end;
	}
	public JYBookParam(int seq, String id, String title, String content, int readcount, int rate, int ratecount,
			Date wdate, int recordCountPerPage, int pageNumber, int start, int end) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.readcount = readcount;
		this.rate = rate;
		this.ratecount = ratecount;
		this.wdate = wdate;
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
		return "JYBookParam [seq=" + seq + ", titleseq=" + titleseq + ", id=" + id + ", title=" + title + ", content="
				+ content + ", readcount=" + readcount + ", rate=" + rate + ", ratecount=" + ratecount + ", wdate="
				+ wdate + ", recordCountPerPage=" + recordCountPerPage + ", pageNumber=" + pageNumber + ", start="
				+ start + ", end=" + end + "]";
	}

}
