package ca.bcit.comp4656.webapp.employee.presentation.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.displaytag.decorator.TableDecorator;

import ca.bcit.comp4656.jpa.entity.Employee;



public class TimeDecorator extends TableDecorator 
{
	public String getDob()
	{
		Date dob = ( (Employee ) ( this.getCurrentRowObject()) ).getDob();
		if ( dob==null || "".equals( dob ) )
		{
			return "";
		}
		
		DateFormat formatter = new SimpleDateFormat( "yyyy/MM/dd" );
		return formatter.format(dob);
	}
}
