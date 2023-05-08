package com.class16Pratice.class16Practice.controllers;

import com.class16Pratice.class16Practice.model.SiteUser;
import com.class16Pratice.class16Practice.repos.SiteUserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

// Step 2: CheatSheet 2: Create user repository and Controller
@Controller
public class SiteUserController {
  @Autowired
  SiteUserRepository siteUserRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  private HttpServletRequest request;

  // Step 8B: CheatSheet 8, part 2: create get mapping for splash page
  @GetMapping("/")
  public String getHomePage(Model m, Principal p) {
    // Step 8C: CheatSheet 8, part 2: grab principle to display user information
    if(p != null) {
      String username = p.getName();
      SiteUser foundUser = siteUserRepository.findByUsername(username);

      m.addAttribute("username", username);
    }
    return "index.html";
  }

  // Step 3: CheatSheet 7, part 1: Make a dummy login page
  @GetMapping("/login")
  public String getLoginPage() {
    return "login.html";
  }

  // Step 7B: CheatSheet6: implement signup page (get method)
  @GetMapping("/signup")
  public String getSignUpPage() {
    return "signup.html";
  }

  // Step 7C: CheatSheet: implement signup page (post method)
  @PostMapping("/signup")
  public RedirectView createUser(String username, String password) {
    SiteUser user = new SiteUser();
    user.setUsername(username);
    String encryptedPassword = passwordEncoder.encode(password);
    user.setPassword(encryptedPassword);

    siteUserRepository.save(user);
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
