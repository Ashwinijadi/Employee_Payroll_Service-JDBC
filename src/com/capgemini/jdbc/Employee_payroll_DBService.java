package com.capgemini.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Employee_payroll_DBService {
	public List<Employee_payroll_Data> readData() {
		String sql = "SELECT * FROM employee_payroll;";
		List<Employee_payroll_Data> employeePayrollList = new ArrayList<Employee_payroll_Data>();
		try (Connection connection = this.getConnection();) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				double salary = resultSet.getDouble("salary");
				LocalDate startDate = resultSet.getDate("start").toLocalDate();
				employeePayrollList.add(new Employee_payroll_Data(id, name, salary, startDate));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeePayrollList;
	}

	private Connection getConnection() throws SQLException {
		Connection connection;
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_service", "root",
				"Jashwini@2298");
		System.out.println("Connection successful: " + connection);
		return connection;
	}
}
