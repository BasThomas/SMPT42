package smpt42.nl.printmanager.model;

import java.lang.Override;
import java.util.Date;

import smpt42.nl.printmanager.control.SCAN_STATUS;
import smpt42.nl.printmanager.control.ScanManager;

/**
 * Created by Bas on 08/12/14.
 */
public class Scan {
    private int scanID;
    private int companyID;
	private Company company;
	private String name;
	private Date scanDate;
	private Date printDate;
    private boolean isStarred;
    private SCAN_STATUS status;
    private String barcode;

	public Scan(Company company, String name, Date scanDate, Date printDate, String barcode) {
		this.company = company;
		this.name = name;
		this.scanDate = scanDate;
		this.printDate = printDate;
        this.barcode = barcode;
	}

    public Scan(int scanID, int companyID, String name, Date scanDate, Date printDate, String barcode) {
        this.scanID = scanID;
        this.companyID = companyID;
        this.name = name;
        this.scanDate = scanDate;
        this.printDate = printDate;
        this.barcode = barcode;
    }

    @Override
    public String toString()
    {
        return "[" + this.scanID + "] Name: " + this.getName();
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

    public String getBarcode() { return barcode; }
}