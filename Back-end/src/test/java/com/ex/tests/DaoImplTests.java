package com.ex.tests;

import com.ex.Dao.Dao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

public class DaoImplTests {

    @Autowired
    private Dao dao;

    @Test
    @Transactional
    @Rollback(true)
    public void loginTest() {

    }

}
