package com.butshow.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import weibo4j.Account;
import weibo4j.Oauth;
import weibo4j.Users;
import weibo4j.http.AccessToken;
import weibo4j.model.User;
import weibo4j.org.json.JSONObject;

import com.butshow.entity.UserDefault;
import com.butshow.service.user.UserService;
import com.butshow.util.Constants;


/**
 * 新浪微博登录
 * 
 * @author SweetPink
 * 
 */
@Controller
@RequestMapping("/wechat")
public class WechatController {
	
	@Resource
	private UserService userService;

	@RequestMapping("/login")
	public String SinaLogin(HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		try {
			Oauth oauth = new Oauth();
			response.sendRedirect(oauth.authorize("code")); // 重定向到新浪授权页面
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 新浪微博登录的回调
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/afterlogin")
	public String SinaAfterLogin(HttpServletResponse response,
			HttpServletRequest request, Model model) throws Exception {

		HttpSession session = request.getSession();
		String accesstoken = null;
		String code = request.getParameter("code");
		if (code != null) {
			Oauth oauth = new Oauth();
			AccessToken accessToken = oauth.getAccessTokenByCode(code);
			accesstoken = accessToken.getAccessToken();
			if (accessToken != null) {
				Users users = new Users(accesstoken);
				
				Account account = new Account(accesstoken) ;
	            JSONObject uidJson = account.getUid() ;
	            String uid = uidJson.getString("uid") ;
				User weiboUser = users.showUserById(uid);
				UserDefault user = new UserDefault();
				user.setAddress(weiboUser.getLocation());
				user.setEmail("");
				user.setIsrealemail(0);
				user.setLoginpassword("null");
				user.setRemark(weiboUser.getProfileImageUrl());
				user.setSex(weiboUser.getGender().equals("m")?1:0);
				user.setSignupsource(Constants.SIGNUPSOURCE_SINA);
				user.setUuid(weiboUser.getId());
				user.setUsername(weiboUser.getScreenName());
				user.setUsertype("1");
				user.setSignuptime(new Date());
				if(null==userService.getUserByUUID(weiboUser.getId(),Constants.SIGNUPSOURCE_SINA))
                	userService.saveUser(user);
				model.addAttribute("user", user);
        		session.setAttribute("user", user);
			}
			return "redirect:/user/index";

		} else {
			return "redirect:/user/gotoLogin";
		}
	}
}
