package net.wszf.client.wooyun.utils;

public class FiledMark 
	{
		private static int NUM = 10;
		public static final String PUBLIC_URL = "http://api.wooyun.org/bugs/public/limit/" + NUM;
		public static final String CONFIRM_URL = "http://api.wooyun.org/bugs/confirm/limit/" + NUM;
		public static final String SUBMIT_URL = "http://api.wooyun.org/bugs/submit/limit/" + NUM;
		public static final String UNCLAIM_URL = "http://api.wooyun.org/bugs/unclaim/limit/" + NUM;
		public static final String[] status = { "待厂商确认处理", "厂商已经确认", "漏洞通知厂商但厂商忽略", "未联系到厂商或厂商忽略", "正在联系厂商并等待认领" };
		public static final String[] user_harmlevels = { "低", "中", "高" };
		public static final String[] corp_harmlevels = { "无影响厂商忽略", "未能联系到厂商或者厂商积极拒绝", "低", "中", "高" };

		public enum Page 
			{
				PUBLIC, CONFIRM, SUBMIT, UNCLAIM;
			}

	}
