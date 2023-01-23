package com.kojelauta.projekti.controllers;

import java.util.List;

import com.kojelauta.projekti.entities.Userinfo;

import org.springframework.http.HttpStatus;
import com.kojelauta.projekti.repositories.UserinfoRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Taito
 */

@RestController
@RequestMapping("kojelauta")
public class UserController {
    @Autowired
    UserinfoRepo repo;

    @GetMapping
    String jokesOnYou() {
        return "Nice try! ;) nothing to see here!";
    }

    @GetMapping("/{userId}")
    List<Userinfo> getUser(@PathVariable String userId) {
        List<Userinfo> user = repo.searchUser(userId);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return user;
    }

    @PostMapping
    List<Userinfo> saveUser(@RequestBody Userinfo user) {
        List<Userinfo> users = repo.findByEmail(user.getEmail());
        if (users.isEmpty()) {
            for (int i = 1; i <= 8; i++) {
                Userinfo usertodb = new Userinfo(user.getUserId(), user.getEmail(), i);
                usertodb.setVisible(false);
                if (usertodb.getUserId().length() == 21) {
                    if (usertodb.getEmail().contains("@gmail.com") || usertodb.getEmail().contains("@google.com")) {
                        users.add(usertodb);
                    }
                }
            }
            repo.saveAllAndFlush(users);
        }
        return users;
    }

    @PutMapping
    Userinfo updateUser(@RequestBody Userinfo user) {
        Userinfo updatedUser = repo.findByUserIdAndBoxId(user.getUserId(), user.getBoxId());
        updatedUser.setApiId(user.getApiId());
        if (user.getApiName().equals("weather") ||
                user.getApiName().equals("price") ||
                user.getApiName().equals("hsl") ||
                user.getApiName().equals("stock") ||
                user.getApiName().equals("joke")||
                user.getApiName().equals("textTv")) {
            updatedUser.setApiName(user.getApiName());
        }
        updatedUser.setBoxId(user.getBoxId());
        updatedUser.setApiValue(user.getApiValue());
        updatedUser.setVisible(true);
        repo.saveAndFlush(updatedUser);
        return updatedUser;
    }

}
