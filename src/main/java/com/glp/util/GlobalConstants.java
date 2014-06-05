package com.glp.util;

import org.apache.commons.lang.StringUtils;

/**
 * global constants 
 * @author springpig
 * @date 2013-11-26
 */
public abstract class GlobalConstants {
	
	/************* user cookie name *************/
	public static final String COOKIE_VERFIY_KEY = "!@#GLP#@!";		// md加密的秘钥
	
	public static final String COOKIE_TWITTER = "_twitter_";		// twitter的oauth_token cookie验证用
	// 忘记密码的cookie名
	public static final String COOKIE_USER_FORGET_PWD = "_pwdVerify_";
	// 修改email后的cookie名
	public static final String COOKIE_USER_CHANGE_EMAIL = "_emailVerify_";
	// 注册验证cookie名
	public static final String COOKIE_USER_REG_VERIFY = "_regVerify_";
	// 重发email的cookie名
	public static final String COOKIE_RESEND_EMAIL = "_resendEmail_";
	// 信息import的cookie名
	public static final String COOKIE_INFOR_IMPORT = "_inforImport_";
	
	// cookie domain
//	@Value("cookie.domain")
//	public static final String COOKIE_DOMAIN = ".goldenlink.com";
	
	public static final String COOKIE_USER_ID = "userId";

	public static final String COOKIE_USER_LOGIN_NAME = "loginName";

	public static final String COOKIE_BUSINESS_ENTITY_ID = "businessEntityId";

	public static final String COOKIE_BUSINESS_ACTIVITY_ID = "businessActivityId";

	public static final String COOKIE_USER_PERMISSIONS = "userPermissions";

	public static final String COOKIE_USER_SAVE_LOGIN_INFO = "userSaveLoginInfo";
	
	public static final String COOKIE_CHECK_CODE = "checkCode";
	
	// email发送类型
	// 验证邮箱邮件
	public static final String SEND_EMAIL_TYPE_VERIFYEMAIL = "1";
	// 找回密码邮件
	public static final String SEND_EMAIL_TYPE_FINDPWD = "2";
	
	// verify code type：register
	public static final String VERIFY_CODE_TYPE_REG = "1";
	// verfiy code type：modify pwd
	public static final String VERIFY_CODE_TYPE_PWD = "2";
	// verify code type:change email
	public static final String VERIFY_CODE_TYPE_EMAIL = "3";
	// verify code type:other
	public static final String VERIFY_CODE_TYPE_OTHER = "4";	// not insert into db, cookie use only
	
	// business type:business
	public static final String BUSINESS_TYPE_BUSINESS = "1";
	// business type:chain
	public static final String BUSINESS_TYPE_CHAIN = "2";
	
	// business category status:normal
	public static final String CATEGORY_STATUS_NORMAL = "NORMAL";
	
	// gender
	public static final String GENDER_UNKNOWN = "0";		// 未知
	public static final String GENDER_MALE = "1";			// 男性
	public static final String GENDER_FEMALE = "2";			// 女性
		
	// business file ref_type
	// ref_type certification
//	public static final String BUSINESS_FILE_REF_TYPE_CERT = "2";
	// ref_type trademark
//	public static final String BUSINESS_FILE_REF_TYPE_TRADEMARK = "3";
	
