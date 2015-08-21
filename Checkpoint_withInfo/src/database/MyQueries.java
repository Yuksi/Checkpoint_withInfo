package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;

public class MyQueries {
	private static SQLteDBConnector dbconnector = new SQLteDBConnector("checkpoint.db");
	private static Formatter dateFmt = new Formatter();
	private static Formatter timeFmt = new Formatter();
	private static ArrayList<String []> dataArrayList = new ArrayList<String []>();
	
	public static boolean doesExistsID(int id) {
		String queryID = "SELECT EXISTS (SELECT* FROM employee WHERE empl_id="+id+");";
		String resultID = null;
		try {
			resultID = dbconnector.resultSetQuery(queryID).getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (resultID.equals("1"))
			return true;
		else return false;
	}
	
	public static int getID(String name, String surname, String password) {
		String queryID = "select empl_id from employee where name='"+name+"' and surname = '"+surname+"' and password = '"+password+"';";
		int resultID = 0;
		try {
			resultID = dbconnector.resultSetQuery(queryID).getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultID;
	}
	
	public static boolean doesRightPSW(int id, String password) {
		String queryPSW = "SELECT password FROM employee WHERE empl_id ="+id+";";
		String resultPSW = null;
		try {
			resultPSW = dbconnector.resultSetQuery(queryPSW).getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (resultPSW.equals(password))
			return true;
		else return false;
	}
	
	public static String nameSurname(int id) {
		String queryName = "SELECT name FROM employee WHERE empl_id="+id+";";
		String resultName = null;
		String querySurame = "SELECT surname FROM employee WHERE empl_id="+id+";";
		String resultSurname = null;
		try {
			resultName = dbconnector.resultSetQuery(queryName).getString(1);
			resultSurname = dbconnector.resultSetQuery(querySurame).getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultName+" "+resultSurname;
	}
	
	public static boolean isInside(int id) {
		String query = "SELECT exists (SELECT * FROM authorization WHERE empl_id="+id+" AND time_out IS NULL);";
		String result = null;
		try {
			result = dbconnector.resultSetQuery(query).getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (result.equals("1"))
			return true;
		else return false;
	}
	
	public static void insertTimeIn(int id) {
		long curTime = System.currentTimeMillis(); 
		String curDate = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ").format(curTime); 
		
		String query = "INSERT INTO authorization (empl_id, time_in) VALUES ("+id+", '"+curDate+"');";
		dbconnector.sqlQuery(query);
	}
	
	public static void insertTimeOut(int id) {
		long curTime = System.currentTimeMillis(); 
		String curDate = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ").format(curTime); 
		
		String query = "UPDATE authorization SET time_out = '"+curDate+"' WHERE empl_id="+id+" AND time_out IS NULL;";
		dbconnector.sqlQuery(query);
	}
	
	public static void deleteEmployee(int id) {
		String query = "DELETE FROM employee WHERE empl_id="+id+";";
		dbconnector.sqlQuery(query);
	}
	
	public static void createEmployee(String name, String surname, String password) {
		String query = "INSERT INTO employee (name, surname, password) VALUES ('"+name+"', '"+surname+"', '"+password+"');";
		dbconnector.sqlQuery(query);
	}
	
	public static int numberOfEmplInside() {
		String query = "select count(time_in) from authorization where time_out is null;";
		int result = 0;
		try {
			result = dbconnector.resultSetQuery(query).getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static int authorizIdCount() {
		String query = "select count(authoriz_id) from authorization;";
		int result = 0;
		try {
			result = dbconnector.resultSetQuery(query).getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String timeIn(int id) {
		String query = "select time_in from authorization where authoriz_id="+id+";";
		String result = "";
		try {
			result = dbconnector.resultSetQuery(query).getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String timeOut(int id) {
		String query = "select time_out from authorization where authoriz_id="+id+";";
		String result = "";
		try {
			result = dbconnector.resultSetQuery(query).getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String timeWhenMax() {
	
		int max=0;
		String t1="", t2="";
		for (int i=1; i<=authorizIdCount(); i++) {
			int amount = 0;
			String maxIn=timeIn(i);
			String minOut=timeOut(i);
			/*
			for (int x=1; x<=authorizIdCount(); x++) {
				if (timeOut(x)>timeIn(i)&&timeIn(x)<timeOut(i)) {
					amount+=1;
					if (timeIn(x)>maxIn)
						maxIn=timeIn(x);
					if (timeOut(x)<minOut)
						minOut=timeOut(x);
				}
			}
			*/
			if (amount>max) {
				max = amount;
				t1=maxIn;
				t2=minOut;
			}
		}
		
	//	return t1+" - "+t2;
	}
	
	public static ArrayList<String []> getAllEmployees(){
		ResultSet rs = dbconnector.resultSetQuery("SELECT empl_id, name, surname FROM employee;");
		dataArrayList.clear();
		try {
			while (rs.next()){
							
				String [] row = {
						rs.getString("empl_id"),
						rs.getString("name"),
						rs.getString("surname")
				};
				dataArrayList.add(row);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataArrayList;
	}
	
	public static ArrayList<String []> getEmployeesInside(){
		ResultSet rs = dbconnector.resultSetQuery("SELECT e.empl_id, name, surname FROM employee e, authorization a WHERE e.empl_id=a.empl_id AND time_out IS NULL;");
		dataArrayList.clear();
		try {
			while (rs.next()){
							
				String [] row = {
						rs.getString("empl_id"),
						rs.getString("name"),
						rs.getString("surname")
				};
				dataArrayList.add(row);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataArrayList;
	}
	
	public static ArrayList<String []> getAuthorizations(int id){
		ResultSet rs = dbconnector.resultSetQuery("SELECT time_in, time_out FROM authorization WHERE empl_id="+id+";");
		dataArrayList.clear();
		try {
			while (rs.next()){
							
				String [] row = {
						rs.getString("time_in"),
						rs.getString("time_out"),
				};
				dataArrayList.add(row);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataArrayList;
	}
	/*
	public static void main(String[] args) {
		addData();
		System.out.println(dataArrayList);
	}
	*/
}
