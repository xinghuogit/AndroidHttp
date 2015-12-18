package com.xh.androidhttp.serve.extend;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.widget.ImageView;

/**
 * 下载图片
 * 
 */
public class DownLoad {

	// 创建线程池
	private Executor threadPool = Executors.newFixedThreadPool(3);

	private Handler handler;

	public DownLoad(Handler handler) {
		this.handler = handler;
	}

	public void downLoadFile(String url, final ImageView dowloadIv) {
		URL httpUrl;
		try {
			httpUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) httpUrl
					.openConnection();
			connection.setReadTimeout(5000);
			connection.setRequestMethod("GET");
			int count = connection.getContentLength();
			int block = count / 3;

			String fileName = getFileName(url);

			File fileDownLoad = null;
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {// 判断SD卡是否挂载
				File parent = Environment.getExternalStorageDirectory();// 获取到SD卡根目录
				fileDownLoad = new File(parent, fileName);// 设置文件目录文件名字
			}
			System.out.println("fileDownLoad" + fileDownLoad.getAbsolutePath());
			/**
			 * 11/3 3 2 第一个线程0-2 第二个线程3-5 第三个线程6-10
			 */
			for (int i = 0; i < 3; i++) {
				long start = i * block;
				long end = (i + 1) * block - 1;
				if (i == 2) {
					end = count;
				}
				DownLoadRunnable runnable = new DownLoadRunnable(url,
						fileDownLoad.getAbsolutePath(), start, end);
				threadPool.execute(runnable);
			}

			final Bitmap bitmap = BitmapFactory.decodeFile(fileDownLoad
					.getAbsolutePath());

			handler.post(new Runnable() {

				@Override
				public void run() {
					dowloadIv.setImageBitmap(bitmap);
				}
			});
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	static class DownLoadRunnable implements Runnable {
		private String url;
		private String fileName;

		private long start;
		private long end;

		public DownLoadRunnable(String url, String fileName, long start,
				long end) {
			this.url = url;
			this.fileName = fileName;
			this.start = start;
			this.end = end;
		}

		@Override
		public void run() {
			try {
				URL httpUrl = new URL(url);
				HttpURLConnection connection = (HttpURLConnection) httpUrl
						.openConnection();
				connection.setReadTimeout(5000);
				connection.setRequestProperty("Range", "bytes=" + start + "-"
						+ end);// Http协议字段
				connection.setRequestMethod("GET");

				System.out.println("fileName:" + fileName);

				RandomAccessFile access = new RandomAccessFile(new File(
						fileName), "rwd");// 设置吸入的位置

				access.seek(start);
				InputStream is = connection.getInputStream();
				byte[] bs = new byte[1024 * 4];
				int len;
				while ((len = is.read(bs)) != -1) {
					access.write(bs, 0, len);
				}

				if (access != null) {
					access.close();
				}

				if (is != null) {
					is.close();
				}

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getFileName(String url) {
		return url.substring(url.lastIndexOf("/") + 1);
	}
}
