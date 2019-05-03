 package ca.bcit.comp4656.webapp.controller;

import javax.servlet.http.HttpServletRequest;


public interface Command 
{

	public void execute( HttpServletRequest request ) throws Exception;
}
