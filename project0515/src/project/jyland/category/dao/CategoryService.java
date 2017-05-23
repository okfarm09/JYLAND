package project.jyland.category.dao;

import java.util.List;

import project.jyland.board.model.JYCat;

public interface CategoryService {
	
	List<JYCat> getCatList();
	void createCategory(JYCat cat);
	List<JYCat> getPopCatList();

}
