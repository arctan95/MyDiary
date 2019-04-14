package com.tan.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tan.entity.Diary;
import com.tan.entity.DiaryType;
import com.tan.entity.PageBean;
import com.tan.service.DiaryService;
import com.tan.service.DiaryTypeService;
import com.tan.util.PropertiesUtil;
import com.tan.util.StringUtil;

@Controller
public class MainController {

	@Resource
	private DiaryService diaryService;
	@Resource
	private DiaryTypeService diaryTypeService;
	
	@RequestMapping("/main")
	public ModelAndView main(@RequestParam(value="all",required=false)String all,@RequestParam(value="page",required=false)String page,HttpServletRequest request,Diary s_diary){
		HttpSession session=request.getSession();
		String typeId=request.getParameter("typeId");
		String releaseDateStr=request.getParameter("releaseDateStr");
		ModelAndView mav=new ModelAndView();
		PageBean pageBean=null;
		if(page!=null){
			pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(PropertiesUtil.getValue("pageSize")));
		}else{
			pageBean=new PageBean(1,Integer.parseInt(PropertiesUtil.getValue("pageSize")));
		}
		Map<String,Object> map=new HashMap<String,Object>();
		if("true".equals(all)){
			if(s_diary.getTitle()!=null){
				map.put("title", StringUtil.formatLike(s_diary.getTitle()));
			}
			map.remove("releaseDateStr");
			map.remove("typeId");
		}else{
			if(typeId!=null){
				map.put("typeId", typeId);
				map.remove("releaseDateStr");
				map.remove("title");
			}
			if(releaseDateStr!=null){
				try {
					releaseDateStr = new String(request.getParameter("releaseDateStr").getBytes("iso8859-1"),"UTF-8");
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					map.put("releaseDateStr", releaseDateStr);
					map.remove("title");
					map.remove("typeId");
			}
		}
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Diary> diaryList=diaryService.diaryList(map);
		List<Diary> diaryCountList=diaryService.diaryCountList();
		List<DiaryType> diaryTypeCountList=diaryTypeService.diaryTypeCountList();
		int total=diaryService.diaryCount(map);
		String pageCode=this.genPagation(total, pageBean.getPage(), pageBean.getPageSize());
		mav.addObject("pageCode",pageCode);
		mav.addObject("diaryList",diaryList);
		session.setAttribute("diaryCountList",diaryCountList);
		session.setAttribute("diaryTypeCountList",diaryTypeCountList);
		mav.addObject("mainPage","diary/diaryList.jsp");
		mav.setViewName("mainTemp");
		return mav;
		
	}
	
	private String genPagation(int totalNum,int currentPage,int pageSize){
		int totalPage=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
		StringBuffer pageCode=new StringBuffer();
		pageCode.append("<li><a href='main?page=1'>首页</a></li>");
		if(currentPage==1){
			pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
		}else{
			pageCode.append("<li><a href='main?page="+(currentPage-1)+"'>上一页</a></li>");
		}
		for(int i=currentPage-2;i<=currentPage+2;i++){
			if(i<1||i>totalPage){
				continue;
			}
			if(i==currentPage){
				pageCode.append("<li class='active'><a href='#'>"+i+"</a></li>");
			}else{
				pageCode.append("<li><a href='main?page="+i+"'>"+i+"</a></li>");
			}
		}
		if(currentPage==totalPage){
			pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
		}else{
			pageCode.append("<li><a href='main?page="+(currentPage+1)+"'>下一页</a></li>");
		}
		pageCode.append("<li><a href='main?page="+totalPage+"'>尾页</a></li>");
		return pageCode.toString();
	}
}
