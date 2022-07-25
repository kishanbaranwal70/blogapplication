package mainpackage.controller;

import mainpackage.model.LoginRequest;
import mainpackage.model.LoginResponse;
import mainpackage.model.SignupResponse;
import mainpackage.model.User;
import mainpackage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value="/loginreq", consumes="application/json",produces="application/json")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){

       LoginResponse loginResponse = userService.authenticate(loginRequest);
       if(loginResponse.isStatus()){
           return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
        }
       else {
           return new ResponseEntity<LoginResponse>(loginResponse,HttpStatus.BAD_REQUEST);
       }
    }

    @PostMapping(value= "/signup",consumes="application/json",produces = "application/json")
    public SignupResponse signup(@RequestBody User user){
        SignupResponse signupResponse = userService.register(user);
        return signupResponse;
    }

    
    //http://localhost:3200/article?id:101
    @GetMapping(value="/article")
    public String reqParamDemo(@RequestParam int id){
        return "id is" +id;
    }


    //http://localhost:3200/article/get/101
    @GetMapping(value="/article/get/{id}")
    public String pathVariableDemo(@PathVariable int id)
    {
        return "id is" + id;
    }
}


