package project.jyland.board.model;

import java.io.Serializable;

public class JYCat implements Serializable {
	
	private int catid;
	private String catname;
	public JYCat(int catid, String catname) {
		super();
		this.catid = catid;
		this.catname = catname;
	}
	public JYCat() {
		super();
	}
	public int getCatid() {
		return catid;
	}
	public void setCatid(int catid) {
		this.catid = catid;
	}
	public String getCatname() {
		return catname;
	}
	public void setCatname(String catname) {
		this.catname = catname;
	}
	@Override
	public String toString() {
		return "JYCat [catid=" + catid + ", catname=" + catname + "]";
	}
}
