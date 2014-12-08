package smpt42.nl.printmanager.model;

import java.util.ArrayList;

/**
 * Created by Bas on 08/12/14.
 */
public class User
{
	private Company company;
	private String username;
	private String email;
	private ArrayList<Scan> scans = null;

	public User(Company company, String username, String email)
	{
		this.company = company;
		this.username = username;
		this.email = email;

		this.scans = new ArrayList<>();
	}

	public ArrayList<Scan> getScans()
	{
		return scans;
	}

	public Company getCompany()
	{
		return company;
	}

	public String getUsername()
	{
		return username;
	}

	public String getEmail()
	{
		return email;
	}
}
