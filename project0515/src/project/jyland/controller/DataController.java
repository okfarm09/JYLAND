package project.jyland.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import project.jyland.board.model.JYBoard;
import project.jyland.board.model.JYBoardParam;
import project.jyland.data.dao.DataService;
import project.jyland.data.model.Region;

@Controller
public class DataController {
	
	private static final Logger logger =
			LoggerFactory.getLogger(DataController.class);
	@Autowired
	private DataService regionService;
	
	@RequestMapping(value = "region.jy", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody HashMap<Integer, String> region(Model model) {
		logger.info("Welcome DataController region! " + new Date());
		List<Region> list=regionService.getRegionList();
		HashMap<Integer, String> map=new HashMap<>();
		for(Region rr:list) {
			map.put(rr.getRegionid(), rr.getRegionname());
		}
		return map;
	}
	
	@RequestMapping(value = "search.jy", method = { RequestMethod.GET, RequestMethod.POST })
	public String search(JYBoardParam param, Model model) throws Exception {
		logger.info("Welcome DataController search! " + new Date());

		int sn = param.getPageNumber();
		int start = (sn) * param.getRecordCountPerPage() + 1;
		int end = (sn + 1) * param.getRecordCountPerPage();

		param.setStart(start);
		param.setEnd(end);
		logger.info("Welcome DataController search param! " + param);

		int totalRecordCount = regionService.getSearchTotalCount(param);
		logger.info("Welcome DataController search totalRecordCount! " + totalRecordCount);

		List<JYBoard> searchlist = regionService.getSearchList(param);
		logger.info("Welcome DataController search list! " + searchlist.size());

		model.addAttribute("doc_title", "검색 리스트");
		model.addAttribute("searchlist", searchlist);

		model.addAttribute("pageNumber", sn);
		model.addAttribute("pageCountPerScreen", 10);
		model.addAttribute("recordCountPerPage", param.getRecordCountPerPage());
		model.addAttribute("totalRecordCount", totalRecordCount);

		return "searchlist.tiles";
	}

}