	/**
	 * File ref_type
	 * @author springpig
	 *
	 */
	public static enum FILE_REF_TYPE{
		CERT("1"),			// business_cert file
//		TRADEMARK("2"),		// business_trademark file
		ACTIVITY("3"),		// business activity file
		PRODUCT("4"),		// business product file
		PRODUCT_TEMP("5");	// business product temp file
		public String value;
		private FILE_REF_TYPE(String value){
			this.value = value;
		}
		public String toString(){
			return this.value;
		}
		//  判断值是否合法
		public static boolean compare(String value){
			boolean is_exists = false;
			if(StringUtils.isNotBlank(value)){
				for(int i = 0; i < FILE_REF_TYPE.values().length; i++ ){
					if(value.equals(FILE_REF_TYPE.values()[i].value)){
						is_exists = true;
						break;
					}
				}
			}
			return is_exists;
		}
	}
	/**
	 * 返回值定义
	 * @author think
	 *
	 */
	public static enum RETURN_ENUM{
		TOKEN_OK(2),			// token ok
		SUCCESS(1),				// 成功
		ERROR(0),				// 失败
		DUPLICATE(-1),			// 唯一值重复
		NOT_EXISTS(-2),			// 不存在对方
		EXISTS(-3),				// 已经存在
		GLOBAL_CODE(-11),		// 验证码不匹配
		TOKEN_NULL(-21),		// token不存在
		TOKEN_EXPIRED(-22),		// token过期
		TOKEN_ERROR(-23),		// token错误
		TOKEN_KEY_ERROR(-24),	// token验证的key出错
		BUSINESS_NOTEXIST(-30),	// business 不存在
		PRODUCT_NOTEXIST(-31),	// product 不存在
		FILE_NO_REFTYPE(-32),	// 文件的对象类型错误
		FILE_2LARGE(-33),		// 文件太大
		FILE_TYPE_NOTALLOW(-34),// 文件格式出错，只允许jpg,png格式
		FILE_SAVE_FAIL(-35),	// 文件保存出错
		BUSINESS_NO_COUNTRY(-36),			// 没有选择国家
		ACTIVITY_USER_NOTMATCH(-40),		// 活动、用户id不匹配
		ACCOUNT_PWD_NOTMATCH(-41),			// 账号密码不匹配
		IMPORT_WEBSITETYPE_NOTEXIST(-50),	// 导入账号类型不对
		IMPORT_WEBSITE_NOTEXIST(-51),		// 导入的账号不存在
		IMPORT_PRODUCT_UNFILL(-52),			// 导入的产品信息不全
		IMPORT_STEP_ERROR(-53),				// 数据导入步骤不对
		IMPORT_TIMEOUT(-54),				// 请求超时
		USER_CLOSE(-95),					// 用户资料被关闭
		USER_DELETE(-96),					// 用户被删除 
		USER_NOTACTIVE(-97),				// 用户尚未激活
		USER_PWD_WRONG(-98),				// 密码错误
		CODE_WRONG(-99);					// 校验码错误
		public int value;
		private RETURN_ENUM(int value){
			this.value = value;
		}
		public int toInt(){
			return this.value;
		}
		public String toString(){
			return String.valueOf(this.value);
		}
	}
	
	/**
	 * status enum
	 * @author springpig
	 * @date 2013-11-27
	*/
	public static enum STATUS_ENUM{
		UNCHECK("0"),		// uncheck or unverify
		CHECKED("1"),		// check or verify
		DELETE("2");		// delete
		public String value;
		private STATUS_ENUM(String value){
			this.value = value;
		}
		public String toString(){
			return this.value; 
		}
		// check value
		public static boolean compare(String value){
			boolean is_exists = false;
			if(value != null && value.length() > 0){
				for(int i = 0; i < STATUS_ENUM.values().length; i++ ){
					if(value.equals(STATUS_ENUM.values()[i].value)){
						is_exists = true;
						break;
					}
				}
			}
			return is_exists;
		}
	} 
	
	/**
	 * role_Enum
	 * @author springpig
	 * @date 2013-11-27
	 */
	public static enum ROLE_ENUM{
		ADMIN("1"),			// admin
		SELLER("2"),		// seller
		VIP_SELLER("3"),	// vip seller
		BUYER("4"),			// buyer
		GENERAL("5");		// general
		public String value;
		private ROLE_ENUM(String value){
			this.value = value;
		}
		public String toString(){
			return this.value;
		}
	}
	
