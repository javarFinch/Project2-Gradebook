package com.ex.Models.APIThrowaways;

public class TeacherList {

    private int id;
    private String fName;
    private String lName;
    private int numberClasses;

    public TeacherList() {
    }

    public TeacherList(int id, String fName, String lName, int numberClasses) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.numberClasses = numberClasses;
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
}
