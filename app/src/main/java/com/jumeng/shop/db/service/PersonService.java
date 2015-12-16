package com.jumeng.shop.db.service;

import com.jumeng.shop.db.model.Person;

import de.greenrobot.dao.AbstractDao;

/**
 * ============================================================
 * 描 述 :
 * 作 者 : 鸿浩
 * 时 间 : 2015/12/10.
 * ============================================================
 */
public class PersonService extends BaseService<Person, Long> {
    public PersonService(AbstractDao dao) {
        super(dao);
    }
}
