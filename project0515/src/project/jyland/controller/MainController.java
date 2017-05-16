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

import project.jyland.board.dao.BoardService;
import project.jyland.board.help.MainHelper;
import project.jyland.board.model.JYBoard;


@Controller
public class MainController {

	private static final Logger logger =
			LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "main.jy", method = { RequestMethod.GET, RequestMethod.POST })
	public String main(Model model) throws Exception {
		logger.info("Welcome MainController main! " + new Date());
		List<JYBoard> recentlist = boardService.getRecentList();
		for (JYBoard board : recentlist) {
			board.setTitle(MainHelper.message(board.getTitle()));
			board.setDateForMain(MainHelper.mmdd(board.getWdate()));
		}
		model.addAttribute("recentlist", recentlist);
		return "mainpage.tiles";
	}//
	
}
