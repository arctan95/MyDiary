package com.tan.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class MD5Util {

	public static String EncodePwdByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		// 拿到一个MD5转换器(如果想要SHA1参数换成"SHA1")
		MessageDigest md5=MessageDigest.getInstance("MD5");
		//使用Base64再次处理
		BASE64Encoder base64en=new BASE64Encoder();
		return base64en.encode(md5.digest(str.getBytes("utf-8")));
	}
	
}
