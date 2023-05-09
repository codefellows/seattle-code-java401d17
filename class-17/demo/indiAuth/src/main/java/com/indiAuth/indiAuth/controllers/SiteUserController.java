package com.indiAuth.indiAuth.controllers;

import com.indiAuth.indiAuth.models.SiteUser;
import com.indiAuth.indiAuth.repos.SiteUserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.print.attribute.standard.PrinterInfo;
import java.security.Principal;
import java.time.LocalDate;

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

    throw new RuntimeException("It's a 404!");
    // throw new ResourceNotFoundException("It's a 404!");

    // return "index.html";
  }

  // Lines below create custom exception
  // @ResponseStatus(value = HttpStatus.NOT_FOUND)
  // public class ResourceNotFoundException extends RuntimeException
  // {
  //   ResourceNotFoundException(String message)
  //   {
  //     super(message);
  //   }
  // }

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
    newUser.setDateCreated(LocalDate.now());

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

  @GetMapping("/test")
  public String getTestPage(Model m, Principal p) {
    if(p != null) {
      String username = p.getName();
      SiteUser user = siteUserRepository.findByUsername(username);

      if(user != null) {
        m.addAttribute("username", user.getUsername());
      }
    }

    return "test.html";
  }

  @GetMapping("/user/{id}")
  public String getUserInfoPage(Model m, Principal p, @PathVariable long id) {
    if(p != null) { //not strictly needed if WebSecurityConfig is set up properly
      String username = p.getName();
      SiteUser browsingUser = siteUserRepository.findByUsername(username);
      m.addAttribute("username", browsingUser.getUsername());
    }

    SiteUser profileUser = siteUserRepository.findById(id).orElseThrow();
    m.addAttribute("profileUsername", profileUser.getUsername());
    m.addAttribute("profileId", profileUser.getId());
    m.addAttribute("profileDateCreated", profileUser.getDateCreated());

    return "user-info.html";
  }

  @PutMapping("/user/{id}")
  public RedirectView updateUserInfo(Model m, Principal p, @PathVariable Long id, String profileUsername,
                                     RedirectAttributes redir) {
    SiteUser userToBeEdited = siteUserRepository.findById(id).orElseThrow();
    if(p != null && p.getName().equals(userToBeEdited.getUsername())) {
      userToBeEdited.setUsername(profileUsername);
      siteUserRepository.save(userToBeEdited);

      // include lines below if your principal is not updating
      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userToBeEdited, userToBeEdited.getPassword(),
        userToBeEdited.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
    } else {
      redir.addFlashAttribute("errorMessage", "Cannont edit another user's page!");
    }

    return new RedirectView("/user/"+id);
  }

}
