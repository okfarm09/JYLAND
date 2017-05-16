package project.jyland.message.help;

public class MessageHelper {

	public static String msgContentRmBr(String msg) {
		return msg.replaceAll("<br>", "//");
	}

	public static String msgLength(String msg) {
		String ss = "";
		if (msg.length() > 20) {
			ss = msg.substring(0, 20) + "...";
		} else {
			ss = msg;
		}
		return ss;
	}

}
