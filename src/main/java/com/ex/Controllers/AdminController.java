package com.ex.Controllers;

import com.ex.Dao.Dao;
import com.ex.Models.APIThrowaways.AllClasses;
import com.ex.Models.APIThrowaways.StudentClass;
import com.ex.Models.APIThrowaways.StudentList;
import com.ex.Models.APIThrowaways.TeacherList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {

    Dao dao;

    @Autowired
    public AdminController(Dao dao) {
        this.dao = dao;
    }

    @GetMapping(path = "/student", produces = "application/json")
    @ResponseBody
    public ResponseEntity<ArrayList<StudentList>> studentList() {
        ArrayList<StudentList> arraylist1 = dao.getStudentList();
        if (arraylist1 == null) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity(arraylist1, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/teacher", produces = "application/json")
    @ResponseBody
    public ResponseEntity<ArrayList<TeacherList>> teacherList() {
        ArrayList<TeacherList> arraylist1 = dao.getTeacherList();
        if (arraylist1 == null) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity(arraylist1, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/classes", produces = "application/json")
    @ResponseBody
    public ResponseEntity<ArrayList<AllClasses>> classList() {
        ArrayList<AllClasses> arraylist1 = dao.getClassList();
        if (arraylist1 == null) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity(arraylist1, HttpStatus.OK);
        }
    }
}
