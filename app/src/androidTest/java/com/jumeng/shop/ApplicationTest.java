package com.jumeng.shop;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.jumeng.shop.db.dao.CardDao;
import com.jumeng.shop.db.model.Card;
import com.jumeng.shop.db.model.Person;
import com.jumeng.shop.db.service.CardService;
import com.jumeng.shop.db.service.PersonService;
import com.jumeng.shop.db.util.DbCore;
import com.jumeng.shop.db.util.DbUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    private PersonService mPersonService;
    private CardService mCardService;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        DbCore.init(getContext());
        DbCore.enableQueryBuilderLog();
        mPersonService = DbUtil.getPersonService();
        mCardService = DbUtil.getCardService();
    }


    public void testSave(){
        Card c=new Card();
        c.setNum("3303241646813416463468");
        c.setAddress("杭州");

        mCardService.save(c);

        Person p=new Person();
        p.setName("张三");
        p.setHeight(178.00);
        p.setWeight(65.00);
        p.setCard(c);
        mPersonService.save(p);

        c.setPerson(p);
        mCardService.saveOrUpdate(c);

    }
    public void testSave1(){
        Card c=new Card();
        c.setNum("3303241646813416463468");
        c.setAddress("杭州");

        Card c1=new Card();
        c1.setNum("12121646813416463468");
        c1.setAddress("温州");

        mCardService.save(c, c1);

        c.setNum("11111");
        mCardService.saveOrUpdate(c, c1);

    }

    public void testSave2(){
        Card c=new Card();
        c.setNum("3303241646813416463468");
        c.setAddress("杭州");

        Card c1=new Card();
        c1.setNum("12121646813416463468");
        c1.setAddress("温州");

        List<Card> cards=new ArrayList<>();

        cards.add(c);
        cards.add(c1);

        mCardService.save(cards);

        c1.setNum("22222");

        mCardService.saveOrUpdate(cards);

    }

    public void testDelete(){
        Card c=new Card();
        c.setNum("333333333333333");
        c.setAddress("3333");

        mCardService.save(c);
        mCardService.delete(c);

        c=new Card();
        c.setNum("444444");
        c.setAddress("44444444");

        mCardService.save(c);
        mCardService.deleteByKey(c.getId());

    }

    public void testDelete1(){
        Card c=new Card();
        c.setNum("55555");
        c.setAddress("5555");

        Card c1=new Card();
        c1.setNum("666666");
        c1.setAddress("66666666");
        mCardService.save(c,c1);
        mCardService.delete(c, c1);

    }

    public void testDelete2(){
        Card c=new Card();
        c.setNum("55555");
        c.setAddress("5555");

        Card c1=new Card();
        c1.setNum("666666");
        c1.setAddress("66666666");
        List<Card> cards=new ArrayList<>();

        cards.add(c);
        cards.add(c1);

        mCardService.save(cards);
        mCardService.delete(cards);

    }

    public  void testDelete3(){
        mCardService.deleteAll();
    }

    public void testUpdate(){
        Card c=new Card();
        c.setNum("55555");
        c.setAddress("5555");

        mCardService.save(c);
        c.setNum("123456");
        mCardService.update(c);
    }
    public void testUpdate1(){
        Card c=new Card();
        c.setNum("55555");
        c.setAddress("5555");

        mCardService.save(c);
        c.setNum("123456");

        Card c1=new Card();
        c1.setNum("6666");
        c1.setAddress("66666");

        mCardService.save(c1);
        c1.setNum("654321");
        mCardService.update(c,c1);
    }

    public void testUpdate2(){
        Card c=new Card();
        c.setNum("aaaaa");
        c.setAddress("aaaaaaaaaa");

        mCardService.save(c);
        c.setNum("bbbbbbbbb");

        Card c1=new Card();
        c1.setNum("ccccc");
        c1.setAddress("cccccccc");

        mCardService.save(c1);
        c1.setNum("dddddddddd");

        List<Card> cards=new ArrayList<Card>();

        cards.add(c);
        cards.add(c1);
        mCardService.update(cards);
    }

    public void testQuery(){
        Card c=new Card();
        c.setNum("aaaaa111");
        c.setAddress("aaaaaaaa11111aa");

        mCardService.save(c);

        List<Card> cards = mCardService.queryAll();
        Log.e("TAG", cards + "");

        Card query = mCardService.query(c.getId());
        Log.e("TAG", query + "");

        List<Card> query1 = mCardService.query("where NUM=?", c.getNum());
        Log.e("TAG", query1 + "");

        long count = mCardService.count();
        Log.e("TAG", count + "");

        List<Card> list = mCardService.queryBuilder().where(CardDao.Properties.Num.eq(c.getNum())).list();
        Log.e("TAG", list + "");
    }
}