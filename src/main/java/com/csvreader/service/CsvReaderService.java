package com.csvreader.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csvreader.model.Employee;
import com.csvreader.repository.CsvRepository;
import com.csvreader.utils.ManipulateFile;


@Service
public class CsvReaderService {
	
	@Autowired
	CsvRepository csvRepository;
	
	@Autowired
	ManipulateFile manipulateRepository;
	
	public List<Employee> readCsvFile()  {
		List<Employee> empList=null;
		try {
			empList= csvRepository.readCsvFile();
		}catch(Exception ex) {
			System.out.println("Exception due to: "+ex.getMessage());
		}
		return empList;
	}
	
	public Employee getEmpById(Integer id){
		List<Employee> empList=null;
		try {
			empList= csvRepository.readCsvFile();
		}catch(Exception ex) {
			System.out.println("Exception due to: "+ex.getMessage());
		}
		
		Optional<Employee> employee = empList.stream().filter(emp->emp.getId() ==id.intValue()).findFirst();
		
		return employee.get();
		
	}
	
	public List<Employee> getEmpByType(Employee emp) {
		
		List<Employee> empList=null;
		try {
			empList= csvRepository.readCsvFile();
		}catch(Exception ex) {
			System.out.println("Exception due to: "+ex.getMessage());
		}
		
		List<Employee> employee=null;
		
		if(emp.getId()!=null) {
			employee = empList.stream().filter(e->e.getId() ==emp.getId().intValue()).collect(Collectors.toList());
		}else if(emp.getName()!=null) {
			 employee = empList.stream().filter(e->e.getName() ==emp.getName()).collect(Collectors.toList());
		}
		else if(emp.getAge()!=null) {
			 employee = empList.stream().filter(e->e.getAge() ==emp.getAge().intValue()).collect(Collectors.toList());
		}
		else if(emp.getExperience()!=null) {
			 employee = empList.stream().filter(e->e.getExperience() ==emp.getExperience().intValue()).collect(Collectors.toList());
		}
		else if(emp.getDoj()!=null) {
			 employee = empList.stream().filter(e->e.getDoj() ==emp.getDoj()).collect(Collectors.toList());
		}
		
		return employee;
		
	}
	
	public void addRecord(Employee employee) {
		try {
		csvRepository.writeToCsv(employee);
		}catch(Exception ex) {
			System.out.println("Exception due to : "+ex.getMessage());
		}
	}
	
	public  void updateCsvFile(Employee emp) throws Exception {
		try {
			manipulateRepository.updateCsvFile(emp);
		} catch (Exception e) {
			throw new Exception("Update failed ");
		}
	}
	
	public  void deleteRow(Integer id) throws Exception {
		manipulateRepository.deleteRow(id);
	}
	
	public void createCsvData(int count) {
		csvRepository.createCsvData(count);
	}

}
