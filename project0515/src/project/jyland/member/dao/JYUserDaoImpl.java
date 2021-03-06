package project.jyland.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.jyland.board.model.JYBoard;
import project.jyland.board.model.JYBoardParam;
import project.jyland.comment.model.JYComment;
import project.jyland.member.model.JYUser;
import project.jyland.member.model.JYUserParam;

@Repository
public class JYUserDaoImpl implements JYUserDao {

	String ns="JYUser.";
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void addUser(JYUser user) {
		sqlSession.insert(ns+"addUser", user);
	}

	@Override
	public JYUser loginUser(JYUser user) {
		return sqlSession.selectOne(ns+"loginUser", user);
	}

	@Override
	public int checkId(JYUser user) {
		return sqlSession.selectOne(ns+"checkId", user);
	}

	@Override
	public void updateUser(JYUser user) {
		sqlSession.update(ns+"updateUser", user);
	}

	@Override
	public JYUser userInfo(String id) {
		return sqlSession.selectOne(ns+"userInfo", id);
	}

	@Override
	public List<JYBoard> myList(JYBoardParam param) {
		return sqlSession.selectList(ns+"myList", param);
	}

	@Override
	public int getMyListTotalCount(JYBoardParam param) {
		return sqlSession.selectOne(ns+"getMyListTotalCount", param);
	}

	@Override
	public List<JYComment> myCommentList(JYComment comment) {
		return sqlSession.selectList(ns+"myCommentList", comment);
	}

	@Override
	public List<JYUser> getUserList(JYUserParam param) {
		return sqlSession.selectList(ns+"getUserList", param);
	}

	@Override
	public int getUserTotalCount(JYUserParam param) {
		return sqlSession.selectOne(ns+"getUserTotalCount", param);
	}

	@Override
	public void deleteUser(JYUser user) {
		sqlSession.update(ns+"deleteUser", user);
	}

	@Override
	public List<JYUser> getAllUserList() {
		return sqlSession.selectList(ns+"getAllUserList");
	}

	@Override
	public void addApi() {
		sqlSession.update(ns+"addApi");
	}

	@Override
	public JYUser mobileLogin(JYUser user) {
		return sqlSession.selectOne(ns+"mobileLogin", user);
	}

}
