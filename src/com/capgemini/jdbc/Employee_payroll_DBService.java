package com.capgemini.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Employee_payroll_DBService {
	private PreparedStatement employeePayrollDataStatement;
	private static Employee_payroll_DBService employeePayrollDBService;

	public Employee_payroll_DBService() {
	}

	public static Employee_payroll_DBService getInstance() {
		if (employeePayrollDBService == null)
			employeePayrollDBService = new Employee_payroll_DBService();
		return employeePayrollDBService;
	}

	public List<Employee_payroll_Data> readData() {
		String sql = "SELECT * FROM employee_payroll;";
		List<Employee_payroll_Data> employeePayrollList = new ArrayList<Employee_payroll_Data>();
		try (Connection connection = this.getConnection();) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			employeePayrollList = this.getEmployeePayrollData(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeePayrollList;
	}

	public int updateEmployeeData(String name, double salary) {
		return this.updateDataUsingStatement(name, salary);
	}

	private int updateDataUsingStatement(String name, double salary) {
		String sql = String.format("UPDATE employee_payroll SET salary = %.2f where name = '%s';", salary, name);
		try (Connection connection = this.getConnection();) {
			Statement statement = connection.createStatement();
			return statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	private void preparedStatementForEmployeeData() {
		try {
			Connection connection = this.getConnection();
			String sql = "Select * from employee_payroll WHERE name = ?";
			employeePayrollDataStatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Employee_payroll_Data> getEmployeePayrollData(String name) {
		List<Employee_payroll_Data> employeePayrollList = null;
		if (this.employeePayrollDataStatement == null)
			this.preparedStatementForEmployeeData();
		try {
			employeePayrollDataStatement.setString(1, name);
			ResultSet resultSet = employeePayrollDataStatement.executeQuery();
			employeePayrollList = this.getEmployeePayrollData(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeePayrollList;
	}

	private List<Employee_payroll_Data> getEmployeePayrollData(ResultSet resultSet) {
		List<Employee_payroll_Data> employeePayrollList = new ArrayList<>();
		try {
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
