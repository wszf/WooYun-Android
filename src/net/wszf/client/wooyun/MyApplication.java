package net.wszf.client.wooyun;

import java.util.ResourceBundle.Control;

import android.app.Application;
import android.content.Context;
/**
 * 全局context
 * @author wszfer
 *
 */
public class MyApplication extends Application
	{
		private static Context context;

		@Override
		public void onCreate()
			{
				super.onCreate();
				context=getApplicationContext();
			}
		public static Context getContext()
			{
				return context;
			}
	}
