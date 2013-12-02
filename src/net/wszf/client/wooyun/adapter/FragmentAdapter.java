package net.wszf.client.wooyun.adapter;

import net.wszf.client.wooyun.fragments.ListShowFragment;
import net.wszf.client.wooyun.utils.FiledMark.Page;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

public class FragmentAdapter extends FragmentStatePagerAdapter
	{
		protected static final String[] CONTENT = new String[] { "最新提交", "最新公开", "最新确认","等待认领"};

		public FragmentAdapter(FragmentManager fm)
			{
				super(fm);
				// TODO Auto-generated constructor stub
			}

		@Override
		public Fragment getItem(int position)
			{
				// TODO Auto-generated method stub
				Fragment fragment = null;
				switch (position)
					{
					case 0:
						fragment=ListShowFragment.newInstance(Page.SUBMIT);
						break;
					case 1:
						fragment=ListShowFragment.newInstance(Page.PUBLIC);
						break;
					case 2:
						fragment=ListShowFragment.newInstance(Page.CONFIRM);
						break;
					case 3:
						fragment=ListShowFragment.newInstance(Page.UNCLAIM);
						break;
					}	
				return fragment;
			}

		@Override
		public int getCount()
			{
				// TODO Auto-generated method stub
				return CONTENT.length;
			}

		@Override
		public CharSequence getPageTitle(int position)
			{
				// TODO Auto-generated method stub
				return CONTENT[position % CONTENT.length];
			}

		@Override
		public Object instantiateItem(ViewGroup container, int position)
			{
				// TODO Auto-generated method stub
			//	System.out.println("instantiateItem");
				return super.instantiateItem(container, position);
				
			}




	}
