package com.ex.tests;

import com.ex.Dao.Dao;
import com.ex.Models.ClazzEntity;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.transaction.TransactionScoped;


@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DaoImplTests {

    @Autowired
    private Dao dao;

    @Test
    @Transactional
    @Rollback
    public void loginTest() {
        Assert.assertNotNull(dao.logIn(1002, "password"));
        Assert.assertNull(dao.logIn(0000, "doesnotexist"));
    }

    @Test
    @Transactional
    @Rollback
    public void getClassForStudentTest() {
        Assert.assertNotNull(dao.getClassForStudent(1002));
        Assert.assertNull(dao.getClassForStudent(0000));
    }

    @Test
    @Transactional
    @Rollback
    public void getAssignmentsForStudentPerClassTest() {
        Assert.assertNotNull(dao.getAssignmentListByClassID(2));
        Assert.assertNull(dao.getAssignmentListByClassID(0000));
    }

    @Test
    @Transactional
    @Rollback
    public void createUserTest() {
        Assert.assertNotNull(dao.createUser());
    }

    @Test(expected = ConstraintViolationException.class)
    @Transactional
    @Rollback
    public void createClassTest() {
        String[] usernamePassword = dao.createUser();
        Assert.assertNotNull(dao.createClass("testclass", "math", Integer.parseInt(usernamePassword[0])));
        dao.createClass("testclass", "math", 0000);
    }

    @Test
    @Transactional
    @Rollback
    public void updateUserTest() {
        String[] usernamePassword = dao.createUser();
        Assert.assertNotNull(dao.updateUser(Integer.parseInt(usernamePassword[0]), "test", "test", usernamePassword[1], "student"));
        Assert.assertNull(dao.updateUser(0000, "test", "test", "doesnotexist", "student"));
    }

    @Test
    @Transactional
    @Rollback
    public void getTeacherNameTest() {
        String[] usernamePassword = dao.createUser();
        Assert.assertNotNull(dao.updateUser(Integer.parseInt(usernamePassword[0]), "test", "test", usernamePassword[1], "teacher"));
        String actual = dao.getTeacherName(Integer.parseInt(usernamePassword[0]));
        Assert.assertEquals("test test", actual);
    }

//    @Test
//    @Transactional
//    @Rollback
//    public void updatePasswordTest() {
//        String[] usernamePassword = dao.createUser();
//        Assert.assertNotNull(dao.updateUser(Integer.parseInt(usernamePassword[0]), "test", "test", usernamePassword[1], "teacher"));
//        Assert.assertTrue(dao.updatePassword(usernamePassword[1], "newpassword"));
//    }

    @Test
    @Transactional
    @Rollback
    public void deleteUserTest() {
        String[] username = dao.createUser();
        Assert.assertTrue(dao.deleteUser(Integer.parseInt(username[0])));
    }

//    @Test
//    @Transactional
//    @Rollback
//    public void getPairIDTest() {
//        Assert.assertNotNull(dao.getPairID(2, 1002));
//    }
//
//    @Test
//    @Transactional
//    @Rollback
//    public void assignmentTypeGradeTest() {
//
//    }
//
//    @Test
//    @Transactional
//    @Rollback
//    public void getClazzByIdTest() {
//
//    }
//
//    @Test
//    @Transactional
//    @Rollback
//    public void overAllGradeTest() {
//
//    }
//
//    @Test
//    @Transactional
//    @Rollback
//    public void getAssignmentListByClassIDTest() {
//    }

    @Test
    @Transactional
    @Rollback
    public void getClassForTeacherTest() {
        Assert.assertNotNull(dao.getClassForTeacher(1001));
        Assert.assertNull(dao.getClassForTeacher(0000));
    }

    @Test
    @Transactional
    @Rollback
    public void updateGradeTest() {
        String[] usernamePassword = dao.createUser();
        String[] usernamePassword1 = dao.createUser();
        dao.updateUser(Integer.parseInt(usernamePassword[0]), "test", "test", usernamePassword[1], "teacher");
        dao.updateUser(Integer.parseInt(usernamePassword1[0]), "test", "test", usernamePassword1[1], "student");
        ClazzEntity clazz = dao.createClass("test", "test", Integer.parseInt(usernamePassword[0]));
        dao.assignStudent(clazz.getId(), Integer.parseInt(usernamePassword1[0]));
        dao.createAssignmentAndAssign("test", "quiz", 100, "28-08-2020 10:00 EST", clazz.getId());
        Assert.assertTrue(dao.updateGrade("test", "quiz", Integer.parseInt(usernamePassword1[0]), 80));
    }

    @Test
    @Transactional
    @Rollback
    public void createAssignmentAndAssignTest() {
        String[] usernamePassword = dao.createUser();
        String[] usernamePassword1 = dao.createUser();
        dao.updateUser(Integer.parseInt(usernamePassword[0]), "test", "test", usernamePassword[1], "teacher");
        dao.updateUser(Integer.parseInt(usernamePassword1[0]), "test", "test", usernamePassword1[1], "student");
        ClazzEntity clazz = dao.createClass("test", "test", Integer.parseInt(usernamePassword[0]));
        dao.assignStudent(clazz.getId(), Integer.parseInt(usernamePassword1[0]));
        Assert.assertTrue(dao.createAssignmentAndAssign("test", "math", 100, "28-08-2020 10:00 EST", clazz.getId()));
    }

}
