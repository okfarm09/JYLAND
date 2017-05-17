package project.jyland.member.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.jyland.board.model.JYBoard;
import project.jyland.board.model.JYBoardParam;
import project.jyland.comment.model.JYComment;
import project.jyland.member.model.JYUser;
import project.jyland.member.model.JYUserParam;

@Service
public class JYUserServiceImpl implements JYUserService {

	@Autowired
	JYUserDao jYUserDao;
	
	@Override
	@Transactional
	public void addUser(JYUser user) {
		jYUserDao.addUser(user);
	}

	@Override
	@Transactional(readOnly=true)
	public JYUser loginUser(JYUser user) {
		return jYUserDao.loginUser(user);
	}

	@Override
	@Transactional(readOnly=true)
	public int checkId(JYUser user) {
		return jYUserDao.checkId(user);
	}

	@Override
	@Transactional
	public void updateUser(JYUser user) {
		jYUserDao.updateUser(user);
	}

	@Override
	@Transactional(readOnly=true)
	public JYUser userInfo(String id) {
		return jYUserDao.userInfo(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<JYBoard> myList(JYBoardParam param) {
		return jYUserDao.myList(param);
	}

	@Override
	@Transactional(readOnly=true)
	public int getMyListTotalCount(JYBoardParam param) {
		return jYUserDao.getMyListTotalCount(param);
	}

	@Override
	@Transactional(readOnly=true)
	public List<JYComment> myCommentList(JYComment comment) {
		return jYUserDao.myCommentList(comment);
	}

	@Override
	@Transactional(readOnly=true)
	public List<JYUser> getUserList(JYUserParam param) {
		return jYUserDao.getUserList(param);
	}

	@Override
	@Transactional(readOnly=true)
	public int getUserTotalCount(JYUserParam param) {
		return jYUserDao.getUserTotalCount(param);
	}

	@Override
	@Transactional
	public void deleteUser(JYUser user) {
		jYUserDao.deleteUser(user);
	}

	@Override
	@Transactional(readOnly=true)
	public List<JYUser> getAllUserList() {
		return jYUserDao.getAllUserList();
	}

}
