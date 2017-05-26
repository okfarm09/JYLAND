package project.jyland.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.jyland.book.dao.JYBookService;
import project.jyland.book.model.JYBooktitle;
import project.jyland.book.model.JYBooktitleParam;

@Controller
public class LibraryController {
	
	private static final Logger logger =
			LoggerFactory.getLogger(LibraryController.class);
	@Autowired
	private JYBookService jYBookService;
	
	@RequestMapping(value = "library.jy", method = {RequestMethod.POST, RequestMethod.GET})
	public String library(JYBooktitleParam param, Model model) {
		logger.info("Wilkommen LibraryController library "+ new Date());
		
		int sn = param.getPageNumber();
		int start = (sn) * param.getRecordCountPerPage() + 1;
		int end = (sn + 1) * param.getRecordCountPerPage();

		param.setStart(start);
		param.setEnd(end);
		
		logger.info("Welcome LibraryController library param! " + param);
		
		int totalRecordCount = jYBookService.getBookTitleTotalCount();
		logger.info("Welcome LibraryController library totalRecordCount! " + totalRecordCount);
		
		List<JYBooktitle> booktitleList=jYBookService.getBookTitleList(param);
		model.addAttribute("booktitleList", booktitleList);
		model.addAttribute("doc_title", "서고");
		model.addAttribute("pageNumber", sn);
		model.addAttribute("pageCountPerScreen", 10);
		model.addAttribute("recordCountPerPage", param.getRecordCountPerPage());
		model.addAttribute("totalRecordCount", totalRecordCount);
		return "library.tiles";
	}
	
	@RequestMapping(value = "newbook.jy", method = {RequestMethod.POST, RequestMethod.GET})
	public String newbook(Model model) {
		logger.info("Wilkommen LibraryController newbook "+ new Date());
		model.addAttribute("doc_title", "책 등록");
		return "newbook.tiles";
	}
}
