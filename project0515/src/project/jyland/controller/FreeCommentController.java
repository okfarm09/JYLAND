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
import org.springframework.web.bind.annotation.ResponseBody;

import project.jyland.comment.free.dao.FreeCommentService;
import project.jyland.comment.model.JYComment;
 
@Controller
public class FreeCommentController {
	
	Logger logger = LoggerFactory.getLogger(FreeCommentController.class);
	
	@Autowired
	FreeCommentService freeCommentService;
	
	@RequestMapping(value = "writeComment.jy", method = {RequestMethod.GET, RequestMethod.POST})
	public String writeComment(JYComment comment,Model model) {
		logger.info("Welcome FreeCommentController writeComment! " + new Date());
		String tempip = "000.000.000.000";
		comment.setIp(tempip);
		logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@2"+comment + new Date());
		freeCommentService.writeComment(comment);
		
		return "redirect:/freedetail.jy?seq="+comment.getBoardseq();
	}
	
	@RequestMapping(value = "getCommentList.jy", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JYComment> getCommentList(JYComment comment,Model model) {
		logger.info("Welcome FreeCommentController getCommentList! " + new Date());
		List<JYComment> CommentList= freeCommentService.getCommentList(comment);
		logger.info("Welcome FreeCommentController getCommentList! " + CommentList);
		return CommentList;
	}
	
	@RequestMapping(value = "replyComment.jy", method = {RequestMethod.POST, RequestMethod.GET})
	public String replyComment(JYComment comment, Model model) {
		logger.info("Welcome FreeCommentController replyComment " + new Date());
		String tempip = "000.000.000.000";
		comment.setIp(tempip);
		freeCommentService.replyComment(comment);
		return "redirect:/freedetail.jy?seq="+comment.getBoardseq();
	}
	
	@RequestMapping(value = "deleteComment.jy", method = {RequestMethod.POST, RequestMethod.GET})
	public String deleteComment(JYComment comment, Model mode) {
		logger.info("Welcome FreeCommentController deleteComment " + new Date());
		logger.info("Welcome FreeCommentController deleteComment " + comment);
		freeCommentService.deleteComment(comment);
		return "redirect:/freedetail.jy?seq="+comment.getBoardseq();
	}
}
