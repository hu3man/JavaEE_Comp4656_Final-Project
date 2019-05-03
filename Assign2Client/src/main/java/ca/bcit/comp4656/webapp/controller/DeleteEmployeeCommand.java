package ca.bcit.comp4656.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ca.bcit.comp4656.jpa.entity.Employee;
import ca.bcit.comp4656.jpa.entity.ResponseCode;


public class DeleteEmployeeCommand extends AbstractCommand
{
	Logger logger = Logger.getLogger( DeleteEmployeeCommand.class );
	
	@Override
	public void execute( HttpServletRequest request )
	{
		Employee employee = new Employee();
		String empId = request.getParameter( "id" );
		employee.setId( empId );
		ResponseCode responseCode = employeeServices.removeEmployee( employee );
		request.setAttribute( "delResponseCode", responseCode );
	}
}
