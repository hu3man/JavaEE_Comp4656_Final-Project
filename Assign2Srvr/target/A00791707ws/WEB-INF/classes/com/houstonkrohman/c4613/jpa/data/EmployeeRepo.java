package com.houstonkrohman.c4613.jpa.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.*;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.houstonkrohman.c4613.jpa.entity.Employee;
import com.houstonkrohman.c4613.jpa.entity.EmployeeException;

@Stateless
//@Local(EmployeeRepoI.class)
public class EmployeeRepo implements EmployeeRepoI
{
	@PersistenceContext
	EntityManager entityManager;

	public List<Employee> getAllEmployees()
	{
		List<Employee> employees=null;
		try
		{
			TypedQuery<Employee> query = entityManager.createNamedQuery( "getAllEmployee", Employee.class );
			employees = query.getResultList();
		}
		catch (EJBTransactionRolledbackException ejbExp )
		{
			System.err.println("Rollback exception");
			ejbExp.printStackTrace();
		}
		catch (Exception e )
		{
			System.err.println(e.getMessage());
		}
		return employees;
	}

	public Employee findEmployeeById( String id )
	{
		try
		{
			return entityManager.createNamedQuery( "Employee.findById", Employee.class ).setParameter("id", id ).getSingleResult();
		}
		catch ( NoResultException nre )
		{
			return null;
		}

	}

	public Set<String> addEmployee(Employee employee) throws EmployeeException
	{
		Set<String> returnMessages = null;
		try {
			returnMessages = validate( employee );
			if ( returnMessages!=null && returnMessages.size()>0 )
			{
				return returnMessages;
			}
			entityManager.persist( employee );
			return returnMessages;
		} catch (Exception e) {
			returnMessages = new HashSet<String>();
			returnMessages.add("error.add.employee.duplicate");
			e.printStackTrace();
		}
		return returnMessages;
	}

	@Transactional
	public void removeEmployee(String employeeID) throws EmployeeException
	{
		Employee employee = entityManager.find(Employee.class, employeeID );
		if ( employee == null )
		{
			throw new EmployeeException();
		}
		try {
			int i = entityManager.createNamedQuery( "Employee.deleteById").setParameter("id", employeeID).executeUpdate();
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>> " + i + " rows got affected");
		} catch (Exception e ) {
			e.printStackTrace();
			throw new EmployeeException();
		}
	}

	private Set<String> validate( Employee employee )
	{
		Set<String> messages = new HashSet<String>();
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Employee>> constraintViolations = validator.validate( employee );
		if ( constraintViolations.size() > 0 )
		{
			 for ( ConstraintViolation<Employee> constraintViolation: constraintViolations )
			 {
				 messages.add( constraintViolation.getPropertyPath() + " : "  + constraintViolation.getMessage() );
			 }
		}
		return messages;
	}

}
