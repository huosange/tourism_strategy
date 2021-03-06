package com.tourism.strategy.tourism_strategy.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

    private static Toast toast=null;

    /**
     * 显示Toast
     */
    public static void showText(Context context, String text) {
        if (toast == null) {
            toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
        toast.setText(text);
        toast.show();
    }
}
