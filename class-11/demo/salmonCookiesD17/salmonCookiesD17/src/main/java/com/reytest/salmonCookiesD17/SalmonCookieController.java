package com.reytest.salmonCookiesD17;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SalmonCookieController {
  @GetMapping("/cookie")
  public String getCookie() {
    return "salmonCookies/cookie.html";
  }

  @GetMapping("/cookie/{cookieName}")
  public String getNamedCookie(Model m, @PathVariable String cookieName, String occupation) {
    m.addAttribute("years","26");
    m.addAttribute("occupation", occupation);
    System.out.println(occupation);
    System.out.println(cookieName);
    return "salmonCookies/cookie.html";
  }
}
