package com.cmpe273.resource;

import com.cmpe273.repository.UserRepository;
import com.cmpe273.repository.Skillrepository;
import com.cmpe273.model.User;
import com.cmpe273.model.Skill;
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
@RequestMapping(path="/user") // This means URL's start with /demo (after Application path)
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Skillrepository skillrepository;

    @PostMapping(path="/login",consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
    public ResponseEntity<?> login (@RequestBody User user) {
        System.out.println("Login");
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        List<User> us = userRepository.findByEmailAndPassword(user.getEmail(),user.getPassword());
        if(us.isEmpty()){
            System.out.println("Not found user");
            Map<String, Object> json = new HashMap<String, Object>();
            json.put("success",  false);
            json.put("message", "Login unuccessful");
            return new ResponseEntity(json, null,HttpStatus.OK);
            //json.put("userId", user.getUserId());
        }
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("success",  true);
        json.put("message", "Login Successful");
        json.put("userId", us.get(0).getUserId());
        return new ResponseEntity(json, null,HttpStatus.OK);
    }


    @PostMapping(path="/signup",consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
    public ResponseEntity<?> addNewUser (@RequestBody User user) {
        System.out.println("Signup");
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        userRepository.save(user);
        System.out.println("Saved");
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("success",  true);
        json.put("message", "Login Successful");
        json.put("userId", user.getUserId());
        return new ResponseEntity(json, null,HttpStatus.OK);
    }


    @PostMapping(path="/fetchByUserId",consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
    public ResponseEntity<?> fetchByUserId (@RequestBody User user) {
        System.out.println("Signup");
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        List<User> u = userRepository.findByUserId(user.getUserId());
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("user",  u);
        return new ResponseEntity(u,  null,HttpStatus.OK);
    }


    @PostMapping(path="/setProfile",consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
    public ResponseEntity<?> setProfile (@RequestBody User user) {

        System.out.println("Set Profile");
        System.out.println(user.getUserId());
        List<User> u = userRepository.findByUserId(user.getUserId());
        u.get(0).setFirstname(user.getFirstname());
        u.get(0).setLastname(user.getLastname());
        u.get(0).setEmail(user.getEmail());
        u.get(0).setPhone(user.getPhone());
        u.get(0).setProf_headline(user.getProf_headline());
        u.get(0).setUserskills(user.getUserskills());
        userRepository.save(u);

//        userRepository.updateProfile(user.getUserId(),user.getFirstname(), user.getLastname(), user.getEmail(), user.getProf_headline(), user.getUserskills());
//        System.out.println("updated");
//
//
//
//        userRepository.save(user);
        System.out.println("Saved");
      Map<String, Object> json = new HashMap<String, Object>();
        json.put("success",  true);
        json.put("message", "Set Profile Successful");
        json.put("userId", user.getUserId());
        System.out.println(json);
        return new ResponseEntity(json, null,HttpStatus.OK);
    }

    @GetMapping(path="/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable<User> getAllUsers() {
        // This returns a JSON with the users
        return userRepository.findAll();
    }


    @GetMapping(path="/allskills",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable<Skill> getAllSkills() {
        System.out.println("Inside Fetch Skills");
        // This returns a JSON with the skills
        return skillrepository.findAll();
    }


//    @PostMapping(path="/signup",consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> login(@RequestBody String user, HttpSession session)
//    {
//        JSONObject jsonObject = new JSONObject(user);
//        session.setAttribute("name",jsonObject.getString("username"));
//        return new ResponseEntity(userRepository.login(jsonObject.getString("username"),jsonObject.getString("password")),HttpStatus.OK);
//    }

//    @PostMapping(value = "/logout")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public ResponseEntity<?> logout(HttpSession session) {
//        System.out.println(session.getAttribute("name"));
//        session.invalidate();
//        return  new ResponseEntity(HttpStatus.OK);
//    }
}