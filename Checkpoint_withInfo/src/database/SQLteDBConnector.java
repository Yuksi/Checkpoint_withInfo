package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SQLteDBConnector {
	private String dbName;
	private Connection connection;  
	private Statement statement;  
	private ResultSet resultSet;
	
	public SQLteDBConnector(){}

	public SQLteDBConnector (String dbName){
		setDBName(dbName);
	}

	public void start() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}  
		}
		try {  
			Class.forName("org.sqlite.JDBC");  
			connection = DriverManager.getConnection("jdbc:sqlite:"+dbName);  
		} catch (ClassNotFoundException e) {  
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setDBName (String dbName) {
		this.dbName = dbName;
	}

	public String getDBName () {
		return dbName;
	}

	public ResultSet resultSetQuery (String query){
		start();
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			return resultSet;
		} catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		end();
		return null;
	}
	
	public void sqlQuery (String query){
		start();
		try {
			statement = connection.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		end();
	}
	
	public void end()
	{  
		if (statement != null) {
			try {  
				statement.close();
			} catch (Exception e){
				e.printStackTrace();
			}  
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/*
	public static void main(String[] args) throws SQLException {
		SQLteDBConnector myconn =new SQLteDBConnector("checkpoint.db");
		
		String query = "select time_in from authorization;";
		ResultSet rs = myconn.resultSetQuery (query);
		while (rs.next()){
		System.out.println(rs.getString("time_in"));
		}
	}
	*/
}
