package com.ex.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "class_student", schema = "public", catalog = "postgres")
public class ClassStudentEntity {
    private int pairId;
    private Integer classId;
    private Integer studentId;

    @Id
    @Column(name = "pair_id", nullable = false)
    public int getPairId() {
        return pairId;
    }

    public void setPairId(int pairId) {
        this.pairId = pairId;
    }

    @Basic
    @Column(name = "class_id", nullable = true)
    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    @Basic
    @Column(name = "student_id", nullable = true)
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassStudentEntity that = (ClassStudentEntity) o;
        return pairId == that.pairId &&
                Objects.equals(classId, that.classId) &&
                Objects.equals(studentId, that.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pairId, classId, studentId);
    }
}