	/**
	 * user login action_type Enum
	 * @author springpig
	 * @date 2013-11-26
	 */
	public static enum ACTION_TYPE_ENUM{
		LOGIN("0"),					// login
		LOGIN_FACEBOOK("1"),		// login with fackbook
		LOGIN_TWITTER("2"),			// login with twitter 
		LOGIN_LINKEDIN("3"),		// login with linkedin
		LOGIN_GOOGLE("4"),			// login with goole+
		LOGOUT("5");				// logout
		public String value;
		private ACTION_TYPE_ENUM(String value){
			this.value = value;
		}
		public String toString(){
			return this.value;
		}
		//  check value
		public static boolean compare(String value){
			boolean is_exists = false;
			if(StringUtils.isNotBlank(value)){
				for(int i = 0; i < ACTION_TYPE_ENUM.values().length; i++ ){
					if(value.equals(ACTION_TYPE_ENUM.values()[i].value)){
						is_exists = true;
						break;
					}
				}
			}
			return is_exists;
		}
	}
	
	/**
	 * user operate log action_type Enum
	 * @author springpig
	 * @date 2014-05-06
	 */
	public static enum OPERATE_ACTION_TYPE_ENUM{
		ADD("0"),					// add
		UPDATE("1"),				// update
		DELETE("2");				// delete 
		public String value;
		private OPERATE_ACTION_TYPE_ENUM(String value){
			this.value = value;
		}
		public String toString(){
			return this.value;
		}
		//  check value
		public static boolean compare(String value){
			boolean is_exists = false;
			if(StringUtils.isNotBlank(value)){
				for(int i = 0; i < OPERATE_ACTION_TYPE_ENUM.values().length; i++ ){
					if(value.equals(OPERATE_ACTION_TYPE_ENUM.values()[i].value)){
						is_exists = true;
						break;
					}
				}
			}
			return is_exists;
		}
	}
	
	/**
	 * user operate log object_type Enum
	 * @author springpig
	 * @date 2014-05-06
	 */
	public static enum OPERATE_OBJECT_TYPE_ENUM{
		BUSINESS("0"),				
		CERTIFICATION("1"),			
		LOCATION("2"),		
		REFERENCE("3"),
		REFER_BUSIENSS("4"),
		PRODUCT("5"),
		ACTIVITY("6"),
		USER_3RDPARTY("7"),
		FAQ("8");
		public String value;
		private OPERATE_OBJECT_TYPE_ENUM(String value){
			this.value = value;
		}
		public String toString(){
			return this.value;
		}
	}
	// 管理员
	public static String OL_USER_TYPE_ADMIN = "2";
	// 一般用户
	public static String OL_USER_TYPE_NORMAL = "1";
	
	
	/**
	 * account type
	 * @author springpig
	 * @date 2014-01-17
	 */
	public static enum ACCOUNT_TYPE_ENUM{
		Facebook("1"),		// fackbook account
		Twitter("2"),		// twitter account
		Linkedin("3"),		// linkedin account
		Google("4");		// google+ account
		public String value;
		private ACCOUNT_TYPE_ENUM(String value){
			this.value = value;
		}
		public String toString(){
			return this.value;
		}
		public String getValue(){
			return this.value;
		}
		public String getName(){
			return this.name();
		}
		//  check value
		public static boolean compare(String value){
			boolean is_exists = false;
			if(StringUtils.isNotBlank(value)){
				for(int i = 0; i < ACCOUNT_TYPE_ENUM.values().length; i++ ){
					if(value.equals(ACCOUNT_TYPE_ENUM.values()[i].value)){
						is_exists = true;
						break;
					}
				}
			}
			return is_exists;
		}
	}
	
	/**
	 * search type
	 *
	 */
	public static enum SEARCH_TYPE_ENUM{
		PRODUCT("product"),
		ACTIIVTY("activity"),
		BUSINESS("business");
		public String value;
		private SEARCH_TYPE_ENUM(String value){
			this.value = value;
		}
		public String toString(){
			return this.value;
		}
		//  判断值是否合法
		public static boolean compare(String value){
			boolean is_exists = false;
			if(StringUtils.isNotBlank(value)){
				for(int i = 0; i < SEARCH_TYPE_ENUM.values().length; i++ ){
					if(value.equals(SEARCH_TYPE_ENUM.values()[i].value)){
						is_exists = true;
						break;
					}
				}
			}
			return is_exists;
		}
	}
	
	// Business role：其他的名称
	public static String BUSINESS_ROLE_OTHER_NAME = "Others";
	// Business role：其他的值
	public static String BUSINESS_ROLE_OTHER_VALUE = "9";
	
