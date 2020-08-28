package com.ex.Models.APIThrowaways;

public class StudentList {

    private int id;
    private String fName;
    private String lName;
    private int numberClasses;
    private double gpa;

    public StudentList() {
    }

    public StudentList(int id, String fName, String lName, int numberClasses, double gpa) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.numberClasses = numberClasses;
        this.gpa = gpa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public int getNumberClasses() {
        return numberClasses;
    }

    public void setNumberClasses(int numberClasses) {
        this.numberClasses = numberClasses;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

}
