package com.ex.Controllers;

import com.ex.Dao.Dao;
import com.ex.Models.APIThrowaways.AllClasses;
import com.ex.Models.APIThrowaways.StudentClass;
import com.ex.Models.APIThrowaways.StudentList;
import com.ex.Models.APIThrowaways.TeacherList;
import com.ex.Models.ClazzEntity;
import com.ex.Models.UsersEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    @PostMapping(path = "/newUser", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<UsersEntity> newUser(@RequestBody String data) {
        System.out.println(data);
        UsersEntity user = new UsersEntity();
        if(data==null){
            return new ResponseEntity<>(null, HttpStatus.OK);
        }else{
            ObjectMapper om = new ObjectMapper();
            try {
                Map<String,Object> check = om.readValue(data,Map.class);
                String[] idPass = dao.createUser();
                user = dao.updateUser(Integer.parseInt(idPass[0]), check.get("firstName").toString(), check.get("lastName").toString(), idPass[1], check.get("type").toString().toLowerCase());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @PostMapping(path = "/newClass", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<ClazzEntity> newClass(@RequestBody String data) {
        System.out.println(data);
        if (data == null) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            ObjectMapper om = new ObjectMapper();
            try {
                Map<String, Object> check = om.readValue(data,Map.class);
                String[] teacherId=check.get("teacherId").toString().split("-");
                ClazzEntity clazz = dao.createClass(check.get("name").toString(), check.get("subject").toString(), Integer.parseInt(teacherId[0]));
                String studentIds = check.get("studentIds").toString();
                System.out.println(studentIds);
                studentIds=studentIds.substring(1,studentIds.length()-1);
                String str[] = studentIds.split(", ");
                List<String> list = Arrays.asList(str);
                for (String s: list) {
                    dao.assignStudent(clazz.getId(), Integer.parseInt(s));
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @PostMapping(path = "/assignStudent", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<String> assignStudent(@RequestBody String data) {
        System.out.println(data);
        if (data == null) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            ObjectMapper om = new ObjectMapper();
            try {
                Map<String,Object> check = om.readValue(data,Map.class);
                dao.assignStudent(Integer.parseInt(check.get("studentId").toString()), Integer.parseInt(check.get("classId").toString()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }
}
