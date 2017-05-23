package project.jyland.category.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.jyland.board.model.JYCat;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<JYCat> getCatList() {
		return categoryDao.getCatList();
	}

	@Override
	@Transactional
	public void createCategory(JYCat cat) {
		categoryDao.createCategory(cat);
	}

	@Override
	@Transactional(readOnly=true)
	public List<JYCat> getPopCatList() {
		return categoryDao.getPopCatList();
	}

}
