package com.houstonkrohman.c4613.jpa.data;

import java.util.List;
import java.util.Set;

import com.houstonkrohman.c4613.jpa.entity.Employee;
import com.houstonkrohman.c4613.jpa.entity.EmployeeException;

import javax.ejb.Local;

@Local
public interface EmployeeRepoI {
	List<Employee> getAllEmployees() throws EmployeeException;
	Employee findEmployeeById( String id );
	void removeEmployee (String employeeID) throws EmployeeException;
	Set<String> addEmployee(Employee employee) throws EmployeeException;
}
