package com.ex.Dao;

import com.ex.Models.AssignmentEntity;
import com.ex.Models.ClazzEntity;
import com.ex.Models.UsersEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Repository
@Transactional
public interface Dao {

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

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public String getTeacherName(int ID);

    /***
     * This method will be used primarily by students to change their passwords
     * @param oldPassword
     * @param newPassword
     * @return True if the password was successfully updated
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean updatePassword (String oldPassword, String newPassword);

    /***
     * This method is delete a user from the system
     * @param ID
     * @return This will return true if the user was successfully deleted
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean deleteUser(int ID);

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public int getPairID(int classID, int studentID);

    /***
     * This method is used to get the overall average for an assignment category
     * @param pairID
     * @param type
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public int assignmentTypeGrade(int pairID, String type);

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public ClazzEntity getClazzById(int ID);

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public int overAllGrade(int pairID);

}


