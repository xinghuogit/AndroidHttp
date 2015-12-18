package com.xh.androidhttp.ui.home.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xh.androidhttp.R;
import com.xh.androidhttp.constant.Constant;
import com.xh.androidhttp.serve.extend.DownLoad;
import com.xh.androidhttp.serve.extend.HttpIvService;
import com.xh.androidhttp.serve.extend.HttpService;
import com.xh.androidhttp.serve.extend.HttpWebService;

public class HttpGetFragment extends Fragment implements OnClickListener {
	private Activity activity;
	private View parent;

	private Handler handler = new Handler();

	private WebView webView;
	private TextView tv_get;
	private ImageView iv;

	private EditText user, psw;
	private Button login;

	private ImageView dowload_iv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_httpget, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		activity = getActivity();
		parent = getView();
		findView();
		setListener();
		startWebIvService();
		startDowloadIv();
	}

	private void findView() {
		webView = (WebView) parent.findViewById(R.id.webView);
		iv = (ImageView) parent.findViewById(R.id.iv);

		user = (EditText) parent.findViewById(R.id.user);
		psw = (EditText) parent.findViewById(R.id.psw);
		login = (Button) parent.findViewById(R.id.login);

		dowload_iv = (ImageView) parent.findViewById(R.id.dowload_iv);
	}

	private void setListener() {
		login.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login:
			startGetService();
			break;
		default:
			break;
		}

	}

	private void startGetService() {
		new HttpService(Constant.getService(Constant.API_REGISTER), user
				.getText().toString().trim(), psw.getText().toString().trim())
				.start();
	}

	private void startWebIvService() {
		// new HttpThreedImage(
		// "http://i2.tietuku.com/8cbe6669f781eb60s.jpg",
		// imageView, handler).start();//有时候打不开
		new HttpIvService(
				"http://e.hiphotos.baidu.com/zhidao/wh%3D600%2C800/sign=b3f83a5681d6277fe9473a3e18083308/7c1ed21b0ef41bd59997461957da81cb38db3d12.jpg",
				iv, handler).start();
		new HttpWebService("http://hao.360.cn/", webView, handler).start();
	}

	private void startDowloadIv() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				DownLoad downLoad = new DownLoad(handler);
				downLoad.downLoadFile(Constant.getService(Constant.API_PHONE),
						dowload_iv);
			}
		});
		thread.start();
	}

}
