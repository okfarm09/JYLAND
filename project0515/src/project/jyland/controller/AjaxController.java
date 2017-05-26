package project.jyland.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import project.jyland.board.dao.BoardService;
import project.jyland.book.dao.JYBookService;
import project.jyland.category.dao.CategoryService;
import project.jyland.comment.dao.CommentService;
import project.jyland.data.dao.DataService;
import project.jyland.member.dao.JYUserService;
import project.jyland.message.dao.JYMessageService;

@Controller
public class AjaxController {
	
	private static final Logger logger =
			LoggerFactory.getLogger(AjaxController.class);
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private JYBookService bookService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private DataService dataService;
	@Autowired
	private JYUserService userService;
	@Autowired
	private JYMessageService messageService;
	

}
