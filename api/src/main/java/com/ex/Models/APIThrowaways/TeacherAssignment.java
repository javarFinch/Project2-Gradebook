package com.ex.Models.APIThrowaways;

import java.util.ArrayList;

public class TeacherAssignment {
    private String assignmentName;
    private String assignmentType;
    private int totalPoints;
    private String dueDate;
    private ArrayList<StudentGrade> gradeList;

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getAssignmentType() {
        return assignmentType;
    }

    public void setAssignmentType(String assignmentType) {
        this.assignmentType = assignmentType;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public ArrayList<StudentGrade> getGradeList() {
        return gradeList;
    }

    public void setGradeList(ArrayList<StudentGrade> gradeList) {
        this.gradeList = gradeList;
    }
}
