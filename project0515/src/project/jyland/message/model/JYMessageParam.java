package project.jyland.message.model;

import java.io.Serializable;
import java.sql.Date;

public class JYMessageParam implements Serializable {
	
	private int seq;
	private String sid;
	private String rid;
	private String content;
	private Date wdate;
	private int readchk;
	
	private int recordCountPerPage=30;
	private int pageNumber=0;
	
	private int start=1;
	private int end=30;
	
	public JYMessageParam() {
		super();
	}
	public JYMessageParam(int seq, String sid, String rid, String content, Date wdate, int readchk) {
		super();
		this.seq = seq;
		this.sid = sid;
		this.rid = rid;
		this.content = content;
		this.wdate = wdate;
		this.readchk = readchk;
	}
	public JYMessageParam(int seq, String sid, String rid, String content, Date wdate, int readchk,
			int recordCountPerPage, int pageNumber, int start, int end) {
		super();
		this.seq = seq;
		this.sid = sid;
		this.rid = rid;
		this.content = content;
		this.wdate = wdate;
		this.readchk = readchk;
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
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}
	public int getReadchk() {
		return readchk;
	}
	public void setReadchk(int readchk) {
		this.readchk = readchk;
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
		return "JYMessageParam [seq=" + seq + ", sid=" + sid + ", rid=" + rid + ", content=" + content + ", wdate="
				+ wdate + ", readchk=" + readchk + ", recordCountPerPage=" + recordCountPerPage + ", pageNumber="
				+ pageNumber + ", start=" + start + ", end=" + end + "]";
	}

}
