package com.ex.tests;

import com.ex.Dao.Dao;
import com.ex.Models.APIThrowaways.*;
import com.ex.Models.AssignmentEntity;
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
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.transaction.TransactionScoped;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
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
        ArrayList<StudentGrade> gradeList1 = new ArrayList<>();
        ArrayList<StudentGrade> gradeList2 = new ArrayList<>();
        TeacherAssignment ts1 = new TeacherAssignment();
        TeacherAssignment ts2 = new TeacherAssignment();
        StudentGrade sg1 = new StudentGrade();
        StudentGrade sg2 = new StudentGrade();
        StudentGrade sg3 = new StudentGrade();
        StudentGrade sg4 = new StudentGrade();

        ts1.setAssignmentName("test");
        ts1.setAssignmentType("test");
        ts1.setTotalPoints(100);
        ts1.setDueDate("28-08-2020");
        sg1.setStudentID(Integer.parseInt(student1[0]));
        sg1.setFirstName("test");
        sg1.setLastName("test");
        sg1.setPoints(80);
        sg2.setStudentID(Integer.parseInt(student1[0]));
        sg2.setFirstName("test");
        sg2.setLastName("test");
        sg2.setPoints(50);

        ts2.setAssignmentName("quiz");
        ts2.setAssignmentType("quiz");
        ts2.setTotalPoints(75);
        ts2.setDueDate("28-08-2020");
        sg3.setStudentID(Integer.parseInt(student2[0]));
        sg3.setFirstName("test");
        sg3.setLastName("test");
        sg3.setPoints(70);
        sg4.setStudentID(Integer.parseInt(student2[0]));
        sg4.setFirstName("test");
        sg4.setLastName("test");
        sg4.setPoints(60);
        gradeList1.add(sg1);
        gradeList1.add(sg3);
        gradeList2.add(sg2);
        gradeList2.add(sg4);

        ArrayList<TeacherAssignment> assignmentList = new ArrayList<>();

        ts1.setGradeList(gradeList1);
        ts2.setGradeList(gradeList2);

        assignmentList.add(ts1);
        assignmentList.add(ts2);

        Assert.assertEquals(assignmentList.get(0).getGradeList().get(0).getPoints(), dao.getAssignmentListByClassID(clazz.getId()).get(0).getGradeList().get(0).getPoints(), 0);
        Assert.assertEquals(assignmentList.get(1).getGradeList().get(0).getPoints(), dao.getAssignmentListByClassID(clazz.getId()).get(1).getGradeList().get(0).getPoints(), 0);
        Assert.assertEquals(assignmentList.get(0).getGradeList().get(1).getPoints(), dao.getAssignmentListByClassID(clazz.getId()).get(0).getGradeList().get(1).getPoints(), 0);
        Assert.assertEquals(assignmentList.get(1).getGradeList().get(1).getPoints(), dao.getAssignmentListByClassID(clazz.getId()).get(1).getGradeList().get(1).getPoints(), 0);
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
        Assert.assertEquals(3.02, dao.studentGPA(Integer.parseInt(student1[0])), 0);
    }

    @Test
    @Transactional
    @Rollback
    public void getStudentListTest() {
//        ArrayList<StudentList> list = dao.getStudentList();
//        for (int i=0; i<list.size(); i++) {
//            System.out.println("StudentList: "+list.get(i).getId());
//            System.out.println("StudentList: "+list.get(i).getGpa());
//            System.out.println("StudentList: "+list.get(i).getNumberClasses());
//            System.out.println("StudentList: "+list.get(i).getfName());
//        }
        Assert.assertNotNull(dao.getStudentList());
    }

    @Test
    @Transactional
    @Rollback
    public void getTeacherListTest() {
//        ArrayList<TeacherList> list = dao.getTeacherList();
//        for (int i=0; i<list.size(); i++) {
//            System.out.println("TeacherList: "+list.get(i).getId());
//            System.out.println("TeacherList: "+list.get(i).getfName());
//            System.out.println("TeacherList: "+list.get(i).getNumberClasses());
//        }
        Assert.assertNotNull(dao.getTeacherList());
    }

    @Test
    @Transactional
    @Rollback
    public void getClassListTest() {
//        ArrayList<AllClasses> list = dao.getClassList();
//        for (int i=0; i<list.size(); i++) {
//            System.out.println("ClassList: "+list.get(i).getName());
//            System.out.println("ClassList: "+list.get(i).getSubject());
//            System.out.println("ClassList: "+list.get(i).getTeacherName());
//            System.out.println("ClassList: "+list.get(i).getNumberStudents());
//        }
        Assert.assertNotNull(dao.getClassList());
    }
}
