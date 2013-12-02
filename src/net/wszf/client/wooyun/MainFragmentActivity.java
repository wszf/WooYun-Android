package net.wszf.client.wooyun;

import net.wszf.client.wooyun.adapter.FragmentAdapter;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TitlePageIndicator;

public class MainFragmentActivity extends SherlockFragmentActivity
	{
		protected FragmentAdapter mAdapter;
		protected ViewPager mPager;
		protected PageIndicator mIndicator;
		@Override
		protected void onCreate(Bundle savedInstanceState)
			{
				super.onCreate(savedInstanceState);
				setContentView(R.layout.simple_titles);

				mAdapter = new FragmentAdapter(getSupportFragmentManager());

				mPager = (ViewPager) findViewById(R.id.pager);
				mPager.setAdapter(mAdapter);

				mIndicator = (TitlePageIndicator) findViewById(R.id.indicator);
				mIndicator.setViewPager(mPager);
			}
		@Override
		public boolean onCreateOptionsMenu(Menu menu)
			{
				// TODO Auto-generated method stub
				getSherlock().getMenuInflater().inflate(R.menu.activity_main, menu);
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
