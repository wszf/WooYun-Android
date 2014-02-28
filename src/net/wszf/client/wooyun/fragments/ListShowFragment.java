package net.wszf.client.wooyun.fragments;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.wszf.client.wooyun.BuildConfig;
import net.wszf.client.wooyun.R;
import net.wszf.client.wooyun.adapter.CommonAdapter;
import net.wszf.client.wooyun.domain.BugInfoDomain;
import net.wszf.client.wooyun.utils.CommonUtils;
import net.wszf.client.wooyun.utils.FiledMark;
import net.wszf.client.wooyun.utils.FiledMark.Page;
import net.wszf.client.wooyun.utils.RequestManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.handmark.pulltorefresh.library.extras.SoundPullEventListener;


public class ListShowFragment extends Fragment
	{
		private ListView listView;
		private View view;
		private CommonAdapter adapter;
		private ProgressDialog dialog;
		private List<BugInfoDomain> list = new ArrayList<BugInfoDomain>();
		private Page flag;
		private PullToRefreshListView mPullRefreshListView;
		private Handler handler = new Handler(new Callback()
			{
				@Override
				public boolean handleMessage(Message msg)
					{
						listView.setVisibility(View.GONE);
						adapter.notifyDataSetChanged();
						listView.setVisibility(View.VISIBLE);
						mPullRefreshListView.onRefreshComplete();
						// dialog.cancel();
						return false;
					}
			});

		public static final ListShowFragment newInstance(Page flag)
			{
				ListShowFragment fragment = new ListShowFragment();
				Bundle bundle = new Bundle();
				bundle.putSerializable("flag", flag);
				fragment.setArguments(bundle);
				return fragment;
			}

		@Override
		public void onCreate(Bundle savedInstanceState)
			{
				// TODO Auto-generated method stub
				super.onCreate(savedInstanceState);
			}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
			{
				// TODO Auto-generated method stub
				flag = (Page) getArguments().getSerializable("flag");
				view = inflater.inflate(R.layout.activity_main, container, false);
				fillView();
				return view;
			}

		private void fillView()
			{
				dialog = CommonUtils.getLoading(getActivity(), dialog);
				mPullRefreshListView = (PullToRefreshListView) view.findViewById(R.main.listView);
				adapter = new CommonAdapter(list, getActivity());
				listView = mPullRefreshListView.getRefreshableView();
				listView.setOnItemClickListener(new OnItemClickListener()
					{

						@Override
						public void onItemClick(AdapterView<?> parent, View view, int position, long id)
							{
								Intent intent = new Intent();
								intent.setAction("android.intent.action.VIEW");
								Uri content_url = Uri.parse(list.get(position-1).getLink());
								intent.setData(content_url);
								startActivity(intent);

							}
					});
				mPullRefreshListView.setOnRefreshListener(new OnRefreshListener<ListView>()
					{
						@Override
						public void onRefresh(PullToRefreshBase<ListView> refreshView)
							{
								String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE
										| DateUtils.FORMAT_ABBREV_ALL);

								// Update the LastUpdatedLabel
								refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

								// Do work to refresh the list here.
								switchPage(flag);
							}
					});
				/**
				 * Add Sound Event Listener
				 */
				SoundPullEventListener<ListView> soundListener = new SoundPullEventListener<ListView>(getActivity());
				soundListener.addSoundEvent(State.PULL_TO_REFRESH, R.raw.pull_event);
				soundListener.addSoundEvent(State.RESET, R.raw.reset_sound);
				soundListener.addSoundEvent(State.REFRESHING, R.raw.refreshing_sound);
				mPullRefreshListView.setOnPullEventListener(soundListener);
				listView.setAdapter(adapter);
				if (list.size() == 0)
					{
						switchPage(flag);
					}
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
								list.clear();
								list.addAll((Collection<? extends BugInfoDomain>) new Gson().fromJson(response, listType));
								handler.sendEmptyMessage(1);
							}
					}, new ErrorListener()
					{
						@Override
						public void onErrorResponse(VolleyError error)
							{
								Toast.makeText(getActivity(), "数据获取失败", Toast.LENGTH_SHORT).show();
							}
					});
				RequestManager.addRequest(stringRequest, this);
			}

		private void switchPage(Page flag)
			{
				switch (flag)
					{
					case PUBLIC:
						getRespones(FiledMark.PUBLIC_URL);
						break;
					case CONFIRM:
						getRespones(FiledMark.CONFIRM_URL);
						break;
					case SUBMIT:
						getRespones(FiledMark.SUBMIT_URL);
						break;
					case UNCLAIM:
						getRespones(FiledMark.UNCLAIM_URL);
						break;
					}
			}

	}
