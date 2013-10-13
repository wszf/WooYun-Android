package net.wszf.client.wooyun.adapter;

import java.util.List;

import net.wszf.client.wooyun.R;
import net.wszf.client.wooyun.domain.BugInfoDomain;
import net.wszf.client.wooyun.utils.CommonUtils;
import net.wszf.client.wooyun.utils.FiledMark;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
/**
 * 通用adapter
 * @author wszf
 * @project WooYun
 * @date 2013-1-25下午1:30:08
 * @version 1.0
 */
public class CommonAdapter extends BaseAdapter
	{
		private List<BugInfoDomain> list;
		private Context context;
		private LayoutInflater layoutInflater;
		public static  int mark=0;
		public CommonAdapter(List<BugInfoDomain> list, Context context)
			{
				super();
				this.list = list;
				this.context = context;
				layoutInflater=LayoutInflater.from(context);
			}

		
		@Override
		public int getCount()
			{
				// TODO Auto-generated method stub
				return list.size();
			}

		@Override
		public Object getItem(int position)
			{
				// TODO Auto-generated method stub
				return list.get(position);
			}

		@Override
		public long getItemId(int position)
			{
				// TODO Auto-generated method stub
				return 0;
			}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
			{
				if(list.size()>0)
					{
						WrapperView wrapperView;
						BugInfoDomain info=(BugInfoDomain) getItem(position);
						switch (mark)
							{
							case 0://最新提交
								if(convertView==null)
									{
									wrapperView=new WrapperView();
									convertView=layoutInflater.inflate(R.layout.new_item, null);
									wrapperView.title=(TextView) convertView.findViewById(R.item.title);
									wrapperView.date=(TextView) convertView.findViewById(R.item.date);
									wrapperView.link=(TextView) convertView.findViewById(R.item.link);
									wrapperView.user_harmlevel=(TextView) convertView.findViewById(R.item.user_harmlevel);
									wrapperView.status=(TextView) convertView.findViewById(R.item.status);
									wrapperView.comment=(TextView) convertView.findViewById(R.item.comment);
									convertView.setTag(wrapperView);
									}
								else{
									wrapperView=(WrapperView) convertView.getTag();
								}
								wrapperView.title.setText(CommonUtils.getStringToHtml(context, R.string.item_title, info.getTitle()));
								wrapperView.date.setText(CommonUtils.getStringToHtml(context, R.string.item_date, info.getDate()));
								wrapperView.link.setText(CommonUtils.getStringToHtml(context, R.string.item_link, info.getLink()));
								wrapperView.user_harmlevel.setText(CommonUtils.getStringToHtml(context, R.string.item_harmlevel, FiledMark.user_harmlevels[Integer.valueOf(info.getUser_harmlevel())-1]));
								wrapperView.comment.setText(CommonUtils.getStringToHtml(context, R.string.item_comment, info.getComment()));
								wrapperView.status.setText(CommonUtils.getStringToHtml(context, R.string.item_status, FiledMark.status[Integer.valueOf(info.getStatus())]));	
							
								break;

							default:
								//公开
								if(convertView==null)
									{
									wrapperView=new WrapperView();
									convertView=layoutInflater.inflate(R.layout.public_item, null);
									wrapperView.title=(TextView) convertView.findViewById(R.item.title);
									wrapperView.date=(TextView) convertView.findViewById(R.item.date);
									wrapperView.link=(TextView) convertView.findViewById(R.item.link);
									wrapperView.user_harmlevel=(TextView) convertView.findViewById(R.item.user_harmlevel);
									wrapperView.comment=(TextView) convertView.findViewById(R.item.comment);
									wrapperView.corp_harmlevel=(TextView) convertView.findViewById(R.item.corp_harmlevel);
									wrapperView.corp_rank=(TextView) convertView.findViewById(R.item.corp_rank);
									wrapperView.status=(TextView) convertView.findViewById(R.item.status);
									convertView.setTag(wrapperView);
									}
								else{
									wrapperView=(WrapperView) convertView.getTag();
								}
								wrapperView.title.setText(CommonUtils.getStringToHtml(context, R.string.item_title, info.getTitle()));
								wrapperView.date.setText(CommonUtils.getStringToHtml(context, R.string.item_date, info.getDate()));
								wrapperView.link.setText(CommonUtils.getStringToHtml(context, R.string.item_link, info.getLink()));
								wrapperView.user_harmlevel.setText(CommonUtils.getStringToHtml(context, R.string.item_harmlevel, FiledMark.user_harmlevels[Integer.valueOf(info.getUser_harmlevel())-1]));
								wrapperView.corp_harmlevel.setText(CommonUtils.getStringToHtml(context, R.string.item_corp_harmlevel, FiledMark.corp_harmlevels[Integer.valueOf(info.getCorp_harmlevel())+1]));	
								wrapperView.comment.setText(CommonUtils.getStringToHtml(context, R.string.item_comment, info.getComment()));	
								wrapperView.corp_rank.setText(CommonUtils.getStringToHtml(context, R.string.item_corp_rank, info.getCorp_rank()));	
								wrapperView.status.setText(CommonUtils.getStringToHtml(context, R.string.item_status, FiledMark.status[Integer.valueOf(info.getStatus())]));	
								break;
							}
					}
				return convertView;
			}
		
		
		static class WrapperView{
			TextView title,date,link,user_harmlevel,comment,corp_harmlevel,corp_rank,status;
		}
	}
