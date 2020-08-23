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
import java.util.HashMap;
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

        SQLQuery query1 = session.createSQLQuery("select class_id from class_student where student_id = ?");

        query1.setInteger(0, studentID);
        List<Integer> classIds = query1.list();

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

    public double overAllGrade(int pairID) {
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

        List<Object[]> gradeInfo = query.list();
        int ta = 0, tt = 0;
        int qa = 0, qt = 0;
        int ha = 0, ht = 0;
        int pa = 0, pt = 0;
        boolean[] check = new boolean[4];
        check [0] = false;
        check [1] = false;
        check [2] = false;
        check [3] = false;

        for (Object[] row : gradeInfo) {
            if (row[2].toString().equalsIgnoreCase("Test")) {
                check[0] = true;
                ta = ta + Integer.parseInt(row[0].toString());
                tt = tt + Integer.parseInt(row[1].toString());
            } else if (row[2].toString().equalsIgnoreCase("Quiz")) {
                check[1] = true;
                qa = qa + Integer.parseInt(row[0].toString());
                qt = qt + Integer.parseInt(row[1].toString());
            } else if (row[2].toString().equalsIgnoreCase("Homework")) {
                check[2] = true;
                ha = ha + Integer.parseInt(row[0].toString());
                ht = ht + Integer.parseInt(row[1].toString());
            } else if (row[2].toString().equalsIgnoreCase("Participation")) {
                check[3] = true;
                pa = pa + Integer.parseInt(row[0].toString());
                pt = pt + Integer.parseInt(row[1].toString());
            }
        }

        double overAll = 0;
        int weight = 0;
        if (check[0]) {
            overAll+=(((double)ta)/tt)*testW;
            weight+=testW;
        }
        if (check[1]) {
            overAll+=(((double)qa)/qt)*quizW;
            weight+=quizW;
        }
        if (check[2]) {
            overAll+=(((double)ha)/ht)*homeworkW;
            weight+=homeworkW;
        }
        if (check[3]) {
            overAll+=(((double)pa)/pt)*participationW;
            weight+=participationW;
        }

        return (overAll/weight)*100;
    }

    @Override
    public ArrayList<Map<String, Object>> getAssignmentListByClassID(int id) {
        Session session = sessionFactory.getCurrentSession();

        SQLQuery query1 = session.createSQLQuery("select distinct assignment_name, assignment_type, total_points,due_date from public.assignment join class_student on assignment.pair_id = class_student.pair_id where class_id = ?;");
        query1.setInteger(0, id);
        List<Object[]> assignment_info = query1.list();
        if (assignment_info.isEmpty()) {
            return null;
        }
        ArrayList<Map<String,Object>> assignmentList = new ArrayList<>();
        for (Object[] assignment : assignment_info) {
            Map<String,Object> assignment_object = new HashMap<>();
            //put assignment details into object
            assignment_object.put("assignmentName",(String)assignment[0]);
            System.out.println(assignment[0]);
            assignment_object.put("assignmentType",(String)assignment[1]);
            assignment_object.put("totalPoints",(int)assignment[2]);
            assignment_object.put("dueDate",(String)assignment[3]);


            //for each assignment create the list of students and their grade
            SQLQuery query2 = session.createSQLQuery("select users.id, users.first_name, users.last_name, assignment.actual_points from public.assignment join class_student on assignment.pair_id = class_student.pair_id " +
                                                        "join users on class_student.student_id = users.id where class_id=? and assignment.assignment_name=?");
            query2.setInteger(0,id);
            query2.setString(1, (String) assignment[0]);
            List<Object[]> studentList= query2.list();

            ArrayList<Map<String,Object>> gradeList = new ArrayList<>();
            for(Object[] student:studentList){
                System.out.println("\""+student[0]+"\"");
                Map<String,Object> grade = new HashMap<>();
                grade.put("studentID",(int)student[0]);
                grade.put("firstName",(String)student[1]);
                grade.put("lastName",(String)student[2]);
                if(student[3]==null){
                    grade.put("points",-1);
                }else{
                    grade.put("points",(int)student[3]);
                }

                gradeList.add(grade);
            }

            assignment_object.put("gradeList",gradeList);
            assignmentList.add(assignment_object);

        }
        if(assignmentList.isEmpty()){
            return null;
        }else{
            return assignmentList;
        }

    }


    @Override
    public ArrayList<ClazzEntity> getClassForTeacher(int teacherID) {
        ArrayList<ClazzEntity> list = new ArrayList<>();

        Session session = sessionFactory.getCurrentSession();

        SQLQuery query2 = session.createSQLQuery("select * from class where teacher_id = ?");
        query2.setInteger(0, teacherID);

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
        }
        if (!list.isEmpty()) {
            return list;
        }
        return null;
    }
}
