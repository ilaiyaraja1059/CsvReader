package com.csvreader.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvDates;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	
	@CsvBindByName
	private Integer Id;
	
	@CsvBindByName
	private String name;
	
	@CsvBindByName
	private Integer age;
	
	@CsvBindByName
	private Integer experience;
	
	@CsvBindByName
	private String doj;
	

	
	
}
