package ca.bcit.comp4656.webapp.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import ca.bcit.comp4656.jpa.entity.Employee;
import ca.bcit.comp4656.webapp.employee.service.EmployeeServices;
import ca.bcit.comp4656.webapp.employee.service.EmployeeServicesImpl;

@WebFilter( filterName="employeeFilter", urlPatterns="/*")
public class EmployeeFilter implements Filter 
{

	EmployeeServices employeeServices;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

			if (req.isUserInRole("admin")) {
			req.setAttribute("adminAuthorization", true);
		}

		if(!employeeServices.serviceIsUp()){
			resp.sendError(404, "Webservice is down, please try again later");
		}
		else {
			List<Employee> employeeList = employeeServices.getEmployeeList();
			req.setAttribute( "employees", employeeList );
		}
		chain.doFilter(req, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException
	{
		employeeServices = new EmployeeServicesImpl();
	}

	public void destroy()
	{
		// TODO Auto-generated method stub
		
	}

}
