package com.indibean.salmonCookiesD17;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SalmonCookiesController {

  @GetMapping("/cookies")
  public String getCookies() {
    return "/salmonCookies/salmonCookies.html";
  }

  // @GetMapping("/cookies/{cookieName}")
  // public String getNamedCookies(Model m, @PathVariable String cookieName) {
  //   m.addAttribute("name", cookieName);
  //   System.out.println(cookieName);
  //   return "/salmonCookies/salmonCookies.html";
  // }

  // localhost:8080/cookies/chocolate%20chip?cookieString=%20is%20yummy
  @GetMapping("/cookies/{cookieName}")
  public String getNamedCookies(Model m,
                                @PathVariable String cookieName,
                                @RequestParam(required = false, defaultValue = "is my favorite") String cookieString) {
    m.addAttribute("name", cookieName + cookieString);
    System.out.println(cookieName);
    return "/salmonCookies/salmonCookies.html";
  }

  @GetMapping("/albums")
  public String getAlbums(Model m) {
    Album album1 = new Album()
    Album album1 = new Album()
    Album album1 = new Album()

      m.addAttributes()
      return "pathToHTMLPage";
  }
}
