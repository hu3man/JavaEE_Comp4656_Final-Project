package ca.bcit.comp4656.webapp.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import org.apache.log4j.Logger;

import ca.bcit.comp4656.webapp.employee.service.EmployeeServices;
import ca.bcit.comp4656.webapp.employee.service.EmployeeServicesImpl;


/**
 * Servlet implementation class EmployeeServlet
 */
public class EmployeeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1380213578838938048L;
	private static final String COMMANDS_FILE = "/data/commands.properties";
	
	// Initial command
	private static final String SERVICE_INIT_ACTION = "service.getEmployees";
	
	private Logger logger = Logger.getLogger( EmployeeServlet.class );
	private static Properties commandsProps;
	
	@Override
	public void init() throws ServletException 
	{
		super.init();
		commandsProps = new Properties();
		try 
		{
			commandsProps.load( new FileInputStream(getServletContext().getRealPath( COMMANDS_FILE ) ) );
			
		}
		catch (FileNotFoundException e) 
		{
			logger.fatal("Can't find " + COMMANDS_FILE, e );
		} 
		catch (IOException e) 
		{
			logger.fatal( "Problem loading " + COMMANDS_FILE , e );
		}
		catch ( Exception e )
		{
			logger.fatal( "something went wrong " + e );
		}
	};
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String action = request.getParameter( "action" );
		try 
		{	
			/* Locate the command and execute it */
			Command c = getCommand( action );
			c.execute(request);
			response.setCharacterEncoding( "UTF-8" );
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} 
		catch ( Exception e ) 
		{
			logger.error( "An exception occurred processing an action request : " + e );
			request.setAttribute("errorTitle", e );
			request.setAttribute( "errorMsg", e.getCause()!=null?e.getCause().getMessage():e.getMessage() );
			e.printStackTrace();
			request.getRequestDispatcher("/errors.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}


	private Command getCommand(String action) throws Exception 
	{

		/* First time in, call initialisation command */
		if ( action == null ) 
		{
			action = SERVICE_INIT_ACTION;
		}
		
		/* Simple factory for the commands */
		String clazz = commandsProps.getProperty( action );
		Command command  = (Command) Class.forName( clazz ).newInstance();
		
		return command;	
	}
}
