package org.autodoc.form;

@SuppressWarnings("serial")
public class ListItem implements java.io.Serializable
{
	private int id;
	private String value;

	public ListItem()
	{
	}

	public ListItem(int id, String value)
	{
		this.id = id;
		this.value = value;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}
}