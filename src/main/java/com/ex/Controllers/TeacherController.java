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

        
        ArrayList<ClazzEntity> classes = dao.getClassForStudent(id);

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
            // This will getAllAssignmentsByClassId
            //ArrayList<AssignmentEntity> assignments = dao.getAssignmentsForStudentPerClass(classes.get(i).getId(), id);

            /*
                Here we are going ot generate a arrayList of Maps -> this will be a student list that includes every student and their assignment lists respectivly
                            
                get an arraylist of all the pair ids that contain the class id: classes.get(i).getID(); return the pair_id value and the user_id value
                ArrayList<Map<String,Object>> : arr2
                for each pair id:
                    put these into the ArrayList : arr2
                        ("id",user.id)
                        user.firstname ... all the values in a user object

                        also into this map:
                            ("Assignments",ArrayList<Map<String,Object>>)
                                ArrayList will be all the assignments returned with the pair id : (classes.get(i),user.getID);
                                

             */

             //^^^^^^Replaces Below

            // ArrayList<Map<String, Object>> arraylist2 = new ArrayList<>();
            // for (int j=0; j<assignments.size(); j++) {
            //     Map<String, Object> map1 = new HashMap<>();
            //     map1.put("AssignmentName", assignments.get(j).getAssignmentName());
            //     map1.put("AssignmentType", assignments.get(j).getAssignmentType());
            //     map1.put("ActualPoints", assignments.get(j).getActualPoints());
            //     map1.put("TotalPoints", assignments.get(j).getTotalPoints());
            //     map1.put("DueDate", assignments.get(j).getDueDate());
            //     arraylist2.add(map1);
            // }
            map.put("AssignmentList",arraylist2);

            //these should be class averages
            map.put("TestGrade", dao.assignmentTypeGrade(dao.getPairID(classes.get(i).getId(), id), "test"));
            map.put("QuizGrade", dao.assignmentTypeGrade(dao.getPairID(classes.get(i).getId(), id), "quiz"));
            map.put("HomeworkGrade", dao.assignmentTypeGrade(dao.getPairID(classes.get(i).getId(), id), "homework"));
            map.put("ParticipationGrade", dao.assignmentTypeGrade(dao.getPairID(classes.get(i).getId(), id), "participation"));
            map.put("OverAllGrade", dao.overAllGrade(dao.getPairID(classes.get(i).getId(), id)));
            arraylist1.add(map);
        }

        if (arraylist1 == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(arraylist1, HttpStatus.OK);
        }
    }

}
