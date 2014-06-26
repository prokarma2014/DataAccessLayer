package com.pkrm;

import java.io.FileNotFoundException;

import com.pkrm.csv.CsvReaderImplementation;
import com.pkrm.db.DbReaderImplementation;
import com.pkrm.excel.ExcelReaderImplementation;
import com.pkrm.exceptions.DataBaseConnectionException;
import com.pkrm.xml.XmlReaderImplementation;

/**
 * 
 * @author Prokarma
 *
 */
public class Reader {

	private static ExcelReaderImplementation excelReader = null;
	private static CsvReaderImplementation csvReader = null;
	private static DbReaderImplementation dbReader = null;
	private static XmlReaderImplementation xmlRreader = null;

	public Reader() {

	}

	/**
	 * 
	 * @param filePath
	 * @param sheetNum
	 * @return ExcelReader
	 * @throws Exception
	 */
	public ExcelReaderImplementation readExcelFile(String filePath, int sheetNum)
			throws Exception {

		if (filePath == null || filePath.trim().isEmpty() || sheetNum < 0) {

			throw new FileNotFoundException("file path is not specified");
		} else {
			excelReader = ExcelReaderImplementation.getInstance(filePath, sheetNum);
		}
		return excelReader;

	}

	/**
	 * 
	 * @param filePath
	 * @return CsvReader
	 * @throws Exception
	 */
	public CsvReaderImplementation readCsvFile(String filePath) throws Exception {

		if (filePath == null || filePath.trim().isEmpty()) {

			throw new FileNotFoundException("file path is not specified");
		} else {
			csvReader = CsvReaderImplementation.getInstance(filePath);
		}
		return csvReader;

	}

	/**
	 * 
	 * @param driver
	 * @param url
	 * @param userName
	 * @param password
	 * @param tableName
	 * @return DbReader
	 * @throws Exception
	 */
	public DbReaderImplementation readDbTableData(String driver, String url, String userName,
			String password, String tableName) throws Exception {
		if (isvalidproperties(driver, url, userName, password, tableName)) {
			return dbReader = DbReaderImplementation.getInstance(driver, url, userName,
					password, tableName);
		} else {

			throw new DataBaseConnectionException(
					"Incorrect database properties");
		}

	}

	/**
	 * 
	 * @param driver
	 * @param url
	 * @param userName
	 * @param password
	 * @param tableName
	 * @return boolean
	 */
	private boolean isvalidproperties(String driver, String url,
			String userName, String password, String tableName) {
		boolean valid = true;
		if (null == driver || driver.trim().isEmpty()) {
			valid = false;
		}
		if (null == url || url.trim().isEmpty()) {
			valid = false;
		}
		if (null == userName || userName.trim().isEmpty()) {
			valid = false;
		}
		if (null == tableName || tableName.trim().isEmpty()) {
			valid = false;
		}
		return valid;
	}

	/**
	 * 
	 * @param filePath
	 * @return XmlReader
	 * @throws Exception
	 */
	public XmlReaderImplementation readXmlFile(String filePath) throws Exception {

		if (filePath == null || filePath.trim().isEmpty()) {

			throw new FileNotFoundException("file path is not specified");
		} else {
			xmlRreader = XmlReaderImplementation.getInstance(filePath);
		}
		return xmlRreader;

	}
}
