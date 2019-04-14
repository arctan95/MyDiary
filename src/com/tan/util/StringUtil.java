package com.tan.util;

public class StringUtil {

	public static boolean isEmpty(String str){
		if("".equals(str.trim())|| str==null){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isNotEmpty(String str){
		if(!"".equals(str.trim())&&str!=null){
			return true;
		}else{
			return false;
		}
	}
	
	public static String formatLike(String str){
		if(isNotEmpty(str)){
			return "%"+str+"%";
		}else{
			return null;
		}
	}
}
