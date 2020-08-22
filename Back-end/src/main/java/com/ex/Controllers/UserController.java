package com.ex.Controllers;

import com.ex.Dao.Dao;
import com.ex.Models.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    Dao dao;

    @Autowired
    public UserController(Dao dao) {
        this.dao = dao;
    }

    @RequestMapping(path = "/{id}/{password}", produces = "application/json")
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

    @RequestMapping(path = "/new", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> newUser() {
        String[] user = dao.createUser();
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity(user, HttpStatus.OK);
        }
    }

    //@RequestParam int id,@RequestParam String firstName,@RequestParam String lastName,@RequestParam String password,@RequestParam String type
    @RequestMapping(path = "/update/{id}/{firstName}/{lastName}/{password}/{type}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> update(@PathVariable int id,@PathVariable String firstName,@PathVariable String lastName,@PathVariable String password,@PathVariable String type) {
        UsersEntity user = dao.updateUser(id, firstName, lastName, password, type);
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity(user, HttpStatus.OK);
        }
    }

    //@RequestParam String oldPassword,@RequestParam String newPassword
    @RequestMapping(path = "/newpassword/{oldPassword}/{newPassword}")
    @ResponseBody
    public ResponseEntity<String> changePassword (@PathVariable String oldPassword, @PathVariable String newPassword) {
        boolean check = dao.updatePassword(oldPassword, newPassword);
        if (!check) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity(HttpStatus.OK);
        }
    }

}
