package project.jyland.message.dao;

import java.util.List;

import project.jyland.message.model.JYMessage;
import project.jyland.message.model.JYMessageParam;

public interface JYMessageService {
	void writeMessage(JYMessage msg);
	void updateReadchk(JYMessage msg);
	JYMessage getMessage(JYMessage msg);
	List<JYMessage> getMessageList(JYMessageParam param);
	void deleteMessage(JYMessage msg);
	int getMessageTotalCount(JYMessageParam param);
}
