package smpt42.nl.printmanager.model;

import java.lang.Override;
import java.util.Date;

/**
 * Created by Bas on 08/12/14.
 */
public class Scan implements Comparable {
    private int scanID;
    private int companyID;
	private Company company;
	private String name;
	private Date scanDate;
	private Date printDate;

	public Scan(Company company, String name, Date scanDate, Date printDate) {
		this.company = company;
		this.name = name;
		this.scanDate = scanDate;
		this.printDate = printDate;
	}

    public Scan(int scanID, int companyID, String name, Date scanDate, Date printDate) {
        this.scanID = scanID;
        this.companyID = companyID;
        this.name = name;
        this.scanDate = scanDate;
        this.printDate = printDate;
    }

    @Override
    public String toString()
    {
        return "[" + this.scanID "] Name: " + this.getName();
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

    @Override
    public int compareTo(Object o) {
        Scan another = (Scan) o;
        return scanDate.compareTo(another.getScanDate());
    }
}