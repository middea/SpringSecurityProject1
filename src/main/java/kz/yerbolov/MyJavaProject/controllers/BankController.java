package kz.yerbolov.MyJavaProject.controllers;

import kz.yerbolov.MyJavaProject.services.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/bank")
public class BankController {
    @Autowired
    private MyUserService userService;
    @GetMapping(value = "/home")
    public String openHome(){
        return "homepage";
    }
    @PreAuthorize("isAnonymous()")
    @GetMapping(value = "/login")
    public String openLogin(){
        return "login";
    }
    @PreAuthorize("isAnonymous()")
    @GetMapping(value = "/register")
    public String openRegister(){
        return "register";
    }
    @PreAuthorize("isAnonymous()")
    @PostMapping(value = "/sign-up")
    public String postSignUp(@RequestParam(name = "fullName") String fullName,
                             @RequestParam(name = "email") String email,
                             @RequestParam(name = "password") String password,
                             @RequestParam(name = "re-password") String rePassword){
        String checkFlag = userService.signUpUser(fullName, email, password, rePassword);
        if(checkFlag.equals("userExist")){
            return "redirect:register?userExist";
        }
        else if(checkFlag.equals("passwordNotMatch")){
            return "redirect:register?passwordNotMatch";
        }
        else {
            return "redirect:login";
        }
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/profile")
    public String openProfile(){
        return "profile";
    }
}
