package com.capgemini.jdbc;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import com.capgemini.jdbc.Employee_payroll_service.IOService;

class Employee_payroll_service_Test {

	@Test
	public void givenEmployeesAddedToDbShouldMatchEmpCount() {
		Employee_payroll_Data[] arrayOfEmps = { new Employee_payroll_Data(0, "Navya", 100000.0, LocalDate.now(), "F"),
				new Employee_payroll_Data(0, "Bill Gates", 100000.0, LocalDate.now(), "M"),
				new Employee_payroll_Data(0, "Sunder", 100000.0, LocalDate.now(), "M"),
				new Employee_payroll_Data(0, "Mark", 100000.0, LocalDate.now(), "M") };
		Employee_payroll_service employeePayrollService = new Employee_payroll_service();
		employeePayrollService.readEmployeepayrollData(IOService.DB_IO);
		Instant start = Instant.now();
		employeePayrollService.addEmployeeToPayrollIn(Arrays.asList(arrayOfEmps));
		Instant end = Instant.now();
		System.out.println("Duration without thread : " + Duration.between(start, end));
		Assert.assertEquals(5, employeePayrollService.countEntries(IOService.DB_IO));
	}

//	@Test
//	public void addingAddresses_WhenAddedToDB_ShouldMatchEmployeeEntries() {
//		LocalDate date = LocalDate.now();
//		Employee_payroll_Data arrayOfEmps[] = { new Employee_payroll_Data(0, "Navya", 100000.0, date, "F"),
//				new Employee_payroll_Data(0, "Bill Gates", 100000.0, date, "M"),
//				new Employee_payroll_Data(0, "Sunder", 200000.0, date, "M"),
//				new Employee_payroll_Data(0, "Mark", 300000.0, date, "M") };
//		Employee_payroll_service employeePayrollService = new Employee_payroll_service();
//		employeePayrollService.readEmployeepayrollData(IOService.DB_IO);
//		Instant start = Instant.now();
//		employeePayrollService.addEmployeeToPayrollIn(Arrays.asList(arrayOfEmps));
//		Instant end = Instant.now();
//		System.out.println("Duration without thread : " + Duration.between(start, end));
//		Instant threadStart = Instant.now();
//		employeePayrollService.addEmployeeToPayrollWithThreads(Arrays.asList(arrayOfEmps));
//		Instant threadEnd = Instant.now();
//		System.out.println("Duartion with Thread : " + Duration.between(threadStart, threadEnd));
//		Assert.assertEquals(8, employeePayrollService.countEntries(IOService.DB_IO));
//	}
}