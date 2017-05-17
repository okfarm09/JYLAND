package project.jyland.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
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
import project.jyland.comment.model.JYComment;
import project.jyland.member.dao.JYUserService;
import project.jyland.member.model.JYUser;
import project.jyland.member.model.JYUserParam;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private JYUserService jYUserService;

	@RequestMapping(value = "join.jy", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(JYUser user, Model model) throws Exception {
		logger.info("Welcome LoginController join! " + new Date());
		jYUserService.addUser(user);
		return "mainpage.tiles";
	}//

	@RequestMapping(value = "checkId.jy", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody int idCheck(JYUser user, Model model) {
		logger.info("Welcome LoginController idCheck! " + new Date());
		return jYUserService.checkId(user);
	}//

	@RequestMapping(value = "login.jy", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(HttpServletRequest request, JYUser user, Model model) {
		logger.info("Welcome LoginController login! " + new Date());
		JYUser login = jYUserService.loginUser(user);
		logger.info("Welcome LoginController login!---- " + login);
		if (login != null && !login.getId().equals("")) {
			request.getSession().setAttribute("login", login);
			request.getSession().setMaxInactiveInterval(60 * 60 * 24);
			return "forward:/main.jy";
		} else {
			request.getSession().invalidate();
			return "redirect:/main.jy";
		}
	}//

	@RequestMapping(value = "logout.jy", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, Model model) {
		logger.info("Welcome LoginController logout! " + new Date());
		request.getSession().invalidate();
		return "redirect:/main.jy";
	}//

	@RequestMapping(value = "myinfo.jy", method = RequestMethod.GET)
	public String myinfo(Model model) {
		logger.info("Welcome LoginController myinfo! " + new Date());
		model.addAttribute("doc_title", "내 정보");
		return "myinfo.tiles";
	}

	@RequestMapping(value = "infochange.jy", method = RequestMethod.GET)
	public String infochange(Model model) {
		logger.info("Welcome LoginController infochange! " + new Date());
		model.addAttribute("doc_title", "내 정보 수정");
		return "infochange.tiles";
	}

	@RequestMapping(value = "infochangeAf.jy", method = RequestMethod.POST)
	public String infochangeAf(HttpServletRequest request, JYUser user, String rpwd, Model model) {
		logger.info("Welcome LoginController infochangeAf! " + new Date());
		logger.info("Welcome LoginController infochangeAf! user: " + user);
		JYUser tempUser1 = new JYUser();
		logger.info("Welcome LoginController infochangeAf! tempUser1-1: " + tempUser1);
		tempUser1.setId(user.getId());
		tempUser1.setUpwd(rpwd);
		logger.info("Welcome LoginController infochangeAf! tempUser1-2: " + tempUser1);
		JYUser tempUser2 = jYUserService.loginUser(tempUser1);
		logger.info("Welcome LoginController infochangeAf! tempUser1-3: " + tempUser1);
		logger.info("Welcome LoginController infochangeAf! tempUser3-1: " + tempUser2);
		if (user.getNickname() == null || user.getNickname().equals("")) {
			user.setNickname(tempUser2.getNickname());
		}
		if (user.getEmail() == null || user.getEmail().equals("")) {
			user.setEmail(tempUser2.getEmail());
		}
		if (user.getUpwd() == null || user.getUpwd().equals("")) {
			user.setUpwd(tempUser2.getUpwd());
		}
		logger.info("Welcome LoginController infochangeAf! tempUser1-4: " + tempUser1);
		jYUserService.updateUser(user);
		JYUser login = jYUserService.loginUser(user);
		logger.info("Welcome LoginController infochangeAf! login: " + login);
		request.getSession().setAttribute("login", login);
		return "redirect:/myinfo.jy";
	}
	
	@RequestMapping(value = "userinfo.jy", method = RequestMethod.GET)
	public String userinfo(String id, Model model) {
		logger.info("Welcome LoginController userinfo " + new Date());
		JYUser user = jYUserService.userInfo(id);
		model.addAttribute("doc_title", user.getNickname()+"의 정보");
		model.addAttribute("userInfo", user);
		return "userinfo.tiles";
	}
	
	@RequestMapping(value = "mylist.jy", method = { RequestMethod.GET, RequestMethod.POST })
	public String mylist(JYBoardParam param, Model model) throws Exception {
		logger.info("Welcome LoginController mylist!------------------------------------------------------- " + new Date());

		int sn = param.getPageNumber();
		int start = (sn) * param.getRecordCountPerPage() + 1;
		int end = (sn + 1) * param.getRecordCountPerPage();

		param.setStart(start);
		param.setEnd(end);
		logger.info("Welcome LoginController mylist param! " + param);

		int totalRecordCount = jYUserService.getMyListTotalCount(param);
		logger.info("Welcome LoginController mylist totalRecordCount! " + totalRecordCount);

		List<JYBoard> mylist = jYUserService.myList(param);
		logger.info("Welcome LoginController mylist! " + mylist.size());

		model.addAttribute("doc_title", param.getId() + "의 전체글 보기");
		model.addAttribute("mylist", mylist);

		model.addAttribute("pageNumber", sn);
		model.addAttribute("pageCountPerScreen", 10);
		model.addAttribute("recordCountPerPage", param.getRecordCountPerPage());
		model.addAttribute("totalRecordCount", totalRecordCount);

		return "mylist.tiles";
	}
	
	@RequestMapping(value = "mycomment.jy", method = {RequestMethod.GET, RequestMethod.POST})
	public String mycomment(JYComment comment,Model model) {
		logger.info("Welcome LoginController mycomment! " + new Date());
		List<JYComment> mycomment= jYUserService.myCommentList(comment);
		logger.info("Welcome LoginController mycomment! " + mycomment);
		
		model.addAttribute("doc_title",  comment.getId() + "의 전체 댓글 보기");
		model.addAttribute("mycomment", mycomment);
		return "mycomment.tiles";
	}

	@RequestMapping(value = "userlist.jy", method = {RequestMethod.GET})
	public String userlist(JYUserParam param, Model model) {
		logger.info("Welcome LoginController userlist!" + new Date());
		int sn = param.getPageNumber();
		int start = (sn) * param.getRecordCountPerPage() + 1;
		int end = (sn + 1) * param.getRecordCountPerPage();

		param.setStart(start);
		param.setEnd(end);
		logger.info("Welcome LoginController userlist param! " + param);

		int totalRecordCount = jYUserService.getUserTotalCount(param);
		logger.info("Welcome LoginController userlist totalRecordCount! " + totalRecordCount);

		List<JYUser> userlist = jYUserService.getUserList(param);
		logger.info("Welcome LoginController userlist! " + userlist.size());

		model.addAttribute("doc_title", "회원리스트");
		model.addAttribute("userlist", userlist);

		model.addAttribute("pageNumber", sn);
		model.addAttribute("pageCountPerScreen", 10);
		model.addAttribute("recordCountPerPage", param.getRecordCountPerPage());
		model.addAttribute("totalRecordCount", totalRecordCount);

		return "userlist.tiles";
	}
	
	@RequestMapping(value="deleteuser.jy", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteUser(JYUser user, Model model) {
		logger.info("Welcome LoginController deleteUser!" + new Date());
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
		String pwd = RandomStringUtils.random( 15, characters );
		user.setUpwd(pwd);
		jYUserService.deleteUser(user);
		return "redirect:/userlist.jy";
	}
	
	@RequestMapping(value = "getalluserlist.jy", method = RequestMethod.GET)
	public @ResponseBody List<JYUser> getAllUserList(Model model) {
		logger.info("Welcome LoginController getAllUserList " + new Date());
		List<JYUser> list=jYUserService.getAllUserList();
		return list;
	}
}
