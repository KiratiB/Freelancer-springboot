package com.cmpe273.resource;
import com.cmpe273.model.User;
import com.cmpe273.repository.ProjectBidRepository;
import com.cmpe273.repository.UserRepository;
import com.cmpe273.repository.Skillrepository;
import com.cmpe273.repository.ProjectRepository;
import com.cmpe273.model.ProjectBid;
import com.cmpe273.model.Skill;
import com.cmpe273.model.Project;
//import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller    // This means that this class is a Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/project") // This means URL's start with /demo (after Application path)
public class ProjectController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Skillrepository skillrepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectBidRepository projectBidRepository;

    @PostMapping(path="/postproject",consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
    public ResponseEntity<?> postproject (@RequestParam MultipartFile file, @RequestBody Project project) {
        System.out.println("Post Project");
        projectRepository.save(project);
        System.out.println("Saved");
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("success",  true);
        json.put("message", "Posted Project Successfully");
        return new ResponseEntity(json, null,HttpStatus.OK);
    }

    @PostMapping(path="/savebid",consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
    public ResponseEntity<?> savebid (@RequestBody ProjectBid bid) {
        System.out.println("Save bid");
        projectBidRepository.save(bid);
        System.out.println("Saved");
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("success",  true);
        json.put("message", "Bid added successfully");
        return new ResponseEntity(json, null,HttpStatus.OK);
    }

    @PostMapping(path="/fetchProjectBidder",consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
    public ResponseEntity<?> fetchProjectBidder (@RequestBody ProjectBid bid) {
        System.out.println("Fetch project users");
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        List<ProjectBid> u = projectBidRepository.findByProjectId(bid.getProjectId());
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("users",  u);
        return new ResponseEntity(u, HttpStatus.OK);
    }

    @GetMapping(path="/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable<Project> getAllProjects() {
        // This returns a JSON with the users
        return projectRepository.findAll();
    }
//
//    @PostMapping(value = "/logout")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public ResponseEntity<?> logout() {
////        System.out.println(session.getAttribute("name"));
////        session.invalidate();
//        return  new ResponseEntity(HttpStatus.OK);
//    }

    @PostMapping(path="/fetchmypostedProjects",consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
    public ResponseEntity<?> fetchmypostedProjects (@RequestBody Project project) {
        System.out.println("Fetch posted project");
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        List<Project> p = projectRepository.findByUserId(project.getUserId());
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("projects",  p);
        return new ResponseEntity(p, HttpStatus.OK);
    }
}