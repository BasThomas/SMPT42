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
public class Database {
    private static Database database = null;

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

    private String host = "jdbc:mysql://moridrin.com:3306/smpt42";
    private String username = "SMPT42";
    private String password = "xYQ4TsAUDAnWYvV7";

    protected Database() {
        openConnection();
    }

    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return database;
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
			System.out.printf("openConnection(): " + e.getMessage() + "\n");
		}
	}

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                System.out.printf(e.getMessage() + "\n");
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.printf(e.getMessage() + "\n");
            }
        }
    }

    public boolean login(String username, String password) {
        try {
            openConnection();

            String sql = "SELECT u.USERNAME, u.EMAIL, u.COMPANY_ID\n" +
                    "FROM user u\n" +
                    "WHERE u.USERNAME = ?\n" +
                    "AND u.password = ?";

            System.out.println("isClosed = " + conn.isClosed());
            ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();

            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.printf(ex.getMessage() + "\n");
        } finally {
            closeConnection();
        }

        return false;
    }

    public List<Company> getCompanies() {
        List<Company> companies = new ArrayList<>();

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
        }
        return companies;
    }

    public List<Scan> getScans() throws ParseException {
        List<Scan> scans = new ArrayList<>();
        DateFormat format = new SimpleDateFormat("d-MMM-y, h:m");

        try {
            openConnection();

            String sql =
                    "SELECT *\n" +
                            "FROM scan s, company c;";

            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                int scanID = rs.getInt("SCAN_ID");
                int companyID = rs.getInt("COMPANY_ID");
                String name = rs.getString("NAME");
                Date dateScanned = format.parse(rs.getString("DATE_SCANNED"));
                Date datePrinted = format.parse(rs.getString("DATE_PRINTED"));
                String barcode = rs.getString("BARCODE");

                Scan scan = new Scan(scanID, companyID, name, dateScanned, datePrinted, barcode);

                scans.add(scan);
            }
        } catch (SQLException e) {
            System.out.printf(e.getMessage() + "\n");
        } finally {
            closeConnection();
        }
        return scans;
    }

    /**
     * Methode om scan toe te voegen aan de database
     *
     * @param scan
     */
    public void addScan(Scan scan) {
        String sql =
                "INSERT INTO scan (SCAN_ID, COMPANY_ID, NAME, DATE_SCANNED, DATE_PRINTED, BARCODE) VALUES(?, ?, ?, ?, ?, ?)";
        int id;

        try {
            if (!getScans().contains(scan)) {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, getNewScanId());
                ps.setInt(2, scan.getCompany().getCompanyID());
                ps.setString(3, scan.getName());
                //ps.setDate(4, scan.getScanDate());
                //ps.setDate(5, scan.getPrintDate());
                ps.setString(6, scan.getBarcode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    /**
     * Methode om een nieuw ID te maken.
     *
     * @return          Een nieuw ID.
     */
    public Integer getNewScanId() {
        Integer result = null;
        String sql =
                "SELECT MAX(SCAN_ID) FROM scan";
        try {
            openConnection();
            ps = conn.prepareStatement(sql);
            if(rs!=null && rs.next()){
                result = rs.getInt("SCAN_ID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }
}
