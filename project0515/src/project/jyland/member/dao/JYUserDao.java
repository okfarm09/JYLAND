package project.jyland.member.dao;

import java.util.List;

import project.jyland.board.model.JYBoard;
import project.jyland.board.model.JYBoardParam;
import project.jyland.comment.model.JYComment;
import project.jyland.member.model.JYUser;
import project.jyland.member.model.JYUserParam;

public interface JYUserDao {
	
	void addUser(JYUser user);
	JYUser loginUser(JYUser user);
	int checkId(JYUser user);
	void updateUser(JYUser user);
	JYUser userInfo(String id);
	
	List<JYBoard> myList(JYBoardParam param);
	int getMyListTotalCount(JYBoardParam param);
	List<JYComment> myCommentList(JYComment comment);
	
	List<JYUser> getUserList(JYUserParam param);
	int getUserTotalCount(JYUserParam param);
	void deleteUser(JYUser user);
}
