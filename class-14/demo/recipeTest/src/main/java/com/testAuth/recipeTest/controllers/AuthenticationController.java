package com.testAuth.recipeTest.controllers;

import com.testAuth.recipeTest.models.SiteUser;
import com.testAuth.recipeTest.repos.SiteUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AuthenticationController {
  @Autowired
  SiteUserRepository siteUserRepository;

  @GetMapping("/")
  public String getHome() {
    return "login.html";
  }

  @PostMapping("/signup")
  public RedirectView signUp(String userName, String password) {
    // hash pw
    password = BCrypt.hashpw(password, BCrypt.gensalt(10));
    // new User instance with hashed PW
    SiteUser newuser = new SiteUser(userName, password);
    //save to DB
    siteUserRepository.save(newuser);
    //Redirect
    return new RedirectView("/");
  }

  @PostMapping("/login")
  public RedirectView login(HttpServletRequest request, String userName, String password) {
    // Find by username
    SiteUser siteUser = siteUserRepository.getSiteUserByUserName(userName);
    //conditional: checking is we have a siteUser
    //1. is user null? -> login.html
    if(siteUser != null) {
      //2. compare DB passowrd to given
      if(BCrypt.checkpw(password, siteUser.getPassword())) {
        //generate a secure session
        HttpSession session = request.getSession();
        session.setAttribute("userName", userName);
        //return recipe
        return new RedirectView("/recipe");
      }
      return new RedirectView("/?message=Bad Password");
    }
    //return bad password or bad user
    return new RedirectView("/?message=No User");
  }
}
