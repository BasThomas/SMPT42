package smpt42.nl.printmanager.model;

import java.util.Date;

/**
 * Created by Bas on 08/12/14.
 */
public class Scan
{
	private Company company;
	private String name;
	private Date scanDate;
	private Date printDate;

	public Scan(Company company, String name, Date scanDate, Date printDate)
	{
		this.company = company;
		this.name = name;
		this.scanDate = scanDate;
		this.printDate = printDate;
	}

	public String getName()
	{
		return name;
	}

	public Date getScanDate()
	{
		return scanDate;
	}

	public Date getPrintDate()
	{
		return printDate;
	}
}