/*************************************************************************************************
 * 版权所有 (C)2015
 * 
 * 文件名称：Constant.java
 * 内容摘要：Constant.java
 * 当前版本：TODO
 * 作        者：李加蒙1605651971@qq.com
 * 完成日期：2015-12-17 下午2:29:15
 * 修改记录：
 * 修改日期：2015-12-17 下午2:29:15
 * 版   本 号：
 * 修   改 人：
 * 修改内容：
 ************************************************************************************************/
package com.xh.androidhttp.constant;

import java.io.Serializable;

/**
 * @filename 文件名称：Constant.java
 * @contents 内容摘要：
 */
public class Constant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String BOUNDARY__ = "--";

	/**
	 * 公司http请求boundary
	 */
	// public static final String BOUNDARY =
	// "----WebKitFormBoundaryEnPfBCbCqpmt7O2G";

	/**
	 * 家http请求boundary
	 */
	public static final String BOUNDARY = "----WebKitFormBoundaryYouqTAhHk35vGjVB";

	/**
	 * 家
	 */
	private static final String START_SERVICE = "http://192.168.1.100:8080/xhsp/";

	/**
	 * 公司
	 */
	// public static final String START_SERVICE =
	// "http://192.168.31.107:8080/xhsp/";

	public static String getService(String str) {
		return START_SERVICE + str;
	}

	public static final String API_REGISTER = "register.json";
	public static final String API_PHONE = "360w.jpg";
	public static final String API_UPLOAD_IMG = "upload.json";

}
