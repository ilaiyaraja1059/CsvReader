package com.csvreader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.csvreader.model.Employee;
import com.csvreader.service.CsvReaderService;


@RestController
public class CsvController {

	@Autowired
	CsvReaderService csvService;
	
	
	@GetMapping("/readAll")
	public List<Employee> retriveAllEmployee(){
		return csvService.readCsvFile();
	}
	
	@PostMapping("/add")
	public void addRecord(@RequestBody Employee employee) throws Exception{
		
		if(!isValidDate(employee.getDoj())){
			throw new Exception("Date of joining not in valid format yyyy-mm-dd");
		}
		
		csvService.addRecord(employee);
		
	}
	
	@PutMapping("/update")
	public void update(@RequestBody Employee emp) throws Exception {
		try {
			csvService.updateCsvFile(emp);
		} catch (Exception e) {
			throw new Exception("Update failed");
		}
	}
	
	@PostMapping("/generate/{count}")
	public void generate(@PathVariable int count) {
		csvService.createCsvData(count);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Integer id) throws Exception {
		csvService.deleteRow(id);
	}
	
	@GetMapping("/get/{id}")
	public Employee findEmployee(@PathVariable("id") Integer id) {
		return csvService.getEmpById(id);
	}
	
	@GetMapping("/getByType")
	public List<Employee> findEmployee(@RequestBody Employee emp ){
		return csvService.getEmpByType(emp);
	}
	
	public boolean isValidDate(String doj) {
		return doj.matches("^\\d{4}-\\d{2}-\\d{2}$");
	}

}
