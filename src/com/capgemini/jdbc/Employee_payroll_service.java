package com.capgemini.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Employee_payroll_service {
	public enum IOService {
		CONSOLE_IO, FILE_IO, DB_IO, REST_IO
	}

	public List<Employee_payroll_Data> employeePayrollList;

	public Employee_payroll_service() {
	}

	public Employee_payroll_service(List<Employee_payroll_Data> employeePayrollList) {
		this.employeePayrollList = employeePayrollList;
	}

	public static void main(String[] args) {
		List<Employee_payroll_Data> employeePayrollList = new ArrayList<Employee_payroll_Data>();
		Employee_payroll_service employeePayrollService = new Employee_payroll_service(employeePayrollList);
		Scanner consoleInputReader = new Scanner(System.in);
		employeePayrollService.readEmployeeData(consoleInputReader);
		employeePayrollService.writeEmployeeData(IOService.CONSOLE_IO);
	}

	public void readEmployeeData(Scanner consoleInputReader) {
		System.out.println("Enter employee ID : ");
		int id = Integer.parseInt(consoleInputReader.nextLine());
		System.out.println("Enter employee name : ");
		String name = consoleInputReader.nextLine();
		System.out.println("Enter employee salary : ");
		double salary = Double.parseDouble(consoleInputReader.nextLine());
		employeePayrollList.add(new Employee_payroll_Data(id, name, salary));
	}

	public void writeEmployeeData(IOService ioService) {
		if (ioService.equals(IOService.CONSOLE_IO))
			System.out.println("Employee Payroll Data to Console\n" + employeePayrollList);
		else if (ioService.equals(IOService.FILE_IO))
			new Employee_payroll_FileIOService().writeData(employeePayrollList);
	}

	public void printData(IOService ioService) {
		new Employee_payroll_FileIOService().printData();
	}

	public long countEntries(IOService ioService) {
		if (ioService.equals(IOService.FILE_IO))
			return new Employee_payroll_FileIOService().countEntries();
		return 0;
	}

	public List<Employee_payroll_Data> readEmployeepayrollData(IOService ioService) {
		if (ioService.equals(IOService.FILE_IO))
			return new Employee_payroll_FileIOService().readData();
		else if (ioService.equals(IOService.DB_IO))
			return new Employee_payroll_DBService().readData();
		else
			return null;
	}
}
