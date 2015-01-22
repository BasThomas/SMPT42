package smpt42.nl.printmanager.model;

import java.lang.Override;

/**
 * Created by Bas on 08/12/14.
 */
public class Company
{
    private int companyID;
	private String name;
	private String street;
	private String city;
	private String telephone;

	public Company(String name, String street, String city, String telephone)
	{
		// TODO: Autoincrement company_ID
		this.name = name;
		this.street = street;
		this.city = city;
		this. telephone = telephone;
	}

    public Company(int companyID, String name, String street, String city, String telephone)
    {
        this.companyID = companyID;
        this.name = name;
        this.street = street;
        this.city = city;
        this.telephone = telephone;
    }

    @Override
    public String toString()
    {
        return "[" + this.companyID + "] Name: " + this.getName();
    }

	public String getName()
	{
		return name;
	}

	public String getStreet()
	{
		return street;
	}

	public String getCity()
	{
		return city;
	}

	public String getTelephone()
	{
		return telephone;
	}

    public int getCompanyID() { return companyID; }

    public String getAdress() {
        return street + " " + city;
    }

    public boolean hasString(String s) {

        if (name.toLowerCase().contains(s)) {
            return true;
        } else if (street.toLowerCase().contains(s)) {
            return true;
        } else if (city.toLowerCase().contains(s)) {
            return true;
        } else if (telephone.toLowerCase().contains(s)) {
            return true;
        } else if (String.valueOf(companyID).toLowerCase().contains(s)) {
            return true;
        } else {
            return false;
        }
    }
}
