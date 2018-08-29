package com.tourism.strategy.tourism_strategy.utils;

import com.tourism.strategy.tourism_strategy.R;

public class StringUtils {

    public static String getTitleByType(int type) {
        switch (type) {
            case 0:
                return "概览\nOverview";
            case 1:
                return "出行须知\nNote";
            case 2:
                return "如何到达\nArrive";
            case 3:
                return "当地交通\nTraffic";
            case 7:
                return "美食\nFood";
            case 8:
                return "购物\nShopping";
            default:
                return "";

        }
    }

    public static int getIconByType(int type){
        switch (type) {
            case 0:
                return R.mipmap.icon_gailan;
            case 1:
                return R.mipmap.icon_chuxing;
            case 2:
                return R.mipmap.icon_daoda;
            case 3:
                return R.mipmap.icon_jiaotong;
            case 7:
                return R.mipmap.icon_meishi;
            case 8:
                return R.mipmap.icon_gouwu;
            default:
                return 0;

        }
    }
}