	/**
	 * business certification type：
	 * @author springpig
	 * @date 2013-12-09
	 */
	public static enum CERTIFICATION_TYPE{
		Business_Permit("1"),		
		Business_License("2"),
		Authorized_Regional_Distributor("3"),
		Authorized_Wholesaler("6"),
		Authorized_Franchise("4"),
		Patent_Certification("5"),
		Other_Certification("9");
		
		public String type;		// value
		private CERTIFICATION_TYPE(String type){
			this.type = type;
		}
		public String getType(){
			return this.type;
		}
		public String getName(){
			return this.name().replace("_", " ");
		}
		//  判断值是否合法
		public static boolean compare(String value){
			boolean is_exists = false;
			if(StringUtils.isNotBlank(value)){
				for(int i = 0; i < CERTIFICATION_TYPE.values().length; i++ ){
					if(value.equals(CERTIFICATION_TYPE.values()[i].type)){
						is_exists = true;
						break;
					}
				}
			}
			return is_exists;
		}
		// get certification name by type
		public static String getCertificationName(String type){
			String certificationName = "";
			if(StringUtils.isNotBlank(type)){
				for(int i = 0; i < CERTIFICATION_TYPE.values().length; i++ ){
					if(type.equals(CERTIFICATION_TYPE.values()[i].type)){
						certificationName = CERTIFICATION_TYPE.values()[i].getName();
						break;
					}
				}
			}
			return certificationName;
		}
	}
	
	/**
	 * business patent type：
	 * @author springpig
	 * @date 2013-12-10
	 */
	public static enum CERTIFICATION_ORGANIZATION_TYPE{
    	Goverment("1"),				// 
    	Trademark("2"),				// 
    	Brand_Owner("3");			// 
		public String type;		// value
		private CERTIFICATION_ORGANIZATION_TYPE(String type){
			this.type = type;
		}
		public String getType(){
			return this.type;
		}
		public String getName(){
			return this.name();
		}
		//  判断值是否合法
		public static boolean compare(String value){
			boolean is_exists = false;
			if(StringUtils.isNotBlank(value)){
				for(int i = 0; i < CERTIFICATION_ORGANIZATION_TYPE.values().length; i++ ){
					if(value.equals(CERTIFICATION_ORGANIZATION_TYPE.values()[i].type)){
						is_exists = true;
						break;
					}
				}
			}
			return is_exists;
		}
		// get organization name by type
		public static String getOrganizationName(String type){
			String organizationName = "";
			if(StringUtils.isNotBlank(type)){
				for(int i = 0; i < CERTIFICATION_ORGANIZATION_TYPE.values().length; i++ ){
					if(type.equals(CERTIFICATION_ORGANIZATION_TYPE.values()[i].type)){
						organizationName = CERTIFICATION_ORGANIZATION_TYPE.values()[i].name().replace("_", " ");
						break;
					}
				}
			}
			return organizationName;
		}
	}
	
	/**
	 * business reference type：
	 * @author springpig
	 * @date 2013-12-11
	 */
	public static enum REFERENCE_TYPE{
		Provider("1"),			// 上游公司
		Customer("2");			// 下游公司
		public String value;	// value
		private REFERENCE_TYPE(String value){
			this.value = value;
		}
		public String getValue(){
			return this.value;
		}
		public String getName(){
			return this.name();
		}
		//  判断值是否合法
		public static boolean compare(String value){
			boolean is_exists = false;
			if(StringUtils.isNotBlank(value)){
				for(int i = 0; i < REFERENCE_TYPE.values().length; i++ ){
					if(value.equals(REFERENCE_TYPE.values()[i].value)){
						is_exists = true;
						break;
					}
				}
			}
			return is_exists;
		}
	}
	
