package ca.bcit.comp4656.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class FindEmployeeCommand extends AbstractCommand 
{
	Logger logger = Logger.getLogger( FindEmployeeCommand.class );
	
	@Override
	public void execute(HttpServletRequest request)
	{
		String empId = request.getParameter( "id" );
		request.setAttribute( "findResponseCode", employeeServices.findEmployeeById( empId ) );
	}
}
