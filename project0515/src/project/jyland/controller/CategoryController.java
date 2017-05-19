package project.jyland.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.jyland.board.model.JYCat;
import project.jyland.category.dao.CategoryService;

@Controller
public class CategoryController {
	
	private static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "createboard.jy", method = RequestMethod.POST)
	public String createboard(JYCat cat, Model model) {
		logger.info("willkommen CategoryController createboard " + new Date());
		categoryService.createCategory(cat);
		return "redirect:/myinfo.jy";
	}

}
