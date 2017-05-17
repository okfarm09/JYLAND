package project.jyland.chat;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ServerEndpoint("/broadcasting")
public class BroadSocket {
	private ChatFilter cf=new ChatFilter();
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	
	private static final Logger logger =
			LoggerFactory.getLogger(BroadSocket.class);
	
	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		logger.info("Welcome BroadSocket onMessage " + new Date());
		logger.info("Welcome BroadSocket onMessage " + message);
		synchronized(clients) {
			for(Session client : clients) {
				if(!client.equals(session)) {
					String msg=cf.rmScript(message);
					logger.info("Welcome BroadSocket onMessage " + msg);
					client.getBasicRemote().sendText(msg);
				}
			}
		}
	}
	
	@OnOpen
	public void onOpen(Session session) {
		logger.info("Welcome BroadSocket onOpen " + new Date());
		logger.info("Welcome BroadSocket onOpen " + session);
		clients.add(session);
	}
	
	@OnClose
	public void onClose(Session session) {
		clients.remove(session);
	}
}
