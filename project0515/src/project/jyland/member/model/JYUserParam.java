package project.jyland.member.model;

import java.io.Serializable;

//project.jyland.member.model.JYUser

public class JYUserParam implements Serializable {
	
	private String id;
	private String upwd;
	private String nickname;
	private String email;
	private int auth;
	private String api;
	
	private int recordCountPerPage=30;
	private int pageNumber=0;
	
	private int start=1;
	private int end=30;
	
	public JYUserParam() {
		super();
	}
	public JYUserParam(String id, String upwd, String nickname, String email, int auth, String api,
			int recordCountPerPage, int pageNumber, int start, int end) {
		super();
		this.id = id;
		this.upwd = upwd;
		this.nickname = nickname;
		this.email = email;
		this.auth = auth;
		this.api = api;
		this.recordCountPerPage = recordCountPerPage;
		this.pageNumber = pageNumber;
		this.start = start;
		this.end = end;
	}
	public JYUserParam(String id, String upwd, String nickname, String email, int auth) {
		super();
		this.id = id;
		this.upwd = upwd;
		this.nickname = nickname;
		this.email = email;
		this.auth = auth;
	}
	public JYUserParam(String id, String upwd, String nickname, String email) {
		super();
		this.id = id;
		this.upwd = upwd;
		this.nickname = nickname;
		this.email = email;
	}
	public JYUserParam(String id, String upwd, String nickname, String email, int auth, int recordCountPerPage,
			int pageNumber, int start, int end) {
		super();
		this.id = id;
		this.upwd = upwd;
		this.nickname = nickname;
		this.email = email;
		this.auth = auth;
		this.recordCountPerPage = recordCountPerPage;
		this.pageNumber = pageNumber;
		this.start = start;
		this.end = end;
	}
	public JYUserParam(String id, String upwd) {
		super();
		this.id = id;
		this.upwd = upwd;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAuth() {
		return auth;
	}
	public void setAuth(int auth) {
		this.auth = auth;
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
	public String getApi() {
		return api;
	}
	public void setApi(String api) {
		this.api = api;
	}
	@Override
	public String toString() {
		return "JYUserParam [id=" + id + ", upwd=" + upwd + ", nickname=" + nickname + ", email=" + email + ", auth="
				+ auth + ", api=" + api + ", recordCountPerPage=" + recordCountPerPage + ", pageNumber=" + pageNumber
				+ ", start=" + start + ", end=" + end + "]";
	}


}
