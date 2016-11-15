package com.ktb.plugin.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.ktb.cordova.R;


/**
 * 通用 LoadingDialog对话框
 *
 * @author HaorenWu
 */
public class LoadingDialog extends Dialog {
    private ImageView mIvLoading;
    private TextView mTvMsg;
    RotateAnimation anim;
    Context context;
    int tag;

    public LoadingDialog(Context context, int Tag) {
        super(context, R.style.UIUtilDialogStyle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setContentView(R.layout.comm_view_loading_dialog);
        this.context = context;
        this.tag = Tag;
        setViews();
        initData();
    }

    public LoadingDialog(Context context) {
        super(context, R.style.UIUtilDialogStyle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setContentView(R.layout.comm_view_loading_dialog);
        setCanceledOnTouchOutside(false);
        this.context = context;
        setViews();
        initData();
    }


    private void setViews() {
        mIvLoading = (ImageView) findViewById(R.id.iv_loading);
        mTvMsg = (TextView) findViewById(R.id.tv_msg);
    }

    private void initData() {
        anim = (RotateAnimation) AnimationUtils.loadAnimation(getContext(), R.anim.trade_comm_loading_anim);
    }

    @Override
    public void show() {
        if (tag == 0) {
            String className = ((Activity) context).getClass().getName();
            if (UIUtil.isActivityRunning(context, className)) {
                mIvLoading.startAnimation(anim);
                super.show();
            }
        } else if (tag == 3) {
            setCancelable(false);
            mIvLoading.startAnimation(anim);
            super.show();
        } else {
            mIvLoading.startAnimation(anim);
            super.show();
        }
    }

    public void setMessage(String msg) {
        mTvMsg.setVisibility(View.VISIBLE);
        mTvMsg.setText(msg);
    }

    public void show(boolean cancelble) {
        setCancelable(cancelble);
        show();
    }

    @Override
    public void dismiss() {
        if (isShowing()) {
            mIvLoading.setAnimation(null);
            super.dismiss();
        }
    }

}
