package project.jyland.helper;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class GetIp {
	
	public static String getIp(HttpServletRequest request) {
		String ip = null;
		try {
			
			boolean isLoopBack = true;
			Enumeration<NetworkInterface> en=NetworkInterface.getNetworkInterfaces();
			
			while(en.hasMoreElements()) {
				NetworkInterface ni = en.nextElement();
				if(ni.isLoopback()) {
					continue;
				}
				Enumeration<InetAddress> inetAdress=ni.getInetAddresses();
				while(inetAdress.hasMoreElements()) {
					InetAddress ia=inetAdress.nextElement();
					if(ia.getHostAddress()!=null&&ia.getHostAddress().indexOf(".")!=-1) {
						ip=ia.getHostAddress();
						isLoopBack=false;
						break;
						
					}
				}
				if(!isLoopBack) {
					break;
				}
			}
		} catch(SocketException e) {
			e.printStackTrace();
		}
		return ip;
	}

}
