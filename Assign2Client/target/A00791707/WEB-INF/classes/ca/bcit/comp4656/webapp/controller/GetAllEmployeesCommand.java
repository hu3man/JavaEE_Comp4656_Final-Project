package ca.bcit.comp4656.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class GetAllEmployeesCommand extends AbstractCommand
{
	Logger logger = Logger.getLogger( GetAllEmployeesCommand.class );

	@Override
	public void execute( HttpServletRequest request )
	{
		// nothing needs to be done here since list of employees are handled in the filter class
	}
}
