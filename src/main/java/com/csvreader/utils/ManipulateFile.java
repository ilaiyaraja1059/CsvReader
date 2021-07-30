package com.csvreader.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import com.csvreader.constants.Constant;
import com.csvreader.model.Employee;

@Component
public class ManipulateFile {
	
	 static File f = new File(Constant.FILENAME);

	public void updateCsvFile(Employee emp)  {
		try {
        CSVParser parser = new CSVParser(new FileReader(f), CSVFormat.DEFAULT);
        List<CSVRecord> list = parser.getRecords();
        String edited = f.getAbsolutePath();

        f.delete();
        CSVPrinter printer = new CSVPrinter(new FileWriter(edited), CSVFormat.DEFAULT.withRecordSeparator("\n").withSkipHeaderRecord(true));
     
        for (CSVRecord record : list) {
        	
        	
            String[] s = toArray(record);
        	
            
            if(Integer.parseInt(s[0])== emp.getId().intValue()){
            	
                if(!s[1].equalsIgnoreCase(emp.getName())) {                	                	
                	System.out.format("Updating Name from %s to %s",s[1],emp.getName());
                	s[1]=emp.getName();
                }
                
                if(Integer.parseInt(s[2])!=emp.getAge().intValue()) {
                	System.out.format("Updating Age from %d to %d",s[2],emp.getAge());
                	s[2]=emp.getAge().toString();
                }
                
                if(Integer.parseInt(s[3])!=emp.getExperience().intValue()) {
                	System.out.format("Updating Experience from %d to %d",s[3],emp.getExperience());
                	s[3]=emp.getExperience().toString();
                }
                if(!s[4].equalsIgnoreCase(emp.getDoj())) {
                	System.out.format("Updating DOJ from %s to %s",s[4],emp.getDoj());
                	s[4]=emp.getDoj().toString();
                }
                
            }
            print(printer, s);
        }
        parser.close();
        printer.close();

        System.out.println("CSV file was updated successfully !!!");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
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
    
    
    public void deleteRow(Integer id)  {
		try {
        CSVParser parser = new CSVParser(new FileReader(f), CSVFormat.DEFAULT);
        List<CSVRecord> list = parser.getRecords();
        String edited = f.getAbsolutePath();

        f.delete();
        CSVPrinter printer = new CSVPrinter(new FileWriter(edited), CSVFormat.DEFAULT.withRecordSeparator("\n").withSkipHeaderRecord(true));
     
        for (CSVRecord record : list) {
        	
        	
            String[] s = toArray(record);
        	
            if(Integer.parseInt(s[0])== id.intValue()){
              continue;    
            }
            print(printer, s);
        }
        parser.close();
        printer.close();

        System.out.println("CSV file was updated successfully !!!");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
    }


    
}
