package com.houstonkrohman.c4613.jpa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "A00791707_Employees", schema = "dbo", uniqueConstraints = @UniqueConstraint (columnNames={"id"}))
@NamedQueries ({
		@NamedQuery ( name="getAllEmployee" , query="select e from Employee e" ),
		@NamedQuery( name= "Employee.findById", query= "select e from Employee e where e.id= :id" ),
		@NamedQuery( name= "Employee.deleteById", query= "delete from Employee where id= :id" )
		})
@XmlRootElement
public class Employee
{

	@Id
	@NotNull( message= "error.add.employee.invalid" )
	@Pattern( regexp = "[A][0][0-9]{7}", message= "error.add.employee.invalid" )
	@Column( unique=true)
	private String id;

	@Column
	@NotNull
	private String firstName;
	
	@Column
	@NotNull
	private String lastName;
	
	@Column
	private Date dob;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public Date getDob()
	{
		return dob;
	}

	public void setDob(Date dob)
	{
		this.dob = dob;
	}

	@Override
	public String toString()
	{
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
