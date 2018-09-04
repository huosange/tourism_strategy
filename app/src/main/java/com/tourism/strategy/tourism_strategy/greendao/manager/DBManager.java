package com.tourism.strategy.tourism_strategy.greendao.manager;

import com.tourism.strategy.tourism_strategy.BaseApplication;
import com.tourism.strategy.tourism_strategy.greendao.DaoMaster;
import com.tourism.strategy.tourism_strategy.greendao.DaoSession;

public class DBManager {
    private DaoSession daoSession;
    private static DBManager dbManager;

    private DBManager() {
        DBHelper helper = new DBHelper(BaseApplication.getInstance());
        DaoMaster master = new DaoMaster(helper.getWritableDatabase());
        daoSession = master.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public static DBManager getInstance() {
        if (dbManager == null) {
            synchronized (DBManager.class) {
                if (dbManager == null) {
                    dbManager = new DBManager();
                }
            }
        }
        return dbManager;
    }
}
