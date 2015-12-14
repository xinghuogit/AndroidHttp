package com.xh.androidhttp.serve.extend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;

import org.apache.http.client.utils.URLEncodedUtils;

/**
 * get请求必须转码 如果有中文 post内部就转码了
 * 
 */
public class HttpService extends Thread {

	private String url;
	private String user;
	private String psw;

	public HttpService(String url, String user, String psw) {
		this.url = url;
		this.user = user;
		this.psw = psw;
	}

	@Override
	public void run() {
		getUrl();
		postUrl();
	}

	private void postUrl() {
		//当前系统信息
		Properties properties = System.getProperties();
		properties.list(System.out);

		try {
			URL httpUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) httpUrl
					.openConnection();
			connection.setConnectTimeout(5000);
			connection.setRequestMethod("POST");

			OutputStream os = connection.getOutputStream();
			String content = "user=" + user + "&password=" + psw;
			os.write(content.getBytes());

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			StringBuffer buffer = new StringBuffer();
			String str;
			while ((str = reader.readLine()) != null) {
				buffer.append(str);
			}
			System.out.println("post:" + buffer.toString());

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getUrl() {
		try {
			String geturl = url + "?user=" + URLEncoder.encode(user, "utf-8")
					+ "&password=" + URLEncoder.encode(psw, "utf-8");
			URL httpUrl = new URL(geturl);
			HttpURLConnection connection = (HttpURLConnection) httpUrl
					.openConnection();
			connection.setConnectTimeout(5000);
			connection.setRequestMethod("GET");

			StringBuffer buffer = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String str;
			if ((str = reader.readLine()) != null) {
				buffer.append(str);
			}

			System.out.println("get:" + buffer.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
