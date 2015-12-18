package com.xh.androidhttp.ui.home.fragment;

import java.io.File;

import com.xh.androidhttp.R;
import com.xh.androidhttp.constant.Constant;
import com.xh.androidhttp.serve.extend.HttpService;
import com.xh.androidhttp.serve.extend.UploadThread;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
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
	private Button button1;

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
		button1 = (Button) parent.findViewById(R.id.button1);
	}

	private void setListener() {
		login.setOnClickListener(this);
		button1.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login:
			startPostService();
			break;
		case R.id.button1:
			startUpload();
			break;
		default:
			break;
		}

	}

	private void startPostService() {
		new HttpService(Constant.getService(Constant.API_REGISTER), user
				.getText().toString().trim(), psw.getText().toString().trim())
				.start();
	}

	private void startUpload() {
		String url = Constant.getService(Constant.API_UPLOAD_IMG);
		File file = Environment.getExternalStorageDirectory();

		File fileAbs = new File(file, "360w.jpg");
		String fileName = fileAbs.getAbsolutePath();
		System.out.println("fileName:" + fileName);
		UploadThread uploadThread = new UploadThread(fileAbs, fileName,
				Constant.getService(Constant.API_UPLOAD_IMG));
		// "http://192.168.31.107:8080/xhsp/Upload"
		uploadThread.start();
	}
}
