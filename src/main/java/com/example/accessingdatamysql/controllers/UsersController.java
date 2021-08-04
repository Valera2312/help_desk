package com.example.accessingdatamysql.controllers;

import com.example.accessingdatamysql.models.Request;
import com.example.accessingdatamysql.models.Users;
import com.example.accessingdatamysql.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class UsersController {
    @Autowired
    UserRepo userRepo;

    @GetMapping(path = "/admin/users")
    public String mainUsers(Model model) {

        Iterable<Users> Users = userRepo.findAll();
        model.addAttribute("Users", Users);

        return "users";
    }
}


