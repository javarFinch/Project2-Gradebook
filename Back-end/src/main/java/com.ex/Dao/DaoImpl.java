package com.ex.Dao;

import com.ex.Models.AssignmentEntity;
import com.ex.Models.ClassStudentEntity;
import com.ex.Models.ClazzEntity;
import com.ex.Models.UsersEntity;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DaoImpl implements Dao {

    SessionFactory sessionFactory;

    @Autowired
    public DaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UsersEntity logIn(int ID, String password) {

        Session session = sessionFactory.getCurrentSession();
        UsersEntity user = new UsersEntity();

        SQLQuery query = session.createSQLQuery("select * from users where id = ? and password = ?");
        query.setInteger(0, ID);
        query.setString(1, password);
        List<Object[]> studentData = query.list();

        for (Object[] row : studentData) {
            user.setId(Integer.parseInt(row[0].toString()));
            user.setFirstName(row[1].toString());
            user.setLastName(row[2].toString());
            user.setPassword(row[3].toString());
            user.setType(row[4].toString());
        }
        if (!studentData.isEmpty()) {
            return user;
        }
        return null;
    }

    public ArrayList<ClazzEntity> getClassForStudent(int studentID) {

        ArrayList<ClazzEntity> list = new ArrayList<>();
        ClazzEntity clazz = new ClazzEntity();
        List<ClazzEntity> allClasses;
        List<ClassStudentEntity> classStudent;

        Session session = sessionFactory.getCurrentSession();
        UsersEntity user = new UsersEntity();

        SQLQuery query1 = session.createSQLQuery("select class_id from class_student where student_id = ?");

        query1.setInteger(0, studentID);
        List<Integer> classIds = query1.list();

        for (int i : classIds) {
            SQLQuery query2 = session.createSQLQuery("select * from class where id = ?");
            query2.setInteger(0, i);

            List<Object[]> classData = query2.list();
            for (Object [] row : classData) {
                clazz.setId(Integer.parseInt(row[0].toString()));
                clazz.setClassName(row[1].toString());
                clazz.setClassSubject(row[2].toString());
                clazz.setTeacherId(Integer.parseInt(row[3].toString()));
                clazz.setTestWeight(Integer.parseInt(row[4].toString()));
                clazz.setQuizWeight(Integer.parseInt(row[5].toString()));
                clazz.setHomeworkWeight(Integer.parseInt(row[6].toString()));
                clazz.setParticipationWeight(Integer.parseInt(row[7].toString()));
                list.add(clazz);
            }
        }
        if (!list.isEmpty()) {
            return list;
        }
        return null;
    }

    public ArrayList<AssignmentEntity> getAssignmentsForStudentPerClass(int clazzID, int studentID) {

        Session session = sessionFactory.getCurrentSession();
        AssignmentEntity assignment = new AssignmentEntity();
        ArrayList<AssignmentEntity> list = new ArrayList<>();

        SQLQuery query1 = session.createSQLQuery("select pair_id from class_student where class_id = ? and student_id = ?");

        query1.setInteger(0, clazzID);
        query1.setInteger(1, studentID);
        int pairId = Integer.parseInt(query1.list().get(0).toString());

        SQLQuery query2 = session.createSQLQuery("select * from assignment where pair_id = " + pairId);
        List<Object[]> assignmentData = query2.list();

        for (Object[] row : assignmentData) {
            assignment.setId(Integer.parseInt(row[0].toString()));
            assignment.setAssignmentName(row[1].toString());
            assignment.setAssignmentType(row[2].toString());
            assignment.setActualPoints(Integer.parseInt(row[3].toString()));
            assignment.setTotalPoints(Integer.parseInt(row[4].toString()));
            assignment.setDueDate(row[5].toString());
            assignment.setPairId(Integer.parseInt(row[6].toString()));
            list.add(assignment);
        }
        if (!list.isEmpty()) {
            return list;
        }
        return null;
    }

}
