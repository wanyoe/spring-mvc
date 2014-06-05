package com.glp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtils {
	// 数字
	public static final String NUMBER = "[0-9]+";

	public static final String NUMERAL = "([1-9][0-9]*.?[0-9]*)|(0.[0-9]*[1-9])";
	// 邮编编码
	public static final String ZIPCODE = "[0-9a-zA-Z]{2,8}$";
	// 电话号码
	public static final String PHONENUMBER = "(\\d{1,5}-\\d{5,10}(-\\d{1,5})?)$|(\\d{1,5}-\\d{2,5}-\\d{2,5}(-\\d{1,5})?)$";
	// 手机号码
	public static final String MOBILE_PHONE = "\\d{5,13}$";
	// 验证email
	public static final String EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	// 验证网址
	public static final String WEBSITE = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./\\+?%&=]*)?";
	// 专利编号
	public static final String REFERENCE_CODE = "\\d{5,20}";
	// 货币格式
	public static final String MONEY = "(^([1-9][0-9]{0,2}(,[0-9]{3})*|0)\\.[0-9]{2}$)|(^[1-9]\\d$)";
	
	
	
	public static boolean isNumber(String str){
		Matcher matcher = Pattern.compile(NUMBER).matcher(str);
		return matcher.matches();
	}
	
	public static boolean isNumeral(String str){
		Matcher matcher = Pattern.compile(NUMERAL).matcher(str);
		return matcher.matches();
	}
	
	public static boolean isZipcode(String str){
		Matcher matcher = Pattern.compile(ZIPCODE).matcher(str);
		return matcher.matches();
	}
	
	public static boolean isPhoneNumber(String str){
		Matcher matcher = Pattern.compile(PHONENUMBER).matcher(str);
		return matcher.matches();
	}
	
	public static boolean isMobilePhone(String str){
		Matcher matcher = Pattern.compile(MOBILE_PHONE).matcher(str);
		return matcher.matches();
	}
	
	public static boolean isEmail(String str){
		Matcher matcher = Pattern.compile(EMAIL).matcher(str);
		return matcher.matches();
	}
	
	public static boolean isWebSite(String str){
		Matcher matcher = Pattern.compile(WEBSITE).matcher(str);
		return matcher.matches();
	}
	
	public static boolean isReferenceCode(String str){
		Matcher matcher = Pattern.compile(REFERENCE_CODE).matcher(str);
		return matcher.matches();
	}
	
	public static boolean isMoney(String str){
		Matcher matcher = Pattern.compile(MONEY).matcher(str);
		return matcher.matches();
	}
	
	public static void main(String[] args){
		String phone = "867-7889889-2";
		String phone2 = "768-978-8787";
		String phone3 = "787-988-9798-22";
		
		System.out.println("phone:" + isPhoneNumber(phone) + ";phone2:" + isPhoneNumber(phone2) + ";phone3:" + isPhoneNumber(phone3));
		
	}

}
