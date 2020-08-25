package com.ex.Controllers;


import com.ex.Dao.Dao;
import com.ex.Models.AssignmentEntity;
import com.ex.Models.ClazzEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path="/teacher")
public class TeacherController {

    Dao dao;

    @Autowired
    public TeacherController(Dao dao) {
        this.dao = dao;
    }

    @RequestMapping(path = "/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> classList(@PathVariable int id) {

        //This is an ArrayList of Maps -> this will be our class list where each item in the list corresponds to all the data needed about a class
        ArrayList<Map<String, Object>> arraylist1 = new ArrayList<>();
        
        ArrayList<ClazzEntity> classes = dao.getClassForTeacher(id);

        if(classes!=null){
            for (int i=0; i<classes.size(); i++) {
                arraylist1.add(generateClassObject(classes.get(i)));
            }
        }

        if (arraylist1.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity(arraylist1, HttpStatus.OK);
        }
    }

    @PostMapping(path = "/update", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<String> classList(@RequestBody String data) {
        if(data==null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            System.out.println(data);
            ObjectMapper om = new ObjectMapper();
            try {
                Map<String,Object> check = om.readValue(data,Map.class);
                System.out.println(check.get("name"));
                System.out.println(check.get("type"));
                System.out.println(check.get("data"));
                String hold = check.get("data").toString();
                hold=hold.substring(1,hold.length()-1);
                System.out.println(hold);
                String[] list = hold.split(", ");
                for(String pair : list){
                    String[]a = pair.split("=");
                    dao.updateGrade(check.get("name").toString(),check.get("type").toString(),Integer.parseInt(a[0]),Integer.parseInt(a[1]));
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return new ResponseEntity<>(null,HttpStatus.OK);
        }
    }


    @RequestMapping(path = "/updateClass/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> updateClassObject(@PathVariable int id){
        ClazzEntity holder = dao.getClazzById(id);
        Map<String,Object> output= generateClassObject(holder);
        if(output.isEmpty()){
            return new ResponseEntity<>(null,HttpStatus.OK);
        }else{
            return new ResponseEntity(output,HttpStatus.OK);
        }

    }

    private Map<String,Object> generateClassObject(ClazzEntity clazz){
        //This is all the data about 1 class needed
        Map<String, Object> map = new HashMap<>();
        map.put("Id",clazz.getId());
        map.put("ClassName", clazz.getClassName());
        map.put("ClassSubject", clazz.getClassSubject());
        map.put("TeacherName", dao.getTeacherName(clazz.getTeacherId()));
        map.put("TestWeight", clazz.getTestWeight());
        map.put("QuizWeight", clazz.getQuizWeight());
        map.put("HomeworkWeight", clazz.getHomeworkWeight());
        map.put("ParticipationWeight", clazz.getParticipationWeight());


        map.put("AssignmentList",dao.getAssignmentListByClassID(clazz.getId()));

        //these should be class averages
        map.put("TestAverage", null);
        map.put("QuizAverage", null);
        map.put("HomeworkAverage", null);
        map.put("ParticipationAverage", null);
        map.put("OverAllAverage",null);
        return map;
    }

}
