package project.jyland.board.free.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.jyland.board.model.JYBoard;
import project.jyland.board.model.JYBoardParam;

@Repository
public class FreeBoardDaoImpl implements FreeBoardDao {
	
	String ns="FreeBoard.";
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void writeFreeBoard(JYBoard board) {
		sqlSession.insert(ns+"writeFreeBoard", board);
	}

	@Override
	public List<JYBoard> getFreeList() {
		return sqlSession.selectList(ns+"getFreeList");
	}

	@Override
	public JYBoard getFree(JYBoard board) {
		return sqlSession.selectOne(ns+"getFree", board);
	}

	@Override
	public List<JYBoard> freePageList(JYBoardParam param) {
		return sqlSession.selectList(ns+"freePageList", param);
	}

	@Override
	public int getFreeTotalCount(JYBoardParam param) {
		return sqlSession.selectOne(ns+"getFreeTotalCount", param);
	}

}
