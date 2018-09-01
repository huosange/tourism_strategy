package com.tourism.strategy.tourism_strategy.fragment;

import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.tourism.strategy.tourism_strategy.R;

public class BaseFragment extends Fragment {

    private Dialog progressDialog;

    public void showDialog() {
        progressDialog = new Dialog(getActivity(), R.style.progress_dialog);
        progressDialog.setContentView(R.layout.dialog);
        progressDialog.setCancelable(true);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView msg = (TextView) progressDialog.findViewById(R.id.progressTextView);
        msg.setText("卖力加载中");
        progressDialog.show();
    }

    public void hideDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
