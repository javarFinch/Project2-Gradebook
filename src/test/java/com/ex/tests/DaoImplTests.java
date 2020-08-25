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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DaoImplTests {

    @Autowired
    private Dao dao;
    static String[] student1;
    static String[] student2;
    static String[] teacher;
    static ClazzEntity clazz;


    @Before
    @Rollback
    public void setup() {
        student1 = dao.createUser();
        dao.updateUser(Integer.parseInt(student1[0]), "test", "test", student1[1], "student");
        student2 = dao.createUser();
        dao.updateUser(Integer.parseInt(student2[0]), "test", "test", student2[1], "student");
        teacher = dao.createUser();
        dao.updateUser(Integer.parseInt(teacher[0]), "test", "test", teacher[1], "teacher");
        clazz = dao.createClass("test", "test", Integer.parseInt(teacher[0]));
        dao.assignStudent(clazz.getId(), Integer.parseInt(student1[0]));
        dao.assignStudent(clazz.getId(), Integer.parseInt(student2[0]));
        dao.createAssignmentAndAssign("test", "test", 100, "28-08-2020", clazz.getId());
        dao.createAssignmentAndAssign("quiz", "quiz", 75, "28-08-2020", clazz.getId());
        dao.updateGrade("test", "test", Integer.parseInt(student1[0]), 80);
        dao.updateGrade("test", "test", Integer.parseInt(student2[0]), 70);
        dao.updateGrade("quiz", "quiz", Integer.parseInt(student1[0]), 50);
        dao.updateGrade("quiz", "quiz", Integer.parseInt(student2[0]), 60);
    }

    @Test
    @Transactional
    @Rollback
    public void loginTest() {
        Assert.assertNotNull(dao.logIn(Integer.parseInt(student1[0]), student1[1]));
        Assert.assertNull(dao.logIn(0000, "doesnotexist"));
    }

    @Test
    @Transactional
    @Rollback
    public void getClassForStudentTest() {
        Assert.assertNotNull(dao.getClassForStudent(Integer.parseInt(student1[0])));
        Assert.assertNull(dao.getClassForStudent(0000));
    }

    @Test
    @Transactional
    @Rollback
    public void getAssignmentsForStudentPerClassTest() {
        Assert.assertNotNull(dao.getAssignmentListByClassID(clazz.getId()));
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
        Assert.assertNotNull(dao.createClass("testclass", "math", Integer.parseInt(teacher[0])));
        dao.createClass("testclass", "math", 0000);
    }

    @Test
    @Transactional
    @Rollback
    public void updateUserTest() {
        Assert.assertNotNull(dao.updateUser(Integer.parseInt(student1[0]), "test", "test", student1[1], "student"));
        Assert.assertNull(dao.updateUser(0000, "test", "test", "doesnotexist", "student"));
    }

    @Test
    @Transactional
    @Rollback
    public void getTeacherNameTest() {
        String actual = dao.getTeacherName(Integer.parseInt(teacher[0]));
        Assert.assertEquals("test test", actual);
    }

    @Test
    @Transactional
    @Rollback
    public void updatePasswordTest() {
        Assert.assertTrue(dao.updatePassword(Integer.parseInt(student1[0]), "newpassword"));
        Assert.assertFalse(dao.updatePassword(0000, "doesnotexist"));
    }

    @Test
    @Transactional
    @Rollback
    public void deleteUserTest() {
        Assert.assertTrue(dao.deleteUser(Integer.parseInt(student1[0])));
    }


    @Test
    @Transactional
    @Rollback
    public void getClazzByIdTest() {
        Assert.assertNotNull(dao.getClazzById(clazz.getId()));
        Assert.assertNull(dao.getClazzById(0000));
    }

    @Test
    @Transactional
    @Rollback
    public void overAllGradeTest() {
        Assert.assertEquals(75.56, dao.overAllGrade(dao.getPairID(clazz.getId(), Integer.parseInt(student1[0]))), 0);
    }

    @Test
    @Transactional
    @Rollback
    public void getAssignmentListByClassIDTest() {
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        Map<String, Object> s1 = new HashMap<>();
        Map<String, Object> s2 = new HashMap<>();
        Map<String, Object> s3 = new HashMap<>();
        Map<String, Object> s4 = new HashMap<>();
        ArrayList<Map<String, Object>> gradeList1 = new ArrayList<>();
        ArrayList<Map<String, Object>> gradeList2 = new ArrayList<>();

        map1.put("assignmentName", "test");
        map1.put("assignmentType", "test");
        map1.put("totalPoints", 100);
        map1.put("dueDate", "28-08-2020");
        s1.put("studentID", Integer.parseInt(student1[0]));
        s1.put("firstName", "test");
        s1.put("lastName", "test");
        s1.put("points", 80);
        s2.put("studentID", Integer.parseInt(student1[0]));
        s2.put("firstName", "test");
        s2.put("lastName", "test");
        s2.put("points", 50);

        map2.put("assignmentName", "quiz");
        map2.put("assignmentType", "quiz");
        map2.put("totalPoints", 75);
        map2.put("dueDate", "28-08-2020");
        s3.put("studentID", Integer.parseInt(student2[0]));
        s3.put("firstName", "test");
        s3.put("lastName", "test");
        s3.put("points", 70);
        s4.put("studentID", Integer.parseInt(student2[0]));
        s4.put("firstName", "test");
        s4.put("lastName", "test");
        s4.put("points", 60);
        gradeList1.add(s1);
        gradeList1.add(s3);
        gradeList2.add(s2);
        gradeList2.add(s4);

        map1.put("gradeList", gradeList1);
        map2.put("gradeList", gradeList2);

        ArrayList<Map<String,Object>> assignmentList = new ArrayList<>();
        assignmentList.add(map1);
        assignmentList.add(map2);

        Assert.assertEquals(assignmentList, dao.getAssignmentListByClassID(clazz.getId()));
    }

    @Test
    @Transactional
    @Rollback
    public void getClassForTeacherTest() {
        ArrayList<ClazzEntity> c = new ArrayList<>();
        c.add(clazz);
        Assert.assertEquals(c, dao.getClassForTeacher(Integer.parseInt(teacher[0])));
        Assert.assertNull(dao.getClassForTeacher(0000));
    }

    @Test
    @Transactional
    @Rollback
    public void updateGradeTest() {

        Assert.assertTrue(dao.updateGrade("test", "test", Integer.parseInt(student1[0]), 85));
        Assert.assertFalse(dao.updateGrade("doesnotexist", "doesnotexist", 0000, 0));
    }

    @Test
    @Transactional
    @Rollback
    public void createAssignmentAndAssignTest() {
        Assert.assertTrue(dao.createAssignmentAndAssign("homework", "homework", 100, "28-08-2020", clazz.getId()));
    }

    @Test
    @Transactional
    @Rollback
    public void numberOfClassesForStudentTest() {
        Assert.assertEquals(1, dao.numberOfClassesForStudent(Integer.parseInt(student1[0])), 0);
    }

    @Test
    @Transactional
    @Rollback
    public void numberOfClassesForTeacherTest() {
        Assert.assertEquals(1, dao.numberOfClassesForTeacher(Integer.parseInt(teacher[0])), 0);
    }

    @Test
    @Transactional
    @Rollback
    public void numberOfStudentsInClassTest() {
        Assert.assertEquals(2, dao.numberOfStudentsInClass(clazz.getId()), 0);
    }

    @Test
    @Transactional
    @Rollback
    public void classAveragePerTypeTest() {
        Map<String, Double> expected = new HashMap<>();
        expected.put("test", 75.0);
        expected.put("quiz", 73.34);
        expected.put("homework", 0.0);
        expected.put("participation", 0.0);
        Assert.assertEquals(expected, dao.classAveragePerType(clazz.getId()));
    }

    @Test
    @Transactional
    @Rollback
    public void classAverageTest() {
        double s1 = dao.overAllGrade(dao.getPairID(clazz.getId(), Integer.parseInt(student1[0])));
        double s2 = dao.overAllGrade(dao.getPairID(clazz.getId(), Integer.parseInt(student2[0])));
        Assert.assertEquals(dao.format((s1+s2)/2), dao.classAverage(clazz.getId()), 0);
    }

    @Test
    @Transactional
    @Rollback
    public void studentGPATest() {
        Assert.assertEquals(2, dao.studentGPA(Integer.parseInt(student1[0])), 0);
    }

}
