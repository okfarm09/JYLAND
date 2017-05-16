package project.jyland.comment.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.jyland.comment.model.JYComment;
@Repository
public class CommentDaoImpl implements CommentDao {

	String ns = "Comment.";
	
	@Autowired
	SqlSession sqlSession; 
	
	@Override
	public void writeComment(JYComment comment) {
		sqlSession.insert(ns+"writeComment",comment);
		
	}

	@Override
	public List<JYComment> getCommentList(JYComment comment) {
		 
		return sqlSession.selectList(ns+"getCommentList",comment);
	}

	@Override
	public void deleteComment(JYComment comment) {
		 
		sqlSession.delete(ns+"deleteComment",comment);
	}

	@Override
	public void replyComment(JYComment comment) {
		sqlSession.insert(ns+"replyComment", comment);
	}

}
