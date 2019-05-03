package ca.bcit.comp4656.webapp.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ca.bcit.comp4656.jpa.entity.Employee;
import ca.bcit.comp4656.jpa.entity.ResponseCode;
import ca.bcit.comp4656.webapp.employee.presentation.util.PresentationUtil;

public class AddEmployeesCommand extends AbstractCommand {

	Logger logger = Logger.getLogger( AddEmployeesCommand.class );
	@Override
	public void execute(HttpServletRequest request)
	{
		Employee emp = new Employee();
		emp.setId( request.getParameter("id") );
		emp.setFirstName( request.getParameter( "fname" ) );
		emp.setLastName( request.getParameter( "lname" ) );
		try
		{
			emp.setDob( getDob( request.getParameter("dob")));
		}
		catch ( ParseException pe )
		{
			ResponseCode rc = new ResponseCode();
			rc.setCode( PresentationUtil.getString( "error.add.employee.invalidDate.code" ) );
			rc.setDesc( PresentationUtil.getString( "error.add.employee.invalidDate.desc" ) );
			request.setAttribute( "addResponseCode", rc );
			return;
		}
		request.setAttribute( "addResponseCode", employeeServices.addEmployee( emp ) );
		
	}
	private Date getDob(String dob) throws ParseException
	{
		DateFormat format = new SimpleDateFormat( "yyyy/MM/dd" );
		return format.parse(dob);
	}

	
	
}
