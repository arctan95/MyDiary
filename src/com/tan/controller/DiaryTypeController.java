package com.tan.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tan.entity.DiaryType;
import com.tan.service.DiaryService;
import com.tan.service.DiaryTypeService;

@Controller
@RequestMapping("/diaryType")
public class DiaryTypeController {

	@Resource
	private DiaryTypeService diaryTypeService;
	@Resource
	private DiaryService diaryService;
	
	@RequestMapping("/list")
	public ModelAndView diaryTypeList(){
		ModelAndView mav=new ModelAndView();
		List<DiaryType> diaryTypeList=diaryTypeService.diaryTypeList();
		mav.addObject("diaryTypeList",diaryTypeList);
		mav.addObject("mainPage","diaryType/diaryTypeList.jsp");
		mav.setViewName("forward:/mainTemp.jsp");
		return mav;
	}
	
	@RequestMapping("/preSave")
	public ModelAndView diaryTypePreSave(@RequestParam(value="diaryTypeId",required=false)String diaryTypeId){
		ModelAndView mav=new ModelAndView();
		if(diaryTypeId!=null){
			DiaryType diaryType=diaryTypeService.diaryTypeShow(Integer.parseInt(diaryTypeId));
			mav.addObject("diaryType",diaryType);
		}
		mav.addObject("mainPage","diaryType/diaryTypeSave.jsp");
		mav.setViewName("forward:/mainTemp.jsp");
		return mav;
	}
	
	@RequestMapping("/save")
	public ModelAndView diaryTypeSave(DiaryType diaryType){
		ModelAndView mav=new ModelAndView();
		int saveNums=0;
		if(diaryType.getDiaryTypeId()!=null){
			saveNums=diaryTypeService.updateDiaryType(diaryType);
		}else{
			saveNums=diaryTypeService.addDiaryType(diaryType);
		}
		if(saveNums>0){
			mav.setViewName("redirect:list");
		}else{
			mav.addObject("diaryType",diaryType);//保存失败后把原来输入的数据还原回去
			mav.addObject("error","保存失败！");
			mav.addObject("mainPage","diaryType/diaryTypeSave.jsp");
			mav.setViewName("forward:/mainTemp.jsp");
		}
		return mav;
	}
	
	@RequestMapping("/delete")
	public ModelAndView diaryTypeDelete(@RequestParam(value="diaryTypeId")String diaryTypeId){
		ModelAndView mav=new ModelAndView();
		if(diaryService.existDiaryWithTypeId(Integer.parseInt(diaryTypeId))>0){
			mav.addObject("error", "日志类别下存在日志，无法删除！");
		}else{
			diaryTypeService.deleteDiaryType(Integer.parseInt(diaryTypeId));
		}
		mav.setViewName("forward:list");
		return mav;
	}
}
