package project.jyland.member.model;

import java.io.Serializable;

//project.jyland.member.model.JYUser

public class JYUser implements Serializable {
	
	private String id;
	private String upwd;
	private String nickname;
	private String email;
	private int auth;
	private String api;
	public JYUser(String id, String upwd, String nickname, String email, int auth, String api) {
		super();
		this.id = id;
		this.upwd = upwd;
		this.nickname = nickname;
		this.email = email;
		this.auth = auth;
		this.api = api;
	}
	public JYUser() {
		super();
	}
	public JYUser(String id, String upwd, String nickname, String email, int auth) {
		super();
		this.id = id;
		this.upwd = upwd;
		this.nickname = nickname;
		this.email = email;
		this.auth = auth;
	}
	public JYUser(String id, String upwd, String nickname, String email) {
		super();
		this.id = id;
		this.upwd = upwd;
		this.nickname = nickname;
		this.email = email;
	}
	public JYUser(String id, String upwd) {
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
	public String getApi() {
		return api;
	}
	public void setApi(String api) {
		this.api = api;
	}
	@Override
	public String toString() {
		return "JYUser [id=" + id + ", upwd=" + upwd + ", nickname=" + nickname + ", email=" + email + ", auth=" + auth
				+ ", api=" + api + "]";
	}

}
