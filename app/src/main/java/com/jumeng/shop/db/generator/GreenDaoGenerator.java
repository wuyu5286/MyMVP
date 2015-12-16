package com.jumeng.shop.db.generator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

/**
 * ============================================================
 * 描 述 :
 * 作 者 : 鸿浩
 * 时 间 : 2015/12/10.
 * ============================================================
 */
public class GreenDaoGenerator {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.jumeng.shop.db.model");//创建的数据库的版本号以及默认的java package,如果不修改默认的包名，生成的dao和model都会在该包下
        schema.setDefaultJavaPackageDao("com.jumeng.shop.db.dao");
        schema.enableKeepSectionsByDefault();
        addEntity(schema);
        new DaoGenerator().generateAll(schema, "./app/src/main/java");
    }

    private static void addEntity(Schema schema) {
        Entity person = schema.addEntity("Person");
        person.addIdProperty().primaryKey();
        person.addStringProperty("name");
        person.addDoubleProperty("height");
        person.addDoubleProperty("weight");

        Entity card = schema.addEntity("Card");
        card.addIdProperty().primaryKey();
        card.addStringProperty("num");
        card.addStringProperty("address");

        Property idcardPK = person.addLongProperty("cardId").getProperty();
        person.addToOne(card, idcardPK);

        Property personPK = card.addLongProperty("personId").getProperty();
        card.addToOne(person, personPK);
    }
}
