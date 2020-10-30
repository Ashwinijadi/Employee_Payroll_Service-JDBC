package com.capgemini.jdbc;

import java.time.LocalDate;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import com.capgemini.jdbc.Employee_payroll_service.IOService;

class Employee_payroll_service_Test {

	@Test
	public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCount() {
		Employee_payroll_service employeePayrollService = new Employee_payroll_service();
		List<Employee_payroll_Data> employeePayrollData = employeePayrollService
				.readEmployeepayrollData(IOService.DB_IO);
		System.out.println(employeePayrollData);
		Assert.assertEquals(4, employeePayrollData.size());
	}

	@Test
	public void givenDateRange_WhenRetrieved_ShouldMatchEmployeeCount() {
		Employee_payroll_service employeePayrollService = new Employee_payroll_service();
		employeePayrollService.readEmployeepayrollData(IOService.DB_IO);
		LocalDate startDate = LocalDate.of(2018, 01, 01);
		LocalDate endDate = LocalDate.now();
		List<Employee_payroll_Data> employeePayrollData = employeePayrollService
				.readPayrollDataForRange(IOService.DB_IO, startDate, endDate);
		Assert.assertEquals(4, employeePayrollData.size());
	}
}
