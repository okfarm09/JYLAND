package project.jyland.comment.free.dao;

import java.util.List;

import project.jyland.comment.model.JYComment;

public interface FreeCommentDao {
	
	void writeComment(JYComment comment);
	List<JYComment> getCommentList(JYComment comment);
	void deleteComment(JYComment comment);
	void replyComment(JYComment comment);

}
