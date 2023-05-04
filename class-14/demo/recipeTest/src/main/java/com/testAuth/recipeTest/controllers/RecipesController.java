package com.testAuth.recipeTest.controllers;

import com.testAuth.recipeTest.models.SiteUser;
import com.testAuth.recipeTest.repos.SiteUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecipesController {
  @Autowired
  SiteUserRepository siteUserRepository;

  @GetMapping("/recipe")
  public String getRecipe(HttpServletRequest request, Model m) {
    // create HttpSession
    HttpSession session = request.getSession();
    String userName = session.getAttribute("userName").toString();
    //authenticate user
    if(userName != null) {
      m.addAttribute("userName", userName);
      SiteUser siteUser = siteUserRepository.getSiteUserByUserName(userName);
      // m.addAttribute("recipes", siteUser.getRecipes());
      // we need to create recipes and store them with users to show them here
      return "recipes.html";
    }

    // if no user session
    return "login.html";
  }
}
