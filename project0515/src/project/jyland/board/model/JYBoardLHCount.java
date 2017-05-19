package project.jyland.board.model;

import java.io.Serializable;

public class JYBoardLHCount implements Serializable {
	
	private int seq;
	private int boardseq;
	private String userid;
	public JYBoardLHCount(int seq, int boardseq, String userid) {
		super();
		this.seq = seq;
		this.boardseq = boardseq;
		this.userid = userid;
	}
	public JYBoardLHCount() {
		super();
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getBoardseq() {
		return boardseq;
	}
	public void setBoardseq(int boardseq) {
		this.boardseq = boardseq;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "JYBoardLHCount [seq=" + seq + ", boardseq=" + boardseq + ", userid=" + userid + "]";
	}

}
