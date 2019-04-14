﻿package com.tan.dao;

import java.util.List;
import com.tan.entity.DiaryType;

public interface DiaryTypeDao {

	public List<DiaryType> diaryTypeList();
	
	public List<DiaryType> diaryTypeCountList();
	
	public DiaryType diaryTypeShow(Integer diaryTypeId);
	
	public int addDiaryType(DiaryType diaryType);
	
	public int updateDiaryType(DiaryType diaryType);
	
	public int deleteDiaryType(int id);
}
