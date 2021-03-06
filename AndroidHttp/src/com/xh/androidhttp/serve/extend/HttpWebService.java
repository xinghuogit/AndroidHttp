/*************************************************************************************************
 * 版权所有 (C)2015,  
 * 
 * 文件名称：HttpWebService.java
 * 内容摘要：
 * 当前版本：
 * 作         者： 李加蒙
 * 完成日期：2015年12月14日 下午18:25:03
 * 修改记录：
 * 修改日期：
 * 版   本  号：
 * 修   改  人：
 * 修改内容：
 ************************************************************************************************/

package com.xh.androidhttp.serve.extend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.webkit.WebView;
import android.widget.ImageView;

/**
 * @filename 文件名称：HttpWebService.java
 * @contents 内容摘要：WebView服务
 */
public class HttpWebService extends Thread {
	private String url;// 网址
	private WebView webView;// 显示网址的控件
	private Handler handler;// 线程

	public HttpWebService(String url, WebView webView, Handler handler) {
		this.url = url;
		this.webView = webView;
		this.handler = handler;
	}

	@Override
	public void run() {
		getWeb();
	}

	private void getWeb() {
		URL httpUrl;
		try {
			httpUrl = new URL(url);// 获取一个网址
			HttpURLConnection connection = (HttpURLConnection) httpUrl
					.openConnection();// 打开网址、获取连接
			connection.setConnectTimeout(5000);// 设置超时时间ms
			connection.setRequestMethod("GET");// 请求的方式get/post
			final StringBuffer buffer = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));// 在外层包过一层读取，并且用连接打开一个输入流
			String str;
			while ((str = reader.readLine()) != null) {// 如果不为空
				buffer.append(str);// 得到的数据加载到buffer
			}
			handler.post(new Runnable() {

				@Override
				public void run() {
					webView.loadData(buffer.toString(),
							"text/html;charset=UTF-8", null);
				}
			});

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
