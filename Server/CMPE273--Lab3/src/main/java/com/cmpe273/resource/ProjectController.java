package com.cmpe273.resource;
import com.cmpe273.repository.UserRepository;
import com.cmpe273.repository.Skillrepository;
import com.cmpe273.repository.ProjectRepository;
import com.cmpe273.model.User;
import com.cmpe273.model.Skill;
import com.cmpe273.model.Project;
//import com.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.transaction.Transactional;
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

    @PostMapping(path="/postproject",consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
    public ResponseEntity<?> postproject (@RequestBody Project project) {
        System.out.println("Post Project");
        projectRepository.save(project);
        System.out.println("Saved");
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("success",  true);
        json.put("message", "Posted Project Successfully");
        return new ResponseEntity(json, null,HttpStatus.OK);
    }

    @GetMapping(path="/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable<Project> getAllProjects() {
        // This returns a JSON with the users
        return projectRepository.findAll();
    }


    @PostMapping(value = "/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> logout() {
//        System.out.println(session.getAttribute("name"));
//        session.invalidate();
        return  new ResponseEntity(HttpStatus.OK);
    }
}