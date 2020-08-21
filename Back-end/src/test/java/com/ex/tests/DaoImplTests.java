package com.ex.tests;

import com.ex.Dao.Dao;
import com.ex.Dao.DaoImpl;
import com.ex.Models.UsersEntity;
import org.hibernate.SessionFactory;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DaoImplTests {

    @Autowired
    static SessionFactory sessionFactory;

    static Dao dao;

    @BeforeClass
    public static void setup() {
        System.out.println("This is the setup for the DaoImplTests");
        dao = new DaoImpl(sessionFactory);
        System.out.println(dao.toString());
        dao.createUser(99, "test", "test", "test", "admin");
    }

    @AfterClass
    public static void cleanup() {
        System.out.println("This is the cleanup for the DaoImplTests");
        dao.deleteUser(99);
    }

    @Test
    public void logInTest() {
        UsersEntity a1 = dao.logIn(99, "test");
        Assert.assertNotNull(a1);

        UsersEntity a2 = dao.logIn(65748, "IDontExist");
        Assert.assertNull(a2);
    }

}
