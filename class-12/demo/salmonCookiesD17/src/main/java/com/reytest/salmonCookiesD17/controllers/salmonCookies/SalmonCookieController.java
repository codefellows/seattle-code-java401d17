package com.reytest.salmonCookiesD17.controllers.salmonCookies;

import com.reytest.salmonCookiesD17.models.SalmonCookieStore;
import com.reytest.salmonCookiesD17.repositories.SalmonCookiesStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class SalmonCookieController {
  //6. Wire up the repository
  @Autowired
  SalmonCookiesStoreRepository salmonCookiesStoreRepository;

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

  @PostMapping("/")
  public RedirectView createSalmonCookieStore(String name, Integer averageCookiesPerDay) {
    SalmonCookieStore newStore = new SalmonCookieStore(name, averageCookiesPerDay);
    salmonCookiesStoreRepository.save(newStore);

    return new RedirectView("/");
  }

  @GetMapping("/")
  public String getSalmonCookieStores(Model m) {
    List<SalmonCookieStore> stores = salmonCookiesStoreRepository.findAll();
    m.addAttribute("stores", stores);

    SalmonCookieStore salmonCookiesStore = salmonCookiesStoreRepository.findByName("Best Salmon Cookies");
    m.addAttribute("store", salmonCookiesStore);

    return "salmonCookies/salmon-cookies-stores.html";
  }

  @PostMapping("/cookie-store/")
  public RedirectView addStoreForm(String name, Integer averageCookiesPerDay) {
    SalmonCookieStore newStore = new SalmonCookieStore(name, averageCookiesPerDay);
    salmonCookiesStoreRepository.save(newStore);

    return new RedirectView("/");
  }

  @DeleteMapping("/cookie-store/{id}")
  public RedirectView deleteCookieStore(@PathVariable long id) {
    salmonCookiesStoreRepository.deleteById(id);
    return new RedirectView("/");
  }


}
