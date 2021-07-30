package com.csvreader.utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import com.csvreader.constants.Constant;
import com.csvreader.model.Employee;
import com.csvreader.repository.CsvRepository;
import com.opencsv.CSVWriter;

@Component
public class ReadOrWriteCsv implements CsvRepository {

	 String fileName = Constant.FILENAME;
	 
		
		  public static int sequence=0; //Assuming there is no record in csv file
		  
		  public String getNextSequence() { 
			  return Integer.toString(++sequence); 
		  }
		 
	 
public List<Employee> readCsvFile(){
	
     
     List<Employee> employees= new ArrayList<Employee>();
     
try {
     CSVParser parser = new CSVParser(new FileReader(fileName), CSVFormat.DEFAULT.withSkipHeaderRecord(true));
     List<CSVRecord> list = parser.getRecords();

     for (CSVRecord record : list) {
    	  String[] s = toArray(record);
    	  employees.add(new Employee(Integer.parseInt(s[0]),s[1],Integer.parseInt(s[2]),Integer.parseInt(s[3]),s[4]));
     }       
         
} catch (IOException e) {
	e.printStackTrace();
}
	return employees;
}

     public void writeToCsv(Employee employee) {
    	 CSVWriter writer = null;
		try {
			writer = new CSVWriter(new FileWriter(fileName, true));
		} catch (IOException e) {
			e.printStackTrace();
		}

         String [] record = {getNextSequence(),
        		 employee.getName(),
        		 employee.getAge().toString(),
        		 employee.getExperience().toString(),
        		 employee.getDoj()};
         
         writer.writeNext(record,false);
         
         try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
     }
     
     public void createCsvData(int count) {
    	 
    	 Employee employee=new Employee();
    	 int increment=0;
    	 
    	 if(count!=0) {
    		 increment=count; 
    	 }else {
    		 increment=200;
    	 }
    	 
    	 for(int i=1;i<=increment;i++) {
    		 
    		 employee.setId(0);
    		 employee.setName(generateName());
    		 employee.setAge(generateAge());
    		 employee.setExperience(generateExp());
    		 employee.setDoj(generateDate());
    		 writeToCsv( employee);
    	 }   	
             
     }
     
     public String generateName() {
    	    int leftLimit = 97; // letter 'a'
    	    int rightLimit = 122; // letter 'z'
    	    int targetStringLength = 10;
    	    Random random = new Random();

    	    String generatedString = random.ints(leftLimit, rightLimit + 1)
    	      .limit(targetStringLength)
    	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
    	      .toString();
    	    
    	    String s1 = generatedString.substring(0, 1).toUpperCase();
    	    String nameCapitalized = s1 + generatedString.substring(1);

    	    return nameCapitalized;
    	}
     
     public int generateAge() {
    	    List<Integer> givenList = Arrays.asList(19, 20,21,22,23,24,25,26,27,28,19,30);
    	    Random rand = new Random();
    	    int randomElement = givenList.get(rand.nextInt(givenList.size()));
    	    return randomElement;
    	}
     
     public int generateExp() {
 	    List<Integer> givenList = Arrays.asList(19, 20,21,22,23,24,25,26,27,28,19,30);
 	    Random rand = new Random();
 	    int randomElement = givenList.get(rand.nextInt(givenList.size()));
 	    return randomElement;
 	}
     
     public String generateDate() {
    	 Random random = new Random();
    	 int minDay = (int) LocalDate.of(2010, 1, 1).toEpochDay();
    	 int maxDay = (int) LocalDate.of(2021, 1, 1).toEpochDay();
    	 long randomDay = minDay + random.nextInt(maxDay - minDay);

    	 LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
    	 
    	// SimpleDateFormat fm=new SimpleDateFormat("yyyy/MM/dd");
    	 DateTimeFormatter fm=DateTimeFormatter.ofPattern("yyyy/MM/dd");
    	 return randomDate.format(fm);

     }
     
     public static String[] toArray(CSVRecord rec) {
         String[] arr = new String[rec.size()];
         int i = 0;
         for (String str : rec) {
             arr[i++] = str;
         }
         return arr;
     }
     
     public static void print(CSVPrinter printer, String[] s) throws Exception {
         for (String val : s) {
             printer.print(val != null ? String.valueOf(val) : "");
         }
         printer.println();
     }
     
}
	    
