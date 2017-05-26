package project.jyland.helper;

import java.util.UUID;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class ApiCreator {

	
	private String rmDash(String msg) {
		String temps[];
		if(msg.contains("-")) {
			temps=msg.split("-");
		}else {
			return msg;
		}
		String rtSt="";
		for(String temp:temps) {
			rtSt += temp;
		}
		return rtSt;
	}
	
	public String getApi() {
		return rmDash(UUID.randomUUID().toString());
	}
	
	public String encodeApi(String api) {
		return Base64.encode(api.getBytes());
	}
	
	public String decodeApi(String msg) {
		String api="";
		try {
			api= new String(Base64.decode(msg));
		} catch (Base64DecodingException e) {
			e.printStackTrace();
		}
		return api;
	}

}
