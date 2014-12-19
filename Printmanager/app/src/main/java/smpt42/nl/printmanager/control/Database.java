package smpt42.nl.printmanager.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import smpt42.nl.printmanager.model.Company;
import smpt42.nl.printmanager.model.Scan;

/**
 * Created by Bas on 08/12/14.
 */
public class Database
{
	private static Database database = null;

	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	private String host = "jdbc:mysql://moridrin.nl:3306/smpt42";
	private String username = "SMPT42";
	private String password = "xYQ4TsAUDAnWYvV7";

	protected Database()
	{
		openConnection();
	}

	public static Database getInstance()
	{
		if (database == null)
		{
			database = new Database();
		}

		return database;
	}

	/**
	 * Opens the connection with the database.
	 */
	public final void openConnection()
	{
		try
		{
			conn = DriverManager.getConnection(host, username, password);
		}
		catch(SQLException e)
		{
			System.out.printf(e.getMessage() + "\n");
		}
	}

	/**
	 * Closes the connection with the database.
	 */
	public void closeConnection()
	{
		if(rs != null)
		{
			try
			{
				rs.close();
			}
			catch(SQLException e)
			{
				System.out.printf(e.getMessage() + "\n");
			}
		}

		if(ps != null)
		{
			try
			{
				ps.close();
			}
			catch(SQLException e)
			{
				System.out.printf(e.getMessage() + "\n");
			}
		}

		if(conn != null)
		{
			try
			{
				conn.close();
			}
			catch(SQLException e)
			{
				System.out.printf(e.getMessage() + "\n");
			}
		}
	}

    public boolean login(String username, String password)
    {
        try
        {
            openConnection();

            String sql = "SELECT u.USERNAME, u.EMAIL, u.COMPANY_ID\n" +
                    "FROM user u\n" +
                    "WHERE u.USERNAME = ?\n" +
                    "AND u.password = ?";

            ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();

            while(rs.next())
            {
                return true;
            }
        }
        catch (SQLException ex)
        {
            System.out.printf(ex.getMessage() + "\n");
        }
        finally
        {
            closeConnection();
        }

        return false;
    }

    public List<Company> getCompanies()
    {
        List<Company> companies = new ArrayList<>();
        /*
        try
        {
            openConnection();

            String sql =
                    "SELECT *\n" +
                            "FROM company com;";

            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while(rs.next())
            {
                int companyID = rs.getInt("COMPANY_ID");
                String name = rs.getString("NAME");
                String street = rs.getString("STREET");
                String city = rs.getString("CITY");
                String telephone = rs.getString("TELEPHONE");

                Company company = new Company(companyID, name, street, city, telephone);

                companies.add(company);
            }
        }
        catch (SQLException e)
        {
            System.out.printf(e.getMessage() + "\n");
        }
        finally
        {
            closeConnection();
        }*/
        return companies;
    }

    public List<Scan> getScans() throws ParseException
    {
        List<Scan> scans = new ArrayList<>();
        DateFormat format = new SimpleDateFormat("d-MMM-y, h:m");

        try
        {
            openConnection();

            String sql =
                    "SELECT *\n" +
                            "FROM scan s, company c;";

            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while(rs.next())
            {
                int scanID = rs.getInt("SCAN_ID");
                int companyID = rs.getInt("COMPANY_ID");
                String name = rs.getString("NAME");
                Date dateScanned = format.parse(rs.getString("DATE_SCANNED"));
                Date datePrinted = format.parse(rs.getString("DATE_PRINTED"));

                Scan scan = new Scan(scanID, companyID, name, dateScanned, datePrinted);

                scans.add(scan);
            }
        }
        catch (SQLException e)
        {
            System.out.printf(e.getMessage() + "\n");
        }
        finally
        {
            closeConnection();
        }
        return scans;
    }
}
