package com.houstonkrohman.c4613.jpa.service;

import com.houstonkrohman.c4613.jpa.data.EmployeeRepoI;
import com.houstonkrohman.c4613.jpa.entity.*;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Stateless
//@Local(EmployeeServicesI.class)
@Path("/employee/")
public class EmployeeServices implements EmployeeServicesI
{
	@Context
	UriInfo uriInfo;

	@EJB
	EmployeeRepoI employeeRepo;

	@GET
	@Path("/getAllEmployees")
	@Produces(MediaType.APPLICATION_XML)
	public List<Employee> getEmployeeList()
	{
		List<Employee> employees = null;
		try {
			employees = employeeRepo.getAllEmployees();
		} catch (EmployeeException e) {
			e.printStackTrace();
		}
		return employees;

	}

	@GET
	@Path("/find")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseCode findEmployeeById(@QueryParam("id") String employeeId) {

		ResponseCode rs = new ResponseCode();

		MultivaluedMap<String, String> params =
				uriInfo.getQueryParameters();

		String id = params.getFirst("id");

		if (id.length() == 0){
			rs.setCode(PresentationUtil.getString("etrror.client.emptyfield.code"));
			rs.setDesc(PresentationUtil.getString("error.client.emptyfield.desc"));
		}

		//Validate Employee ID
		if(!id.matches("[A][0][0-9]{7}")){
			rs.setCode(PresentationUtil.getString("error.add.employee.invalid.code"));
			rs.setDesc(PresentationUtil.getString("error.add.employee.invalid.desc"));
			return rs;
		}

		Employee emp = employeeRepo.findEmployeeById(employeeId);
		if ( emp!= null ){
			rs.setCode( PresentationUtil.getString("find.employee.success.code"));
			Object[] args = { emp.getId(), emp.getFirstName(), emp.getLastName() };
			rs.setDesc( PresentationUtil.getString( "find.employee.success.desc", args ) );

		} else {
			rs.setCode( PresentationUtil.getString("error.find.employee.code"));
			Object[] args = {employeeId};
			rs.setDesc( PresentationUtil.getString("error.find.employee.desc", args));
		}
		return rs;
	}

	@POST
	@Path("/addEmployee")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AddEmpResponse addEmployee(Employee employee) {

		List<ResponseCode> responses = new ArrayList<ResponseCode>();
		try {
			Set<String> msgs = employeeRepo.addEmployee( employee );
			responses = buildResponseCode( msgs, "add", employee );
		}
		catch (EmployeeException e)	{
			ResponseCode rc = new ResponseCode();
			Throwable t = e.getCause();
			if  ( t instanceof EJBTransactionRolledbackException )
			{
				rc.setCode( PresentationUtil.getString( "error.add.employee.invalid.code" ) );
				rc.setDesc( PresentationUtil.getString( "error.add.employee.invalid.desc" ) );
				responses.add( rc );
			}
			else
			{
				rc.setCode( PresentationUtil.getString( "error.add.employee.duplicate.code" ) );
				rc.setDesc( PresentationUtil.getString( "error.add.employee.duplicate.desc" ) );
				responses.add( rc );
			}
		}

		AddEmpResponse addEmpResponse = new AddEmpResponse();
		addEmpResponse.setResponseCode(responses.get(0));
		addEmpResponse.setEmployee(employee);

		return addEmpResponse;
	}

	@DELETE
	@Path("/remove")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseCode removeEmployee(@QueryParam("id") String employeeID )
	{
		ResponseCode rs = new ResponseCode();

		//Check if employee exists in system
		Employee result = employeeRepo.findEmployeeById(employeeID);
		if(result == null){
			rs.setCode(PresentationUtil.getString("error.remove.employee.notfound.code"));
			rs.setDesc(PresentationUtil.getString("error.remove.employee.notfound.desc"));
			return rs;
		}

		//Remove employee
		try
		{
			employeeRepo.removeEmployee(employeeID);
		}
		catch (EmployeeException e)
		{
			rs.setCode( PresentationUtil.getString( "error.remove.employee.code" ) );
			rs.setDesc( PresentationUtil.getString( "error.remove.employee.desc" ) );
			return rs;
		}
		rs.setCode( PresentationUtil.getString( "remove.employee.success.code" ) );
		Object[] args = { result.getId(), result.getFirstName(), result.getLastName() };
		rs.setDesc( PresentationUtil.getString( "remove.employee.success.code", args ) );
		return rs;
	}

	private List<ResponseCode> buildResponseCode( Set<String> errorMsgs, String operationName, Employee employee )
	{
		List<ResponseCode> responses = new ArrayList<ResponseCode>();
		if ( errorMsgs != null && errorMsgs.size()==0 )
		{
			Object[] args = { employee.getId(), employee.getFirstName(), employee.getLastName() };

			ResponseCode rs = new ResponseCode();
			rs.setCode( PresentationUtil.getString( operationName + ".employee.success.code" ) );
			rs.setDesc( PresentationUtil.getString( operationName + ".employee.success.desc", args ) );
			responses.add(rs);
		}

		if ( errorMsgs!=null && errorMsgs.size()> 0 )
		{
			for ( String errMsg : errorMsgs )
			{
				ResponseCode rc = new ResponseCode();
				if ( errMsg.startsWith("id : ") )
				{
					String [] msgParts = errMsg.split("id : " ); //TODO lame way of getting rid of the Bean Validation prefix
					rc.setCode( PresentationUtil.getString( msgParts[1] + ".code" ) );
					rc.setDesc( PresentationUtil.getString( msgParts[1] + ".desc" ) );
				}
				else{
					rc.setCode( errMsg + ".code");
					rc.setDesc( errMsg + ".desc");
				}

				responses.add(rc);
			}

		}
		return responses;
	}

}
