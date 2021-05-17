package org.uniken.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.uniken.bo.CorporateBO;

public class CorporateDAOImpl implements ICorporateDAO {

	private static final String INSERT_CORPORATE = "INSERT INTO CORPORATEDETAILS (CORP_RID, CORP_NAME, CORP_ID, ACC_NO) VALUES (RID_SEQ.NEXTVAL, ?, ?, ?)";
	private static final String GET_ALL_CORPORATES = "SELECT CORP_RID, CORP_NAME, CORP_ID, ACC_NO FROM CORPORATEDETAILS";
	private static final String GET_CORPORATE_BY_ID = "SELECT CORP_NAME, CORP_ID, ACC_NO FROM CORPORATEDETAILS WHERE CORP_RID=?";
	private static final String DELETE_CORPORATE_BY_ID = "DELETE FROM CORPORATEDETAILS WHERE CORP_RID=?";
	private static final String UPDATE_CORPORATE_BY_NO = "UPDATE CORPORATEDETAILS SET CORP_NAME=?, CORP_ID=?, ACC_NO=? WHERE CORP_RID=?";

	
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	// Parameterized constructor for assigning the required details for DB
	// connection
	public CorporateDAOImpl(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	// Create Connection Object and return to the caller
	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	// Closing the connection object
	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	@Override
	public int insertCorporate(CorporateBO bo) throws SQLException {
		int count = 0;
		// Create Connection object
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(INSERT_CORPORATE);
		statement.setString(1, bo.getCorporateName());
		statement.setString(2, bo.getCorporateId());
		statement.setLong(3, bo.getAccountNo());

		count = statement.executeUpdate();
		System.out.println(count);
		statement.close();
		disconnect();
		return count;
	}

	@Override
	public List<CorporateBO> getAllEmployees() throws SQLException {
		List<CorporateBO> listBO = new ArrayList<CorporateBO>();
		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(GET_ALL_CORPORATES);

		while (resultSet.next()) {
			CorporateBO bo = new CorporateBO();
			bo.setCorporateRID(resultSet.getInt(1));
			bo.setCorporateName(resultSet.getString(2));
			bo.setCorporateId(resultSet.getString(3));
			bo.setAccountNo(resultSet.getLong(4));

			listBO.add(bo);
		}

		resultSet.close();
		statement.close();
		disconnect();

		return listBO;
	}

	@Override
	public CorporateBO getCorporateById(int id) throws SQLException {
		connect();
		CorporateBO corpBO = new CorporateBO();
		PreparedStatement statement = jdbcConnection.prepareStatement(GET_CORPORATE_BY_ID);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
     
		if (resultSet.next()) {
			corpBO.setCorporateRID(id);
			corpBO.setCorporateName(resultSet.getString(1));
			corpBO.setCorporateId(resultSet.getString(2));
			corpBO.setAccountNo(resultSet.getLong(3));
		}
    	resultSet.close();
		statement.close();

		return corpBO;
	}

	@Override
	public int deleteCorporate(int id) throws SQLException {
		int count = 0;
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(DELETE_CORPORATE_BY_ID);
		statement.setInt(1, id);

		count = statement.executeUpdate();
		statement.close();
		disconnect();
		
		return count;
	}

	@Override
	public int updateEmployee(CorporateBO bo) throws SQLException {
		int count =0;
		connect();
        
        PreparedStatement statement = jdbcConnection.prepareStatement(UPDATE_CORPORATE_BY_NO);
        statement.setString(1, bo.getCorporateName());
        statement.setString(2, bo.getCorporateId());
        statement.setLong(3, bo.getAccountNo());
        statement.setInt(4, bo.getCorporateRID());
         
        count = statement.executeUpdate();
        
        statement.close();
        disconnect();
		return count;
	}

}
