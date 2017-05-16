package project.jyland.message.model;

import java.io.Serializable;
import java.sql.Date;

public class JYMessage implements Serializable {
	
	private int seq;
	private String sid;
	private String rid;
	private String content;
	private Date wdate;
	private int readchk;
	public JYMessage() {
		super();
	}
	public JYMessage(int seq, String sid, String rid, String content, Date wdate, int readchk) {
		super();
		this.seq = seq;
		this.sid = sid;
		this.rid = rid;
		this.content = content;
		this.wdate = wdate;
		this.readchk = readchk;
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
	@Override
	public String toString() {
		return "JYMessage [seq=" + seq + ", sid=" + sid + ", rid=" + rid + ", content=" + content + ", wdate=" + wdate
				+ ", readchk=" + readchk + "]";
	}

}
