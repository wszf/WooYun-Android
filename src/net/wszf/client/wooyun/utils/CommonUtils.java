package net.wszf.client.wooyun.utils;

import java.text.SimpleDateFormat;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.Html;
import android.text.Spanned;

public class CommonUtils
	{
		/**
		 * Unicode解码
		 * @param strs
		 * @return
		 */
		public static String decodeUnicode(String strs)
			{
				char aChar;
				int len = strs.length();
				StringBuffer outBuffer = new StringBuffer(len);
				for (int x = 0; x < len;)
					{
						aChar = strs.charAt(x++);
						if (aChar == '\\')
							{
								aChar = strs.charAt(x++);
								if (aChar == 'u')
									{
										int value = 0;
										for (int i = 0; i < 4; i++)
											{
												aChar = strs.charAt(x++);
												switch (aChar)
													{
													case '0':
													case '1':
													case '2':
													case '3':
													case '4':
													case '5':
													case '6':
													case '7':
													case '8':
													case '9':
														value = (value << 4) + aChar - '0';
														break;
													case 'a':
													case 'b':
													case 'c':
													case 'd':
													case 'e':
													case 'f':
														value = (value << 4) + 10 + aChar - 'a';
														break;
													case 'A':
													case 'B':
													case 'C':
													case 'D':
													case 'E':
													case 'F':
														value = (value << 4) + 10 + aChar - 'A';
														break;
													default:
														throw new IllegalArgumentException("Malformed      encoding.");
													}
											}
										outBuffer.append((char) value);
									} else
									{
										if (aChar == 't')
											{
												aChar = '\t';
											} else if (aChar == 'r')
											{
												aChar = '\r';
											} else if (aChar == 'n')
											{
												aChar = '\n';
											} else if (aChar == 'f')
											{
												aChar = '\f';
											}
										outBuffer.append(aChar);
									}
							} else
							{
								outBuffer.append(aChar);
							}
					}
				return outBuffer.toString();
			}
		/**
		 * 转换为Unicode编码
		 * @param str
		 * @return
		 */
		public static String encodeUnicode(String str)
			{
				char[] utfBytes = str.toCharArray();
				StringBuffer buffer = new StringBuffer();
				for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++)
					{
						String hexB = Integer.toHexString(utfBytes[byteIndex]);
						if (hexB.length() <= 2)
							{
								hexB = "00" + hexB;
							}
						buffer.append("\\u" + hexB);
					}
				return buffer.substring(0);
			}
		/**
		 * String 格式化
		 * 
		 * @param context
		 * @param format
		 * @param str
		 * @return
		 */
		public static String getStringFormat(Context context, int format, Object... str)
			{
				return String.format(context.getText(format).toString(), str);
			}
		public static String getTime(long time)
			{
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				return df.format(time*1000l); 
			}
		public static Spanned getStringToHtml(Context context, int format, Object... str)
			{
				return Html.fromHtml(String.format(context.getText(format).toString(), str));
			}
		/**
		 * 通用loading
		 * 
		 * @param context
		 * @param tips
		 * @param dialog
		 * @return
		 */
		public static ProgressDialog getLoading(Context context, String tips, ProgressDialog dialog)
			{
				dialog = new ProgressDialog(context);
				dialog.setIndeterminate(true);
				dialog.setCancelable(true);
				dialog.setMessage(tips);
				return dialog;
			}

		public static ProgressDialog getLoading(Context context, int tips, ProgressDialog dialog)
			{
				dialog = new ProgressDialog(context);
				dialog.setIndeterminate(true);
				dialog.setCancelable(true);
				dialog.setMessage(context.getText(tips));
				return dialog;
			}
		
		public static ProgressDialog getLoading(Context context,ProgressDialog dialog)
			{
				dialog = new ProgressDialog(context);
				dialog.setIndeterminate(true);
				dialog.setCancelable(true);
				dialog.setMessage("Loading...");
				return dialog;
			}
	}
