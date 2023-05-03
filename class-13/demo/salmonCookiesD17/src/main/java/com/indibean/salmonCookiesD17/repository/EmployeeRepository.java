package com.indibean.salmonCookiesD17.repository;

import com.indibean.salmonCookiesD17.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
