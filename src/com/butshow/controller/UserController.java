package com.butshow.controller;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.butshow.entity.UserDefault;
import com.butshow.service.user.UserService;
import com.butshow.util.Constants;
import com.butshow.util.TimeUtils;
import com.butshow.util.Util;
import com.butshow.util.page.PageResultSet;

@Controller
@RequestMapping("/user")
@SessionAttributes(value = {"user"})
public class UserController {

	@Resource
	private UserService userService;
	
	@RequestMapping("/gotoLogin")
	public String gotoLogin(){
		return "login";
	}
	
	@RequestMapping("/userLogin")
	public String userLogin(UserDefault userModel, Model model){
		userModel.setLoginpassword(Util.encryptMD5(userModel.getLoginpassword()));
		UserDefault user = userService.findUserByByCondition(userModel);
		if (user == null) {
			model.addAttribute("errorInfo", "you password is error!");
			return "login";
		}
		user.setLoginpassword("");
		model.addAttribute("user", user);
		return "redirect:/user/index";
	}
	@RequestMapping("/addUser")
	public String addUser(UserDefault user,@RequestParam("SexRadio")  String[] Sex, Model model){
		Timestamp date = new Timestamp(new Date().getTime());
		//BeanUtils.copyProperties(userModel, user); //实体属性复制，将userModel中的属性值复制到User中
		user.setLoginpassword(Util.encryptMD5(user.getLoginpassword()));
		user.setSignuptime(date);
		user.setUuid(Util.getUUID());
		user.setIsrealemail(0);
		user.setSignupsource(Constants.SIGNUPSOURCE_WEB);
		user.setUpdatatime(date);
		user.setUsertype(Constants.USERTYPE_ONE);
		user.setSex(Integer.parseInt(Sex.length>0?Sex[0]:"0"));
		userService.saveUser(user);
		model.addAttribute("user", user);
		return "redirect:/user/index";
	}
	@RequestMapping("/gotoAdd")
	public String gotoAdd(){
		return "user/adduser";
	}
	@RequestMapping("/index")
	public ModelAndView index(@ModelAttribute("user") UserDefault user, Model model) {  
		if(Util.isNull(user)){
			ModelAndView mav = new ModelAndView("login");
			mav.addObject("errorInfo", "user is overdue!");
			return mav;
		}
		model.addAttribute("user", user);
		return new ModelAndView("user/manager");
	 }  
	
	@RequestMapping("/getUserByName")
	@ResponseBody
	public boolean getUserByName(HttpServletRequest request,@ModelAttribute("username") String username) {  
		UserDefault user = userService.getUserByName(username);
		return user!=null;
	}  
	
	@RequestMapping("/getUserByEmail")
	@ResponseBody
	public boolean getUserByEmail(HttpServletRequest request,@ModelAttribute("email") String email) {  
		UserDefault user = userService.getUserByEmail(email);
		return user!=null;
	}  
	
	@RequestMapping("/getUserByPhone")
	@ResponseBody
	public boolean getUserByPhone(HttpServletRequest request,@ModelAttribute("phone") String phone) {  
		UserDefault user = userService.getUserByPhone(phone);
		return user!=null;
	}  
	
	@RequestMapping("/getUser")
	public @ModelAttribute("user") UserDefault getUser(HttpServletRequest request, HttpServletResponse response, Model model) {  
	    int id = Integer.parseInt(request.getParameter("userId"));
	    UserDefault user = userService.getUserById(id);
		return user;
	 }  
	
	/**
	 * 效果同上，写法不同
	 */
//	@RequestMapping("/getUserDefault")
//	@ModelAttribute
//	public void getUserDefault(HttpServletRequest request, HttpServletResponse response, Model model) {  
//		int id = Integer.parseInt(request.getParameter("userId"));
//		UserDefault user = userService.getUserById(id);
//	    model.addAttribute("user", user);
//	}  
	
	@RequestMapping("/updateUser")
	public String updateUser(UserDefault user){
		if (Util.isNull(user.getLoginpassword())) {
			user.setLoginpassword(user.getLoginpassword());
		}else {
			user.setLoginpassword(Util.encryptMD5(user.getLoginpassword()));
		}
		user.setSignuptime(TimeUtils.getSysTimestamp());
//		BeanUtils.copyProperties(userModel, user);
		userService.updateUser(user);
		return "redirect:/user/index";
	}
	
	@RequestMapping("/deleteUser")
	public String deleteUser(HttpServletRequest request){
		  int id = Integer.parseInt(request.getParameter("userId"));
		  UserDefault user = userService.getUserById(id);
		  userService.deleteUser(user);
		  return "redirect:/user/index";
	}
	
	@RequestMapping("/exit")
	public String exit(){
		Constants.LOGIN_USER = null;
		return "redirect:/";
	}
	//返回json
	@RequestMapping("/getJsonUserList")
	@ResponseBody
	public ModelMap getJsonUserList(UserDefault userModel, HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		response.setHeader("Access-Control-Allow-Origin", "*");
		String sPage = request.getParameter("page");
		int page = 1;
		if (!Util.isNull(sPage)) {
			page = Integer.parseInt(sPage);
		}
		PageResultSet<UserDefault> userPageResult = userService.findPageUserList(userModel, page, Constants.PAGE_SIZE);
		
		modelMap.addAttribute("event",userPageResult.getList());
		modelMap.addAttribute("pageBean", userPageResult.getPage());
		modelMap.addAttribute("pageCount",userPageResult.getPage().getTotalPage());
		return modelMap;
	}
	  
}
