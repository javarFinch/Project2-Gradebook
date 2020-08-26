package com.ex.Models.APIThrowaways;

import com.ex.Models.AssignmentEntity;

import java.util.ArrayList;

public class StudentClass {
    private String ClassName;
    private String ClassSubject;
    private String TeacherName;
    private int TestWeight;
    private int QuizWeight;
    private int HomeworkWeight;
    private int ParticipationWeight;
    private ArrayList<AssignmentEntity> AssignmentList;
    private double TestGrade;
    private double QuizGrade;
    private double HomeworkGrade;
    private double ParticipationGrade;
    private double OverAllGrade;

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getClassSubject() {
        return ClassSubject;
    }

    public void setClassSubject(String classSubject) {
        ClassSubject = classSubject;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setTeacherName(String teacherName) {
        TeacherName = teacherName;
    }

    public int getTestWeight() {
        return TestWeight;
    }

    public void setTestWeight(int testWeight) {
        TestWeight = testWeight;
    }

    public int getQuizWeight() {
        return QuizWeight;
    }

    public void setQuizWeight(int quizWeight) {
        QuizWeight = quizWeight;
    }

    public int getHomeworkWeight() {
        return HomeworkWeight;
    }

    public void setHomeworkWeight(int homeworkWeight) {
        HomeworkWeight = homeworkWeight;
    }

    public int getParticipationWeight() {
        return ParticipationWeight;
    }

    public void setParticipationWeight(int participationWeight) {
        ParticipationWeight = participationWeight;
    }

    public ArrayList<AssignmentEntity> getAssignmentList() {
        return AssignmentList;
    }

    public void setAssignmentList(ArrayList<AssignmentEntity> assignmentList) {
        AssignmentList = assignmentList;
    }

    public double getTestGrade() {
        return TestGrade;
    }

    public void setTestGrade(double testGrade) {
        TestGrade = testGrade;
    }

    public double getQuizGrade() {
        return QuizGrade;
    }

    public void setQuizGrade(double quizGrade) {
        QuizGrade = quizGrade;
    }

    public double getHomeworkGrade() {
        return HomeworkGrade;
    }

    public void setHomeworkGrade(double homeworkGrade) {
        HomeworkGrade = homeworkGrade;
    }

    public double getParticipationGrade() {
        return ParticipationGrade;
    }

    public void setParticipationGrade(double participationGrade) {
        ParticipationGrade = participationGrade;
    }

    public double getOverAllGrade() {
        return OverAllGrade;
    }

    public void setOverAllGrade(double overAllGrade) {
        OverAllGrade = overAllGrade;
    }
}
