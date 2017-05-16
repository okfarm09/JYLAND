package project.jyland.data.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.jyland.board.model.JYBoard;
import project.jyland.board.model.JYBoardParam;
import project.jyland.data.model.Region;

@Repository
public class DataDaoImpl implements DataDao {
	
	String ns="Datas.";
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<Region> getRegionList() {
		return sqlSession.selectList(ns+"getRegionList");
	}

	@Override
	public List<JYBoard> getSearchList(JYBoardParam param) {
		return sqlSession.selectList(ns+"getSearchList", param);
	}

	@Override
	public int getSearchTotalCount(JYBoardParam param) {
		return sqlSession.selectOne(ns+"getSearchTotalCount", param);
	}

}
