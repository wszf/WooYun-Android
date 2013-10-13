package net.wszf.client.wooyun;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import net.wszf.client.wooyun.adapter.CommonAdapter;
import net.wszf.client.wooyun.domain.BugInfoDomain;
import net.wszf.client.wooyun.utils.CommonUtils;
import net.wszf.client.wooyun.utils.FiledMark;
import net.wszf.client.wooyun.utils.RequestManager;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends Activity
	{
		private ListView listView;
		private CommonAdapter adapter;
		private ArrayList<BugInfoDomain> list = new ArrayList<BugInfoDomain>();
		private ProgressDialog dialog;
		private String url = FiledMark.SUBMIT_URL;
		private ActionBar actionBar;
		private Runnable infoRunnable = new Runnable()
			{
				@Override
				public void run()
					{
						getRespones(url);
						handler.sendEmptyMessage(1);
					}
			};
		private Handler handler = new Handler(new Callback()
			{
				@Override
				public boolean handleMessage(Message msg)
					{
						listView.setVisibility(View.GONE);
						listView.setAdapter(adapter);
						listView.setVisibility(View.VISIBLE);
						dialog.cancel();
						return false;
					}
			});

		private void fillView()
			{
				dialog = CommonUtils.getLoading(this, dialog);
				listView = (ListView) findViewById(R.main.listView);
				adapter = new CommonAdapter(list, this);
				listView.setAdapter(adapter);
				listView.setOnItemClickListener(new OnItemClickListener()
					{
						@Override
						public void onItemClick(AdapterView<?> parent, View view, int position, long id)
							{
								Intent intent = new Intent();
								intent.setAction("android.intent.action.VIEW");
								Uri content_url = Uri.parse(list.get(position).getLink());
								intent.setData(content_url);
								// intent.setClassName("com.android.browser","com.android.browser.BrowserActivity");
								startActivity(intent);
							}
					});
				dialog.show();
				new Thread(infoRunnable).start();
			}

		@Override
		public void onPause()
			{
				// TODO Auto-generated method stub
				super.onPause();
			}

		
		@Override
		public void onStop()
			{
				// TODO Auto-generated method stub
				super.onStop();
			}

		@Override
		protected void onCreate(Bundle savedInstanceState)
			{
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_main);
				actionBar=getActionBar();
				actionBar.setTitle(R.string.menu_new_submit);
				fillView();
			}

		@Override
		public boolean onCreateOptionsMenu(Menu menu)
			{
				// Inflate the menu; this adds items to the action bar if it is present.
				getMenuInflater().inflate(R.menu.activity_main, menu);
				return true;
			}

		public boolean onOptionsItemSelected(MenuItem item)
			{
				switch (item.getItemId())
					{
					case R.menu.about:
						new AlertDialog.Builder(this).setTitle("关于").setMessage("感谢WooYun开放API.\n").setNegativeButton("确定", null).create().show();
						return true;
					case R.menu.new_submit:
						url = FiledMark.SUBMIT_URL;
						CommonAdapter.mark = 0;
						list.clear();
						dialog.show();
						actionBar.setTitle(R.string.menu_new_submit);
						new Thread(infoRunnable).start();
						break;
					case R.menu.new_confirm:
						url = FiledMark.CONFIRM_URL;
						CommonAdapter.mark = 1;
						list.clear();
						dialog.show();
						actionBar.setTitle(R.string.menu_new_confirm);
						new Thread(infoRunnable).start();
						break;
					case R.menu.new_public:
						url = FiledMark.PUBLIC_URL;
						CommonAdapter.mark = 1;
						list.clear();
						dialog.show();
						actionBar.setTitle(R.string.menu_new_public);
						new Thread(infoRunnable).start();
						break;
					case R.menu.new_unclaim:
						url = FiledMark.UNCLAIM_URL;
						CommonAdapter.mark = 0;
						list.clear();
						dialog.show();
						actionBar.setTitle(R.string.menu_new_unclaim);
						new Thread(infoRunnable).start();
						break;
					}
				return super.onOptionsItemSelected(item);
			}

		public void getRespones(String url)
			{
				StringRequest stringRequest = new StringRequest(url, new Listener<String>()
					{
						@Override
						public void onResponse(String response)
							{
								Type listType = new TypeToken<ArrayList<BugInfoDomain>>()
									{
									}.getType();
								// list=new Gson().fromJson(response, listType);
								list.addAll((Collection<? extends BugInfoDomain>) new Gson().fromJson(response, listType));
								handler.sendEmptyMessage(1);
							}
					}, new ErrorListener()
					{
						@Override
						public void onErrorResponse(VolleyError error)
							{
								Toast.makeText(MainActivity.this, "数据获取失败", Toast.LENGTH_SHORT).show();
							}
					});
				RequestManager.addRequest(stringRequest, this);
			}
	}
