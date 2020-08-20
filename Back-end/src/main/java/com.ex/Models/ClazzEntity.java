package com.ex.Models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Table(name = "class", schema = "public", catalog = "postgres")
public class ClazzEntity {
    private int id;
    private String className;
    private String classSubject;
    private int teacherId;
    private int testWeight;
    private int quizWeight;
    private int homeworkWeight;
    private int participationWeight;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "class_name", nullable = false, length = 50)
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Basic
    @Column(name = "class_subject", nullable = false, length = 50)
    public String getClassSubject() {
        return classSubject;
    }

    public void setClassSubject(String classSubject) {
        this.classSubject = classSubject;
    }

    @Basic
    @Column(name = "teacher_id", nullable = true)
    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Basic
    @Column(name = "test_weight", nullable = false)
    public int getTestWeight() {
        return testWeight;
    }

    public void setTestWeight(int testWeight) {
        this.testWeight = testWeight;
    }

    @Basic
    @Column(name = "quiz_weight", nullable = false)
    public int getQuizWeight() {
        return quizWeight;
    }

    public void setQuizWeight(int quizWeight) {
        this.quizWeight = quizWeight;
    }

    @Basic
    @Column(name = "homework_weight", nullable = false)
    public int getHomeworkWeight() {
        return homeworkWeight;
    }

    public void setHomeworkWeight(int homeworkWeight) {
        this.homeworkWeight = homeworkWeight;
    }

    @Basic
    @Column(name = "participation_weight", nullable = false)
    public int getParticipationWeight() {
        return participationWeight;
    }

    public void setParticipationWeight(int participationWeight) {
        this.participationWeight = participationWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClazzEntity that = (ClazzEntity) o;
        return id == that.id &&
                teacherId == that.teacherId &&
                testWeight == that.testWeight &&
                quizWeight == that.quizWeight &&
                homeworkWeight == that.homeworkWeight &&
                participationWeight == that.participationWeight &&
                Objects.equals(className, that.className) &&
                Objects.equals(classSubject, that.classSubject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, className, classSubject, teacherId, testWeight, quizWeight, homeworkWeight, participationWeight);
    }

}
