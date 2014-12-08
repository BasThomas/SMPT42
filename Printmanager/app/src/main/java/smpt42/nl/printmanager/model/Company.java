package smpt42.nl.printmanager.model;

/**
 * Created by Bas on 08/12/14.
 */
public class Company
{
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
}
