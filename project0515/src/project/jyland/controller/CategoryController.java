package project.jyland.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import project.jyland.category.dao.CategoryService;

@Controller
public class CategoryController {
	
	private static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryService categoryService;

}
