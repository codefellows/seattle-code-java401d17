package com.indibean.salmonCookiesD17.controllers.salmonCookies;

import com.indibean.salmonCookiesD17.models.SalmonCookieStore;
import com.indibean.salmonCookiesD17.repository.SalmonCookieStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class SalmonCookiesController {
  // 6. wire our repository to our controller
  @Autowired
  SalmonCookieStoreRepository salmonCookieStoreRepository;


  @GetMapping("/cookies")
  public String getCookies() {
    return "/salmonCookies/salmonCookies.html";
  }

  @GetMapping("/")
  public String getsSplashCookies(Model m) {
    List<SalmonCookieStore> stores = salmonCookieStoreRepository.findAll();
    m.addAttribute("stores", stores);

    SalmonCookieStore newStore = salmonCookieStoreRepository.findByName("tampa");
    m.addAttribute("storeName", newStore.getName());
    m.addAttribute("storeSales", newStore.getAverageCookiesPerDay());

    return "salmonCookies/salmon-cookies-stores.html";
  }

  // localhost:8080/cookies/chocolate%20chip?cookieString=%20is%20yummy
  @GetMapping("/cookies/{cookieName}")
  public String getNamedCookies(Model m,
                                @PathVariable String cookieName,
                                @RequestParam(required = false, defaultValue = "is my favorite") String cookieString) {
    m.addAttribute("name", cookieName + cookieString);
    System.out.println(cookieName);
    return "/salmonCookies/salmonCookies.html";
  }

  @PostMapping("/")
  public RedirectView createSalmonCookieStore(String name, Integer averageCookiesPerDay) {
    SalmonCookieStore newStore = new SalmonCookieStore(name, averageCookiesPerDay);
    salmonCookieStoreRepository.save(newStore);

    return new RedirectView("/");
  }

  @PostMapping("/cookie-store/")
  public RedirectView addStoreFromForm(String name, Integer averageCookiesPerDay) {
    SalmonCookieStore newStore = new SalmonCookieStore(name, averageCookiesPerDay);
    salmonCookieStoreRepository.save(newStore);

    return new RedirectView("/");
  }

  @GetMapping("/cookie-store/update")
  public String getUpdateStorePage() {
    return "/salmonCookies/salmon-cookie-update.html";
  }

  @PostMapping("/cookie-store/update")
  public RedirectView updateStore(Long id, String name, Integer averageCookiesPerDay) {
    SalmonCookieStore updatedStore = new SalmonCookieStore(id, name, averageCookiesPerDay);
    salmonCookieStoreRepository.save(updatedStore);

    return new RedirectView("/");
  }
}
