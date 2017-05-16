package project.jyland.data.model;

import java.io.Serializable;

//project.jyland.map.model.Region

public class Region implements Serializable {
	
	private int regionid;
	private String regionname;
	public Region() {
		super();
	}
	public Region(int regionid, String regionname) {
		super();
		this.regionid = regionid;
		this.regionname = regionname;
	}
	@Override
	public String toString() {
		return "Region [regionid=" + regionid + ", regionname=" + regionname + "]";
	}
	public int getRegionid() {
		return regionid;
	}
	public void setRegionid(int regionid) {
		this.regionid = regionid;
	}
	public String getRegionname() {
		return regionname;
	}
	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}

}
