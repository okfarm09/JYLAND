package project.jyland.chat;

public class ChatFilter {
	
//	private String message;
//	private String sender;
//	private String content;
//	
//	public String getMessage() {
//		return this.sender+" : "+this.content;
//	}
//	
//	public void setMessage(String msg) {
//		this.message=msg;
//	}
	
	public String rmScript(String msg) {
		String ss="";
		if(msg.contains("<sc")||msg.contains("</sc")) {
			ss=msg.replaceAll("<", "&lt;");
		}else {
			ss=msg;
		}
		return ss;
	}
	
//	private void splitOr(String msg) {
//		String[] msgs=msg.split("|");
//		this.sender=msgs[0];
//		this.content=msgs[1];
//	}

}
