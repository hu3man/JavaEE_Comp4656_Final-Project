package com.houstonkrohman.c4613.jpa.entity;

import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlRootElement;

@Produces("application/json")
@XmlRootElement
public class ResponseCode
{

	protected String code;
	protected String desc;

	public String getCode()
	{
		return code;
	}

	public void setCode( String value )
	{
		this.code = value;
	}

	public String getDesc()
	{
		return desc;
	}

	public void setDesc( String value )
	{
		this.desc = value;
	}

	@Override
	public String toString()
	{
		return "ResponseCode [code=" + code + ", desc=" + desc + "]";
	}

}
