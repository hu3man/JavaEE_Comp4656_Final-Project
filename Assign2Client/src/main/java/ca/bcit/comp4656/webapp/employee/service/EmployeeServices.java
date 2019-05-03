package ca.bcit.comp4656.webapp.employee.service;

import java.util.List;

import ca.bcit.comp4656.jpa.entity.Employee;
import ca.bcit.comp4656.jpa.entity.ResponseCode;

public interface EmployeeServices
{
	static final String EMP_ID_PATTERN="[A][0][0-9]{7}";
	List<Employee> getEmployeeList();
	ResponseCode findEmployeeById( String id );
	ResponseCode addEmployee(Employee employee );
	ResponseCode removeEmployee( Employee employee );
	boolean serviceIsUp();
}
