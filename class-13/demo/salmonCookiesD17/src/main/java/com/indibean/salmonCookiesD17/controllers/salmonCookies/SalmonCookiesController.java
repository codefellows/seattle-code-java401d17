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

  // *****GET Mappings*****
  /**
   * splash page
   * displays all cookie stores
   * if there is a store named "tampa" shows it again after list
   **/
  @GetMapping("/")
  public String getsSplashCookies(Model m) {
    List<SalmonCookieStore> stores = salmonCookieStoreRepository.findAll();
    m.addAttribute("stores", stores);

    SalmonCookieStore newStore = salmonCookieStoreRepository.findByName("tampa");
    m.addAttribute("storeName", newStore.getName());
    m.addAttribute("storeSales", newStore.getAverageCookiesPerDay());

    return "salmonCookies/salmon-cookies-stores.html";
  }

  /**
   * returns page displaying name of {cookie} from the path
   * adds description for cookie is specified in query param
   * otherwise gives default value
   * route used to show VERY basic styling of a webpage
   **/
  // localhost:8080/cookies/chocolate%20chip?cookieString=%20is%20yummy
  @GetMapping("/cookies/{cookieName}")
  public String getNamedCookies(Model m,
                                @PathVariable String cookieName,
                                @RequestParam(required = false, defaultValue = "is my favorite") String cookieString) {
    m.addAttribute("name", cookieName + cookieString);
    System.out.println(cookieName);
    return "/salmonCookies/salmonCookies.html";
  }

  /**
   * returns page containing form for updating a store
   **/
  @GetMapping("/cookie-store/update")
  public String getUpdateStorePage() {
    return "/salmonCookies/salmon-cookie-update.html";
  }

  /**
   * returns basic, empty page used to demonstrate styling
   **/
  @GetMapping("/cookies")
  public String getCookies() {
    return "/salmonCookies/salmonCookies.html";
  }


  // *****PUT Mappings*****
  /**
   * no associated form,
   * expect posts to be created from this path via Postman
   * returns splash page after post completes
   **/
  @PostMapping("/")
  public RedirectView createSalmonCookieStore(String name, Integer averageCookiesPerDay) {
    SalmonCookieStore newStore = new SalmonCookieStore(name, averageCookiesPerDay);
    salmonCookieStoreRepository.save(newStore);

    return new RedirectView("/");
  }

  /**
   * associated with form on splash page,
   * creates new store with generated id
   * redirects to splash page after post completes
   **/
  @PostMapping("/cookie-store/")
  public RedirectView addStoreFromForm(String name, Integer averageCookiesPerDay) {
    SalmonCookieStore newStore = new SalmonCookieStore(name, averageCookiesPerDay);
    salmonCookieStoreRepository.save(newStore);

    return new RedirectView("/");
  }

  /**
   * associated with form on "/cookie-store/update",
   * updates db entry if store with id from the form exists in db,
   * otherwise creates new store with specified id
   * redirects to splash page after post completes
   **/
  @PostMapping("/cookie-store/update")
  public RedirectView updateStore(Long id, String name, Integer averageCookiesPerDay) {
    SalmonCookieStore updatedStore = new SalmonCookieStore(id, name, averageCookiesPerDay);
    salmonCookieStoreRepository.save(updatedStore);

    return new RedirectView("/");
  }
}
