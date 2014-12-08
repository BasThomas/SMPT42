package smpt42.nl.printmanager.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
