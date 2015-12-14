package com.xh.androidhttp.ui.home.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.xh.androidhttp.R;
import com.xh.androidhttp.serve.extend.HttpWebService;

public class HttpGetFragment extends Fragment {
	private Activity activity;
	private View parent;

	private Handler handler = new Handler();

	private WebView webView;
	private TextView tv_get;
	private ImageView iv;

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
		startService();
	}

	private void findView() {
		webView = (WebView) parent.findViewById(R.id.webView);
		iv = (ImageView) parent.findViewById(R.id.iv);
	}

	private void startService() {
		new HttpWebService("http://hao.360.cn/", webView, handler).start();
		new HttpWebService("http://i2.tietuku.com/8cbe6669f781eb60s.jpg", iv,
				handler).start();
	}
}
