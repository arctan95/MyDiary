package com.tan.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tan.entity.Diary;
import com.tan.service.DiaryService;

@Controller
@RequestMapping("/diary")
public class DiaryController {

	@Resource
	private DiaryService diaryService;
	
	@RequestMapping("/show")
	public ModelAndView diaryShow(@RequestParam(value="diaryId")String diaryId){
		ModelAndView mav=new ModelAndView();
		Diary diary=diaryService.diaryShow(Integer.parseInt(diaryId));
		mav.addObject("diary",diary);
		mav.addObject("mainPage","diary/diaryShow.jsp");
		mav.setViewName("forward:/mainTemp.jsp");
		return mav;
		}
	
	@RequestMapping("/preSave")
	public ModelAndView diaryPreSave(@RequestParam(value="diaryId",required=false)String diaryId){
		ModelAndView mav=new ModelAndView();
		if(diaryId!=null){
			Diary diary=diaryService.diaryShow(Integer.parseInt(diaryId));
			mav.addObject("diary",diary);
		}
		mav.addObject("mainPage","diary/diarySave.jsp");
		mav.setViewName("forward:/mainTemp.jsp");
		return mav;
	}
	
	@RequestMapping("/save")
	public ModelAndView diarySave(Diary diary){
		ModelAndView mav=new ModelAndView();
		int saveNums=0;
		if(diary.getDiaryId()==null){
			saveNums=diaryService.addDiary(diary);
		}else{
			saveNums=diaryService.updateDiary(diary);
		}
		if(saveNums>0){
			mav.setViewName("redirect:/main");
		}else{
			mav.addObject("diary",diary);//保存失败后把原来输入的数据还原回去
			mav.addObject("error","保存失败!");
			mav.addObject("mainPage","diary/diarySave.jsp");
			mav.setViewName("forward:/mainTemp.jsp");
		}
		return mav;
	}
	
	@RequestMapping("/delete")
	public String diaryDelete(@RequestParam(value="diaryId")String diaryId){
		diaryService.deleteDiary(Integer.parseInt(diaryId));
		return "redirect:/main";
	}
	
}
