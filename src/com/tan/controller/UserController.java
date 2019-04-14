package com.tan.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tan.entity.User;
import com.tan.service.UserService;
import com.tan.util.DateUtil;
import com.tan.util.ImageUtil;
import com.tan.util.MD5Util;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource//默认按照名称进行装配
	private UserService userService;
	
	@RequestMapping("/login")
	public ModelAndView login(User user,HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession();
		String remember=request.getParameter("remember");
		User currentUser=userService.checkLogin(user.getUserName(), user.getPassword());
		ModelAndView mav=new ModelAndView();
		if(currentUser!=null){
			if("remember-me".equals(remember)){
				rememberMe(user.getUserName(),user.getPassword(),response);
			}
			session.setAttribute("currentUser", currentUser);
			mav.setViewName("redirect:/main");
			return mav;
		}else{
			mav.addObject("user",user);
			mav.setViewName("forward:/login.jsp");
			mav.addObject("error", "用户名或密码错误！");
			return mav;
		}
	}
	
	@RequestMapping("/register")
	public ModelAndView userRegiseter(User user,HttpServletRequest request) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		ModelAndView mav=new ModelAndView();
		String userName=user.getUserName().trim();
		String password=user.getPassword().trim();
		String password2=request.getParameter("password2");
		
		if(userName==null){
			userName="";
		}
		if(password==""|password==null){
			password="error";
			mav.addObject("backNews","请设置密码！");
		}else if(!(password2.equals(password))){
			mav.addObject("backNews","密码不一致，注册失败！");
		}else{
			if(password.length()>5){
				String EncodedPassword=MD5Util.EncodePwdByMd5(password);
				User saveUser=new User();
				saveUser.setUserName(userName);
				saveUser.setPassword(EncodedPassword);
				
				int Num=0;
				try{
					Num=userService.addUser(saveUser);
				}catch(Exception e){
					e.printStackTrace();
					mav.addObject("backNews","该用户名已被注册！");
				}
				
				if(Num>0){
					mav.addObject("error","注册成功！");
					mav.setViewName("forward:/login.jsp");
				}
			}else{
				mav.addObject("backNews","密码长度不能低于5位！");
				mav.setViewName("forward:/user/register.jsp");
			}
		}
		return mav;
	}
	
	@RequestMapping("/preSave")
	public ModelAndView userPreSave(HttpServletRequest request){
		ModelAndView mav=new ModelAndView(); 
		mav.addObject("mainPage", "user/userSave.jsp");
		mav.setViewName("forward:/mainTemp.jsp");
		return mav;
		}
	
	@RequestMapping("/save")
	public ModelAndView userSave(@RequestParam(value="imagePath",required=false)MultipartFile file,HttpServletRequest request,User s_user) throws Exception{
		ModelAndView mav=new ModelAndView(); 
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("currentUser");
		if(file.getSize()>0){
			String imageName=DateUtil.getCurrentDateStr();
			s_user.setImageName(imageName+"."+file.getOriginalFilename().split("\\.")[1]);
			File imageFolder=new File(session.getServletContext().getRealPath("userImages/"));
			File saveFile=new File(imageFolder,s_user.getImageName());
			if(!saveFile.getParentFile().exists())
				saveFile.getParentFile().mkdirs();
			file.transferTo(saveFile);
			BufferedImage img=ImageUtil.change2jpg(saveFile); 
			ImageIO.write(img, "jpg", saveFile);
		}else{
			s_user.setImageName(user.getImageName());
		}
		s_user.setUserName(user.getUserName());
		int saveNums=userService.updateUser(s_user);
		if(saveNums>0){
			session.setAttribute("currentUser", s_user);
			mav.setViewName("forward:/main?all=true");
		}else{
			mav.addObject("currentUser", user);
			mav.addObject("error", "保存失败！");
			mav.addObject("mainPage", "user/userSave.jsp");
			mav.setViewName("forward:/mainTemp.jsp");
		}
		return mav;
	}
	
	private void rememberMe(String userName,String password,HttpServletResponse response){
		Cookie cookie=new Cookie("user",userName+"-"+password);
		cookie.setMaxAge(1*60*60*24*7);//设置保存期限为7天
		cookie.setPath("/");//设置保存路径为全局都可访问
		response.addCookie(cookie);
	}
}
