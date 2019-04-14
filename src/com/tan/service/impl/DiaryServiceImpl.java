package com.tan.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tan.dao.DiaryDao;
import com.tan.entity.Diary;
import com.tan.service.DiaryService;

@Service("DiaryService")
public class DiaryServiceImpl implements DiaryService{

	@Resource
	private DiaryDao diaryDao;

	@Override
	public List<Diary> diaryList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return diaryDao.diaryList(map);
	}

	@Override
	public int diaryCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return diaryDao.diaryCount(map);
	}


	@Override
	public int addDiary(Diary diary) {
		// TODO Auto-generated method stub
		return diaryDao.addDiary(diary);
	}

	@Override
	public int updateDiary(Diary diary) {
		// TODO Auto-generated method stub
		return diaryDao.updateDiary(diary);
	}

	@Override
	public int deleteDiary(int id) {
		// TODO Auto-generated method stub
		return diaryDao.deleteDiary(id);
	}

	@Override
	public List<Diary> diaryCountList() {
		// TODO Auto-generated method stub
		return diaryDao.diaryCountList();
	}

	@Override
	public Diary diaryShow(Integer diaryId) {
		// TODO Auto-generated method stub
		return diaryDao.diaryShow(diaryId);
	}

	@Override
	public int existDiaryWithTypeId(Integer typeId) {
		// TODO Auto-generated method stub
		return diaryDao.existDiaryWithTypeId(typeId);
	}



}
