package com.tan.dao;

import java.util.List;
import java.util.Map;

import com.tan.entity.Diary;

public interface DiaryDao {

	
	public List<Diary> diaryList(Map<String,Object> map);
	
	public int diaryCount(Map<String,Object> map);
	
	public Diary diaryShow(Integer diaryId);
	
	public int addDiary(Diary diary);
	
	public int updateDiary(Diary diary);
	
	public int deleteDiary(int id);
	
	public List<Diary> diaryCountList();
	
	public int existDiaryWithTypeId(Integer typeId);
}
