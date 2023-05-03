package com.indibean.salmonCookiesD17.controllers.salmonCookies;

import com.indibean.salmonCookiesD17.models.Employee;
import com.indibean.salmonCookiesD17.models.SalmonCookieStore;
import com.indibean.salmonCookiesD17.repository.EmployeeRepository;
import com.indibean.salmonCookiesD17.repository.SalmonCookieStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class EmployeeController {
  @Autowired
  SalmonCookieStoreRepository salmonCookieStoreRepository;
  @Autowired
  EmployeeRepository employeeRepository;

  /**
  * associated with forms on splash page
   * creates a new employee which is associated
   * with the store that the form is under
   * will redirect back to the splash page which
   * should show the new employee
   **/
  @PostMapping("/employee/add")
  // ResponseEntity<Employee> <--- best used for debugging purposes, consider using with postman!
  public RedirectView addEmployee(String name, Integer payPerHour, String storeName) {
    SalmonCookieStore store;

    // Check and create a store given its ID (as opposed to name)
    // if(salmonCookieStoreRepository.findById(storeId).isPresent()) {
    //   store = salmonCookieStoreRepository.findById(storeId).get();
    // } else {
    //   throw new IllegalArgumentException("Could not find cookie store for this employee in db!");
    // }

    // Check and create a store given its name
    if(salmonCookieStoreRepository.findByName(storeName) != null) {
      store = salmonCookieStoreRepository.findByName(storeName);
    } else {
      throw new IllegalArgumentException("Could not find cookie store for this employee in db!");
    }

    // create new employee object and save it to our database with teh help of employeeRepository
    Employee employee = new Employee(name, payPerHour, store);
    employeeRepository.save(employee);

    // best used for debugging purposes, consider using with postman!
    // return new ResponseEntity<Employee>(employee, HttpStatus.OK);

    // returning a redirect back to our splash page
    return new RedirectView("/");
  }
}
