package com.pkrm.csv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import au.com.bytecode.opencsv.CSVReader;

import com.pkrm.Utils;
import com.pkrm.dao.ReaderDAO;

/**
 * 
 * @author Prokarma
 *
 */
public class CsvReaderImplementation implements ReaderDAO {

	private CSVReader reader;
	private List<String[]> myEntries;

	/**
	 * This method is used to open the file
	 * @param path
	 */
	public CsvReaderImplementation(String path) {

		try {
			Utils.logger.info(" Reading the CSV file");
			reader = new CSVReader(new FileReader(path));
			myEntries = reader.readAll();
		} catch (FileNotFoundException e) {
			Utils.logger.error(e.getMessage());
		} catch (IOException e) {
			Utils.logger.error(e.getMessage());
		}

	}
	
	private static volatile CsvReaderImplementation instance = null;

	/**
	 * This method create the instance of CsvReader
	 * @param path
	 * @return CsvReader
	 */
	public static CsvReaderImplementation getInstance(String path) {
		Utils.logger.info(" Trying to create instance for CSVfile");
		if (instance == null) {
			synchronized (CsvReaderImplementation.class) {
				if (instance == null) {
					instance = new CsvReaderImplementation(path);
				}
			}
		}
		return instance;
	}

	/**
	 * This method read the CSV file data 
	 * @return List of map objects
	 */
	public List<Map<String, String>> read() {

		Utils.logger.info(" inside read() method");
		
		List<Map<String, String>> maplist = new ArrayList<Map<String, String>>();
		List<String> keyList = new ArrayList<String>();

		try {

			byte startFlag = 0;
			for (String[] values : myEntries) {
				if (startFlag == 0) {
					keyList = Arrays.asList(values);
					startFlag++;
				} else {
					Map<String, String> map = new HashMap<String, String>();
					byte index = 0;
					for (String value : values) {
						map.put(keyList.get(index), value);
						index++;
					}
					maplist.add(map);
				}

			}
		} catch (Exception e) {
			Utils.logger.error(e.getMessage());
		}
		return maplist;		
	}

}
