package com.ex.Controllers;

import com.ex.Dao.Dao;
import com.ex.Dao.DaoImpl;
import com.ex.Models.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(path="/login")
public class LogInController {

    Dao dao;

    @Autowired
    public LogInController(Dao dao) {
        this.dao = dao;
    }

    @RequestMapping(produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> login(/*@RequestParam("username") int id, @RequestParam("password") String pw*/) {
        int id = 1;
        String pw = "password";
        UsersEntity user = dao.logIn(id, pw);
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(user, HttpStatus.OK);
        }
    }
}
