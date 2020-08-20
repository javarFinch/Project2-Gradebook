package com.ex.Dao;

import com.ex.Models.AssignmentEntity;
import com.ex.Models.ClazzEntity;
import com.ex.Models.UsersEntity;

import java.util.ArrayList;
import java.util.Map;

public interface Dao {

    public UsersEntity logIn(int ID, String password);
    public ArrayList<ClazzEntity> getClassForStudent(int studentID);
    public ArrayList<AssignmentEntity> getAssignmentsForStudentPerClass(int clazzID, int studentID);

}
