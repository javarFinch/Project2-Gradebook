package com.ex.Dao;

import com.ex.Models.APIThrowaways.*;
import com.ex.Models.AssignmentEntity;
import com.ex.Models.ClazzEntity;
import com.ex.Models.UsersEntity;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DaoImpl implements Dao {

    SessionFactory sessionFactory;

    @Override
    public double format(double input) {
        DecimalFormat df = new DecimalFormat("0.00");
        return Double.parseDouble(df.format(input));
    }

    @Autowired
    public DaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
    public ClazzEntity createClass(String name, String subject, int teacherID) {
        Session session = sessionFactory.getCurrentSession();
        ClazzEntity clazz = new ClazzEntity();

        SQLQuery query = session.createSQLQuery("insert into class (class_name, class_subject, teacher_id, " +
                "test_weight, quiz_weight, homework_weight, participation_weight) values (?, " +
                "?, ?, 40, 20, 30, 10)");
        query.setString(0, name);
        query.setString(1, subject);
        query.setInteger(2, teacherID);

        if (query.executeUpdate()!=0) {
            SQLQuery query1 = session.createSQLQuery("select * from class where class_name = ?");
            query1.setString(0, name);

            List<Object[]> classInfo = query1.list();
            for (Object[] row : classInfo) {
                clazz.setId(Integer.parseInt(row[0].toString()));
                clazz.setClassName(row[1].toString());
                clazz.setClassSubject(row[2].toString());
                clazz.setTeacherId(Integer.parseInt(row[3].toString()));
                clazz.setTestWeight(40);
                clazz.setQuizWeight(20);
                clazz.setHomeworkWeight(30);
                clazz.setParticipationWeight(10);
                return clazz;
            }
        }
        return null;
    }

    @Override
    public UsersEntity updateUser(int ID, String firstName, String lastName, String password, String type) {
        Session session = sessionFactory.getCurrentSession();

        UsersEntity user = new UsersEntity();

        SQLQuery query = session.createSQLQuery("update users set first_name = ?, last_name = ?, type = ? ,password = ? where id = ?");
        query.setString(0, firstName);
        query.setString(1, lastName);
        query.setString(2, type);
        query.setInteger(4, ID);
        query.setString(3, password);

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

    @Override
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

    @Override
    public boolean updatePassword (int userId, String newPassword) {
        Session session = sessionFactory.getCurrentSession();

        SQLQuery query = session.createSQLQuery("update users set password = ? where id = ?");
        query.setString(0, newPassword);
        query.setInteger(1, userId);

        if (query.executeUpdate()!=0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(int ID) {
        Session session = sessionFactory.getCurrentSession();

        SQLQuery query = session.createSQLQuery("delete from users where id = ?");
        query.setInteger(0, ID);

        if (query.executeUpdate()!=0) {
            return true;
        }
        return false;
    }

    @Override
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

    @Override
    public double assignmentTypeGrade(int pairID, String type) {
        Session session = sessionFactory.getCurrentSession();

        SQLQuery query = session.createSQLQuery("select actual_points, total_points from assignment where pair_id = ? and assignment_type = ? and actual_points is not null");
        query.setInteger(0, pairID);
        query.setString(1, type);
        List<Object[]> grades = query.list();
        double sum = 0;
        double den = 0;
        for (Object[] i : grades) {
            sum += Double.parseDouble(i[0].toString());
            den += Double.parseDouble(i[1].toString());
        }
        if(grades.isEmpty()) {
            return -1;
        }
        return format((sum*100)/(den));
    }

    @Override
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

    @Override
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

        return format((overAll/weight)*100);
    }

    @Override
    public ArrayList<TeacherAssignment> getAssignmentListByClassID(int id) {
        Session session = sessionFactory.getCurrentSession();

        SQLQuery query1 = session.createSQLQuery("select distinct assignment_name, assignment_type, total_points,due_date from public.assignment join class_student on assignment.pair_id = class_student.pair_id where class_id = ?;");
        query1.setInteger(0, id);
        List<Object[]> assignment_info = query1.list();
        if (assignment_info.isEmpty()) {
            return null;
        }
        ArrayList<TeacherAssignment> assignmentList = new ArrayList<>();
        for (Object[] assignment : assignment_info) {
            TeacherAssignment assignment_object = new TeacherAssignment();
            //put assignment details into object
            assignment_object.setAssignmentName((String)assignment[0]);
            assignment_object.setAssignmentType((String)assignment[1]);
            assignment_object.setTotalPoints((int)assignment[2]);
            assignment_object.setDueDate((String)assignment[3]);


            //for each assignment create the list of students and their grade
            SQLQuery query2 = session.createSQLQuery("select users.id, users.first_name, users.last_name, assignment.actual_points from public.assignment join class_student on assignment.pair_id = class_student.pair_id " +
                                                        "join users on class_student.student_id = users.id where class_id=? and assignment.assignment_name=? and assignment.assignment_type=?");
            query2.setInteger(0,id);
            query2.setString(1, (String) assignment[0]);
            query2.setString(2,(String)assignment[1]);
            List<Object[]> studentList= query2.list();

            ArrayList<StudentGrade> gradeList = new ArrayList<>();
            for(Object[] student:studentList){
                StudentGrade grade = new StudentGrade();
                grade.setStudentID((int)student[0]);
                grade.setFirstName((String)student[1]);
                grade.setLastName((String)student[2]);
                if(student[3]==null){
                    grade.setPoints(-1);
                }else{
                    grade.setPoints((int)student[3]);
                }

                gradeList.add(grade);
            }

            assignment_object.setGradeList(gradeList);
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
        for (Object[] row : classData) {
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

    @Override
    public boolean updateGrade(String assignmentName, String assignmentType, int userID, int grade) {
        Session session = sessionFactory.getCurrentSession();

        SQLQuery query = session.createSQLQuery("update assignment set actual_points=? from class_student where assignment.pair_id=class_student.pair_id " +
                                                    "and class_student.student_id=? and assignment_name=? and assignment_type=?;");
        query.setInteger(0, grade);
        query.setInteger(1, userID);
        query.setString(2,assignmentName);
        query.setString(3,assignmentType);

        if (query.executeUpdate()!=0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean createAssignmentAndAssign(String name, String type, int total, String dueDate, int classID) {
        Session session = sessionFactory.getCurrentSession();
        boolean ret = true;

        SQLQuery query1 = session.createSQLQuery("select pair_id from class_student where class_id = ?");
        query1.setInteger(0, classID);

        List<Integer> pairID = query1.list();
        if (pairID.isEmpty()) {
            return false;
        }
        for (int i=0; i<pairID.size(); i++) {
            // Use the class id instead of pair id. Use all the pair id that come from that and use a for loop for each pair id
            SQLQuery query = session.createSQLQuery("insert into assignment (assignment_name, assignment_type, " +
                    "total_points, due_date, pair_id) values (?, ?, ?, ?, ?)");
            query.setString(0, name);
            query.setString(1, type);
            query.setInteger(2, total);
            query.setString(3, dueDate);
            query.setInteger(4, pairID.get(i));

            if (query.executeUpdate()==0) {
                ret = false;
            }
        }
        return ret;
    }

    @Override
    public boolean assignStudent(int classID, int studentID) {
        Session session = sessionFactory.getCurrentSession();

        SQLQuery query = session.createSQLQuery("insert into class_student (class_id, student_id) values " +
                "(?, ?)");
        query.setInteger(0, classID);
        query.setInteger(1, studentID);

        if (query.executeUpdate()!=0) {
            return true;
        }
        return false;
    }

    @Override
    public int numberOfClassesForStudent(int studentID) {
        Session session = sessionFactory.getCurrentSession();

        SQLQuery query = session.createSQLQuery("select class_id from class_student where student_id = ?");
        query.setInteger(0, studentID);

        return query.list().size();
    }

    @Override
    public int numberOfClassesForTeacher(int teacherID) {
        Session session = sessionFactory.getCurrentSession();

        SQLQuery query = session.createSQLQuery("select id from class where teacher_id = ?");
        query.setInteger(0, teacherID);

        return query.list().size();
    }

    @Override
    public int numberOfStudentsInClass(int class_id) {
        Session session = sessionFactory.getCurrentSession();

        SQLQuery query = session.createSQLQuery("select student_id from class_student where class_id = ?");
        query.setInteger(0, class_id);

        return query.list().size();
    }

    @Override
    public Map<String, Double> classAveragePerType(int classID) {
        Session session = sessionFactory.getCurrentSession();
        Map<String, Double> map = new HashMap<>();

        SQLQuery query = session.createSQLQuery("select pair_id from class_student where class_id = ?");
        query.setInteger(0, classID);

        double test = 0.0;
        double quiz = 0.0;
        double homework = 0.0;
        double participation = 0.0;

        List<Integer> pairID = query.list();
        if (pairID.isEmpty()) {
            return null;
        }
        int g=0, j=0, k=0, l=0;
        for (int i=0; i<pairID.size(); i++) {
            if (assignmentTypeGrade(pairID.get(i), "test") != -1) {
                test += assignmentTypeGrade(pairID.get(i), "test");
            } else {
                g++;
            }
            if (assignmentTypeGrade(pairID.get(i), "quiz") != -1) {
                quiz += assignmentTypeGrade(pairID.get(i), "quiz");
            } else {
                j++;
            }
            if (assignmentTypeGrade(pairID.get(i), "homework") != -1) {
                homework += assignmentTypeGrade(pairID.get(i), "homework");
            } else {
                k++;
            }
            if (assignmentTypeGrade(pairID.get(i), "participation") != -1) {
                participation += assignmentTypeGrade(pairID.get(i), "participation");
            } else {
                l++;
            }
        }
        if (g!=pairID.size()) {
            test = test/(pairID.size()-g);
        }
        if (j!=pairID.size()) {
            quiz = quiz/(pairID.size()-j);
        }
        if (k!=pairID.size()) {
            homework = homework/(pairID.size()-k);
        }
        if (l!=pairID.size()) {
            participation = participation/(pairID.size()-l);
        }

        map.put("test", format(test));
        map.put("quiz", format(quiz));
        map.put("homework", format(homework));
        map.put("participation", format(participation));
        return map;
    }

    @Override
    public double classAverage(int classID) {
        Session session = sessionFactory.getCurrentSession();

        SQLQuery query = session.createSQLQuery("select pair_id from class_student where class_id = ?");
        query.setInteger(0, classID);

        List<Integer> pairID = query.list();
        if (pairID.isEmpty()) {
            return -1;
        }
        double sum = 0;
        double den = 0;
        for (int i : pairID) {
            sum += overAllGrade(i);
            den += 1;
        }
        return format(sum/den);
    }

    @Override
    public double studentGPA(int studentID) {
        Session session = sessionFactory.getCurrentSession();

        SQLQuery query = session.createSQLQuery("select pair_id from class_student where student_id = ?");
        query.setInteger(0, studentID);

        List<Integer> pairID = query.list();
        if (pairID.isEmpty()) {
            return -1;
        }
        ArrayList<Double> array = new ArrayList<>();
        for (Integer i : pairID) {
            array.add(overAllGrade(i));
        }
        double gpa = 0;
        for (int i=0;i<pairID.size();i++) {
            if (array.get(i) >= 90) {
                gpa += 4;
            } else if (array.get(i) >= 80) {
                gpa += 3;
            } else if (array.get(i) >= 70) {
                gpa += 2;
            } else if (array.get(i) >= 60) {
                gpa += 1;
            } else if (array.get(i) < 60) {
                gpa += 0;
            }
        }
        return format(gpa/pairID.size());
    }

    @Override
    public ArrayList<StudentList> getStudentList() {
        Session session = sessionFactory.getCurrentSession();
        ArrayList<StudentList> list = new ArrayList<>();

        SQLQuery query = session.createSQLQuery("Select id, first_name, last_name from users where type = 'student'");

        List<Object[]> results = query.list();
        if (results.isEmpty()) {
            return null;
        }
        for (Object[] row : results) {
            StudentList sList = new StudentList();
            sList.setId(Integer.parseInt(row[0].toString()));
            sList.setfName(row[1].toString());
            sList.setlName(row[2].toString());
            sList.setNumberClasses(numberOfClassesForStudent(Integer.parseInt(row[0].toString())));
            sList.setGpa(studentGPA(Integer.parseInt(row[0].toString())));
            list.add(sList);
        }
        return list;
    }

    @Override
    public ArrayList<TeacherList> getTeacherList() {
        Session session = sessionFactory.getCurrentSession();
        ArrayList<TeacherList> list = new ArrayList<>();

        SQLQuery query = session.createSQLQuery("Select id, first_name, last_name from users where type = 'teacher'");

        List<Object[]> results = query.list();
        if (results.isEmpty()) {
            return null;
        }
        for (Object[] row : results) {
            TeacherList tList = new TeacherList();
            tList.setId(Integer.parseInt(row[0].toString()));
            tList.setfName(row[1].toString());
            tList.setlName(row[2].toString());
            tList.setNumberClasses(numberOfClassesForTeacher(Integer.parseInt(row[0].toString())));
            list.add(tList);
        }
        return list;
    }

    @Override
    public ArrayList<AllClasses> getClassList() {
        Session session = sessionFactory.getCurrentSession();
        ArrayList<AllClasses> list = new ArrayList<>();

        SQLQuery query = session.createSQLQuery("select id, class_name, class_subject, teacher_id from class");

        List<Object[]> results = query.list();
        if (results.isEmpty()) {
            return null;
        }
        for (Object[] row: results) {
            AllClasses cList = new AllClasses();
            cList.setName(row[1].toString());
            cList.setSubject(row[2].toString());
            cList.setTeacherName(getTeacherName(Integer.parseInt(row[3].toString())));
            cList.setNumberStudents(numberOfStudentsInClass(Integer.parseInt(row[0].toString())));
            list.add(cList);
        }
        return list;
    }
}
