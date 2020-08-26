package com.ex.Controllers;

import com.ex.Dao.Dao;
import com.ex.Models.APIThrowaways.AllClasses;
import com.ex.Models.APIThrowaways.StudentClass;
import com.ex.Models.APIThrowaways.StudentList;
import com.ex.Models.APIThrowaways.TeacherList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

//    @PostMapping(path = "/newUser", consumes = "application/json")
//    @ResponseBody
//    public ResponseEntity<String> newUser(@RequestBody String data) {
//        System.out.println(data);
//        if(data==null){
//            return new ResponseEntity<>(null, HttpStatus.OK);
//        }else{
//            ObjectMapper om = new ObjectMapper();
//            try {
////                Map<String,Object> check = om.readValue(data,Map.class);
////                String hold = check.get("date").toString();
////                hold=hold.substring(1,hold.length()-1);
////                String[] date=hold.split(", ");
////                String dueDate="";
////                if(date[2].split("=")[1].length()!=2){
////                    dueDate+="0";
////
////                }
////                dueDate+=date[2].split("=")[1]+"-";
////                if(date[1].split("=")[1].length()!=2){
////                    dueDate+="0";
////
////                }
////                dueDate+=date[1].split("=")[1]+"-";
////                dueDate+=date[0].split("=")[1];
////                dao.createAssignmentAndAssign(check.get("name").toString(), check.get("type").toString().toLowerCase(), Integer.parseInt(check.get("points").toString()), dueDate, Integer.parseInt(check.get("classId").toString()));
//
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//            return new ResponseEntity<>(null,HttpStatus.OK);
//        }
//    }
}
