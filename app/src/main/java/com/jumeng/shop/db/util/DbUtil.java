package com.jumeng.shop.db.util;

import com.jumeng.shop.db.dao.CardDao;
import com.jumeng.shop.db.dao.PersonDao;
import com.jumeng.shop.db.service.CardService;
import com.jumeng.shop.db.service.PersonService;

/**
 * ============================================================
 * 描 述 :
 * 作 者 : 鸿浩
 * 时 间 : 2015/12/10.
 * ============================================================
 */
public class DbUtil {
    private static CardService cardService;
    private static PersonService personService;

    private static PersonDao getPersonDao() {
        return DbCore.getDaoSession().getPersonDao();
    }

    private static CardDao getCardDao() {
        return DbCore.getDaoSession().getCardDao();
    }

    public static CardService getCardService() {
        if (cardService == null) {
            cardService = new CardService(getCardDao());
        }
        return cardService;
    }

    public static PersonService getPersonService() {
        if (personService == null) {
            personService = new PersonService(getPersonDao());
        }
        return personService;
    }
}
