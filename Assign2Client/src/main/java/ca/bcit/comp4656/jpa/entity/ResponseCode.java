package ca.bcit.comp4656.jpa.entity;

import javax.xml.bind.annotation.XmlRootElement;

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
