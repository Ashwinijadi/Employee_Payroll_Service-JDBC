package com.capgemini.jdbc;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import com.capgemini.jdbc.Employee_payroll_service.IOService;

class Employee_payroll_service_Test {

	@Test
	public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCount() {
		Employee_payroll_service employeePayrollService = new Employee_payroll_service();
		List<Employee_payroll_Data> employeePayrollData = employeePayrollService
				.readEmployeepayrollData(IOService.DB_IO);
		Assert.assertEquals(3, employeePayrollData.size());
		System.out.println(employeePayrollData);
	}

	@Test
	public void givenDateRange_WhenRetrieved_ShouldMatchEmployeeCount() {
		Employee_payroll_service employeePayrollService = new Employee_payroll_service();
		employeePayrollService.readEmployeepayrollData(IOService.DB_IO);
		LocalDate startDate = LocalDate.of(2018, 01, 01);
		LocalDate endDate = LocalDate.now();
		List<Employee_payroll_Data> employeePayrollData = employeePayrollService
				.readPayrollDataForRange(IOService.DB_IO, startDate, endDate);
		Assert.assertEquals(3, employeePayrollData.size());
		System.out.println(employeePayrollData);
	}

	@Test
	public void givenEmployeePayrollData_ShouldMatch_AverageSalary_GroupByGender() {
		Employee_payroll_service employeePayrollService = new Employee_payroll_service();
		Map<String, Double> avgSalaryByGender = employeePayrollService.readPayrollDataForAvgSalary(IOService.DB_IO);
		Assert.assertTrue(avgSalaryByGender.get("M").equals(1800000.0) && avgSalaryByGender.get("F").equals(170000.0));
	}

	@Test
	public void givenEmployeePayrollData_ShouldMatch_SumOfSalary_GroupByGender() {
		Employee_payroll_service employeePayrollService = new Employee_payroll_service();
		Map<String, Double> sumOfSalaryByGender = employeePayrollService.readPayrollDataForSumSalary(IOService.DB_IO);
		Assert.assertTrue(
				sumOfSalaryByGender.get("M").equals(5400000.0) && sumOfSalaryByGender.get("F").equals(850000.0));
	}

	@Test
	public void givenEmployeePayrollData_ShouldMatch_MaxSalary_GroupByGender() {
		Employee_payroll_service employeePayrollService = new Employee_payroll_service();
		Map<String, Double> MaxSalaryByGender = employeePayrollService.readPayrollDataForMaxSalary(IOService.DB_IO);
		Assert.assertTrue(MaxSalaryByGender.get("M").equals(5000000.0) && MaxSalaryByGender.get("F").equals(350000.0));
	}

	@Test
	public void givenEmployeePayrollData_ShouldMatch_MinSalary_GroupByGender() {
		Employee_payroll_service employeePayrollService = new Employee_payroll_service();
		Map<String, Double> MinSalaryByGender = employeePayrollService.readPayrollDataForMinSalary(IOService.DB_IO);
		Assert.assertTrue(MinSalaryByGender.get("M").equals(100000.0) && MinSalaryByGender.get("F").equals(100000.0));
	}

	@Test
	public void givenEmployeePayrollData_ShouldMatch_Emp_CountGroupByGender() {
		Employee_payroll_service employeePayrollService = new Employee_payroll_service();
		Map<String, Double> employeeCountByGender = employeePayrollService
				.readPayrollDataFor_CountOfEmployee_ByGender(IOService.DB_IO);
		Assert.assertTrue(employeeCountByGender.get("M").equals(3.0) && employeeCountByGender.get("F").equals(5.0));
	}

	@Test
	public void givenNewSalaryForEmployeeWhenupdatedShouldSyncWith_NewNormalisedDB() throws EmployeePayrollException {
		Employee_payroll_service employeePayrollService = new Employee_payroll_service();
		List<Employee_payroll_Data> employeePayrollData = employeePayrollService
				.readEmployeepayrollData(IOService.DB_IO);
		employeePayrollService.updateEmployeeSalary("Hima", 200000.0);
		System.out.println(employeePayrollData);
		boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDB("Hima");
		Assert.assertTrue(result);
	}

//	@Test
//	public void givenNewEmployee_WhenAddedShouldSyncWithDB() throws EmployeePayrollException {
//		Employee_payroll_service employeePayrollService = new Employee_payroll_service();
//		List<Employee_payroll_Data> employeePayrollData = employeePayrollService
//				.readEmployeepayrollData(IOService.DB_IO);
//		employeePayrollService.addEmployeeToPayRoll("Dhoni", 5000000, LocalDate.now(), "M");
//		System.out.println(employeePayrollData);
//		Boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDB("Dhoni");
//		Assert.assertTrue(result);
//		System.out.println(employeePayrollData);
//	}
}