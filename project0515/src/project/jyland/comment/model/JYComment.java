package project.jyland.comment.model;

import java.io.Serializable;
import java.sql.Date;

public class JYComment implements Serializable {
	private int seq;
	private String id;
	private String content;
	private int boardseq;
	private Date wdate;
	private int likecount;
	private int hatecount;
	private String ip;
	private int seqReply;
	private int ref;
	private int delflag;
	
	public JYComment() {
		super();
	}

	public JYComment(int seq, String id, String content, int boardseq, Date wdate, int likecount, int hatecount,
			String ip, int seqReply, int ref, int delflag) {
		super();
		this.seq = seq;
		this.id = id;
		this.content = content;
		this.boardseq = boardseq;
		this.wdate = wdate;
		this.likecount = likecount;
		this.hatecount = hatecount;
		this.ip = ip;
		this.seqReply = seqReply;
		this.ref = ref;
		this.delflag = delflag;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getBoardseq() {
		return boardseq;
	}

	public void setBoardseq(int boardseq) {
		this.boardseq = boardseq;
	}

	public Date getWdate() {
		return wdate;
	}

	public void setWdate(Date wdate) {
		this.wdate = wdate;
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getSeqReply() {
		return seqReply;
	}

	public void setSeqReply(int seqReply) {
		this.seqReply = seqReply;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getDelflag() {
		return delflag;
	}

	public void setDelflag(int delflag) {
		this.delflag = delflag;
	}

	@Override
	public String toString() {
		return "JYComment [seq=" + seq + ", id=" + id + ", content=" + content + ", boardseq=" + boardseq + ", wdate="
				+ wdate + ", likecount=" + likecount + ", hatecount=" + hatecount + ", ip=" + ip + ", seqReply="
				+ seqReply + ", ref=" + ref + ", delflag=" + delflag + "]";
	}
	
}
