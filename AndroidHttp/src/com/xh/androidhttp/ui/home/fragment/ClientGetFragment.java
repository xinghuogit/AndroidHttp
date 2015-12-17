package com.xh.androidhttp.ui.home.fragment;

import com.xh.androidhttp.R;
import com.xh.androidhttp.constant.Constant;
import com.xh.androidhttp.serve.extend.HttpClientService;
import com.xh.androidhttp.serve.extend.HttpIvService;
import com.xh.androidhttp.serve.extend.HttpService;
import com.xh.androidhttp.serve.extend.HttpWebService;

import android.app.Activity;
import android.os.Bundle;
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

public class ClientGetFragment extends Fragment implements OnClickListener {
	private Activity activity;
	private View parent;

	private EditText user, psw;
	private Button login;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_clientget, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		activity = getActivity();
		parent = getView();
		findView();
		setListener();
	}

	private void findView() {
		user = (EditText) parent.findViewById(R.id.user);
		psw = (EditText) parent.findViewById(R.id.psw);
		login = (Button) parent.findViewById(R.id.login);

	}

	private void setListener() {
		login.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login:
			startClientGetService();
			break;
		default:
			break;
		}

	}

	private void startClientGetService() {
		new HttpClientService(Constant.getInstantce().getService("2"), user
				.getText().toString().trim(), psw.getText().toString().trim())
				.start();
	}
}
