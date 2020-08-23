package com.ex.Models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Table(name = "assignment", schema = "public", catalog = "postgres")
public class AssignmentEntity {

    private int id;
    private String assignmentName;
    private String assignmentType;
    private Integer actualPoints;
    private int totalPoints;
    private String dueDate;
    private Integer pairId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "assignment_name", nullable = false, length = 50)
    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    @Basic
    @Column(name = "assignment_type", nullable = false, length = 50)
    public String getAssignmentType() {
        return assignmentType;
    }

    public void setAssignmentType(String assignmentType) {
        this.assignmentType = assignmentType;
    }

    @Basic
    @Column(name = "actual_points", nullable = true)
    public Integer getActualPoints() {
        return actualPoints;
    }

    public void setActualPoints(Integer actualPoints) {
        this.actualPoints = actualPoints;
    }

    @Basic
    @Column(name = "total_points", nullable = false)
    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    @Basic
    @Column(name = "due_date", nullable = false, length = 50)
    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Basic
    @Column(name = "pair_id", nullable = true)
    public Integer getPairId() {
        return pairId;
    }

    public void setPairId(Integer pairId) {
        this.pairId = pairId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssignmentEntity that = (AssignmentEntity) o;
        return id == that.id &&
                totalPoints == that.totalPoints &&
                assignmentName.equals(that.assignmentName) &&
                assignmentType.equals(that.assignmentType) &&
                actualPoints.equals(that.actualPoints) &&
                dueDate.equals(that.dueDate) &&
                pairId.equals(that.pairId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, assignmentName, assignmentType, actualPoints, totalPoints, dueDate, pairId);
    }

}
