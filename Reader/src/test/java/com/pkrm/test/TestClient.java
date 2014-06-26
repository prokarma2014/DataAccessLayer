package com.pkrm.test;


import java.util.List;
import java.util.Map;

import com.pkrm.CreateReader;
import com.pkrm.Reader;
import com.pkrm.csv.CsvReaderImplementation;
import com.pkrm.db.DbReaderImplementation;
import com.pkrm.excel.ExcelReaderImplementation;
import com.pkrm.xml.XmlReaderImplementation;

/**
 * 
 * @author Prokarma
 *
 */
public class TestClient {
	
	private static String driver = "org.postgresql.Driver";
	private static String url = "jdbc:postgresql://10.5.50.43/Test";
	private static String userName = "postgres";
	private static String password = "postgre";
	private static String tableName = "select * from COMPANY";
			
	public static void main(String[] args) throws Exception {
			
		System.setProperty("log.home", "D:/test/logs");
		
		Reader Reader = CreateReader.createReader("excel");
		ExcelReaderImplementation excelReader = Reader.readExcelFile("D:\\test\\PersonalAdministration.xls", 0);
		List<Map<String, String>> listmap = excelReader.read();
		
		System.out.println(listmap);
		
		Reader Reader1 = CreateReader.createReader("XML");
		XmlReaderImplementation xmlReader =  Reader1.readXmlFile("D:\\test\\readxml.xml");
		List<Map<String, String>> listmap1 = xmlReader.read();
		
		System.out.println(listmap1);
		
		Reader Reader2 = CreateReader.createReader("DB");
		DbReaderImplementation dbReader =  Reader2.readDbTableData(driver, url, userName, password, tableName);
		List<Map<String, String>> listmap2 = dbReader.read();
		
		System.out.println(listmap2);
		
		Reader Reader3 = CreateReader.createReader("CSV");
		CsvReaderImplementation csvReader =  Reader.readCsvFile("D:\\test\\Jsontocsv.csv");
		List<Map<String, String>> csvDataList = csvReader.read();
		
		System.out.println(csvDataList);
	}
}
