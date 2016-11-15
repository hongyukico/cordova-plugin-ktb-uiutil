package com.ktb.plugin.view;

/**
 * 类描述：
 * 创建人： Hao_RenWu
 * 创建时间：2016/4/12 14:18
 * 修改人： Hao_RenWu
 * 修改时间：2016/4/12 14:18
 * 修改备注：
 */

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import io.cordova.hellocordova.R;


public class CommonToast {
    private static Toast toast;
    private static Handler mHandler = new Handler();
    private static Runnable r = new Runnable() {
        public void run() {
            if (toast != null)
                toast.cancel();
            toast = null;
        }
    };

    public static void ToastShow(Context context, String message, boolean isSuccess) {
        View layout = View.inflate(context, R.layout.comm_view_toast, null);
        TextView text = (TextView) layout.findViewById(R.id.tv_msg_toast);
        ImageView mImageView = (ImageView) layout.findViewById(R.id.iv_loading_toast);
        text.setText(message);
        if (isSuccess)
            mImageView.setImageResource(R.drawable.toast_right_icon);
        text.setTextColor(context.getResources().getColor(R.color.white));
        if (toast == null) {
            /*每新建一个 就会把一个Tost 放到一个队列里面*/
            toast = new Toast(context);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setView(layout);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        mHandler.postDelayed(r, 2000);//延迟1秒隐藏toast
        toast.show();
    }

    public static void ToastShow(Context context, String message) {
        View layout = View.inflate(context, R.layout.comm_view_toast, null);
        TextView text = (TextView) layout.findViewById(R.id.tv_msg_toast);
        ImageView mImageView = (ImageView) layout.findViewById(R.id.iv_loading_toast);
        text.setText(message);
        mImageView.setVisibility(View.GONE);
        text.setTextColor(context.getResources().getColor(R.color.white));
        if (toast == null) {
            /*每新建一个 就会把一个Tost 放到一个队列里面*/
            toast = new Toast(context);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setView(layout);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        mHandler.postDelayed(r, 2000);//延迟1秒隐藏toast
        toast.show();
    }
}

