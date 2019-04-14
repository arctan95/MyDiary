package com.tan.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tan.dao.DiaryTypeDao;
import com.tan.entity.DiaryType;
import com.tan.service.DiaryTypeService;

@Service("DiaryTypeService")
public class DiaryTypeServiceImpl implements DiaryTypeService{

	@Resource
	private DiaryTypeDao diaryTypeDao;


	@Override
	public List<DiaryType> diaryTypeCountList() {
		// TODO Auto-generated method stub
		return diaryTypeDao.diaryTypeCountList();
	}


	@Override
	public List<DiaryType> diaryTypeList() {
		// TODO Auto-generated method stub
		return diaryTypeDao.diaryTypeList();
	}


	@Override
	public DiaryType diaryTypeShow(Integer diaryTypeId) {
		// TODO Auto-generated method stub
		return diaryTypeDao.diaryTypeShow(diaryTypeId);
	}


	@Override
	public int addDiaryType(DiaryType diaryType) {
		// TODO Auto-generated method stub
		return diaryTypeDao.addDiaryType(diaryType);
	}


	@Override
	public int updateDiaryType(DiaryType diaryType) {
		// TODO Auto-generated method stub
		return diaryTypeDao.updateDiaryType(diaryType);
	}


	@Override
	public int deleteDiaryType(int id) {
		// TODO Auto-generated method stub
		return diaryTypeDao.deleteDiaryType(id);
	}



}
