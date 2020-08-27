package com.ex.Controllers;

import com.ex.Dao.Dao;
import com.ex.Models.ClazzEntity;
import com.ex.Models.APIThrowaways.StudentClass;
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
@RequestMapping(path = "/student")
public class StudentController {

    Dao dao;

    @Autowired
    public StudentController(Dao dao) {
        this.dao = dao;
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<ArrayList<StudentClass>> classList(@PathVariable int id) {
        ArrayList<StudentClass> arraylist1 = new ArrayList<>();
        ArrayList<ClazzEntity> classes = dao.getClassForStudent(id);
        for (int i=0; i<classes.size(); i++) {
            StudentClass map = new StudentClass();
            map.setClassName(classes.get(i).getClassName());
            map.setClassSubject(classes.get(i).getClassSubject());
            map.setTeacherName(dao.getTeacherName(classes.get(i).getTeacherId()));
            map.setTestWeight(classes.get(i).getTestWeight());
            map.setQuizWeight(classes.get(i).getQuizWeight());
            map.setHomeworkWeight(classes.get(i).getHomeworkWeight());
            map.setParticipationWeight(classes.get(i).getParticipationWeight());
            map.setAssignmentList(dao.getAssignmentsForStudentPerClass(classes.get(i).getId(), id));
            map.setTestGrade(dao.assignmentTypeGrade(dao.getPairID(classes.get(i).getId(), id), "test"));
            map.setQuizGrade(dao.assignmentTypeGrade(dao.getPairID(classes.get(i).getId(), id), "quiz"));
            map.setHomeworkGrade(dao.assignmentTypeGrade(dao.getPairID(classes.get(i).getId(), id), "homework"));
            map.setParticipationGrade(dao.assignmentTypeGrade(dao.getPairID(classes.get(i).getId(), id), "participation"));
            map.setOverAllGrade(dao.overAllGrade(dao.getPairID(classes.get(i).getId(), id)));
            arraylist1.add(map);
        }
        if (arraylist1 == null) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity(arraylist1, HttpStatus.OK);
        }
    }
}
