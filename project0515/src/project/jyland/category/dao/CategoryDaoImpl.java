package project.jyland.category.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.jyland.board.model.JYCat;

@Repository
public class CategoryDaoImpl implements CategoryDao {

	private String ns="Category.";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<JYCat> getCatList() {
		return sqlSession.selectList(ns+"getCatList");
	}

	@Override
	public void createCategory(JYCat cat) {
		sqlSession.insert(ns+"createCategory", cat);
	}

	@Override
	public List<JYCat> getPopCatList() {
		return sqlSession.selectList(ns+"getPopCatList");
	}

}
