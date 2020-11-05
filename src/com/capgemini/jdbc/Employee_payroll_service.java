package com.capgemini.jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.capgemini.jdbc.EmployeePayrollException.Exception;
import java.util.logging.Logger;

public class Employee_payroll_service {
	public enum IOService {
		CONSOLE_IO, FILE_IO, DB_IO, REST_IO
	}

	Logger log = Logger.getLogger(Employee_payroll_service.class.getName());
	public List<Employee_payroll_Data> employeePayrollList;
	private Employee_payroll_DBService employeePayrollDBService;
	private EmployeePayrollNewDBService employeePayrollNewDBService;
	private Map<String, Double> employeePayrollMap;

	public Employee_payroll_service() {
		employeePayrollDBService = Employee_payroll_DBService.getInstance();
		employeePayrollNewDBService = EmployeePayrollNewDBService.getInstance();
	}

	public Employee_payroll_service(List<Employee_payroll_Data> employeePayrollList) {
		this();
		this.employeePayrollList = employeePayrollList;
	}

	public Employee_payroll_service(Map<String, Double> employeePayrollMap) {
		this();
		this.employeePayrollMap = employeePayrollMap;
	}

	public static void main(String[] args) {
		List<Employee_payroll_Data> employeePayrollList = new ArrayList<Employee_payroll_Data>();
		Employee_payroll_service employeePayrollService = new Employee_payroll_service(employeePayrollList);
		Scanner consoleInputReader = new Scanner(System.in);
		employeePayrollService.readEmployeeData(consoleInputReader);
		employeePayrollService.writeEmployeeData(IOService.CONSOLE_IO);
	}

	public void readEmployeeData(Scanner consoleInputReader) {
		log.info("Enter employee ID : ");
		int id = Integer.parseInt(consoleInputReader.nextLine());
		log.info("Enter employee name : ");
		String name = consoleInputReader.nextLine();
		log.info("Enter employee salary : ");
		double salary = Double.parseDouble(consoleInputReader.nextLine());
		employeePayrollList.add(new Employee_payroll_Data(id, name, salary));
	}

	public List<Employee_payroll_Data> readEmployeepayrollData(IOService ioService) {
		if (ioService.equals(IOService.FILE_IO))
			return new Employee_payroll_FileIOService().readData();
		if (ioService.equals(IOService.DB_IO))
			return new EmployeePayrollNewDBService().readData();
		else
			return null;
	}

	public List<Employee_payroll_Data> readPayrollDataForRange(IOService ioService, LocalDate startDate,
			LocalDate endDate) {
		if (ioService.equals(IOService.DB_IO))
			this.employeePayrollList = employeePayrollNewDBService.readData();
		return employeePayrollList;
	}

	public void writeEmployeeData(IOService ioService) {
		if (ioService.equals(IOService.CONSOLE_IO))
			log.info("Employee Payroll Data to Console\n" + employeePayrollList);
		else if (ioService.equals(IOService.FILE_IO))
			new Employee_payroll_FileIOService().writeData(employeePayrollList);
	}

	public void printData(IOService ioService) {
		new Employee_payroll_FileIOService().printData();
	}

	public long countEntries(IOService ioService) {
		if (ioService.equals(IOService.FILE_IO))
			return new Employee_payroll_FileIOService().countEntries();
		return employeePayrollList.size();
	}

	public void updateEmployeeSalary(String name, double salary) throws EmployeePayrollException {
		int result = employeePayrollNewDBService.updateEmployeeData(name, salary);
		if (result == 0) {
			throw new EmployeePayrollException(Exception.DATA_NULL, "No Data to update ");
		}
		Employee_payroll_Data employeePayrollData = this.getEmployee_payroll_Data(name);
		if (employeePayrollData != null)
			employeePayrollData.salary = salary;
	}

	public void addEmployeeToPayRoll(String name, double salary, LocalDate start, String gender) {
		employeePayrollList.add(employeePayrollNewDBService.addEmployeeToPayroll(name, salary, start, gender));
	}

	private Employee_payroll_Data getEmployee_payroll_Data(String name) {
		return this.employeePayrollList.stream().filter(emp_Data -> emp_Data.name.equals(name)).findFirst()
				.orElse(null);
	}

	public boolean checkEmployeePayrollInSyncWithDB(String name) {
		List<Employee_payroll_Data> employeePayrollList = employeePayrollDBService.getEmployeePayrollData(name);
		return employeePayrollList.get(0).equals(getEmployee_payroll_Data(name));
	}

	public Map<String, Double> readPayrollDataForAvgSalary(IOService ioService) {
		if (ioService.equals(IOService.DB_IO))
			this.employeePayrollMap = employeePayrollDBService.get_AverageSalary_ByGender();
		return employeePayrollMap;
	}

	public Map<String, Double> readPayrollDataForSumSalary(IOService ioService) {
		if (ioService.equals(IOService.DB_IO))
			this.employeePayrollMap = employeePayrollDBService.get_SumOfSalary_ByGender();
		return employeePayrollMap;
	}

	public Map<String, Double> readPayrollDataForMaxSalary(IOService ioService) {
		if (ioService.equals(IOService.DB_IO))
			this.employeePayrollMap = employeePayrollNewDBService.get_Max_Salary_ByGender();
		return employeePayrollMap;
	}

	public Map<String, Double> readPayrollDataForMinSalary(IOService ioService) {
		if (ioService.equals(IOService.DB_IO))
			this.employeePayrollMap = employeePayrollDBService.get_Min_Salary_ByGender();
		return employeePayrollMap;
	}

	public Map<String, Double> readPayrollDataFor_CountOfEmployee_ByGender(IOService ioService) {
		if (ioService.equals(IOService.DB_IO))
			this.employeePayrollMap = employeePayrollNewDBService.get_CountOfEmployee_ByGender();
		return employeePayrollMap;
	}
}
