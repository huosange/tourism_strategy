package com.tourism.strategy.tourism_strategy.greendao.manager;

import android.content.Context;
import com.tourism.strategy.tourism_strategy.greendao.DaoMaster;
import org.greenrobot.greendao.database.Database;

public class DBHelper extends DaoMaster.DevOpenHelper{

    public static final String DB_NAME="tourism.db";

    public DBHelper(Context context){
        super(context,DB_NAME,null);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        //目前只是简单的删除表，再创建表
        super.onUpgrade(db, oldVersion, newVersion);
    }
}