	/**
	 * business reference type：
	 * @author springpig
	 * @date 2013-12-11
	 */
	public static enum COMPANY_TYPE{
		Corporation("1"),			// 有限公司
		Partnership("2"),			// 股份公司
		Sole_Proprietorship("3"),	// 独资公司
		S_Corporation("4"),
		C_Corporation("5"),
		Limited_Liability_Company("6"),
		Other("9");
		public String value;// value
		private COMPANY_TYPE(String value){
			this.value = value;
		}
		public String getValue(){
			return this.value;
		}
		public String getName(){
			return this.name();
		}
		//  判断值是否合法
		public static boolean compare(String value){
			boolean isExists = false;
			if(StringUtils.isNotBlank(value)){
				for(int i = 0; i < COMPANY_TYPE.values().length; i++ ){
					if(value.equals(COMPANY_TYPE.values()[i].value)){
						isExists = true;
						break;
					}
				}
			}
			return isExists;
		}
		// check name 是否存在
		public static String getValueByName(String name){
			String value = null;
			if(StringUtils.isNotBlank(name)){
				for(int i = 0; i < COMPANY_TYPE.values().length; i++ ){
					if(COMPANY_TYPE.values()[i].name().replace("_", " ").toLowerCase().indexOf(name.toLowerCase()) != -1 || name.toLowerCase().indexOf(COMPANY_TYPE.values()[i].name().replace("_", " ").toLowerCase()) != -1){
						value = COMPANY_TYPE.values()[i].value;
						break;
					}
				}
			}
			return value;
		}
	}
	
	/**
	 * common data type
	 * @author springpig
	 * 
	 */
	public static enum COMMON_DATA_TYPE{
		BUSINESS_CATEGORY("1"),		// business category
		ACTIVITY_TYPE("2"),			// activity type
		INTERNAL_ROLE_ID("3"),		// internal role
		WEBSITE_TYPE("4"),			// website type id
		ROLE_ID("5");				// role
		public String value;
		private COMMON_DATA_TYPE(String value){
			this.value = value;
		}
		public String toString(){
			return this.value;
		}
	}
	
	/**
	 * review ref type
	 * @author springpig
	 * @date 2013-11-27
	 */
	public static enum REVIEW_REF_TYPE{
		PRODUCT("1"), 		// product
		SERVICE("2"),		// service
		ACTIIVTY("3");		// activity
		public String value;
		private REVIEW_REF_TYPE(String value){
			this.value = value;
		}
		public String toString(){
			return this.value;
		}
	}
	
	/**
	 * price currency
	 * @author springpig
	 * @date 2014-01-22
	 */
	public static enum CURRENCY{
		DOLLAR("1", "$"),
//		RMB("2", "￥"),
		RMB("2", "\uffe5"),
		EURO("3", "\u20ac");
		//EURO("3", "");
		private String type;
		private String typeName;
		private CURRENCY(String type, String typeName){
			this.type = type;
			this.typeName = typeName;
		}
		public String getType(){
			return this.type;
		}
		public String getTypeName(){
			return this.typeName;
		}
		// check type is ok or not
		public static boolean compare(String type){
			boolean is_exists = false;
			if(StringUtils.isNotBlank(type)){
				for(int i = 0; i < CURRENCY.values().length; i++ ){
					if(type.equals(CURRENCY.values()[i].type)){
						is_exists = true;
						break;
					}
				}
			}
			return is_exists;
		}
		// get currency name by type
		public static String getCurrencyName(String type){
			String currencyName = "";
			if(StringUtils.isNotBlank(type)){
				for(int i = 0; i < CURRENCY.values().length; i++ ){
					if(type.equals(CURRENCY.values()[i].type)){
						currencyName = CURRENCY.values()[i].typeName;
						break;
					}
				}
			}
			return currencyName;
		}
	}
	
	/**
	 * Phone Number type
	 * @author springpig
	 */
	public static enum PHONE_TYPE{
		Work("Work");		// work phone number
//		Home("Home"),		// home phone number
//		Mobile("Mobile");	// mobile phone number
		private String value;
		private PHONE_TYPE(String value){
			this.value = value;
		}
		public String getValue(){
			return this.value;
		}
		public String getName(){
			return this.name();
		}
		// check type is ok or not
		public static boolean compare(String value){
			boolean is_exists = false;
			if(StringUtils.isNotBlank(value)){
				for(int i = 0; i < PHONE_TYPE.values().length; i++ ){
					if(value.equalsIgnoreCase(PHONE_TYPE.values()[i].value)){
						is_exists = true;
						break;
					}
				}
			}
			return is_exists;
		}
	}
	
