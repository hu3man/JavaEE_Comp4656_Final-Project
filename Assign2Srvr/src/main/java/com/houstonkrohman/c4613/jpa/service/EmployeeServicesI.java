package com.houstonkrohman.c4613.jpa.service;

import com.houstonkrohman.c4613.jpa.entity.AddEmpResponse;
import com.houstonkrohman.c4613.jpa.entity.Employee;
import com.houstonkrohman.c4613.jpa.entity.ResponseCode;

import java.util.List;

public interface EmployeeServicesI
{
	static final String EMP_ID_PATTERN="[A][0][0-9]{7}";
	List<Employee> getEmployeeList();
	ResponseCode findEmployeeById(String id);
	AddEmpResponse addEmployee(Employee employee);
	ResponseCode removeEmployee(String employeeID);
}
