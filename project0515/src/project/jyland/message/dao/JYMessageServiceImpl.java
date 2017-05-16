package project.jyland.message.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.jyland.message.model.JYMessage;
import project.jyland.message.model.JYMessageParam;
@Service
public class JYMessageServiceImpl implements JYMessageService {

	@Autowired
	JYMessageDao jyMessageDao;
	
	@Override
	@Transactional
	public void writeMessage(JYMessage msg) {
		jyMessageDao.writeMessage(msg);
		
	}

	@Override
	@Transactional
	public void updateReadchk(JYMessage msg) {
		jyMessageDao.updateReadchk(msg);
		
	}

	@Override
	@Transactional(readOnly=true)
	public JYMessage getMessage(JYMessage msg) {
		return jyMessageDao.getMessage(msg);
	}

	@Override
	@Transactional(readOnly=true)
	public List<JYMessage> getMessageList(JYMessageParam param) {
		return jyMessageDao.getMessageList(param);
	}

	@Override
	@Transactional
	public void deleteMessage(JYMessage msg) {
		jyMessageDao.deleteMessage(msg);
		
	}

	@Override
	@Transactional(readOnly=true)
	public int getMessageTotalCount(JYMessageParam param) {
		return jyMessageDao.getMessageTotalCount(param);
	}

}
