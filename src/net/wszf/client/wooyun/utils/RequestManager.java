package net.wszf.client.wooyun.utils;

import net.wszf.client.wooyun.MyApplication;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

public class RequestManager
	{
		public static  RequestQueue requestQueue;
		private static RequestQueue getRequestQueue()
			{
				if(requestQueue==null)
					{
						requestQueue = new RequestQueue(openCache(), new BasicNetwork(new HurlStack()));
						requestQueue.start();
					}
				return requestQueue;
			}
		private static Cache openCache()//设置缓存目录
			{
				return new DiskBasedCache(CacheUtils.getExternalCacheDir(MyApplication.getContext()), 10 * 1024 * 1024);
			}
		public static void addRequest(Request request, Object tag)
			{
				if (tag != null)
					{
						request.setTag(tag);
					}
				getRequestQueue().add(request);
			}

		public static void cancelAll(Object tag)
			{
				getRequestQueue().cancelAll(tag);
			}

	}
