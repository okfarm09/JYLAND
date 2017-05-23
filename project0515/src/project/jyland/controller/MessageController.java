package project.jyland.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import project.jyland.category.dao.CategoryService;
import project.jyland.message.dao.JYMessageService;
import project.jyland.message.help.MessageHelper;
import project.jyland.message.model.JYMessage;
import project.jyland.message.model.JYMessageParam;

@Controller
public class MessageController {
	
	@Autowired
	private JYMessageService jymessageService;

	@Autowired
	private CategoryService categoryService;
	
	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	@RequestMapping(value="writemessage.jy",method=RequestMethod.POST)
	public String writemessage(JYMessage msg,Model model){
		logger.info("Welcome MessageController writeMessage " + new Date());
		logger.info("Welcome MessageController writeMessage " + msg);
		jymessageService.writeMessage(msg);
		return "redirect:/userinfo.jy?id="+msg.getRid();
	}
	
	@RequestMapping(value = "messagelist.jy", method=RequestMethod.GET)
	public String messagelist(JYMessageParam param, HttpServletRequest request, Model model) {
		logger.info("Welcome MessageController messagelist " + new Date());
		logger.info("Welcome MessageController messagelist " + param);
		
		int sn = param.getPageNumber();
		int start = (sn) * param.getRecordCountPerPage() + 1;
		int end = (sn + 1) * param.getRecordCountPerPage();
		
		param.setStart(start);
		param.setEnd(end);
		logger.info("Welcome MessageController messagelist " + param);
		
		int totalRecordCount = jymessageService.getMessageTotalCount(param);
		logger.info("Welcome MessageController messagelist " + totalRecordCount);
		
		List<JYMessage> msglist=new ArrayList<>();
		List<JYMessage> message=jymessageService.getMessageList(param);
		logger.info("Welcome MessageController messagelist " + message.size());
		for(JYMessage mm : message) {
			String msg=mm.getContent();
			String temp=MessageHelper.msgContentRmBr(msg);
			msg=MessageHelper.msgLength(temp);
			mm.setContent(msg);
			msglist.add(mm);
		}
		logger.info("Welcome MessageController messagelist " + msglist.size());
		request.getSession().setAttribute("bestcategorylist", categoryService.getPopCatList());
		request.getSession().setAttribute("categorylist", categoryService.getCatList());
		model.addAttribute("doc_title", "받은 쪽지 목록");
		model.addAttribute("msglist", msglist);
		
		model.addAttribute("pageNumber", sn);
		model.addAttribute("pageCountPerScreen", 10);
		model.addAttribute("recordCountPerPage", param.getRecordCountPerPage());
		model.addAttribute("totalRecordCount", totalRecordCount);
		
		return "messagelist.tiles";
	}
	
	@RequestMapping(value = "recievedmessages.jy", method=RequestMethod.GET)
	public String recievedmessages(JYMessageParam param, Model model) {
		logger.info("Welcome MessageController recievedmessages " + new Date());
		logger.info("Welcome MessageController recievedmessages " + param);

		int sn = param.getPageNumber();
		int start = (sn) * param.getRecordCountPerPage() + 1;
		int end = (sn + 1) * param.getRecordCountPerPage();
		
		param.setStart(start);
		param.setEnd(end);
		logger.info("Welcome MessageController recievedmessages " + param);

		int totalRecordCount = jymessageService.getMessageTotalCount(param);
		logger.info("Welcome MessageController recievedmessages " + totalRecordCount);
		
		List<JYMessage> msglist=new ArrayList<>();
		List<JYMessage> message=jymessageService.getMessageList(param);
		logger.info("Welcome MessageController recievedmessages " + message.size());
		for(JYMessage mm : message) {
			String msg=mm.getContent();
			String temp=MessageHelper.msgContentRmBr(msg);
			msg=MessageHelper.msgLength(temp);
			mm.setContent(msg);
			msglist.add(mm);
		}
		logger.info("Welcome MessageController recievedmessages " + msglist.size());
		model.addAttribute("doc_title", "받은 쪽지 목록");
		model.addAttribute("msglist", msglist);
		
		model.addAttribute("pageNumber", sn);
		model.addAttribute("pageCountPerScreen", 10);
		model.addAttribute("recordCountPerPage", param.getRecordCountPerPage());
		model.addAttribute("totalRecordCount", totalRecordCount);
		
		return "redirect:/messagelist.jy?rid="+param.getRid();
	}
	
	@RequestMapping(value = "messagedetail.jy", method=RequestMethod.POST)
	public @ResponseBody JYMessage messagedetail(JYMessage msg, Model model) {
		logger.info("Welcome MessageController messagedetail " + new Date());
		logger.info("Welcome MessageController messagedetail " + msg);
		jymessageService.updateReadchk(msg);
		JYMessage message=jymessageService.getMessage(msg);
		logger.info("Welcome MessageController messagedetail " + message);
		return message;
	}

}
