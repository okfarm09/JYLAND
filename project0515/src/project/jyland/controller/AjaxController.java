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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import project.jyland.board.dao.BoardService;
import project.jyland.board.help.FUpUtil;
import project.jyland.board.model.JYBoard;
import project.jyland.board.model.JYBoardLHCount;
import project.jyland.board.model.JYBoardMap;
import project.jyland.board.model.JYBoardParam;
import project.jyland.board.model.JYCat;
import project.jyland.book.dao.JYBookService;
import project.jyland.book.model.JYBook;
import project.jyland.book.model.JYBookParam;
import project.jyland.book.model.JYBookRatecount;
import project.jyland.book.model.JYBookimg;
import project.jyland.book.model.JYBooktitle;
import project.jyland.book.model.JYBooktitleParam;
import project.jyland.category.dao.CategoryService;
import project.jyland.comment.dao.CommentService;
import project.jyland.comment.model.JYComment;
import project.jyland.data.dao.DataService;
import project.jyland.data.model.Region;
import project.jyland.helper.GetIp;
import project.jyland.helper.UploadPath;
import project.jyland.member.dao.JYUserService;
import project.jyland.member.model.JYUser;
import project.jyland.member.model.JYUserParam;
import project.jyland.message.dao.JYMessageService;
import project.jyland.message.model.JYMessage;
import project.jyland.message.model.JYMessageParam;

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
	
	//1=현준 2=희석 3=지윤 4=원찬(경로 수정 필요)
	private int pathNum=2;

	//boardService
	@RequestMapping(value = "ajaxWriteBoard.jy", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String ajaxWriteBoard(JYBoard board, HttpServletRequest request,
			@RequestParam(value = "fileload", required = false) MultipartFile fileload,Model model) {
		logger.info("Wilkommen AjaxController board ajaxWriteBoard " +new Date());
		board.setUpload(fileload.getOriginalFilename());
		// 실제 서버용
		// String fupload = request.getServletContext().getRealPath("/upload");
		// 임시 경로
		String fupload = UploadPath.getPath(pathNum);
		logger.info(": " + fupload);
		String f = board.getUpload();
		
		String ip = GetIp.getIp(request);
		String tempip = request.getHeader("x-forwarded-for");
		if(tempip==null) {
			tempip=request.getRemoteAddr();
		}
		logger.info("Wilkommen AjaxController board ajaxWriteBoard IP: " + ip);
		board.setIp(ip);
		
		String newFile = FUpUtil.getNewFile(f);
		logger.info("Wilkommen AjaxController board ajaxWriteBoard newFile: " + newFile);
		logger.info(fupload + "/" + newFile);
		board.setUpload(newFile);
		
		try {
			File file = new File(fupload + "/" + newFile);
			FileUtils.writeByteArrayToFile(file, fileload.getBytes());
			boardService.writeBoard(board);
			logger.info("Wilkommen AjaxController board ajaxWriteBoard s!");
		}catch(IOException e) {
			logger.info("Wilkommen AjaxController board ajaxWriteBoard f!");
		}
		
		return "Done";
	}
	
	@RequestMapping(value = "ajaxUpdateBoard.jy", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String ajaxUpdateBoard(JYBoard board, String originalFile, HttpServletRequest request, 
			@RequestParam(value = "fileload", required = false) MultipartFile fileload, Model model) {
		logger.info("Wilkommen AjaxController board ajaxUpdateBoard " +new Date());
		board.setUpload(fileload.getOriginalFilename());
		// 실제 서버용
		// String fupload = request.getServletContext().getRealPath("/upload");
		// 임시 경로
		String fupload = UploadPath.getPath(pathNum);
		logger.info(": " + fupload);
		String f = board.getUpload();
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
			logger.info("Wilkommen AjaxController board ajaxUpdateBoard success! ");
		} catch (IOException e) {
			logger.info("Wilkommen AjaxController board ajaxUpdateBoard fail! ");
		}
		return "Done";
	}
	
	@RequestMapping(value = "ajaxDeleteBoard.jy", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String ajaxDeleteBoard(JYBoard board, HttpServletRequest request, Model model) {
		logger.info("Wilkommen AjaxController board ajaxDeleteBoard " +new Date());
		boardService.deleteBoard(board);
		return "Done";
	}
//
	@RequestMapping(value = "ajaxGetCatName.jy", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String ajaxGetCatName(int catid, HttpServletRequest request, Model model) {
		logger.info("Wilkommen AjaxController board ajaxGetCatName " +new Date());
		return boardService.getCatName(catid);
	}
//
	@RequestMapping(value = "ajaxGetBoard.jy", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody JYBoardMap ajaxGetBoard(JYBoard board, HttpServletRequest request, Model model) {
		logger.info("Wilkommen AjaxController board ajaxGetBoard " +new Date());
		return new JYBoardMap(boardService.getBoard(board));
	}
//---------------pageNumber, catid 포함한 헬퍼 하나 만들어서 리턴값으로 설정해 줘야 함...
//	@RequestMapping(value = "ajaxGetBoardPageList.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody List<JYBoard> ajaxGetBoardPageList(JYBoardParam param, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController board ajaxGetBoardPageList " +new Date());
//		
//		int sn = param.getPageNumber();
//		int start = (sn) * param.getRecordCountPerPage() + 1;
//		int end = (sn + 1) * param.getRecordCountPerPage();
//
//		param.setStart(start);
//		param.setEnd(end);
//		
//		logger.info("Wilkommen AjaxController board ajaxGetBoardPageList param " +param);
//
//		return boardService.getBoardPageList(param);
//	}
//
//	@RequestMapping(value = "ajaxGetBoardTotalCount.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody int ajaxGetBoardTotalCount(JYBoardParam param, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController board ajaxGetBoardTotalCount " +new Date());
//		return boardService.getBoardTotalCount(param);
//	}
//
//	@RequestMapping(value = "ajaxGetRecentList.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody List<JYBoard> ajaxGetRecentList(HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController board ajaxGetRecentList " +new Date());
//		return boardService.getRecentList();
//	}
//
//	@RequestMapping(value = "ajaxUpdateReadcount.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody String ajaxUpdateReadcount(JYBoard board, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController board ajaxUpdateReadcount " +new Date());
//		boardService.updateReadcount(board);
//		return "Done";
//	}
//
//	@RequestMapping(value = "ajaxUpdateLikecount.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody String ajaxUpdateLikecount(JYBoard board, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController board ajaxUpdateLikecount " +new Date());
//		boardService.updateLikecount(board);
//		return "Done";
//	}
//
//	@RequestMapping(value = "ajaxUpdateHatecount.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody String ajaxUpdateHatecount(JYBoard board, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController board ajaxUpdateHatecount " +new Date());
//		boardService.updateHatecount(board);
//		return "Done";
//	}
//
//	@RequestMapping(value = "ajaxSetLikeHate.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody String ajaxSetLikeHate(JYBoardLHCount board, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController board ajaxSetLikeHate " +new Date());
//		boardService.setLikeHate(board);
//		return "Done";
//	}
//
//	@RequestMapping(value = "ajaxGetLHCount.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody JYBoard ajaxGetLHCount(JYBoard board, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController board ajaxGetLHCount " +new Date());
//		return boardService.getLHCount(board);
//	}
//
//	@RequestMapping(value = "ajaxCheckLikeHate.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody int ajaxCheckLikeHate(JYBoardLHCount board, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController board ajaxCheckLikeHate " +new Date());
//		return boardService.checkLikeHate(board);
//	}
//
//	@RequestMapping(value = "ajaxGetGlobalNoticeList.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody List<JYBoard> ajaxGetGlobalNoticeList(HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController board ajaxGetGlobalNoticeList " +new Date());
//		return boardService.getGlobalNoticeList();
//	}
//
//	@RequestMapping(value = "ajaxGetLocalNoticeList.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody List<JYBoard> ajaxGetLocalNoticeList(JYBoardParam param, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController board ajaxGetLocalNoticeList " +new Date());
//		return boardService.getLocalNoticeList(param);
//	}
//
//	@RequestMapping(value = "ajaxGoNotice.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody String ajaxGoNotice(JYBoard board, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController board ajaxGoNotice " +new Date());
//		boardService.goNotice(board);
//		return "Done";
//	}
//
//	@RequestMapping(value = "ajaxGetBestList.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody List<JYBoard> ajaxGetBestList(HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController board ajaxGetBestList " +new Date());
//		return boardService.getBestList();
//	}
//
//	@RequestMapping(value = "ajaxGetDateBoardList.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody List<JYBoard> ajaxGetDateBoardList(HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController board ajaxGetDateBoardList " +new Date());
//		return boardService.getDateBoardList();
//	}
//	
//	
//	//bookService
//	@RequestMapping(value = "ajaxCreateNewBook.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody String ajaxCreateNewBook(JYBooktitle booktitle, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController book ajaxCreateNewBook " +new Date());
//		bookService.createNewBook(booktitle);
//		return "Done";
//	}
//	
//	@RequestMapping(value = "ajaxWriteNewBook.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody String ajaxWriteNewBook(JYBook book, JYBookimg[] imgs, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController book ajaxWriteNewBook " +new Date());
//		bookService.writeNewBook(book, imgs);
//		return "Done";
//	}
//	
//	@RequestMapping(value = "ajaxSetRate.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody String ajaxSetRate(JYBookRatecount ratecount, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController book ajaxSetRate " +new Date());
//		bookService.setRate(ratecount);
//		return "Done";
//	}
//	
//	@RequestMapping(value = "ajaxGetBookTitleList.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody List<JYBooktitle> ajaxGetBookTitleList(JYBooktitleParam param, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController book ajaxGetBookTitleList " +new Date());
//		return bookService.getBookTitleList(param);
//	}
//	
//	@RequestMapping(value = "ajaxGetBookDetailList.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody List<JYBook> ajaxGetBookDetailList(JYBookParam param, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController book ajaxGetBookDetailList " +new Date());
//		return bookService.getBookDetailList(param);
//	}
//	
//	@RequestMapping(value = "ajaxGetImgList.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody List<JYBookimg> ajaxGetImgList(JYBook book, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController book ajaxGetImgList " +new Date());
//		return bookService.getImgList(book);
//	}
//	
//	@RequestMapping(value = "ajaxGetBookTitle.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody JYBooktitle ajaxGetBookTitle(JYBooktitle booktitle, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController book ajaxGetBookTitle " +new Date());
//		return bookService.getBookTitle(booktitle);
//	}
//	
//	@RequestMapping(value = "ajaxGetBookDetail.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody JYBook ajaxGetBookDetail(JYBook book, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController book ajaxGetBookDetail " +new Date());
//		return bookService.getBookDetail(book);
//	}
//	
//	@RequestMapping(value = "ajaxGetBookTitleTotalCount.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody int ajaxGetBookTitleTotalCount(HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController book ajaxGetBookTitleTotalCount " +new Date());
//		return bookService.getBookTitleTotalCount();
//	}
//	
//	@RequestMapping(value = "ajaxGetBookDetailTotalCount.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody int ajaxGetBookDetailTotalCount(HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController book ajaxGetBookDetailTotalCount " +new Date());
//		return bookService.getBookDetailTotalCount();
//	}
//	
//	@RequestMapping(value = "ajaxGetRate.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody JYBook ajaxGetRate(JYBook book, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController book ajaxGetRate " +new Date());
//		return bookService.getRate(book);
//	}
//	
//	@RequestMapping(value = "ajaxCheckRate.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody int ajaxCheckRate(JYBookRatecount ratecount, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController book ajaxCheckRate " +new Date());
//		return bookService.checkRate(ratecount);
//	}
//	
//	@RequestMapping(value = "ajaxUpdateReadcount.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody String ajaxUpdateReadcount(JYBook book, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController book ajaxUpdateReadcount " +new Date());
//		bookService.updateReadcount(book);
//		return "Done";
//	}
//	
//	@RequestMapping(value = "ajaxUpdateRate.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody String ajaxUpdateRate(JYBook book, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController book ajaxUpdateRate " +new Date());
//		bookService.updateRate(book);
//		return "Done";
//	}
//	
//	@RequestMapping(value = "ajaxUpdateUdate.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody String ajaxUpdateUdate(JYBook book, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController book ajaxUpdateUdate " +new Date());
//		bookService.updateUdate(book);
//		return "Done";
//	}
//	
//	
//	//categoryService
//	@RequestMapping(value = "ajaxGetCatList.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody List<JYCat> ajaxGetCatList(HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController category ajaxGetCatList " +new Date());
//		return categoryService.getCatList();
//	}
//	
//	@RequestMapping(value = "ajaxCreateCategory.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody String ajaxCreateCategory(JYCat cat, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController category ajaxCreateCategory " +new Date());
//		categoryService.createCategory(cat);
//		return "Done";
//	}
//	
//	@RequestMapping(value = "ajaxGetPopCatList.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody List<JYCat> ajaxGetPopCatList(HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController category ajaxGetPopCatList " +new Date());
//		return categoryService.getPopCatList();
//	}
//	
//	
//	//commentService
//	@RequestMapping(value = "ajaxWriteComment.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody String ajaxWriteComment(JYComment comment, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController comment ajaxWriteComment " +new Date());
//		commentService.writeComment(comment);
//		return "Done";
//	}
//	
//	@RequestMapping(value = "ajaxGetCommentList.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody List<JYComment> ajaxGetCommentList(JYComment comment, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController comment ajaxGetCommentList " +new Date());
//		return commentService.getCommentList(comment);
//	}
//	
//	@RequestMapping(value = "ajaxDeleteComment.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody String ajaxDeleteComment(JYComment comment, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController comment ajaxDeleteComment " +new Date());
//		commentService.deleteComment(comment);
//		return "Done";
//	}
//	
//	@RequestMapping(value = "ajaxReplyComment.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody String ajaxReplyComment(JYComment comment, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController comment ajaxReplyComment " +new Date());
//		commentService.replyComment(comment);
//		return "Done";
//	}
//	
//	
//	//dataService
//	@RequestMapping(value = "ajaxGetRegionList.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody List<Region> ajaxGetRegionList(HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController data ajaxGetRegionList " +new Date());
//		return dataService.getRegionList();
//	}
//	
//	@RequestMapping(value = "ajaxGetSearchList.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody List<JYBoard> ajaxGetSearchList(JYBoardParam param, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController data ajaxGetSearchList " +new Date());
//		return dataService.getSearchList(param);
//	}
//	
//	@RequestMapping(value = "ajaxGetSearchTotalCount.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody int ajaxGetSearchTotalCount(JYBoardParam param, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController data ajaxGetSearchTotalCount " +new Date());
//		return dataService.getSearchTotalCount(param);
//	}
//	
//	
//	//userService
//	@RequestMapping(value = "ajaxAddUser.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody String ajaxAddUser(JYUser user, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController user ajaxAddUser " +new Date());
//		userService.addUser(user);
//		return "Done";
//	}
//	
//	@RequestMapping(value = "ajaxLoginUser.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody JYUser ajaxLoginUser(JYUser user, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController user ajaxLoginUser " +new Date());
//		return userService.loginUser(user);
//	}
//	
//	@RequestMapping(value = "ajaxCheckId.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody int ajaxCheckId(JYUser user, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController user ajaxCheckId " +new Date());
//		return userService.checkId(user);
//	}
//	
//	@RequestMapping(value = "ajaxUpdateUser.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody String ajaxUpdateUser(JYUser user, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController user ajaxUpdateUser " +new Date());
//		userService.updateUser(user);
//		return "Done";
//	}
//	
//	@RequestMapping(value = "ajaxUserInfo.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody JYUser ajaxUserInfo(String id, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController user ajaxUserInfo " +new Date());
//		return userService.userInfo(id);
//	}
//	
//	@RequestMapping(value = "ajaxAddApi.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody String ajaxAddApi(HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController user ajaxAddApi " +new Date());
//		userService.addApi();
//		return "Done";
//	}
//	
//	@RequestMapping(value = "ajaxMobileLogin.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody JYUser ajaxMobileLogin(JYUser user, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController user ajaxMobileLogin " +new Date());
//		return userService.mobileLogin(user);
//	}
//	
//	@RequestMapping(value = "ajaxMyList.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody List<JYBoard> ajaxMyList(JYBoardParam param, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController user ajaxMyList " +new Date());
//		return userService.myList(param);
//	}
//	
//	@RequestMapping(value = "ajaxGetMyListTotalCount.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody int getMyListTotalCount(JYBoardParam param, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController user getMyListTotalCount " +new Date());
//		return userService.getMyListTotalCount(param);
//	}
//	
//	@RequestMapping(value = "ajaxMyCommentList.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody List<JYComment> ajaxMyCommentList(JYComment comment, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController user ajaxMyCommentList " +new Date());
//		return userService.myCommentList(comment);
//	}
//	
//	@RequestMapping(value = "ajaxGetUserList.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody List<JYUser> ajaxGetUserList(JYUserParam param, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController user ajaxGetUserList " +new Date());
//		return userService.getUserList(param);
//	}
//	
//	@RequestMapping(value = "ajaxGetUserTotalCount.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody int ajaxGetUserTotalCount(JYUserParam param, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController user ajaxGetUserTotalCount " +new Date());
//		return userService.getUserTotalCount(param);
//	}
//	
//	@RequestMapping(value = "ajaxDeleteUser.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody String ajaxDeleteUser(JYUser user, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController user ajaxDeleteUser " +new Date());
//		userService.deleteUser(user);
//		return "Done";
//	}
//	
//	@RequestMapping(value = "ajaxGetAllUserList.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody List<JYUser> ajaxGetAllUserList(JYUser user, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController user ajaxGetAllUserList " +new Date());
//		return userService.getAllUserList();
//	}
//	
//	
//	//messageService
//	@RequestMapping(value = "ajaxWriteMessage.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody String ajaxWriteMessage(JYMessage msg, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController message ajaxWriteMessage " +new Date());
//		messageService.writeMessage(msg);
//		return "Done";
//	}
//	
//	@RequestMapping(value = "ajaxUpdateReadchk.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody String ajaxUpdateReadchk(JYMessage msg, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController message ajaxUpdateReadchk " +new Date());
//		messageService.updateReadchk(msg);
//		return "Done";
//	}
//	
//	@RequestMapping(value = "ajaxGetMessage.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody JYMessage ajaxGetMessage(JYMessage msg, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController message ajaxGetMessage " +new Date());
//		return messageService.getMessage(msg);
//	}
//	
//	@RequestMapping(value = "ajaxGetMessageList.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody List<JYMessage> ajaxGetMessageList(JYMessageParam param, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController message ajaxGetMessageList " +new Date());
//		return messageService.getMessageList(param);
//	}
//	
//	@RequestMapping(value = "ajaxDeleteMessage.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody String ajaxDeleteMessage(JYMessage msg, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController message ajaxDeleteMessage " +new Date());
//		messageService.deleteMessage(msg);
//		return "Done";
//	}
//	
//	@RequestMapping(value = "ajaxGetMessageTotalCount.jy", method = {RequestMethod.POST, RequestMethod.GET})
//	public @ResponseBody int ajaxGetMessageTotalCount(JYMessageParam param, HttpServletRequest request, Model model) {
//		logger.info("Wilkommen AjaxController message ajaxGetMessageTotalCount " +new Date());
//		return messageService.getMessageTotalCount(param);
//	}
	
}
