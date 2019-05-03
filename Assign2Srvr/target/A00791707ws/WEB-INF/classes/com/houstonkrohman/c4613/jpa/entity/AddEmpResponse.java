package com.houstonkrohman.c4613.jpa.entity;

import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlRootElement;

@Produces("application/json")
@XmlRootElement
public class AddEmpResponse {
    private ResponseCode responseCode;
    private Employee     employee;

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
