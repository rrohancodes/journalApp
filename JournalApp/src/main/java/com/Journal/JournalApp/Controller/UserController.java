package com.Journal.JournalApp.Controller;

import com.Journal.JournalApp.Entity.User;
import com.Journal.JournalApp.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.saveEntry(user);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        User userInDB = userService.findByUsername(user.getUsername());
        if(userInDB != null) {
            userInDB.setUsername(user.getUsername());
            userInDB.setPassword(user.getPassword());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
