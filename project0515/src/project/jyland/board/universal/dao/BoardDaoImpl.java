package project.jyland.board.universal.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.jyland.board.model.JYBoard;
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
	public String getCatPrep(int catid) {
		return sqlSession.selectOne(ns+"getCatPrep", catid);
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

}
