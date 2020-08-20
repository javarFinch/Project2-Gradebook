package com.ex.Dao;

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

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public UsersEntity logIn(int ID, String password);
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public ArrayList<ClazzEntity> getClassForStudent(int studentID);
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public ArrayList<AssignmentEntity> getAssignmentsForStudentPerClass(int clazzID, int studentID);

}
