package com.dary.xmpp;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Vibrator;

public class Tools {

	public static String delLastLine(StringBuilder sb) {
		return sb.delete(sb.toString().length() - 1, sb.toString().length()).toString();
	}

	//Photo命令中,照片的命名
	public static String getTimeStrHyphen() {
		Date d = new Date();
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss",Locale.getDefault());
		String timeStr = sdFormatter.format(d);
		return timeStr;
	}

	//doLog中用到
	public static String getTimeStr() {
		Date d = new Date();
		SimpleDateFormat sdFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss",Locale.getDefault());
		String retStrFormatNowDate = sdFormatter.format(d);
		return retStrFormatNowDate;
	}
	
	public static String getTimeStr(long time) {
		Date d = new Date(time);
		SimpleDateFormat sdFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss",Locale.getDefault());
		String retStrFormatNowDate = sdFormatter.format(d);
		return retStrFormatNowDate;
	}

	public static void Vibrator(Context context, int time) {
		Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		long[] pattern = { 0, time };
		vibrator.vibrate(pattern, -1);
	}

	public static void doLog(String str) {
		try {
			// 注意如果文件不存在的时候(确切的说应该是文件的内容为空时),添加新内容之前要先添加换行符.
			// FileOutputStream outStream = MyApp.getContext().openFileOutput("LoginLog", Context.MODE_APPEND);
			// 会直接创建
			File file = MyApp.getContext().getFileStreamPath("LoginLog");

			StringBuilder sb = new StringBuilder();
			if (file.exists()) {
				sb.append("\n");
			} else {
				file.createNewFile();
			}
			sb.append(str);
			// 占满整个一行,对齐
			int length = 24;
			for (int i = 0; i < length - str.length(); i++) {
				sb.append(" ");
			}
			sb.append(Tools.getTimeStr());
			FileOutputStream fos = new FileOutputStream(file, true);
			fos.write(sb.toString().getBytes());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getAppVersionName(Context context) {
		String versionName = "";
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionName = pi.versionName;
			if (versionName == null || versionName.length() <= 0) {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return versionName;
	}
	
	//移除Resource
	public static String getAddress(String str) {
		return str.split("/", -1)[0];
	}
}
