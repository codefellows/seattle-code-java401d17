package com.indiAuth.indiAuth.controllers;

import com.indiAuth.indiAuth.models.SiteUser;
import com.indiAuth.indiAuth.repos.SiteUserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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

    //trigger a 404 exception!
    // throw new RuntimeException("It's a 404!");

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
  public String getUserInfo(Model m, Principal p, @PathVariable long id) {
    if(p != null) { //not strictly required if WebSecuirtyConfig is set up properly
      String username = p.getName();
      SiteUser browsingUser = siteUserRepository.findByUsername(username);
      m.addAttribute("username", browsingUser.getUsername());
    }

    SiteUser profileUser = siteUserRepository.findById(id).orElseThrow();
    m.addAttribute("profileUsername", profileUser.getUsername());
    m.addAttribute("profileId", profileUser.getId());
    m.addAttribute("dateCreated", profileUser.getDateCreated());
    return "user-info.html";
  }

  @PutMapping("/user/{id}")
  public RedirectView editUserInfo(Model m, Principal p, @PathVariable Long id, String username,
                                   RedirectAttributes redir) {
    SiteUser userToBeEdited = siteUserRepository.findById(id).orElseThrow();
    if(p != null && p.getName().equals(userToBeEdited.getUsername())) {
      userToBeEdited.setUsername(username);
      siteUserRepository.save(userToBeEdited);

      // include lines below if your principal is not updating
      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userToBeEdited, userToBeEdited.getPassword(),
        userToBeEdited.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
    } else {
      redir.addFlashAttribute("errorMessage", "Cannot edit another user's page!");
    }

    return new RedirectView("/user/"+id);
  }

  @DeleteMapping("/user/{id}")
  public RedirectView deleteUser(@PathVariable Long id, Principal p, RedirectAttributes redir) {
    //delete doesn't care if it exists or not
    //so we can just pass in without the need for checks
    //BUT we SHOULD do checks to make sure a user can only delete themselves...
    //so let's check the p first
    SiteUser userToDelete = siteUserRepository.findById(id).orElseThrow();
    if(p != null && p.getName().equals(userToDelete.getUsername())) {
      siteUserRepository.deleteById(id);
      //make sure the p is null after delete
      //otherwise you may have some incorrect values still stored in your session
      p = null;
    } else {
      //if a user isn't authorized to delete and they press the button
      //flash an error and keep them on the same page
      redir.addFlashAttribute("errorMessage", "Cannot delete another user's account!");
      return new RedirectView("/user/"+id);
    }

    //with a void return type we would just return an error page after delete is completed
    //let's bring users back to the homepage instead
    return new RedirectView("/");
  }

}
