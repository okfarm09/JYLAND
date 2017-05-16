package project.jyland.message.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import project.jyland.message.model.JYMessage;
import project.jyland.message.model.JYMessageParam;
@Repository
public class JYMessageDaoImpl implements JYMessageDao {
	
	@Autowired
	SqlSession sqlSession;
	
	String ns="Message.";
	
	@Override
	public void writeMessage(JYMessage msg) {
		sqlSession.insert(ns+"writeMessage",msg);
		
	}

	@Override
	public void updateReadchk(JYMessage msg) {
		sqlSession.update(ns+"updateReadchk",msg);
		
	}

	@Override
	@Transactional(readOnly=true)
	public JYMessage getMessage(JYMessage msg) {
		 
		return sqlSession.selectOne(ns+"getMessage",msg);
	}

	@Override
	public List<JYMessage> getMessageList(JYMessageParam param) {
		
		return sqlSession.selectList(ns+"getMessageList",param);
	}

	@Override
	public void deleteMessage(JYMessage msg) {
		sqlSession.delete(ns+"deleteMessage",msg);
		
	}

	@Override
	public int getMessageTotalCount(JYMessageParam param) {
		return sqlSession.selectOne(ns+"getMessageTotalCount", param);
	}

}
