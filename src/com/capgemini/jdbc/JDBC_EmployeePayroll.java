package com.capgemini.jdbc;

import java.sql.*;
import java.util.Enumeration;

public class JDBC_EmployeePayroll {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver is loaded");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("cannot find driver in classpath", e);
		}
		listDrivers();
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_service?useSSL=false",
					"root", "Jashwini@2298");
			System.out.println("Connection Done..!!!" + con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void listDrivers() {
		Enumeration<Driver> driverList = DriverManager.getDrivers();
		while (driverList.hasMoreElements()) {
			Driver driverClass = (Driver) driverList.nextElement();
			System.out.println(" " + driverClass.getClass().getName());
		}
	}
}
