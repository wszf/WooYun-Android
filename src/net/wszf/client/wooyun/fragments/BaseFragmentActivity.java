package net.wszf.client.wooyun.fragments;

import net.wszf.client.wooyun.R;
import net.wszf.client.wooyun.R.menu;
import net.wszf.client.wooyun.adapter.FragmentAdapter;
import android.app.AlertDialog;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.viewpagerindicator.PageIndicator;

public abstract class BaseFragmentActivity extends FragmentActivity
	{
		protected FragmentAdapter mAdapter;
		protected ViewPager mPager;
		protected PageIndicator mIndicator;

		@Override
		public boolean onCreateOptionsMenu(Menu menu)
			{
				// TODO Auto-generated method stub
				getMenuInflater().inflate(R.menu.activity_main, menu);
				return true;
			}
		@Override
		public boolean onOptionsItemSelected(MenuItem item)
			{
				switch (item.getItemId())
					{
					case R.menu.about:
						new AlertDialog.Builder(this)
						.setTitle("关于")
						.setMessage("感谢WooYun开放API.\n Version:1.0")
						.setNegativeButton("确定", null)
						.create().show();
						return true;

					
					}
				return super.onOptionsItemSelected(item);
			}
	}
