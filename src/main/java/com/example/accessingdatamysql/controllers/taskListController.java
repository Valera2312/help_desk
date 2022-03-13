package com.example.accessingdatamysql.controllers;


import com.example.accessingdatamysql.models.Executor;
import com.example.accessingdatamysql.models.RepairTeam;
import com.example.accessingdatamysql.repo.ExecutorsRepo;
import com.example.accessingdatamysql.repo.RepairTeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class taskListController {

    @Autowired
    ExecutorsRepo executorsRepo ;
    @Autowired
    RepairTeamRepo repairTeam;

    @GetMapping(path = "/lists")
    public String lists(Model model) {

        Iterable<Executor> executors = executorsRepo.findAll();
        model.addAttribute("executors", executors);
        return "taskList";
    }

    @GetMapping(path = "/list/")
    public String lists(Model model,
                        @RequestParam(value = "id",required = true) Integer id) {

        Iterable<RepairTeam> repairTeams = repairTeam.findAllByIdExecutors(id);
        model.addAttribute("repairTeams", repairTeams);
        return "list";
    }

}
