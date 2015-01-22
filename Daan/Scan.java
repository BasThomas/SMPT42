package smpt42.nl.printmanager.model;

import java.util.Date;

import smpt42.nl.printmanager.control.SCAN_STATUS;

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
    private boolean isStarred;
    public SCAN_STATUS status;

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

    public Company getCompany() {return company;}

    public boolean getIsStarred() { return isStarred; }

    public SCAN_STATUS getStatus() { return status; }

    public void setStatus(SCAN_STATUS status) { this.status = status; }
}