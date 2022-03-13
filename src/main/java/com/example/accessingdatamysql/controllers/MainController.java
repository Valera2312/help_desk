package com.example.accessingdatamysql.controllers;
import com.example.accessingdatamysql.models.Executor;
import com.example.accessingdatamysql.models.RepairTeam;
import com.example.accessingdatamysql.models.Request;
import com.example.accessingdatamysql.models.Users;
import com.example.accessingdatamysql.repo.ExecutorsRepo;
import com.example.accessingdatamysql.repo.RepairTeamRepo;
import com.example.accessingdatamysql.repo.RequestRepo;
import com.example.accessingdatamysql.repo.UserRepo;
import com.example.accessingdatamysql.services.RequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.*;

@Controller
public class MainController {
    @Value("${upload.path}")
    private String uploadPath;

    Principal principal;
    @Autowired
    RequestRepo requestRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    ExecutorsRepo executorsRepo;
    @Autowired
    RepairTeamRepo repairTeamRepo;
    @Autowired
    RequestsService requestsService;



    @GetMapping(path = "/")
    public String main(Model model,
                       Principal principal,
                       Authentication auth,
                       @RequestParam(value = "pageNo",required = false) Integer pageNo,
                       @RequestParam(value = "sortField",required = false) String sortField,
                       @RequestParam(value = "sortDir",required = false) String sortDirection) {

        if(pageNo == null){
            pageNo = 1;
        }
        if(sortField == null){
            sortField = "id";
        }
        if(sortDirection == null){
            sortDirection = "asc";
        }

        //запросы
        Iterable<Request> requests = this.requestsService.AllRequests(); //requestRepo.findAll();
        Users user = userRepo.findByUsername(principal.getName()); //need to make service
        //List<Request> requestsByUsers = requestsService.findAllByUser_id(user.getId());


        Iterable<Executor> allExecutors = executorsRepo.findAll();//need to make service

        //---------------

        //-----pagination
        int pageSize = 5;


        Page<Request> page = requestsService.findPaginated(pageNo,pageSize, sortField, sortDirection);
        Page<Request> requestsByUsersPage = requestsService.findPaginatedByUserId(pageNo,pageSize,user.getId(),sortField,sortDirection);

        List<Request> requestList = page.getContent();
        List<Request> requestsByUsers = requestsByUsersPage.getContent();

        model.addAttribute("currentPage",pageNo);

        int totalPages = page.getTotalPages();
        if(page.getTotalPages() == 0) {
            totalPages += 1;
        }
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("listRequest",requestList);

        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDirection",sortDirection);
        model.addAttribute("reverseSortDir",sortDirection.equals("asc") ? "desc" : "asc");
        //-----

        model.addAttribute("RequestsByUser", requestsByUsers);
        model.addAttribute("user", principal.getName());
        //model.addAttribute("role", auth.getAuthorities());
        model.addAttribute("allExecutors", allExecutors);

        return "main";
    }

    @PostMapping(path = "request/add")

    public String RequestAdd(Principal principal, @RequestParam MultipartFile[] files,
                             @RequestParam  String name,
                             @RequestParam  String description,
                             @RequestParam  String initiator,
                             @RequestParam  String deadline

    ) throws IOException, ParseException {

        //запросы
        Users user = userRepo.findByUsername(principal.getName());
        //--------------

        Request request = new Request(name,description,initiator,user.getId(),deadline);
        StringBuilder listFiles = new StringBuilder();

        if (files != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            for(MultipartFile file : files) {
                String uuidFile = UUID.randomUUID().toString();
                String resultFileName = uuidFile + "." + file.getOriginalFilename();
                file.transferTo(new File(uploadPath + "/" + resultFileName));
                listFiles.append(resultFileName).append(";");

            }
        }
        request.setFilename(listFiles.toString());
        requestRepo.save(request);
        return "redirect:/";
    }

    @GetMapping(path = "/delete/{id}")
    public String RequestDetails(Model model, @PathVariable(value = "id") long id) {

        requestRepo.deleteById(id);

        return "redirect:/";
    }

    @GetMapping(path = "/changePriority/{id}")
    public String changePriority(HttpServletRequest request, @PathVariable(value = "id") long id) {
        String priority =  request.getParameter("priority");
        requestsService.UpdatePriority(priority,id);
        return "redirect:/";
    }

    @GetMapping(path = "/status/{id}")
    public String changeStatus(HttpServletRequest request, @PathVariable(value = "id") long id) {
        String status =  request.getParameter("status");
        requestsService.UpdateStatus(status,id);
        return "redirect:/";
    }
    @GetMapping(path = "/comment/{id}")
    public String changeComment(HttpServletRequest request, @PathVariable(value = "id") long id) {
        String comment =  request.getParameter("comment");
        requestsService.UpdateComment(comment,id);
        return "redirect:/";
    }





    @GetMapping(path = "/detail/{id}")
    public String getDetail(Model model,@PathVariable(value = "id") long id,Principal principal) {
        //Users user = userRepo.findByUsername(principal.getName());
        Request request = requestRepo.findById(id);
        String[] files =  request.getFilename().split(";");
        model.addAttribute("files",files);
        model.addAttribute("request",request);
        model.addAttribute("user",principal.getName());
        return "detailsRequest";
    }

    @PostMapping(path = "/addExecutor/{id}")
    public String addExecutors(@PathVariable(value = "id") long id,@RequestParam Map<String, String> parameters) {


        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            RepairTeam repairTeam = new RepairTeam(Integer.parseInt(entry.getValue()),id);
            repairTeamRepo.save(repairTeam);
        }

        return "redirect:/";
    }


}