	/**
	 * business type
	 * @author think
	 *
	 */
	public static enum BUSINESS_TYPE{
		Product("1");
//		Service("2");
		private String value;
		private BUSINESS_TYPE(String value){
			this.value = value;
		}
		public String getValue(){
			return this.value;
		}
		public String getName(){
			return this.name();
		}
		// check type is ok or not
		public static boolean compare(String type){
			boolean is_exists = false;
			if(StringUtils.isNotBlank(type)){
				for(int i = 0; i < BUSINESS_TYPE.values().length; i++ ){
					if(type.equals(BUSINESS_TYPE.values()[i].value)){
						is_exists = true;
						break;
					}
				}
			}
			return is_exists;
		}
		// get business_type_name by type
		public static String getTypeName(String type){
			String typeName = "";
			if(StringUtils.isNotBlank(type)){
				for(int i = 0; i < BUSINESS_TYPE.values().length; i++ ){
					if(type.equals(BUSINESS_TYPE.values()[i].value)){
						typeName = BUSINESS_TYPE.values()[i].name();
						break;
					}
				}
			}
			return typeName;
		}
	}
	
	/**
	 * 公司大小
	 * @author springpig
	 * @date 2014-03-11
	 */
	public static enum COMPANY_SIZE{
		SMALL("1", "1~99 employees"),
		MIDDLE("2", "100~1000 employees"),
		LARGE("3", "1000+ employees");
		private String type;
		private String typeName;
		private COMPANY_SIZE(String type, String typeName){
			this.type = type;
			this.typeName = typeName;
		}
		public String getType(){
			return this.type;
		}
		public String getTypeName(){
			return this.typeName;
		}
		// check type is ok or not
		public static boolean compare(String type){
			boolean is_exists = false;
			if(StringUtils.isNotBlank(type)){
				for(int i = 0; i < COMPANY_SIZE.values().length; i++ ){
					if(type.equals(COMPANY_SIZE.values()[i].type)){
						is_exists = true;
						break;
					}
				}
			}
			return is_exists;
		}
		// get company size name by type
		public static String getCompanySizeName(String type){
			String companySizeName = "";
			if(StringUtils.isNotBlank(type)){
				for(int i = 0; i < COMPANY_SIZE.values().length; i++ ){
					if(type.equals(COMPANY_SIZE.values()[i].type)){
						companySizeName = COMPANY_SIZE.values()[i].typeName;
						break;
					}
				}
			}
			return companySizeName;
		}
	}
	
	/**
	 * 非对外公开的搜索参数
	 * @author springpig
	 */
	public static enum SEARCH_TYPE_PRIVATE{
		DEFAUL("default"),
		QUERY_1("query1"),
		QUERY_2("query2");
		private String value;
		private SEARCH_TYPE_PRIVATE(String value){
			this.value = value;
		}
		public String getValue(){
			return this.value;
		}
		public String getName(){
			return this.name();
		}
		public String toString(){
			return this.value;
		}
		// check type is ok or not
		public static boolean compare(String value){
			boolean is_exists = false;
			if(StringUtils.isNotBlank(value)){
				for(int i = 0; i < SEARCH_TYPE_PRIVATE.values().length; i++ ){
					if(value.equalsIgnoreCase(SEARCH_TYPE_PRIVATE.values()[i].value)){
						is_exists = true;
						break;
					}
				}
			}
			return is_exists;
		}
	}
	
	// 默认编码
	public static String CHARSET_UTF8 = "UTF-8";
	// 索引前缀
	public static String INDEX_ID_PREX = "s_";
	
	
	public static String IS_YES = "1";		// 是
	public static String IS_NO = "0";		// 否
	public static Long COUNTRY_ID_DEFAULT = 226L;	// 默认国家，美国
	
	public static Integer DESCRIPTION_LENGTH = 2000;	// 描述

}
