package com.ex.Controllers;


import com.ex.Dao.Dao;
import com.ex.Models.APIThrowaways.TeacherAssignment;
import com.ex.Models.APIThrowaways.TeacherClass;
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

    @GetMapping(path = "/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<ArrayList<TeacherClass>> classList(@PathVariable int id) {

        //This is an ArrayList of Maps -> this will be our class list where each item in the list corresponds to all the data needed about a class
        ArrayList<TeacherClass> arraylist1 = new ArrayList<>();
        
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

    @GetMapping(path = "/update", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<String> classList(@RequestBody String data) {
        if(data==null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            System.out.println(data);
            ObjectMapper om = new ObjectMapper();
            try {
                Map<String,Object> check = om.readValue(data,Map.class);
                String hold = check.get("data").toString();
                hold=hold.substring(1,hold.length()-1);
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


    @GetMapping(path = "/updateClass/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<TeacherClass> updateClassObject(@PathVariable int id){
        ClazzEntity holder = dao.getClazzById(id);

        if(holder==null){
            return new ResponseEntity<>(null,HttpStatus.OK);
        }else{
            TeacherClass output= generateClassObject(holder);
            return new ResponseEntity(output,HttpStatus.OK);
        }

    }

    private TeacherClass generateClassObject(ClazzEntity clazz){
        //This is all the data about 1 class needed
        TeacherClass map = new TeacherClass();
        map.setId(clazz.getId());
        map.setClassName(clazz.getClassName());
        map.setClassSubject(clazz.getClassSubject());
        map.setTeacherName(dao.getTeacherName(clazz.getTeacherId()));
        map.setTestWeight(clazz.getTestWeight());
        map.setQuizWeight(clazz.getQuizWeight());
        map.setHomeworkWeight(clazz.getHomeworkWeight());
        map.setParticipationWeight(clazz.getParticipationWeight());


        map.setAssignmentList(dao.getAssignmentListByClassID(clazz.getId()));

        //these should be class averages
        map.setTestAverage(dao.classAveragePerType(clazz.getId()).get("test"));
        map.setQuizAverage(dao.classAveragePerType(clazz.getId()).get("quiz"));
        map.setHomeworkAverage(dao.classAveragePerType(clazz.getId()).get("homework"));
        map.setParticipationAverage(dao.classAveragePerType(clazz.getId()).get("participation"));
        map.setOverAllAverage(dao.classAverage(clazz.getId()));
        map.put("AssignmentList",dao.getAssignmentListByClassID(clazz.getId()));

        //these should be class averages
        return map;
    }

    @PostMapping(path = "/newAssignment", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<String> newAssignment(@RequestBody String data) {
        System.out.println(data);
        if(data==null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            ObjectMapper om = new ObjectMapper();
            try {
                Map<String,Object> check = om.readValue(data,Map.class);
                String hold = check.get("date").toString();
                hold=hold.substring(1,hold.length()-1);
                String[] date=hold.split(", ");
                String dueDate="";
                if(date[2].split("=")[1].length()!=2){
                    dueDate+="0";

                }
                dueDate+=date[2].split("=")[1]+"-";
                if(date[1].split("=")[1].length()!=2){
                    dueDate+="0";

                }
                dueDate+=date[1].split("=")[1]+"-";
                dueDate+=date[0].split("=")[1];
                dao.createAssignmentAndAssign(check.get("name").toString(), check.get("type").toString().toLowerCase(), Integer.parseInt(check.get("points").toString()), dueDate, Integer.parseInt(check.get("classId").toString()));

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return new ResponseEntity<>(null,HttpStatus.OK);
        }
    }

}
