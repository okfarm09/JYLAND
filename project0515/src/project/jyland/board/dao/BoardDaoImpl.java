package project.jyland.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.jyland.board.model.JYBoard;
import project.jyland.board.model.JYBoardLHCount;
import project.jyland.board.model.JYBoardParam;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	String ns="Board.";
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void updateBoard(JYBoard board) {
		sqlSession.update(ns+"updateBoard", board);
	}

	@Override
	public void deleteBoard(JYBoard board) {
		sqlSession.update(ns+"deleteBoard", board);
	}

	@Override
	public JYBoard getBoard(JYBoard board) {
		return sqlSession.selectOne(ns+"getBoard", board);
	}

	@Override
	public List<JYBoard> getBoardPageList(JYBoardParam param) {
		return sqlSession.selectList(ns+"getBoardPageList", param);
	}

	@Override
	public int getBoardTotalCount(JYBoardParam param) {
		return sqlSession.selectOne(ns+"getBoardTotalCount", param);
	}

	@Override
	public String getCatName(int catid) {
		return sqlSession.selectOne(ns+"getCatName", catid);
	}

	@Override
	public void writeBoard(JYBoard board) {
		sqlSession.insert(ns+"writeBoard", board);
	}

	@Override
	public List<JYBoard> getRecentList() {
		return sqlSession.selectList(ns+"getRecentList");
	}

	@Override
	public void updateReadcount(JYBoard board) {
		sqlSession.update(ns+"updateReadcount", board);
	}

	@Override
	public void updateLikecount(JYBoard board) {
		sqlSession.update(ns+"updateLikecount", board);
	}

	@Override
	public void updateHatecount(JYBoard board) {
		sqlSession.update(ns+"updateHatecount", board);
	}

	@Override
	public void setLikeHate(JYBoardLHCount board) {
		sqlSession.insert(ns+"setLikeHate", board);
	}

	@Override
	public JYBoard getLHCount(JYBoard board) {
		return sqlSession.selectOne(ns+"getLHCount", board);
	}

	@Override
	public int checkLikeHate(JYBoardLHCount board) {
		return sqlSession.selectOne(ns+"checkLikeHate", board);
	}
}
