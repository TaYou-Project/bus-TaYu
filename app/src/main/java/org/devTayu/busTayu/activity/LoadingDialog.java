package org.devTayu.busTayu.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import org.devTayu.busTayu.R;

public class LoadingDialog {
    private Activity activity;
    private AlertDialog dialog;

    public LoadingDialog(Activity activity) {
        activity = this.activity;
    }

    public void startLoading(Activity activity) {
        // loading 돌 때 touch 막기
//        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_loading, null));
        builder.setCancelable(true);

        dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }

    public void dismissLoading(Activity activity) {
        // loading 끝나고 touch 풀어줌
//        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.dismiss();
    }
}
