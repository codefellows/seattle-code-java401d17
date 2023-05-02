package com.indibean.salmonCookiesD17.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TeachingController {
  @ResponseBody
  @GetMapping("/hello-world")
  public String testMethodHere() {
    return "Hello world!";
  }

  // @GetMapping("/")
  // public String renderBffPage(Model m) {
  //   m.addAttribute("name", "Indi");
  //   m.addAttribute("years", "3");
  //   m.addAttribute("occupation", "Sleep");
  //   return "/teaching/best-friend.html";
  // }
}
