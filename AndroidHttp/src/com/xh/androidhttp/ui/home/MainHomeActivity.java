package com.xh.androidhttp.ui.home;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.xh.androidhttp.R;
import com.xh.androidhttp.ui.home.fragment.ClientGetFragment;
import com.xh.androidhttp.ui.home.fragment.ClientPostFragment;
import com.xh.androidhttp.ui.home.fragment.HttpGetFragment;
import com.xh.androidhttp.ui.home.fragment.HttpPostFragment;

public class MainHomeActivity extends FragmentActivity implements
		OnClickListener {
	private TextView tv1, tv2, tv3, tv4;
	private View currentView;

	private HttpGetFragment httpGetFragment = new HttpGetFragment();
	private HttpPostFragment httpPostFragment = new HttpPostFragment();
	private ClientGetFragment clientGetFragment = new ClientGetFragment();
	private ClientPostFragment clientPostFragment = new ClientPostFragment();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment);
		findView();
		setListener();
	}

	private void findView() {
		tv1 = (TextView) findViewById(R.id.tv1);
		tv2 = (TextView) findViewById(R.id.tv2);
		tv3 = (TextView) findViewById(R.id.tv3);
		tv4 = (TextView) findViewById(R.id.tv4);

	}

	private void setListener() {
		tv1.setOnClickListener(this);
		tv2.setOnClickListener(this);
		tv3.setOnClickListener(this);
		tv4.setOnClickListener(this);
		tv1.performClick();
	}

	@Override
	public void onClick(View v) {
		if (currentView != null && currentView.equals(v)) {
			return;
		}
		FragmentTransaction bt = getSupportFragmentManager().beginTransaction();
		switch (v.getId()) {
		case R.id.tv1:
			setCurrentView(tv1);
			bt.replace(R.id.fragment_layout, httpGetFragment).commit();
			break;
		case R.id.tv2:
			setCurrentView(tv2);
			bt.replace(R.id.fragment_layout, httpPostFragment).commit();
			break;
		case R.id.tv3:
			setCurrentView(tv3);
			bt.replace(R.id.fragment_layout, clientGetFragment).commit();
			break;
		case R.id.tv4:
			setCurrentView(tv4);
			bt.replace(R.id.fragment_layout, clientPostFragment).commit();
			break;
		default:
			break;
		}
	}

	private void setCurrentView(View v) {
		if (currentView != null && !currentView.equals(v)) {
			currentView.setSelected(false);
		}
		currentView = v;
		currentView.setSelected(true);
	}
}
