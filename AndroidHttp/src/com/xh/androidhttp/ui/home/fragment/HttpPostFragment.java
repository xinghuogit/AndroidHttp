package com.xh.androidhttp.ui.home.fragment;

import com.xh.androidhttp.R;
import com.xh.androidhttp.serve.extend.HttpService;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class HttpPostFragment extends Fragment implements OnClickListener {
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
		return inflater.inflate(R.layout.fragment_httppost, container, false);
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
			startPostService();
			break;
		default:
			break;
		}

	}

	private void startPostService() {
		new HttpService("http://192.168.1.100:8080/xhsp/register.json", user
				.getText().toString().trim(), psw.getText().toString().trim())
				.start();
	}
}
