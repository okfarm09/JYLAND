package project.jyland.book.model;

import java.io.Serializable;

public class JYBookRatecount implements Serializable {
	
	private String id;
	private int seq;
	public JYBookRatecount(String id, int seq) {
		super();
		this.id = id;
		this.seq = seq;
	}
	public JYBookRatecount() {
		super();
	}
	@Override
	public String toString() {
		return "JYBookRatecount [id=" + id + ", seq=" + seq + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}

}
