package project.jyland.controller;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Date;
import java.util.Enumeration;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import project.jyland.board.dao.BoardService;
import project.jyland.board.help.FUpUtil;
import project.jyland.board.help.LHCount;
import project.jyland.board.model.JYBoard;
import project.jyland.board.model.JYBoardLHCount;
import project.jyland.board.model.JYBoardMap;
import project.jyland.board.model.JYBoardParam;

@Controller
public class BoardController {
	
	private static final Logger logger =
			LoggerFactory.getLogger(BoardController.class);
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "board.jy", method = { RequestMethod.GET, RequestMethod.POST })
	public String board(JYBoardParam param, Model model) throws Exception {
		logger.info("Welcome BoardController board! " + new Date());

		int sn = param.getPageNumber();
		int start = (sn) * param.getRecordCountPerPage() + 1;
		int end = (sn + 1) * param.getRecordCountPerPage();

		param.setStart(start);
		param.setEnd(end);
		logger.info("Welcome BoardController board param! " + param);

		int totalRecordCount = boardService.getBoardTotalCount(param);
		logger.info("Welcome BoardController board totalRecordCount! " + totalRecordCount);

		List<JYBoard> boardlist = boardService.getBoardPageList(param);
		logger.info("Welcome BoardController board list! " + boardlist.size());
		
		List<JYBoard> GlobalNoticeList = boardService.getGlobalNoticeList();
		logger.info("Welcome BoardController getGlobalNoticeList! " + GlobalNoticeList);
		
		List<JYBoard> LocalNoticeList = boardService.getLocalNoticeList(param);
		logger.info("Welcome BoardController getLocalNoticeList! " + LocalNoticeList);
		
		model.addAttribute("LocalNoticeList", LocalNoticeList);
		model.addAttribute("GlobalNoticeList", GlobalNoticeList);
		model.addAttribute("doc_title", boardService.getCatName(param.getCatid()));
		model.addAttribute("boardlist", boardlist);

		model.addAttribute("catid", param.getCatid());
		model.addAttribute("pageNumber", sn);
		model.addAttribute("pageCountPerScreen", 10);
		model.addAttribute("recordCountPerPage", param.getRecordCountPerPage());
		model.addAttribute("totalRecordCount", totalRecordCount);

		return "boardlist.tiles";
	}
	
	@RequestMapping(value = "boardwrite.jy", method = { RequestMethod.GET, RequestMethod.POST })
	public String boardwrite(int catid, Model model) {
		logger.info("Welcome BoardController freewritebefore! " + new Date());
		model.addAttribute("catid", catid);
		model.addAttribute("doc_title", "글쓰기");
		return "boardwrite.tiles";
	}
	
	@RequestMapping(value = "boardwriteAf.jy", method = { RequestMethod.POST })
	public String boardwriteAf(JYBoard dto, HttpServletRequest request,
			@RequestParam(value = "fileload", required = false) MultipartFile fileload, Model model) {
		logger.info("Welcome BoardController! " + dto);
		dto.setUpload(fileload.getOriginalFilename());

		logger.info("Welcome BoardController boardwriteAf! " + new Date());
		// 실제 서버용
		// String fupload = request.getServletContext().getRealPath("/upload");
		// 리눅스용 경로
		String fupload = "/home/namo/ho2/git/JYLAND/project0515/WebContent/upload";
		// ntfs에서는(현준)
//		String fupload = "C:\\Users\\Jermy\\git\\JYLAND\\project0515\\WebContent\\upload";
		// 지윤
		//String fupload = "C:\\Users\\JY\\git\\JYLAND\\project0515\\WebContent\\upload";
		//원찬
		//String fupload = "F:\\git\\JYLAND\\project0515\\WebContent\\upload ";
		logger.info(": " + fupload);
		String f = dto.getUpload();
		
		String ip = null;
		try {
			
			boolean isLoopBack = true;
			Enumeration<NetworkInterface> en=NetworkInterface.getNetworkInterfaces();
			
			while(en.hasMoreElements()) {
				NetworkInterface ni = en.nextElement();
				if(ni.isLoopback()) {
					continue;
				}
				Enumeration<InetAddress> inetAdress=ni.getInetAddresses();
				while(inetAdress.hasMoreElements()) {
					InetAddress ia=inetAdress.nextElement();
					if(ia.getHostAddress()!=null&&ia.getHostAddress().indexOf(".")!=-1) {
						ip=ia.getHostAddress();
						isLoopBack=false;
						break;
						
					}
				}
				if(!isLoopBack) {
					break;
				}
			}
		} catch(SocketException e) {
			e.printStackTrace();
		}
		String tempip = request.getHeader("x-forwarded-for");
		if(tempip==null) {
			tempip=request.getRemoteAddr();
		}
		logger.info("Wilkommen! aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa " + request.getHeader("x-forwarded-for"));
		logger.info("Wilkommen! aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa " + request.getRemoteHost());
		logger.info("Wilkommen! aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa " + request.getRemoteAddr());
		
		// if(dto.getIp()==""||dto.getIp().equals(null)) {
		//dto.setIp(tempip);
		dto.setIp(ip);
		// }
		String newFile = FUpUtil.getNewFile(f);
		logger.info("Welcome BoardController boardwriteAf " + newFile);

		logger.info(fupload + "/" + newFile);
		dto.setUpload(newFile);
		try {
			File file = new File(fupload + "/" + newFile);
			// logger.info(fupload+"\\"+newFile);
			FileUtils.writeByteArrayToFile(file, fileload.getBytes());

			// db에 저장 안하면 코멘트.
			boardService.writeBoard(dto);
			logger.info("Welcome BoardController boardwriteAf success! ");
		} catch (IOException e) {
			logger.info("Welcome BoardController boardwriteAf fail! ");
		}
		return "redirect:/board.jy?catid="+dto.getCatid();
	}
	
	@RequestMapping(value = "detailupdate.jy", method = RequestMethod.POST)
	public String detailupdate(JYBoard board, Model model) {
		logger.info("Welcome BoardController detailupdate " + new Date());
		model.addAttribute("original", new JYBoardMap(board));
		model.addAttribute("doc_title", "수정하기");
		return "updateboard.tiles";
	}
	
	@RequestMapping(value = "boarddetail.jy", method = { RequestMethod.POST, RequestMethod.GET })
	public String freedetail(JYBoard board, Model model) {
		logger.info("Welcome BoardController boarddetail! ---------------------------------------");
		boardService.updateReadcount(board);
		JYBoardMap bm=new JYBoardMap(boardService.getBoard(board));
		model.addAttribute("boarddetail", bm);
		logger.info("Welcome BoardController boarddetail! " + bm);
		return "boarddetail.tiles";
	}
	
	@RequestMapping(value = "detailupdateAf", method = RequestMethod.POST)
	public String detailupdateAf(JYBoard board, String originalFile, HttpServletRequest request,
			@RequestParam(value = "fileload", required = false) MultipartFile fileload, Model model) {
		logger.info("Welcome BoardController detailupdateAf " + new Date());
		board.setUpload(fileload.getOriginalFilename());
		String f=board.getUpload();
		// 실제 서버용
		// String fupload = request.getServletContext().getRealPath("/upload");
		// 지윤
		//String fupload = "C:\\Users\\JY\\git\\JYLAND\\project0515\\WebContent\\upload";
		// ntfs에서는(현준)
		//String fupload = "C:\\Users\\Jermy\\git\\JYLAND\\project0515\\WebContent\\upload";
		//남희석요
		String fupload = "/home/namo/ho2/git/JYLAND/project0515/WebContent/upload";
		//원찬
		//String fupload = "F:\\git\\JYLAND\\project0515\\WebContent\\upload ";
		String newFile = FUpUtil.getNewFile(f);
		logger.info(fupload + "/" + newFile);
		if(newFile.contains("back")) {
			board.setUpload(originalFile);
		}
		logger.info(fupload + "/" + board.getUpload());
		try {
			File file = new File(fupload + "/" + board.getUpload());
			FileUtils.writeByteArrayToFile(file, fileload.getBytes());
			boardService.updateBoard(board);
			logger.info("Welcome BoardController detailupdateAf success! ");
		} catch (IOException e) {
			logger.info("Welcome BoardController detailupdateAf fail! ");
		}
		return "redirect:/boarddetail.jy?seq="+board.getSeq();
	}
	
	@RequestMapping(value = "detaildelete", method=RequestMethod.POST)
	public String detaildelete(JYBoard board, Model model) {
		logger.info("Welcome BoardController detaildelete " + new Date());
		boardService.deleteBoard(board);
		return "redirect:/board.jy";
	}
	
	@RequestMapping(value = "deleteMylist.jy", method=RequestMethod.POST)
	public String deleteMylist(JYBoard board, Model model) {
		logger.info("Welcome BoardController deleteMylist " + new Date());
		logger.info("Welcome FreeCommentController deleteComment " + board);
		boardService.deleteBoard(board);
		return "redirect:/mylist.jy?id="+board.getId();
	}
	
	@RequestMapping(value = "getdel.jy", method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody JYBoard getdel(JYBoard board, Model model) {
		
		logger.info("Welcome BoardController getDel " + new Date());
		JYBoard bb= boardService.getBoard(board);
		return bb;
	}
	
	@RequestMapping(value = "goNotice.jy", method=RequestMethod.POST)
	public String goNotice(JYBoard board, Model model) {
		logger.info("Welcome BoardController goNotice " + new Date());
		boardService.goNotice(board);
		return "redirect:/board.jy?catid="+board.getCatid();
	}
	
	
//	@RequestMapping(value = "getGlobalNoticeList.jy", method = {RequestMethod.GET, RequestMethod.POST})
//	public List<JYBoard> getGlobalNoticeList(JYBoard board,Model model) {
//		logger.info("Welcome BoardController getGlobalNoticeList! =================================================" + new Date());
//		List<JYBoard> GlobalNoticeList = boardService.getGlobalNoticeList();
//		logger.info("Welcome BoardController getGlobalNoticeList! " + GlobalNoticeList);
//		model.addAttribute("GlobalNoticeList", GlobalNoticeList);
//		return GlobalNoticeList;
//	}
//	
//	@RequestMapping(value = "getLocalNoticeList.jy", method = {RequestMethod.GET, RequestMethod.POST})
//	public List<JYBoard> getLocalNoticeList(JYBoardParam board,Model model) {
//		logger.info("Welcome BoardController getLocalNoticeList! " + new Date());
//		List<JYBoard> LocalNoticeList = boardService.getLocalNoticeList(board);
//		logger.info("Welcome BoardController getLocalNoticeList! " + LocalNoticeList);
//		model.addAttribute("LocalNoticeList", LocalNoticeList);
//		return LocalNoticeList;
//	}

	@RequestMapping(value = "like.jy", method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody LHCount like(JYBoardLHCount board, Model model) {
		logger.info("Welcome BoardController like " + new Date());
		JYBoard bbb=new JYBoard();
		bbb.setSeq(board.getBoardseq());
		int count=boardService.checkLikeHate(board);
		LHCount lh=new LHCount();
		logger.info("Welcome BoardController like " + count);
		if(count>0) {
			lh.setMessage("추천할 수 없사와요");
			logger.info("Welcome BoardController like fail!");
		}else {
			boardService.updateLikecount(bbb);
			boardService.setLikeHate(board);
			lh.setMessage("추천했사와요");
			logger.info("Welcome BoardController like success!");
		}
		JYBoard temp=boardService.getLHCount(bbb);
		lh.setLikecount(temp.getLikecount());
		lh.setHatecount(temp.getHatecount());
		return lh;
	}
	
	@RequestMapping(value = "hate.jy", method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody LHCount hate(JYBoardLHCount board, Model model) {
		logger.info("Welcome BoardController hate " + new Date());
		JYBoard bbb=new JYBoard();
		bbb.setSeq(board.getBoardseq());
		int count=boardService.checkLikeHate(board);
		LHCount lh=new LHCount();
		logger.info("Welcome BoardController hate " + count);
		if(count>0) {
			lh.setMessage("반대할 수 없사와요");
			logger.info("Welcome BoardController hate fail!");
		}else {
			boardService.updateHatecount(bbb);
			boardService.setLikeHate(board);
			lh.setMessage("반대했사와요");
			logger.info("Welcome BoardController hate success!");
		}
		JYBoard temp=boardService.getLHCount(bbb);
		lh.setLikecount(temp.getLikecount());
		lh.setHatecount(temp.getHatecount());
		return lh;
	}

}
