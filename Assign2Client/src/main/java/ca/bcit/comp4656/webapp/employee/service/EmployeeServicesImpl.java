package ca.bcit.comp4656.webapp.employee.service;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import ca.bcit.comp4656.jpa.entity.AddEmpResponse;
import ca.bcit.comp4656.jpa.entity.EmployeeList;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import ca.bcit.comp4656.jpa.entity.Employee;
import ca.bcit.comp4656.jpa.entity.ResponseCode;
import ca.bcit.comp4656.webapp.employee.presentation.util.PresentationUtil;
import com.sun.jersey.api.json.JSONConfiguration;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;

@Stateless
public class EmployeeServicesImpl implements EmployeeServices
{
	private static final String PATH = "http://localhost:8080/A00791707ws/api/employee";

	ClientConfig config = new DefaultClientConfig();
	Client client = Client.create(config);

	WebResource service = client.resource(getBaseURI());

	public EmployeeServicesImpl() {
	}
	
	
	@Override
	protected Object clone() throws CloneNotSupportedException
	{
		throw new CloneNotSupportedException();
	}

	public boolean serviceIsUp(){
		try {
			URL siteURL = new URL(PATH+"/getAllEmployees");
			HttpURLConnection connection = (HttpURLConnection) siteURL
					.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();

			int code = connection.getResponseCode();
			if (code == 200) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	
	@Override
	public List<Employee> getEmployeeList()
	{
		return service.path("/getAllEmployees").accept(MediaType.APPLICATION_XML)
									.get(new GenericType<List<Employee>>(){});
	}

	@Override
	public ResponseCode findEmployeeById( String id ) {
		ResponseCode  rc = service.path("/find").queryParam("id", id).accept(MediaType.APPLICATION_JSON_TYPE).get(ResponseCode.class);
		return rc;
	}

	@Override
	public ResponseCode addEmployee(Employee employee) {
		AddEmpResponse empResponse = service.path("/addEmployee").accept(MediaType.APPLICATION_JSON_TYPE)
										.type(MediaType.APPLICATION_JSON_TYPE).post(AddEmpResponse.class, employee);
		Employee employee1 = empResponse.getEmployee();
		ResponseCode rs = empResponse.getResponseCode();
		return rs;
	}

	@Override
	public ResponseCode removeEmployee( Employee employee )	{

		String id = employee.getId();
		return service.path("/remove").queryParam("id", id).accept(MediaType.APPLICATION_JSON_TYPE).delete(ResponseCode.class);

	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri(PATH).build();
	}
}
