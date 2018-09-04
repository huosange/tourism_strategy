package com.tourism.strategy.tourism_strategy.greendao.manager;

import com.tourism.strategy.tourism_strategy.greendao.ConsumeDao;

public class EntityManager {

    private static EntityManager entityManager;
    private ConsumeDao consumeDao;

    private EntityManager() {
    }

    public static EntityManager getInstance() {
        if (entityManager == null) {
            synchronized (EntityManager.class) {
                if (entityManager == null) {
                    entityManager = new EntityManager();
                }
            }
        }
        return entityManager;
    }

    public ConsumeDao getConsumeDao() {
        consumeDao = DBManager.getInstance().getDaoSession().getConsumeDao();
        return consumeDao;
    }
}
