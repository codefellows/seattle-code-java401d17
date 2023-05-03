package com.indibean.salmonCookiesD17.controllers.salmonCookies;

import com.indibean.salmonCookiesD17.models.Employee;
import com.indibean.salmonCookiesD17.models.SalmonCookieStore;
import com.indibean.salmonCookiesD17.repository.EmployeeRespository;
import com.indibean.salmonCookiesD17.repository.SalmonCookieStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class EmployeeController {
  @Autowired
  SalmonCookieStoreRepository salmonCookieStoreRepository;
  @Autowired
  EmployeeRespository employeeRespository;

  @PostMapping("/employee/add")
  // ResponseEntity<Employee> --- for debugging purposes
  public RedirectView addEmployee(String name, Integer pricePerHour, String storeName) {
    SalmonCookieStore store;
    System.out.println("Store name: " + storeName);
    if(salmonCookieStoreRepository.findByName(storeName) != null) {
      store = salmonCookieStoreRepository.findByName(storeName);
    } else {
      throw new IllegalArgumentException("Could not find cookie store for this employee in db!");
    }
    Employee employee = new Employee(name, pricePerHour, store);
    employeeRespository.save(employee);
    return new RedirectView("/");
    // return new ResponseEntity<Employee>(employee, HttpStatus.OK); --- for debugging purposes
  }

}
