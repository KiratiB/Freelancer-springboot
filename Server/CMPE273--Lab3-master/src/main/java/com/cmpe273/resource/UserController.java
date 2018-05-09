package com.cmpe273.resource;
import com.cmpe273.repository.UserRepository;
import com.cmpe273.repository.Skillrepository;
import com.cmpe273.model.User;
import com.cmpe273.model.Skill;
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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Skillrepository skillrepository;

    @PostMapping(path="/login",consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
    public ResponseEntity<?> login (@RequestBody User user)  throws NoSuchAlgorithmException  {

        MessageDigest messageDigest =null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        messageDigest.update(user.getPassword().getBytes(),0,user.getPassword().length());
        String md5String = new BigInteger(1,messageDigest.digest()).toString(16);
        user.setPassword(md5String);

        List<User> us = userRepository.findByEmailAndPassword(user.getEmail(),user.getPassword());
        if(us.isEmpty()){
            System.out.println("Not found user");
            Map<String, Object> json = new HashMap<String, Object>();
            json.put("success",  false);
            json.put("message", "Login unuccessful");
            return new ResponseEntity(json, HttpStatus.OK);
            //json.put("userId", user.getUserId());
        }else{
            Map<String, Object> json = new HashMap<String, Object>();
            json.put("success",  true);
            json.put("message", "Login Successful");
            json.put("userId", us.get(0).getUserId());
            return new ResponseEntity(json,HttpStatus.OK);
        }

    }


    @PostMapping(path="/signup",consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
    public ResponseEntity<?> addNewUser (@RequestBody User user) throws NoSuchAlgorithmException  {

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(user.getPassword().getBytes(),0,user.getPassword().length());
        String md5String = new BigInteger(1,messageDigest.digest()).toString(16);
        user.setPassword(md5String);
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

    //used for set profile and update profile
    @PostMapping(path="/setProfile",consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
    public ResponseEntity<?> setProfile (@RequestParam MultipartFile file,@RequestBody User user) {

        System.out.println("Set Profile");
        System.out.println(user.getUserId());
        List<User> u = userRepository.findByUserId(user.getUserId());
        u.get(0).setFirstname(user.getFirstname());
        u.get(0).setLastname(user.getLastname());
        u.get(0).setEmail(user.getEmail());
        u.get(0).setPhone(user.getPhone());
        u.get(0).setProf_headline(user.getProf_headline());
        u.get(0).setUserskills(user.getUserskills());
        u.get(0).setFile(file);
        userRepository.save(u);

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
        System.out.println("Inside all uses");
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



}