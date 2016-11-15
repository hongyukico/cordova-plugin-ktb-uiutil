package com.ktb.plugin.view;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;

import java.util.List;


/**
 * Created by Administrator on 2015/9/29.
 */
public class UIUtil {


    public static void toast(Context ctx, String msg, boolean isTure) {
        CommonToast.ToastShow(ctx, msg, isTure);
    }


    public static void toast(Context ctx, String msg) {
        CommonToast.ToastShow(ctx, msg);
    }
    public static boolean isActivityRunning(Context mContext, String activityClassName) {
        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> info = activityManager.getRunningTasks(1);
        if (info != null && info.size() > 0) {
            ComponentName component = info.get(0).topActivity;
            if (activityClassName.equals(component.getClassName())) {
                return true;
            }
        }
        return false;
    }


	
    public static boolean isNull(Object o) {
        if (o != null && o.equals("null"))
            o = null;
        return o == null ? true : false;
    }


    public static boolean isNull(List<?> list) {
        return list == null || list.size() == 0 ? true : false;
    }

    public static boolean isNotNull(List<?> list) {
        return !isNull(list);
    }

    @SuppressLint("DefaultLocale")
    public static boolean isNull(String str) {
        if (str != null && str.toLowerCase().equals("null")) {
            str = "";
        }
        return TextUtils.isEmpty(str) ? true : false;
    }

    public static boolean isNotNull(Object o) {
        return !isNull(o);
    }

	
	
}
