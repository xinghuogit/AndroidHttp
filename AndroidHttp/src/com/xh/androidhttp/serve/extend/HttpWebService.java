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

	private String ivurl;// 网址
	private ImageView imageView;// 显示网址的控件

	public HttpWebService(String url, WebView webView, Handler handler) {
		this.url = url;
		this.webView = webView;
		this.handler = handler;
	}

	public HttpWebService(String ivurl, ImageView imageView, Handler handler) {
		this.ivurl = ivurl;
		this.imageView = imageView;
		this.handler = handler;
	}

	@Override
	public void run() {
		getIv();
//		URL httpUrl;
//		try {
//			httpUrl = new URL(url);// 获取一个网址
//			HttpURLConnection connection = (HttpURLConnection) httpUrl
//					.openConnection();// 打开网址、获取连接
//			connection.setConnectTimeout(5000);// 设置超时时间ms
//			connection.setRequestMethod("GET");// 请求的方式get/post
//			final StringBuffer buffer = new StringBuffer();
//			BufferedReader reader = new BufferedReader(new InputStreamReader(
//					connection.getInputStream()));// 在外层包过一层读取，并且用连接打开一个输入流
//			String str;
//			while ((str = reader.readLine()) != null) {// 如果不为空
//				buffer.append(str);// 得到的数据加载到buffer
//			}
//			handler.post(new Runnable() {
//
//				@Override
//				public void run() {
//					webView.loadData(buffer.toString(),
//							"text/html;charset=UTF-8", null);
//				}
//			});
//
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	private void getIv() {
		try {
			URL httpUrl = new URL(ivurl);// 获取一个网址
			HttpURLConnection connection = (HttpURLConnection) httpUrl
					.openConnection();// 打开网址、获取连接
			connection.setConnectTimeout(5000);// 设置超时时间ms
			connection.setRequestMethod("GET");// 请求的方式get/post
			connection.setDoInput(true);// 是否要有个输入流

			InputStream is = connection.getInputStream();// 获取连接的输入流
			FileOutputStream fos = null;

			String fileName = String.valueOf(System.currentTimeMillis());// 获取当前时间作为图片文件名字
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {// 判断SD卡是否挂载
				File parent = Environment.getExternalStorageDirectory();// 获取到SD卡根目录
				File downloadFile = new File(parent, fileName);// 设置文件目录文件名字
				fos = new FileOutputStream(downloadFile);// 以一个文件目录打开一个输出流
			}
			byte[] bs = new byte[2 * 1024];// 创建一个2k的字节
			int len;
			if (fos != null) {
				while ((len = is.read(bs)) != -1) {
					fos.write(bs, 0, len);
				}
			}

			final Bitmap bitmap = BitmapFactory.decodeFile(fileName);
			handler.post(new Runnable() {
				@Override
				public void run() {
					imageView.setImageBitmap(bitmap);
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
