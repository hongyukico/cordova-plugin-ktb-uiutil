package com.ktb.plugin;

import android.app.Activity;
import android.view.View;

import com.ktb.plugin.view.CommonDialog;
import com.ktb.plugin.view.LoadingDialog;
import com.ktb.plugin.view.UIUtil;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/3/8.
 */

public class UIUtilPlugin extends CordovaPlugin {

    //执行插件的动作，可以有多个动作
    public static final String Alert_Action = "openAlert";
    public static final String loading_Action = "openLoading";
    public static final String openConfirmAlert_Action = "openConfirmAlert";


    //cordova 内部的回调处理类
    CallbackContext callbackContext;
    Activity context;
    LoadingDialog loadingDialog;

    /**
     * Constructor.
     */
    public UIUtilPlugin() {
    }

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        context = cordova.getActivity();
    }

    @Override //插件的执行方法，
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        if (loading_Action.equals(action))
            return loading(args);
        else if (Alert_Action.equals(action))
            return openAlert(args);
        else if (openConfirmAlert_Action.equals(action))
            return openConfirmAlert(args);
        else
            return false;

    }

    /**
     * 加载框
     * @param args
     * @return
     */
    public Boolean loading(JSONArray args) {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(context);
        }
        JSONObject argJson = null;
        try {
            argJson = args.getJSONObject(0);
            Boolean isOpen = argJson.getBoolean("isLoading");
            if (isOpen) {
                if (!loadingDialog.isShowing())
                    loadingDialog.show();
            }else if (loadingDialog.isShowing())
                    loadingDialog.dismiss();
            callbackContext.success();
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 带按钮的提示框
     * @param args
     * @return
     */
    public Boolean openConfirmAlert(JSONArray args) {
        JSONObject argJson = null;
        try {
            argJson = args.getJSONObject(0);

            String message = argJson.getString("message");//弹出框内容
            String title = argJson.getString("title");//弹出框内容
            JSONArray button = argJson.getJSONArray("button");
            final CommonDialog dialog;
            if (button.length() == 2)
                dialog = new CommonDialog(context, title, message, button.getString(0), button.getString(1));
            else
                dialog = new CommonDialog(context, title, message, button.getString(0), "");
            dialog.setRightClickListener(new View.OnClickListener() {//确认
                @Override
                public void onClick(View v) {
                    callbackContext.success("1");
                    dialog.dismiss();
                }
            });
            dialog.setLeftClickListener(new View.OnClickListener() {//取消
                @Override
                public void onClick(View v) {
                    callbackContext.success("0");
                    dialog.dismiss();
                }
            });
            dialog.show();
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 带图标和不带图标的提示框
     * @param args
     * @return
     */
    public Boolean openAlert(JSONArray args) {
        JSONObject argJson = null;
        try {
            argJson = args.getJSONObject(0);
            String message = argJson.getString("message");//弹出框内容
            Boolean status;
            if (UIUtil.isNotNull(message)) {
                if (argJson.has("status")) {
                    status = argJson.getBoolean("status");
                    UIUtil.toast(context, message, status);
                } else {
                    UIUtil.toast(context, message);
                }
            }
            callbackContext.success();
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

}
