package com.ex.Dao;

import com.ex.Models.AssignmentEntity;
import com.ex.Models.ClassStudentEntity;
import com.ex.Models.ClazzEntity;
import com.ex.Models.UsersEntity;
import com.ex.Services.SessionFactoryHelper;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

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
        List<ClazzEntity> allClasses;
        List<ClassStudentEntity> classStudent;

        Session session = sessionFactory.getSessionFactory().openSession();
        UsersEntity user = new UsersEntity();
        Transaction tx = null;
        try {
            tx=session.beginTransaction();
            SQLQuery query = session.createSQLQuery("select * from users where id = ?");
            Query classQuery = session.createQuery("from ClazzEntity");
            Query cStudent = session.createQuery("from ClassStudentEntity");
            allClasses = classQuery.list();
            classStudent = cStudent.list();
            query.setInteger(0, studentID);

            List<Object[]> studentData = query.list();
            for (Object[] row : studentData) {
                user.setId(Integer.parseInt(row[0].toString()));
                user.setFirstName(row[1].toString());
                user.setLastName(row[2].toString());
                user.setPassword(row[3].toString());
                user.setType(row[4].toString());
            }
            for(int i = 0; i < classStudent.size(); i++)
            {
                if(classStudent.get(i).getStudentId().equals(user.getId()))
                {
                    int pinner = classStudent.get(i).getClassId();

                    for(int j = 0; j < allClasses.size(); j++)
                    {
                        if(pinner == allClasses.get(j).getId())
                        {
                            list.add(allClasses.get(j));
                        }
                    }
                }
            }
            for(int k = 0; k < list.size(); k++) {
                System.out.println(list.get(k).getClassName());
            }
            tx.commit();
            if (!studentData.isEmpty()) {
                return list;
            }
        } catch(HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public ArrayList<AssignmentEntity> getAssignmentsForStudentPerClass(int clazzID, int studentID) {
        Session session = s.getSessionFactory().openSession();
        AssignmentEntity assignment = new AssignmentEntity();
        ArrayList<AssignmentEntity> list = new ArrayList<>();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

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
            tx.commit();
            if (!list.isEmpty()) {
                return list;
            }
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

}
