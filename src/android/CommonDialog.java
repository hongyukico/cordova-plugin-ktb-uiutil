package com.ktb.plugin.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.ktb.cordova.R;


/**
 * 通用 LoadingDialog对话框
 *
 * @author HaorenWu
 */
public class CommonDialog extends Dialog {
    private TextView titleView, mTvMsg, btn_dialog_cancel, btn_dialog_ok;
    String title, message, left, right;
    Context context;
    private View.OnClickListener leftClickListener;
    private View.OnClickListener rightClickListener;

    public CommonDialog(Context context, String title, String message, String left, String right) {
        super(context, R.style.UIUtilDialogStyle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().setWindowAnimations(R.style.ToastDialogAnimation);
        setContentView(R.layout.alertdialog_return);
        this.message = message;
        this.title = title;
        this.right = right;
        this.left = left;
        this.context = context;
        setViews();
        show(false);
        setCanceledOnTouchOutside(true);
    }


    private void setViews() {
        mTvMsg = (TextView) findViewById(R.id.comm_dialog_content);
        btn_dialog_cancel = (TextView) findViewById(R.id.btn_dialog_cancel);
        titleView = (TextView) findViewById(R.id.comm_dialog_title);
        btn_dialog_ok = (TextView) findViewById(R.id.btn_dialog_determine);
        mTvMsg.setText(message);
        btn_dialog_cancel.setText(left);
        btn_dialog_ok.setText(right);
        btn_dialog_ok.setVisibility(UIUtil.isNull(right) ? View.GONE : View.VISIBLE);
        if (UIUtil.isNull(right))
            btn_dialog_cancel.setTextColor(context.getResources().getColor(R.color.Or));
        titleView.setVisibility(UIUtil.isNull(title) ? View.GONE : View.VISIBLE);
    }

    /**
     * 设置确认
     *
     * @return
     */
    public void setRightClickListener(View.OnClickListener listener) {
        this.rightClickListener = listener;
        btn_dialog_ok.setOnClickListener(rightClickListener);

    }

    /**
     * 设置取消
     *
     * @return
     */
    public void setLeftClickListener(
            View.OnClickListener listener) {
        this.leftClickListener = listener;
        btn_dialog_cancel.setOnClickListener(leftClickListener);
    }

    @Override
    public void show() {
        super.show();
    }


    public void show(boolean cancelble) {
        setCancelable(cancelble);
        show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

}
