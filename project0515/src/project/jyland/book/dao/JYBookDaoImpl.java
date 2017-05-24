package project.jyland.book.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.jyland.book.model.JYBook;
import project.jyland.book.model.JYBookParam;
import project.jyland.book.model.JYBookRatecount;
import project.jyland.book.model.JYBookimg;
import project.jyland.book.model.JYBooktitle;
import project.jyland.book.model.JYBooktitleParam;
@Repository
public class JYBookDaoImpl implements JYBookDao {

	String ns="Book.";
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void createNewBook(JYBooktitle booktitle) {
		sqlSession.insert(ns+"createNewBook", booktitle);
	}

	@Override
	public void writeBook(JYBook book) {
		sqlSession.insert(ns+"writeBook", book);
	}

	@Override
	public void uploadImg(JYBookimg img) {
		sqlSession.insert(ns+"uploadImg", img);
	}

	@Override
	public void setRate(JYBookRatecount ratecount) {
		sqlSession.insert(ns+"setRate", ratecount);
	}

	@Override
	public List<JYBooktitle> getBookTitleList(JYBooktitleParam param) {
		return sqlSession.selectList(ns+"getBookTitleList", param);
	}

	@Override
	public List<JYBook> getBookDetailList(JYBookParam param) {
		return sqlSession.selectList(ns+"getBookDetailList", param);
	}

	@Override
	public List<JYBookimg> getImgList(JYBook book) {
		return sqlSession.selectList(ns+"getImgList", book);
	}

	@Override
	public JYBooktitle getBookTitle(JYBooktitle booktitle) {
		return sqlSession.selectOne(ns+"getBookTitle", booktitle);
	}

	@Override
	public JYBook getBookDetail(JYBook book) {
		return sqlSession.selectOne(ns+"getBookDetail", book);
	}

	@Override
	public int getBookTitleTotalCount() {
		return sqlSession.selectOne(ns+"getBookTitleTotalCount");
	}

	@Override
	public int getBookDetailTotalCount() {
		return sqlSession.selectOne(ns+"getBookDetailTotalCount");
	}

	@Override
	public JYBook getRate(JYBook book) {
		return sqlSession.selectOne(ns+"getRate", book);
	}

	@Override
	public int checkRate(JYBookRatecount ratecount) {
		return sqlSession.selectOne(ns+"checkRate", ratecount);
	}

	@Override
	public void updateReadcount(JYBook book) {
		sqlSession.update(ns+"updateReadcount", book);
	}

	@Override
	public void updateRate(JYBook book) {
		sqlSession.update(ns+"updateRate", book);
	}

	@Override
	public void updateUdate(JYBook book) {
		sqlSession.update(ns+"updateUdate", book);
	}

}
