package com.pkrm.test;

import java.util.List;
import java.util.Map;

import com.pkrm.CreateReader;
import com.pkrm.Reader;
import com.pkrm.csv.CsvReaderImplementation;
import com.pkrm.db.DbReaderImplementation;
import com.pkrm.excel.ExcelReaderImplementation;
import com.pkrm.xml.XmlReaderImplementation;

public class TestClient {

	
	private static String driver ="org.postgresql.Driver";
	private static String url ="jdbc:postgresql://10.5.50.43/Test";
	private static String userName ="postgres";
	private static String password ="postgre";
	private static String tableName = "select * from COMPANY";
	private Reader readProducer = null;
	
	public static void main(String[] args) throws Exception {
			
		System.setProperty("log.home", "D:/test/logs");
		
		Reader readProducer = CreateReader.createReader("excel");
		ExcelReaderImplementation excelReader = readProducer.readExcelFile("D:\\test\\PersonalAdministration.xls", 0);
		List<Map<String, String>> excelDataList = excelReader.read();
		
		System.out.println(excelDataList);
		
		readProducer = CreateReader.createReader("XML");
		XmlReaderImplementation xmlReader =   readProducer.readXmlFile("D:\\test\\readxml.xml");
		List<Map<String, String>> xmlDataList = xmlReader.read();
		
		System.out.println(xmlDataList);
		
		readProducer = CreateReader.createReader("DB");
		DbReaderImplementation dbReader =  readProducer.readDbTableData(driver, url, userName, password, tableName);
		List<Map<String, String>> dbDataList = dbReader.read();
		
		System.out.println(dbDataList);
		
		readProducer = CreateReader.createReader("CSV");
		CsvReaderImplementation csvReader =  readProducer.readCsvFile("D:\\test\\Jsontocsv.csv");
		List<Map<String, String>> csvDataList = csvReader.read();
		
		System.out.println(csvDataList);
	}
	
	
}
