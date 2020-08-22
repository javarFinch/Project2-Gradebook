package com.ex.Controllers;

import com.ex.Dao.Dao;
import com.ex.Models.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/user")
public class UserController {

    Dao dao;

    @Autowired
    public UserController(Dao dao) {
        this.dao = dao;
    }

    @RequestMapping(path="/{id}/{password}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> login(@PathVariable int id, @PathVariable String password) {
        UsersEntity user = dao.logIn(id, password);
        if (user == null) {
            System.out.println("not found");
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            System.out.println("FOUND");
            return new ResponseEntity(user, HttpStatus.OK);
        }
    }
}
