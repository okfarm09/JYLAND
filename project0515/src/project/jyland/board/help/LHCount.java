package project.jyland.board.help;

import java.io.Serializable;

import project.jyland.board.model.JYBoard;

public class LHCount implements Serializable {
	
	private int likecount;
	private int hatecount;
	private String message;
	public LHCount(int likecount, int hatecount, String message) {
		super();
		this.likecount = likecount;
		this.hatecount = hatecount;
		this.message = message;
	}
	public LHCount() {
		super();
	}
	public LHCount(JYBoard board) {
		super();
		this.likecount = board.getLikecount();
		this.hatecount = board.getHatecount();
		
	}
	@Override
	public String toString() {
		return "LHCount [likecount=" + likecount + ", hatecount=" + hatecount + ", message=" + message + "]";
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
