/*************************************************************************************************
 * 版权所有 (C)2015
 * 
 * 文件名称：UploadThread.java
 * 内容摘要：UploadThread.java
 * 当前版本：TODO
 * 作        者：李加蒙
 * 完成日期：2015-12-18 下午12:40:31
 * 修改记录：
 * 修改日期：2015-12-18 下午12:40:31
 * 版   本 号：
 * 修   改 人：
 * 修改内容：
 ************************************************************************************************/
package com.xh.androidhttp.serve.extend;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.content.Entity;

import com.xh.androidhttp.constant.Constant;

/**
 * @filename 文件名称：UploadThread.java
 * @contents 内容摘要：
 */
public class UploadThread extends Thread {
	private File fileAbs;
	private String fileName;
	private String url;

	public UploadThread(File fileAbs, String fileName, String url) {
		this.fileName = fileName;
		this.url = url;
	}

	@Override
	public void run() {
//		clientUpload();
		httpUpload();
	}

	private void clientUpload() {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		MultipartEntity entity = new MultipartEntity();

		FileBody fileBody = new FileBody(fileAbs);
		entity.addPart("file", fileBody);
		post.setEntity(entity);

		try {
			HttpResponse response = client.execute(post);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				System.out.println("clientUpload:"+EntityUtils.toString(response.getEntity()));
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void httpUpload() {
		String boundary = Constant.BOUNDARY;
		String boundary__ = Constant.BOUNDARY__;
		String end = "\r\n";
		try {
			URL httpUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) httpUrl
					.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type",
					"multipart/form-data; boundary=" + boundary);

			DataOutputStream dos = new DataOutputStream(
					connection.getOutputStream());

			dos.writeBytes(boundary__ + boundary + end);
			dos.writeBytes("Content-Disposition:form-data;"
					+ "name=\"file\";filename=\"" + "360w.jpg" + "\"" + end);
			dos.writeBytes(end);
			FileInputStream fis = new FileInputStream(new File(fileName));

			byte[] b = new byte[1024 * 4];
			int len;
			while ((len = fis.read(b)) != -1) {
				dos.write(b, 0, len);
			}

			dos.writeBytes(end);
			dos.writeBytes(boundary__ + boundary + boundary__ + end);
			dos.flush();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			StringBuffer buffer = new StringBuffer();
			String str;
			while ((str = reader.readLine()) != null) {
				buffer.append(str);
			}

			if (dos != null) {
				dos.close();
			}
			if (fis != null) {
				fis.close();
			}
			if (reader != null) {
				reader.close();
			}
			System.out.println("httpUpload:" + buffer.toString());

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
