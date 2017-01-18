package com.mmgiec.app.controllers;

import com.mmgiec.app.entities.Role;
import com.mmgiec.app.entities.User;
import com.mmgiec.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        List<User> allUsers = userService.findAll();
        if(!allUsers.isEmpty())
            return ResponseEntity.ok(allUsers);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        user.setRole(Role.ROLE_USER);
        userService.save(user);
        if (user.getId_user() != 0) {
            return ResponseEntity.ok(user);
        }
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/current", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getCurrentUserEmail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.findByEmail(auth.getName());
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
}
