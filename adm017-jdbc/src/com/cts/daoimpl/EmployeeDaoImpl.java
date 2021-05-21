package com.cts.daoimpl;

import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.cts.dao.EmployeeDao;
import com.cts.model.Employee;
import com.sun.jdi.connect.spi.Connection;

public class EmployeeDaoImpl implements EmployeeDao {

	//database info
	private final String db_username="root";
	private final String db_password="root";
	private final String db_driver="com.mysql.jdbc.Driver";
	private final String db_url="jdbc:mysql://localhost:3306/adm017";
	
	Connection connectionObj=null;
	PreparedStatement pStatement=null;
	@Override
	public void addEmployee(Employee empObj) {
		
		try {
			//step-1 load the driver
			Class.forName(db_driver);
			
			//step-2: get the connection
		   connectionObj=DriverManager.getConnection(db_url,db_username,db_password);
		   
		   //step-3 Write your queries
		   String insertQuery="insert into employee_tabel vaues(?,?,?,?)";
		   pStatement=connectionObj.prepareStatement(insertQuery);
		   
		  
		   
		   pStatement=setInt(1, empObj.getEmployeeId());
		   pStatement=setString(2, empObj.getEmployeeName());
		   pStatement=setDouble(3, empObj.getSalary());
		   pStatement=setInt(4, empObj.getAge());
		   
		   //step-4: execute Statement
		   pStatement.executeUpdate();
		   System.out.println("Data has been successfully inserted.");
		   
		}catch(Exception exception) {
			exception.printStackTrace();
		}finally {
			//step-5: close the pStatement and connection
			if(pStatement!=null) {
				try {
					pStatement.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(connectionObj!=null) {
				try {
					connectionObj.close();
				}catch()
				
			}
		}
	}
}
