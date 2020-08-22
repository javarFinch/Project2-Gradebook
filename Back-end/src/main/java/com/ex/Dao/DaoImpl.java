package com.ex.Dao;

import com.ex.Models.AssignmentEntity;
import com.ex.Models.ClazzEntity;
import com.ex.Models.UsersEntity;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class DaoImpl implements Dao {

    SessionFactory sessionFactory;

    @Autowired
    public DaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UsersEntity logIn(int ID, String password) {

        System.out.println(sessionFactory.toString());

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

        Session session = sessionFactory.getCurrentSession();
        UsersEntity user = new UsersEntity();

        SQLQuery query1 = session.createSQLQuery("select class_id from class_student where student_id = ?");

        query1.setInteger(0, studentID);
        List<Integer> classIds = query1.list();

        System.out.println(classIds.get(0));
        System.out.println(classIds.get(1));

        for (int i=0; i<classIds.size(); i++) {
            SQLQuery query2 = session.createSQLQuery("select * from class where id = ?");
            query2.setInteger(0, classIds.get(i));

            List<Object[]> classData = query2.list();
            for (Object [] row : classData) {
                ClazzEntity clazz = new ClazzEntity();
                clazz.setId(Integer.parseInt(row[0].toString()));
                clazz.setClassName(row[1].toString());
                clazz.setClassSubject(row[2].toString());
                clazz.setTeacherId(Integer.parseInt(row[3].toString()));
                clazz.setTestWeight(Integer.parseInt(row[4].toString()));
                clazz.setQuizWeight(Integer.parseInt(row[5].toString()));
                clazz.setHomeworkWeight(Integer.parseInt(row[6].toString()));
                clazz.setParticipationWeight(Integer.parseInt(row[7].toString()));
                list.add(clazz);
                System.out.println(clazz.getId());
            }
        }
        if (!list.isEmpty()) {
            return list;
        }
        return null;
    }

    public ArrayList<AssignmentEntity> getAssignmentsForStudentPerClass(int clazzID, int studentID) {

        Session session = sessionFactory.getCurrentSession();
        ArrayList<AssignmentEntity> list = new ArrayList<>();

        SQLQuery query1 = session.createSQLQuery("select pair_id from class_student where class_id = ? and student_id = ?");

        query1.setInteger(0, clazzID);
        query1.setInteger(1, studentID);
        int pairId = Integer.parseInt(query1.list().get(0).toString());

        SQLQuery query2 = session.createSQLQuery("select * from assignment where pair_id = ?");
        query2.setInteger(0, pairId);
        List<Object[]> assignmentData = query2.list();

        for (Object[] row : assignmentData) {
            AssignmentEntity assignment = new AssignmentEntity();
            assignment.setId(Integer.parseInt(row[0].toString()));
            assignment.setAssignmentName(row[1].toString());
            assignment.setAssignmentType(row[2].toString());
            if (row[3] == null) {
                assignment.setActualPoints(0);
            } else {
                assignment.setActualPoints(Integer.parseInt(row[3].toString()));
            }
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

    public String[] createUser() {
        Session session = sessionFactory.getCurrentSession();

        String pw = RandomStringUtils.randomAlphanumeric(8);

        SQLQuery query1 = session.createSQLQuery("insert into users (first_name, last_name, password, type) " +
                "values (null, null, ?, null)");
        query1.setString(0, pw);

        if (query1.executeUpdate()!=0) {
            SQLQuery query2 = session.createSQLQuery("select id from users where password = ?");
            query2.setString(0, pw);
            List<Integer> ID = query2.list();
            if (!ID.isEmpty()) {
                int userID = ID.get(0);
                String[] ret = new String[] {Integer.toString(userID), pw};
                return ret;
            }
        }
        return null;
    }

    public UsersEntity updateUser(int ID, String firstName, String lastName, String password, String type) {
        Session session = sessionFactory.getCurrentSession();

        UsersEntity user = new UsersEntity();

        SQLQuery query = session.createSQLQuery("update users set first_name = ?, last_name = ?, type = ? where id = ? and password = ?");
        query.setString(0, firstName);
        query.setString(1, lastName);
        query.setString(2, type);
        query.setInteger(3, ID);
        query.setString(4, password);

        if (query.executeUpdate()!=0) {
            user.setId(ID);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setType(type);
            user.setPassword(password);
            return user;
        }
        return null;
    }

    public String getTeacherName(int ID) {
        Session session = sessionFactory.getCurrentSession();

        SQLQuery query = session.createSQLQuery("Select first_name, last_name from users where " +
                "id = ?");
        query.setInteger(0, ID);

        List<Object[]> name = query.list();
        if (name.isEmpty()) {
            return null;
        }
        for (Object[] row : name) {
            String s1 = row[0].toString();
            String s2 = row[1].toString();
            String s3 = s1 + " " + s2;
            return s3;
        }
        return null;
    }

    public boolean updatePassword (String oldPassword, String newPassword) {
        Session session = sessionFactory.getCurrentSession();

        SQLQuery query = session.createSQLQuery("update users set password = ? where password = ?");
        query.setString(0, newPassword);
        query.setString(1, oldPassword);

        if (query.executeUpdate()!=0) {
            return true;
        }
        return false;
    }

    public boolean deleteUser(int ID) {
        Session session = sessionFactory.getCurrentSession();

        SQLQuery query = session.createSQLQuery("delete from users where id = ?");
        query.setInteger(0, ID);

        if (query.executeUpdate()!=0) {
            return true;
        }
        return false;
    }

    public int getPairID(int classID, int studentID) {
        Session session = sessionFactory.getCurrentSession();

        SQLQuery query = session.createSQLQuery("select pair_id from class_student where class_id = ? " +
                "and student_id = ?");
        query.setInteger(0, classID);
        query.setInteger(1, studentID);

        List<Integer> pairID = query.list();
        if (pairID.isEmpty()) {
            return -1;
        }
        return pairID.get(0);
    }

    public int assignmentTypeGrade(int pairID, String type) {
        Session session = sessionFactory.getCurrentSession();

        SQLQuery query = session.createSQLQuery("select actual_points from assignment where pair_id = ? and assignment_type = ? and actual_points is not null");
        query.setInteger(0, pairID);
        query.setString(1, type);
        List<Integer> grades = query.list();
        int sum = 0;
        for (int i : grades) {
            sum += i;
        }
        if(grades.isEmpty()) {
            return -1;
        }
        return (sum)/(grades.size());
    }

    public ClazzEntity getClazzById(int ID) {
        Session session = sessionFactory.getCurrentSession();
        ClazzEntity clazz = new ClazzEntity();

        SQLQuery query = session.createSQLQuery("select * from class where id = ?");
        query.setInteger(0, ID);

        List<Object[]> clazzData = query.list();
        if (clazzData.isEmpty()) {
            return null;
        }
        for (Object[] row : clazzData) {
            clazz.setId(Integer.parseInt(row[0].toString()));
            clazz.setClassName(row[1].toString());
            clazz.setClassSubject(row[2].toString());
            clazz.setTeacherId(Integer.parseInt(row[3].toString()));
            clazz.setTestWeight(Integer.parseInt(row[4].toString()));
            clazz.setQuizWeight(Integer.parseInt(row[5].toString()));
            clazz.setHomeworkWeight(Integer.parseInt(row[6].toString()));
            clazz.setParticipationWeight(Integer.parseInt(row[7].toString()));
        }
        return clazz;
    }

    public int overAllGrade(int pairID) {
        Session session = sessionFactory.getCurrentSession();

        SQLQuery query1 = session.createSQLQuery("select class_id from class_student where pair_id = ?");
        query1.setInteger(0, pairID);
        List<Integer> class_id = query1.list();
        if (class_id.isEmpty()) {
            return -1;
        }

        ClazzEntity clazz = getClazzById(class_id.get(0));

        int testW = clazz.getTestWeight();
        int quizW = clazz.getQuizWeight();
        int homeworkW = clazz.getHomeworkWeight();
        int participationW = clazz.getParticipationWeight();

        SQLQuery query = session.createSQLQuery("select actual_points, total_points, assignment_type from assignment where pair_id = ? and actual_points is not null");
        query.setInteger(0, pairID);

        List<String[]> gradeInfo = query.list();
        Map<String, Integer[]> map;
        int ta = 0, tt = 0;
        int qa = 0, qt = 0;
        int ha = 0, ht = 0;
        int pa = 0, pt = 0;
        for (String[] row : gradeInfo) {
            if (row[2].equalsIgnoreCase("Test")) {
                ta = ta + Integer.parseInt(row[0]);
                tt = tt + Integer.parseInt(row[1]);
            } else if (row[2].equalsIgnoreCase("Quiz")) {
                qa = qa + Integer.parseInt(row[0]);
                qt = qt + Integer.parseInt(row[1]);
            } else if (row[2].equalsIgnoreCase("Homework")) {
                ha = ha + Integer.parseInt(row[0]);
                ht = ht + Integer.parseInt(row[1]);
            } else if (row[2].equalsIgnoreCase("Participation")) {
                pa = pa + Integer.parseInt(row[0]);
                pt = pt + Integer.parseInt(row[1]);
            }
        }
        int tGrade = ta/tt;
        int qGrade = qa/qt;
        int hGrade = ha/ht;
        int pGrade = pa/pt;

        int overAll =  (tGrade*testW + qGrade*quizW + hGrade*homeworkW + pGrade*participationW)/100;
        return overAll;
    }

}
