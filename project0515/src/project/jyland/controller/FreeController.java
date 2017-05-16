package project.jyland.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import project.jyland.board.free.dao.FreeBoardService;
import project.jyland.board.help.FUpUtil;
import project.jyland.board.model.JYBoard;
import project.jyland.board.model.JYBoardMap;
import project.jyland.board.model.JYBoardParam;

@Controller
public class FreeController {

	private static final Logger logger = LoggerFactory.getLogger(FreeController.class);
	@Autowired
	private FreeBoardService freeBoardService;

	@RequestMapping(value = "freeboard.jy", method = { RequestMethod.GET, RequestMethod.POST })
	public String freeboard(JYBoardParam param, Model model) throws Exception {
		logger.info("Welcome FreeController freeboard! " + new Date());

		int sn = param.getPageNumber();
		int start = (sn) * param.getRecordCountPerPage() + 1;
		int end = (sn + 1) * param.getRecordCountPerPage();

		param.setStart(start);
		param.setEnd(end);
		logger.info("Welcome FreeController freeboard param! " + param);

		int totalRecordCount = freeBoardService.getFreeTotalCount(param);
		logger.info("Welcome FreeController freeboard totalRecordCount! " + totalRecordCount);

		List<JYBoard> freelist = freeBoardService.freePageList(param);
		logger.info("Welcome FreeController freeboard list! " + freelist.size());

		model.addAttribute("doc_title", "자유게시판");
		model.addAttribute("freelist", freelist);

		model.addAttribute("pageNumber", sn);
		model.addAttribute("pageCountPerScreen", 10);
		model.addAttribute("recordCountPerPage", param.getRecordCountPerPage());
		model.addAttribute("totalRecordCount", totalRecordCount);

		return "freelist.tiles";
	}

	@RequestMapping(value = "freewrite.jy", method = { RequestMethod.GET, RequestMethod.POST })
	public String freewrite(Model model) {
		logger.info("Welcome FreeController freewritebefore! " + new Date());
		model.addAttribute("doc_title", "글쓰기");
		return "freewrite.tiles";
	}

	@RequestMapping(value = "freewriteAf.jy", method = { RequestMethod.POST })
	public String freewriteAf(JYBoard dto, HttpServletRequest request,
			@RequestParam(value = "fileload", required = false) MultipartFile fileload, Model model) {
		logger.info("Welcome FreeController! " + dto);
		dto.setUpload(fileload.getOriginalFilename());

		logger.info("Welcome FreeController freewriteAf! " + new Date());
		// 실제 서버용
		// String fupload = request.getServletContext().getRealPath("/upload");
		// 리눅스용 경로
		String fupload = "/home/namo/ho2/workspaces/workspace3/project0515/WebContent/upload";
		// ntfs에서는(현준)
		//String fupload = "C:\\Users\\Jermy\\workspace\\workspace2_0508\\project0512\\WebContent\\upload";
		// 지윤이
		//String fupload = "C:\\Java\\project\\project\\WebContent\\upload";
		logger.info(": " + fupload);
		String f = dto.getUpload();
		String tempip = "000.000.000.000";
		// if(dto.getIp()==""||dto.getIp().equals(null)) {
		dto.setIp(tempip);
		// }
		String newFile = FUpUtil.getNewFile(f);

		logger.info(fupload + "/" + newFile);
		dto.setUpload(newFile);
		try {
			File file = new File(fupload + "/" + newFile);
			// logger.info(fupload+"\\"+newFile);
			FileUtils.writeByteArrayToFile(file, fileload.getBytes());

			// db에 저장 안하면 코멘트.
			freeBoardService.writeFreeBoard(dto);
			logger.info("Welcome freewriteAf success! ");
		} catch (IOException e) {
			logger.info("Welcome freewriteAf fail! ");
		}
		return "redirect:/freeboard.jy";
	}

	@RequestMapping(value = "freedetail.jy", method = { RequestMethod.POST, RequestMethod.GET })
	public String freedetail(JYBoard board, Model model) {
		logger.info("Welcome FreeController freedetail! ---------------------------------------");
		JYBoardMap bm=new JYBoardMap(freeBoardService.getFree(board));
		model.addAttribute("freedetail", bm);
		logger.info("Welcome FreeController freedetail! " + bm);
		return "freedetail.tiles";
	}

}
