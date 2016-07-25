package com.butshow.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.butshow.entity.UserDefault;
import com.butshow.service.user.UserService;
import com.butshow.util.Constants;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.PageFans;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.PageFansBean;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.javabeans.weibo.Company;
import com.qq.connect.oauth.Oauth;

/**
 * QQ 相关
 * @author SweetPink
 *
 */
@Controller
@RequestMapping("/qq")
public class QQConnectController {
	
	@Resource
	private UserService userService;
	
    /**
     * qq登录页面
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/login")
    public String QQLogin(HttpServletResponse response, HttpServletRequest request) throws IOException{
    	 response.setContentType("text/html;charset=utf-8");
         try {
             response.sendRedirect(new Oauth().getAuthorizeURL(request));
         } catch (QQConnectException e) {
             e.printStackTrace();
         }
        return null;
    }
    
    /**
     * qq登录回调
     * @param response
     * @param request
     * @throws IOException
     */
    @RequestMapping("/afterlogin")
    public String QQAfterlogin(HttpServletResponse response, HttpServletRequest request, Model model) throws IOException{
    	response.setContentType("text/html; charset=utf-8");
    	HttpSession session = request.getSession();
//        PrintWriter out = response.getWriter();
        try {
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
            String accessToken = null,openID = null;
            long tokenExpireIn = 0L;

            if (accessTokenObj.getAccessToken().equals("")) {
//                我们的网站被CSRF攻击了或者用户取消了授权
//                做一些数据统计工作
                System.out.print("没有获取到响应参数");
            } else {
                accessToken = accessTokenObj.getAccessToken();
                tokenExpireIn = accessTokenObj.getExpireIn();

                request.getSession().setAttribute("demo_access_token", accessToken);
                request.getSession().setAttribute("demo_token_expirein", String.valueOf(tokenExpireIn));

                // 利用获取到的accessToken 去获取当前用的openid -------- start
                OpenID openIDObj =  new OpenID(accessToken);
                openID = openIDObj.getUserOpenID();//用户OpenId

                request.getSession().setAttribute("demo_openid", openID);
                // 利用获取到的accessToken 去获取当前用户的openid --------- end
                UserDefault user = new UserDefault();

               //利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息 ---------------------------- start
                UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
                UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
                if (userInfoBean.getRet() == 0) {
                	user.setUuid(openID);
                	user.setSignupsource(Constants.SIGNUPSOURCE_QQ);
                	user.setSex(userInfoBean.getGender().equals("男")?1:0);
                	user.setRemark(userInfoBean.getAvatar().getAvatarURL50());
                } else {
                	
                }
                //利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息 ---------------------------- end



                //验证当前用户是否为认证空间的粉丝------------------------------------------------ start
                PageFans pageFansObj = new PageFans(accessToken, openID);
                PageFansBean pageFansBean = pageFansObj.checkPageFans("97700000");
                if (pageFansBean.getRet() == 0) {
//                    out.println("<p>验证您" + (pageFansBean.isFans() ? "是" : "不是")  + "QQ空间97700000官方认证空间的粉丝</p>");
                } else {
                }
                //验证当前用户是否为认证空间的粉丝------------------------------------------------ end 



                //利用获取到的accessToken,openid 去获取用户在微博的昵称等信息 ---------------------------- start
                com.qq.connect.api.weibo.UserInfo weiboUserInfo = new com.qq.connect.api.weibo.UserInfo(accessToken, openID);
                com.qq.connect.javabeans.weibo.UserInfoBean weiboUserInfoBean = weiboUserInfo.getUserInfo();
                if (weiboUserInfoBean.getRet() == 0) {
                    //获取用户的微博头像----------------------start
//                    weiboUserInfoBean.getAvatar().getAvatarURL50();
                    //获取用户的微博头像 ---------------------end

                    //获取用户的生日信息 --------------------start
                	user.setAddress(weiboUserInfoBean.getLocation());
                	user.setEmail(weiboUserInfoBean.getEmail());
                	user.setUsername(weiboUserInfoBean.getNickName());
                	user.setLoginpassword("null");
                	user.setIsrealemail(0);
                	user.setSignuptime(new Date());
                    //获取用户的生日信息 --------------------end


                    //获取用户的公司信息---------------------------start
                    ArrayList<Company> companies = weiboUserInfoBean.getCompanies();
                    if (companies.size() > 0) {
                        //有公司信息
                    } else {
                        //没有公司信息
                    }
                    //获取用户的公司信息---------------------------end
//                    out.println(sb.toString());

                } else {
                }
                //利用获取到的accessToken,openid 去获取用户在微博的昵称等信息 ---------------------------- end
                if(null==userService.getUserByUUID(openID,Constants.SIGNUPSOURCE_QQ))
                	userService.saveUser(user);
                
                System.out.println(user.toString());
        		model.addAttribute("user", user);
        		session.setAttribute("user", user);
        		return "redirect:/user/index";
            }
        } catch (QQConnectException e) {
        	return "redirect:/user/gotoLogin";
        }
        return "redirect:/user/gotoLogin";
    }
    
}
