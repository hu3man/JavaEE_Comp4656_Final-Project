package ca.bcit.comp4656.jpa.entity;

import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class EmployeeList {

    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }
    
    public void setEmployee(List<Employee> employees){
        this.employees = employees;
    }
}
