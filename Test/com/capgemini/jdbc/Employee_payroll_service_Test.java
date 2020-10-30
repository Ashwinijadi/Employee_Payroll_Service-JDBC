package com.capgemini.jdbc;

import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import com.capgemini.jdbc.Employee_payroll_service.IOService;

class Employee_payroll_service_Test {

	@Test
	public void given3Employees_WhenWrittenToFile_ShouldMatchEmployeeEntries() {
		Employee_payroll_Data[] arrayOfEmp = { new Employee_payroll_Data(1, "Bill", 100000.0),
				new Employee_payroll_Data(2, "Mark ", 200000.0), new Employee_payroll_Data(3, "Charlie", 300000.0) };
		Employee_payroll_service employeePayrollService;
		employeePayrollService = new Employee_payroll_service(Arrays.asList(arrayOfEmp));
		employeePayrollService.writeEmployeeData(IOService.FILE_IO);
		long entries = employeePayrollService.countEntries(IOService.FILE_IO);
		employeePayrollService.printData(IOService.FILE_IO);
		List<Employee_payroll_Data> employeeList = employeePayrollService.readEmployeepayrollData(IOService.FILE_IO);
		System.out.println(employeeList);
		Assert.assertEquals(3, entries);
	}

	@Test
	public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCount() {
		Employee_payroll_service employeePayrollService = new Employee_payroll_service();
		List<Employee_payroll_Data> employeePayrollData = employeePayrollService
				.readEmployeepayrollData(IOService.DB_IO);
		System.out.println(employeePayrollData);
		Assert.assertEquals(4, employeePayrollData.size());

	}
}
