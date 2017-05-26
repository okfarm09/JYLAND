package project.jyland.book.model;

import java.io.Serializable;

public class JYBookimg implements Serializable {
	
	private String filename;
	private int seq;
	@Override
	public String toString() {
		return "JYBookimg [filename=" + filename + ", seq=" + seq + "]";
	}
	public JYBookimg() {
		super();
	}
	public JYBookimg(String filename, int seq) {
		super();
		this.filename = filename;
		this.seq = seq;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}

}
