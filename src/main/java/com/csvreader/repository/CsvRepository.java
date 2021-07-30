package com.csvreader.repository;

import java.util.List;

import com.csvreader.model.Employee;

public interface CsvRepository {
	List<Employee> readCsvFile();
	void createCsvData(int count);
	public void writeToCsv(Employee employee);
}
