package com.reyjroliva.d16Security.controllers;

import com.reyjroliva.d16Security.models.SiteUser;
import com.reyjroliva.d16Security.repositories.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Date;

// TODO: Step 2: Create a controller for SiteUser Model
@Controller
public class SiteUserController {
  @Autowired
  SiteUserRepository siteUserRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  // This is how you do Auto login
  @Autowired
  HttpServletRequest request;

  // GET route /signup
  @GetMapping("/signup")
  public String getSignupPage() {
    return "signup.html";
  }

  // GET route /login
  @GetMapping("/login")
  public String getLoginPage() {
    return "login.html";
  }

  // GET route to get ONE SiteUser

  // Get route /
  // Principle == Http session == current User
  @GetMapping("/")
  public String gethome(Model m, Principal p) {
    if (p != null) {
      String username = p.getName();
      SiteUser dbUser = siteUserRepository.findUserByUsername(username);

      m.addAttribute("username", username);
      m.addAttribute("FirstName", dbUser.getFirstName());
    }

    try {

    } catch(RuntimeException e) {
      throw new RuntimeException("Oh dear, something went wrong");
    }

    return "index.html";
  }

  // Get route to /secret -> secret sauce!
  @GetMapping("/secret")
  public String getSecretSauce() {
    return "secretSauce.html";
  }

  // GET route to /users/id to get ONe user -> send this to user-info.html
  @GetMapping("/user/{id}")
  public String getOneSiteUser(@PathVariable Long id, Model m, Principal p) {
    SiteUser authenticatedUser = siteUserRepository.findUserByUsername(p.getName());
    m.addAttribute("authUser", authenticatedUser);
    // find user by ID from DB
    SiteUser viewUser = siteUserRepository.findById(id).orElseThrow();
    // attach the user to the model
    m.addAttribute("viewUser", viewUser);

    // Add usersIfollow and usersWhoFollowMe to the HTML page to be consumed
    m.addAttribute("usersIFollow", viewUser.getUsersIFollow());
    m.addAttribute("usersWhoFollowMe", viewUser.getUsersWhoFollowMe());

    return "user-info.html";
  }


  // POST route to create new SiteUser
  @PostMapping("/signup")
  public RedirectView createSiteUser(String username, String password, String firstName) {
    // hash the Password
    String hashedPW = passwordEncoder.encode(password);

    // create User
    SiteUser newUser = new SiteUser(username, hashedPW, firstName, new Date());

    // Save the User
    siteUserRepository.save(newUser);

    // AutoLogin -> httpServletRequest
    autoAuthWithHttpServletRequest(username, password);

    // Return rediectView
    return new RedirectView("/");
  }

  public void autoAuthWithHttpServletRequest(String username, String password) {
    try {
      request.login(username, password);
    } catch (ServletException se) {
      se.printStackTrace();
    }
  }

  @PutMapping("/user/{id}")
  public RedirectView editSiteUserInfo(@PathVariable long id, String username, String firstName, Principal p,
                                       RedirectAttributes redir) throws ServletException {
    // find the user we want to edit
    SiteUser userToBeEdited = siteUserRepository.findById(id).orElseThrow();
    if(p.getName().equals(userToBeEdited.getUsername())) {
      // update user
      userToBeEdited.setUsername(username);
      userToBeEdited.setFirstName(firstName);
      // save the edited user back to the DB
      siteUserRepository.save(userToBeEdited);
      request.logout(); // TODO: fix this so the session is updated!
      autoAuthWithHttpServletRequest(username, userToBeEdited.getPassword());
    } else {
      redir.addFlashAttribute("errorMessage", "Cannot edit another user's info");
    }

    return new RedirectView("/user/" + id);
  }

  @PutMapping("/follow-user/{id}")
  public RedirectView followUser(Principal p, @PathVariable Long id) {
    SiteUser userToFollow = siteUserRepository.findById(id).orElseThrow(() -> new RuntimeException("Error reading " +
      "user from the databsae with ID of: " + id));
    SiteUser browsingUser = siteUserRepository.findUserByUsername(p.getName());

    // check that the user isn't trying to follow themselves
    if(browsingUser.getUsername().equals(userToFollow.getUsername())) {
      throw new IllegalArgumentException("Following yourself is a bad idea!");
    }

    // access followers from browsingUser and update with new userToFollow
    browsingUser.getUsersIFollow().add(userToFollow);


    // save to the db
    siteUserRepository.save(browsingUser);


    return new RedirectView("/user/" + id);
  }
}
