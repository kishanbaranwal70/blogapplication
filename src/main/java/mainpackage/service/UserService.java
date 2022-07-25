package mainpackage.service;

import mainpackage.model.LoginRequest;
import mainpackage.model.LoginResponse;
import mainpackage.model.SignupResponse;
import mainpackage.model.User;
import mainpackage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    final String pepper = "qwerty123";
    @Autowired
    UserRepository userRepository;

//From application.properties
//    @Value("${pepper}")
//    String pepper;
    public SignupResponse register(User user){

       User currentUser = userRepository.findByEmail(user.getEmail());
        SignupResponse signupResponse =  new SignupResponse();
       if(currentUser!=null){
           signupResponse.setStatus(false);
           signupResponse.setMessage("User already Exists");
           return signupResponse;
       }
       else {
           String salt = BCrypt.gensalt();
           System.out.println(salt);
           //without pepper
           //String hashedPassword = BCrypt.hashpw(user.getPassword(),salt);

           //with pepper
           String hashedPassword = BCrypt.hashpw(user.getPassword()+pepper,salt);
           user.setPassword(hashedPassword);   //updating password with hashed password
           user.setSalt(salt);   //updating salt
           userRepository.save(user);
           signupResponse.setStatus(true);
           signupResponse.setMessage("Signup Successful");
       }

//        User newUser = userRepository.save(user);
//        SignupResponse signupResponse =  new SignupResponse();
//        if(newUser == null){
//            signupResponse.setStatus(false);
//            signupResponse.setMessage("Signup failed");
//        }
//        else{
//            signupResponse.setStatus(true);
//            signupResponse.setMessage("Signup successful");
//        }
        return signupResponse;
    }
    public LoginResponse authenticate(LoginRequest loginRequest){
        User user = userRepository.findByEmail(loginRequest.getEmail());
        LoginResponse loginResponse = new LoginResponse();


        if(user ==  null)
        {
            loginResponse.setStatus(false);
            loginResponse.setMessage("User not found! Invalid Email");
        }
        else if(user.getPassword().equals(BCrypt.hashpw(loginRequest.getPassword()+pepper,user.getSalt()))){
        //else if(user.getPassword().equals(loginRequest.getPassword())){
            loginResponse.setStatus(true);
            loginResponse.setMessage("login successful");
        }
        else {
            loginResponse.setStatus(false);
            loginResponse.setMessage("Invalid Password");
        }
        return loginResponse;
    }
}
