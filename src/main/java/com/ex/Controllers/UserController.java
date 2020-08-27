package com.ex.Controllers;

import com.ex.Dao.Dao;
import com.ex.Models.UsersEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    Dao dao;

    @Autowired
    public UserController(Dao dao) {
        this.dao = dao;
    }

    @GetMapping(path = "/{id}/{password}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<UsersEntity> login(@PathVariable int id, @PathVariable String password) {
        UsersEntity user = dao.logIn(id, password);
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity(user, HttpStatus.OK);
        }
    }

//    @GetMapping(path = "/new", produces = "application/json")
//    @ResponseBody
//    public ResponseEntity<UsersEntity> newUser() {
//        String[] user = dao.createUser();
//        UsersEntity output=new UsersEntity();
//        output.setId(Integer.parseInt(user[0]));
//        output.setPassword(user[1]);
//
//        if (user == null) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        } else {
//            return new ResponseEntity(output, HttpStatus.OK);
//        }
//    }

    //@RequestParam int id,@RequestParam String firstName,@RequestParam String lastName,@RequestParam String password,@RequestParam String type
    @PutMapping(path = "/update", produces = "application/json")
    @ResponseBody
    public ResponseEntity<UsersEntity> update(@RequestBody String data) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Map<String,Object> info = om.readValue(data,Map.class);
        int id = Integer.parseInt(info.get("userId").toString());
        String firstName=info.get("firstName").toString();
        String lastName=info.get("lastName").toString();
        String password=info.get("new1").toString();
        String type = info.get("type").toString();
        UsersEntity user = dao.updateUser(id, firstName, lastName, password, type);
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity(user, HttpStatus.OK);
        }
    }

//    //@RequestParam String oldPassword,@RequestParam String newPassword
//    @PutMapping(path = "/newpassword/{userId}/{newPassword}")
//    @ResponseBody
//    public ResponseEntity<String> changePassword (@PathVariable int userId, @PathVariable String newPassword) {
//        boolean check = dao.updatePassword(userId, newPassword);
//        if (!check) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        } else {
//            return new ResponseEntity(HttpStatus.OK);
//        }
//    }

}
