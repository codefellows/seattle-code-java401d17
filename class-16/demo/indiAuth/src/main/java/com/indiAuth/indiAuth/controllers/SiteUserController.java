package com.indiAuth.indiAuth.controllers;

import com.indiAuth.indiAuth.models.SiteUser;
import com.indiAuth.indiAuth.repos.SiteUserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

// Step 2: CheatSheet 2: Create a user controller
@Controller
public class SiteUserController {
  @Autowired
  SiteUserRepository siteUserRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  private HttpServletRequest request;

  // Step 8B: CheatSheet 8, part 1: get mapping for basic splash page
  @GetMapping("/")
  public String getHomePage(Model m, Principal p) {
    // Step 8C: CheatSheet 8, part 2: grab principal to display user information
    if (p != null) {
      String username = p.getName();
      SiteUser user = siteUserRepository.findByUsername(username);

      m.addAttribute("username", username);
    }
    return "index.html";
  }

  // Step 3: CheatSheet 7, part1: Make a dummy login page
  @GetMapping("/login")
  public String getLoginPage() {
    return "/login.html";
  }

  // Step 7B: CheatSheet 6: implement sign up page (get mapping)
  @GetMapping("/signup")
  public String getSignUpPage() {
    return "signup.html";
  }

  // Step 7C: CheatSheet 6: implement sign up page (post mapping)
  @PostMapping("/signup")
  public RedirectView createUser(String username, String password) {
    SiteUser newUser = new SiteUser();
    newUser.setUsername(username);
    String encryptedPassword = passwordEncoder.encode(password);
    newUser.setPassword(encryptedPassword);

    siteUserRepository.save(newUser);
    authWithHttpServletRequest(username, password);
    return new RedirectView("/");
  }

  public void authWithHttpServletRequest(String username, String password) {
    try {
      request.login(username, password);
    } catch(ServletException e) {
      System.out.println("Error while logging in!");
      e.printStackTrace();
    }
  }



}
