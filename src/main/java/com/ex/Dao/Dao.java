package com.ex.Dao;

import com.ex.Models.APIThrowaways.AllClasses;
import com.ex.Models.APIThrowaways.StudentList;
import com.ex.Models.APIThrowaways.TeacherAssignment;
import com.ex.Models.APIThrowaways.TeacherList;
import com.ex.Models.AssignmentEntity;
import com.ex.Models.ClazzEntity;
import com.ex.Models.UsersEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Map;

@Repository
@Transactional
public interface Dao {

    public double format(double input);

    /***
     * This method is used to log in a user
     * @param ID
     * @param password
     * @return UsersEntity object
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public UsersEntity logIn(int ID, String password);

    /***
     * This method is used to get the classes for the specified student
     * @param studentID
     * @return List of ClazzEntity objects
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public ArrayList<ClazzEntity> getClassForStudent(int studentID);

    /***
     * This method will be used to get all the assignments for a specific student and class
     * @param clazzID
     * @param studentID
     * @return This will re
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public ArrayList<AssignmentEntity> getAssignmentsForStudentPerClass(int clazzID, int studentID);

    /***
     * This method is used to create a new user. Admins will have access to a form to create students, teachers and other admins
     * @return This method will return string[ID,PASSWORD]
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public String[] createUser();

    /***
     * This method is used to create a new class
     * @param name
     * @param subject
     * @param teacherID
     * @return return a clazz object
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public ClazzEntity createClass(String name, String subject, int teacherID);

    /***
     * This method will be used to update a users information after it has been created.
     * @param ID
     * @param firstName
     * @param lastName
     * @param password
     * @param type
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public UsersEntity updateUser(int ID, String firstName, String lastName, String password, String type);

    /***
     * This method is used to get the name of a any user. Meant for accessing teacher names
     * @param ID
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public String getTeacherName(int ID);

    /***
     * This method will be used primarily by students to change their passwords
     * @param userId
     * @param newPassword
     * @return True if the password was successfully updated
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean updatePassword (int userId, String newPassword);

    /***
     * This method is delete a user from the system
     * @param ID
     * @return This will return true if the user was successfully deleted
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean deleteUser(int ID);

    /***
     * This method is used to return the pairid given the classID and studentID
     * @param classID
     * @param studentID
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public int getPairID(int classID, int studentID);

    /***
     * This method is used to get the overall average for an assignment category
     * @param pairID
     * @param type
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public double assignmentTypeGrade(int pairID, String type);

    /***
     * This is used to return a clazz object given the clazz id
     * @param ID
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public ClazzEntity getClazzById(int ID);

    /***
     * This is used to get a students overall grade in a class
     * @param pairID
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public double overAllGrade(int pairID);

    /***
     * This is used to get the list of assignments in a class given the classID
     * @param id
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public ArrayList<TeacherAssignment> getAssignmentListByClassID(int id);

    /***
     * This method is used to get the classes for the specified student
     * @param teacherID
     * @return List of ClazzEntity objects
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public ArrayList<ClazzEntity> getClassForTeacher(int teacherID);

    /***
     * This method is used to update the grade of an assignment for a single or multiple students
     * @param assignmentName
     * @param assignmentType
     * @param userID
     * @param grade
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean updateGrade (String assignmentName, String assignmentType, int userID, int grade);

    /***
     * This is used to create and assignment and assign it to all students in a class
     * @param name
     * @param type
     * @param total
     * @param dueDate
     * @param classID
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean createAssignmentAndAssign(String name, String type, int total, String dueDate, int classID);

    /***
     * This is used to assign a student to a class
     * @param classID
     * @param studentID
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean assignStudent(int classID, int studentID);

    /***
     * This is used to get the number of classes a student is enrolled in
     * @param studentID
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public int numberOfClassesForStudent(int studentID);

    /***
     * This is used to get the number of classes a teacher is assigned to teach
     * @param teacherID
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public int numberOfClassesForTeacher(int teacherID);

    /***
     * This is used to get the number of students enrolled in a class
     * @param classID
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public int numberOfStudentsInClass(int classID);

    /***
     * This is used to get the class average grades per type of assignment
     * such as tests, quizzes, homework and participation
     * @param classID
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Map<String, Double> classAveragePerType(int classID);

    /***
     * This is used to get the overall class average
     * @param classID
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public double classAverage(int classID);

    /***
     * This is used to calculate a students overall GPA
     * @param studentID
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public double studentGPA(int studentID);

    /***
     * This is used to get a list of students for the admin to view
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public ArrayList<StudentList> getStudentList();

    /***
     * This is used to get a list of teachers for the admin to view
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public ArrayList<TeacherList> getTeacherList();

    /***
     * This is used to get a list of classes for the admin to view
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public ArrayList<AllClasses> getClassList();
}


