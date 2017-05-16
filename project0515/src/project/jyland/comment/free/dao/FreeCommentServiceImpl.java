package project.jyland.comment.free.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.jyland.comment.model.JYComment;
@Service
public class FreeCommentServiceImpl implements FreeCommentService {

	@Autowired
	FreeCommentDao freeCommentDao;
	
	@Override
	@Transactional
	public void writeComment(JYComment comment) {
		freeCommentDao.writeComment(comment);

	}

	@Override
	@Transactional(readOnly=true)
	public List<JYComment> getCommentList(JYComment comment) {
		 
		return freeCommentDao.getCommentList(comment);
	}

	@Override
	@Transactional
	public void deleteComment(JYComment comment) {
		freeCommentDao.deleteComment(comment);

	}

	@Override
	@Transactional
	public void replyComment(JYComment comment) {
		freeCommentDao.replyComment(comment);
	}

}
