package project.jyland.category.dao;

import java.util.List;

import project.jyland.board.model.JYCat;

public interface CategoryDao {
	
	List<JYCat> getCatList();
	void createCategory(JYCat cat);
	List<JYCat> getPopCatList();

}
