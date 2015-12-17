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

	private static Constant constant = new Constant();

	public static Constant getInstantce() {
		return constant;
	}

	private String START_SERVICE_FAMILY = "http://192.168.1.100:8080/xhsp/register.json";
	private String START_SERVICE_COMPANY = "http://192.168.31.107:8080/xhsp/register.json";

	public String getService(String str) {
		if (str.equals("1")) {
			return START_SERVICE_FAMILY;
		} else {
			return START_SERVICE_COMPANY;
		}
	}
}
