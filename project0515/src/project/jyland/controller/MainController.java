package project.jyland.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MainController {

	private static final Logger logger =
			LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value = "main.jy", method = { RequestMethod.GET, RequestMethod.POST })
	public String main(Model model) throws Exception {
		logger.info("Welcome MainController main! " + new Date());
		return "mainpage.tiles";
	}//
	
}
