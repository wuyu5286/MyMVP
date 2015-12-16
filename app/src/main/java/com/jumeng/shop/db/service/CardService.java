package com.jumeng.shop.db.service;

import com.jumeng.shop.db.model.Card;

import de.greenrobot.dao.AbstractDao;

/**
 * ============================================================
 * 描 述 :
 * 作 者 : 鸿浩
 * 时 间 : 2015/12/10.
 * ============================================================
 */
public class CardService extends BaseService<Card,Long> {
    public CardService(AbstractDao dao) {
        super(dao);
    }
}
