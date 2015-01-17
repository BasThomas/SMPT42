package smpt42.nl.printmanager.model;

import java.lang.Override;
import java.util.ArrayList;

/**
 * Created by Bas on 08/12/14.
 */
public class User
{
	private Company company;
	private String username;
    private String surname;
    private String lastname;
	private String email;
	private ArrayList<Scan> scans = null;
    private String previewURL;

    public User(Company company, String username, String email) {
        this(company, username, null, null, email);
    }

    public User(Company company, String username, String surname, String lastname, String email) {
        this(company, username, surname, lastname, email, null);
    }

    public User(Company company, String username, String surname, String lastname, String email, String previewURL) {
        this.company = company;
        this.username = username;
        this.surname = surname;
        this.lastname = lastname;
        this.email = email;
        this.scans = new ArrayList<>();
        this.previewURL = previewURL;
    }

    @Override
    public String toString()
    {
        return "User: " + this.getUsername() + " (who works for " + this.getCompany().toString() + ")";
    }

    public boolean logout()
    {
        return true;
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

    public String getPreviewURL() {
        return previewURL;
    }

    public String getLastname() {
        return lastname;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return lastname + ", " + surname;
    }
}
