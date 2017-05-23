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

import project.jyland.comment.dao.CommentService;
import project.jyland.comment.model.JYComment;
 
@Controller
public class CommentController {
	
	private static Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@Autowired
	private CommentService CommentService;
	
	@RequestMapping(value = "writeComment.jy", method = {RequestMethod.GET, RequestMethod.POST})
	public String writeComment(JYComment comment,Model model) {
		logger.info("Welcome CommentController writeComment! " + new Date());
		String tempip = "000.000.000.000";
		comment.setIp(tempip);
		logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@2"+comment + new Date());
		CommentService.writeComment(comment);
		
		return "redirect:/boarddetail.jy?seq="+comment.getBoardseq();
	}
	
	@RequestMapping(value = "getCommentList.jy", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JYComment> getCommentList(JYComment comment,Model model) {
		logger.info("Welcome CommentController getCommentList! " + new Date());
		List<JYComment> CommentList= CommentService.getCommentList(comment);
		logger.info("Welcome CommentController getCommentList! " + CommentList);
		return CommentList;
	}
	
	@RequestMapping(value = "replyComment.jy", method = {RequestMethod.POST, RequestMethod.GET})
	public String replyComment(JYComment comment, Model model) {
		logger.info("Welcome CommentController replyComment " + new Date());
		String tempip = "000.000.000.000";
		comment.setIp(tempip);
		CommentService.replyComment(comment);
		return "redirect:/boarddetail.jy?seq="+comment.getBoardseq();
	}
	
	@RequestMapping(value = "deleteComment.jy", method = {RequestMethod.POST, RequestMethod.GET})
	public String deleteComment(JYComment comment, Model mode) {
		logger.info("Welcome CommentController deleteComment " + new Date());
		logger.info("Welcome CommentController deleteComment " + comment);
		CommentService.deleteComment(comment);
		return "redirect:/boarddetail.jy?seq="+comment.getBoardseq();
	}
}
