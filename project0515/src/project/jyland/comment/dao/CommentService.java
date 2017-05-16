package project.jyland.comment.dao;

import java.util.List;

import project.jyland.comment.model.JYComment;

public interface CommentService {
	void writeComment(JYComment comment);
	List<JYComment> getCommentList(JYComment comment);
	void deleteComment(JYComment comment);
	void replyComment(JYComment comment);
}
