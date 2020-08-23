package com.ex.Controllers;


import com.ex.Dao.Dao;
import com.ex.Models.AssignmentEntity;
import com.ex.Models.ClazzEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

        for (int i=0; i<classes.size(); i++) {
            //This is all the data about 1 class needed
            Map<String, Object> map = new HashMap<>();
            map.put("ClassName", classes.get(i).getClassName());
            map.put("ClassSubject", classes.get(i).getClassSubject());
            map.put("TeacherName", dao.getTeacherName(classes.get(i).getTeacherId()));
            map.put("TestWeight", classes.get(i).getTestWeight());
            map.put("QuizWeight", classes.get(i).getQuizWeight());
            map.put("HomeworkWeight", classes.get(i).getHomeworkWeight());
            map.put("ParticipationWeight", classes.get(i).getParticipationWeight());


            map.put("AssignmentList",dao.getAssignmentListByClassID(classes.get(i).getId()));

            //these should be class averages
            map.put("TestAverage", null);
            map.put("QuizAverage", null);
            map.put("HomeworkAverage", null);
            map.put("ParticipationAverage", null);
            map.put("OverAllAverage",null);
            arraylist1.add(map);
        }

        if (arraylist1 == null) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity(arraylist1, HttpStatus.OK);
        }
    }

}
