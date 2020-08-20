package com.ex.Dao;

import com.ex.Models.AssignmentEntity;
import com.ex.Models.ClassStudentEntity;
import com.ex.Models.ClazzEntity;
import com.ex.Models.UsersEntity;
import com.ex.Services.SessionFactoryHelper;
import org.hibernate.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DaoImpl implements Dao {

    SessionFactoryHelper s = new SessionFactoryHelper();

    public UsersEntity logIn(int ID, String password) {
        Session session = s.getSessionFactory().openSession();
        UsersEntity user = new UsersEntity();
        Transaction tx = null;
        try {
            tx=session.beginTransaction();
            SQLQuery query = null;
                    query = session.createSQLQuery("select * from users where id = ? and password = ?");

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
            tx.commit();
            if (!studentData.isEmpty()) {
                return user;
            }
        } catch(HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public ArrayList<ClazzEntity> getClassForStudent(int studentID) {
        ArrayList<ClazzEntity> list = new ArrayList<>();
        List<ClazzEntity> allClasses = new ArrayList<>();
        List<ClassStudentEntity> classStudent = new ArrayList<>();

        Session session = s.getSessionFactory().openSession();
        UsersEntity user = new UsersEntity();
        Transaction tx = null;
        try {
            tx=session.beginTransaction();
            SQLQuery query = null;
            Query classQuery = null;
            Query cStudent = null;
            query = session.createSQLQuery("select * from users where id = ?");
            classQuery = session.createQuery("from ClazzEntity");
            cStudent = session.createQuery("from ClassStudentEntity");
            allClasses = classQuery.list();
            classStudent = cStudent.list();
            query.setInteger(0, studentID);
           // System.out.println(classStudent.get(0).getClassId());

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
        ArrayList<AssignmentEntity> list = new ArrayList<>();
        return null;
    }

}
