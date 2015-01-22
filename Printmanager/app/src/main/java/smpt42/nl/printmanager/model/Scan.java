package smpt42.nl.printmanager.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import smpt42.nl.printmanager.model.enums.SCAN_STATUS;

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
    private String previewURL;

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

    public Scan(int scanID, Company company, String name, Date scanDate, Date printDate, String barcode, String previewURL) {
        this.scanID = scanID;
        this.company = company;
        this.name = name;
        this.scanDate = scanDate;
        this.printDate = printDate;
        this.barcode = barcode;
        this.previewURL = previewURL;
    }

    @Override
    public String toString()
    {
        return "[" + this.scanID + "] Name: " + this.getName();
    }

    public int getScanID() { return scanID; }

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

    public void setStarred(boolean isStarred) {
        this.isStarred = isStarred;
    }

    public boolean getIsStarred() { return isStarred; }

    public SCAN_STATUS getStatus() { return status; }

    public void setStatus(SCAN_STATUS status) { this.status = status; }

    public String getBarcode() { return barcode; }

    public String getPreviewURL() {
        return previewURL;
    }

    public String getPrintDateOnly() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(printDate);
    }

    public String getPrintTimeOnly() {
        DateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(printDate);
    }

    public boolean hasString(String s) {
        if (this.name.toLowerCase().startsWith(s)) {
            return true;
        }

        return false;
    }
}
