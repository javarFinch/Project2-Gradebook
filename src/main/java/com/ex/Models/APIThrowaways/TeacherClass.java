package com.ex.Models.APIThrowaways;

import java.util.ArrayList;

public class TeacherClass {
    private int id;
    private String className;
    private String classSubject;
    private String teacherName;
    private int testWeight;
    private int homeworkWeight;
    private int quizWeight;
    private int participationWeight;
    private ArrayList<TeacherAssignment>  assignmentList;
    private double testAverage;
    private double quizAverage;
    private double homeworkAverage;
    private double participationAverage;
    private double overAllAverage;

    public double getHomeworkAverage() {
        return homeworkAverage;
    }

    public int getHomeworkWeight() {
        return homeworkWeight;
    }

    public void setHomeworkWeight(int homeworkWeight) {
        this.homeworkWeight = homeworkWeight;
    }

    public void setHomeworkAverage(double homeworkAverage) {
        this.homeworkAverage = homeworkAverage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassSubject() {
        return classSubject;
    }

    public void setClassSubject(String classSubject) {
        this.classSubject = classSubject;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getTestWeight() {
        return testWeight;
    }

    public void setTestWeight(int testWeight) {
        this.testWeight = testWeight;
    }

    public int getQuizWeight() {
        return quizWeight;
    }

    public void setQuizWeight(int quizWeight) {
        this.quizWeight = quizWeight;
    }

    public int getParticipationWeight() {
        return participationWeight;
    }

    public void setParticipationWeight(int participationWeight) {
        this.participationWeight = participationWeight;
    }

    public ArrayList<TeacherAssignment> getAssignmentList() {
        return assignmentList;
    }

    public void setAssignmentList(ArrayList<TeacherAssignment> assignmentList) {
        this.assignmentList = assignmentList;
    }

    public double getTestAverage() {
        return testAverage;
    }

    public void setTestAverage(double testAverage) {
        this.testAverage = testAverage;
    }

    public double getQuizAverage() {
        return quizAverage;
    }

    public void setQuizAverage(double quizAverage) {
        this.quizAverage = quizAverage;
    }

    public double getParticipationAverage() {
        return participationAverage;
    }

    public void setParticipationAverage(double participationAverage) {
        this.participationAverage = participationAverage;
    }

    public double getOverAllAverage() {
        return overAllAverage;
    }

    public void setOverAllAverage(double overAllAverage) {
        this.overAllAverage = overAllAverage;
    }
}
