package com.pkrm.excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.pkrm.Utils;
import com.pkrm.dao.ReaderDAO;

/**
 * 
 * @author Prokarma
 * 
 */
public class ExcelReaderImplementation implements ReaderDAO {

	private String path;
	private FileInputStream fis = null;
	private FileOutputStream fileOut = null;
	private Workbook workbook = null;
	private Sheet sheet = null;
	private Row row = null;
	private Cell cell = null;
	private int sheetNum;

	/**
	 * This method is used to open the file
	 * 
	 * @param path
	 * @param sheetNum
	 */
	public ExcelReaderImplementation(String path, int sheetNum) {

		this.path = path;
		try {
			Utils.logger.info(" Reading the Excel file");
			fis = new FileInputStream(path);
			this.workbook = WorkbookFactory.create(fis);
			this.sheetNum = sheetNum;
			fis.close();
		} catch (Exception e) {
			Utils.logger.error(e.getMessage());
		}

	}

	private static volatile ExcelReaderImplementation instance = null;

	/**
	 * This method create the instance of ExcelReader
	 * 
	 * @param excelFilePath
	 * @param sheetNum
	 * @return ExcelReader
	 */
	public static ExcelReaderImplementation getInstance(String excelFilePath,
			int sheetNum) {
		Utils.logger.info(" Trying to create instance for Excelfile");
		if (instance == null) {
			synchronized (ExcelReaderImplementation.class) {
				if (instance == null) {
					instance = new ExcelReaderImplementation(excelFilePath,
							sheetNum);
				}
			}
		}
		return instance;
	}

	/**
	 * This method read the excel file data
	 * 
	 * @return List of map objects
	 */
	public List<Map<String, String>> read() {

		Utils.logger.info(" inside read() method");

		List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		Map<Integer, String> headerMap = new HashMap<Integer, String>();
		try {
			int rowCount = 1;

			sheet = workbook.getSheetAt(sheetNum);
			for (Row row : sheet) {
				int columnCouont = 1;
				map = new HashMap<String, String>();

				for (Cell cell : row) {

					if (cell != null) {
						if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
							if (rowCount == 1) {
								headerMap.put(columnCouont,
										cell.getStringCellValue());
							} else {
								map.put(headerMap.get(columnCouont),
										cell.getStringCellValue());
							}

						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

							String cellText = String.valueOf(cell
									.getNumericCellValue());
							if (DateUtil.isCellDateFormatted(cell)) {
								// format in form of M/D/YY
								double d = cell.getNumericCellValue();

								Calendar cal = Calendar.getInstance();
								cal.setTime(DateUtil.getJavaDate(d));
								cellText = (String.valueOf(cal
										.get(Calendar.YEAR))).substring(2);
								cellText = cal.get(Calendar.MONTH) + 1 + "/"
										+ cal.get(Calendar.DAY_OF_MONTH) + "/"
										+ cellText;
							}
							if (rowCount == 1) {
								headerMap.put(columnCouont, cellText);
							} else {
								map.put(headerMap.get(columnCouont), cellText);
							}
						} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
							if (rowCount == 1) {
								headerMap.put(columnCouont, "");
							} else {
								map.put(headerMap.get(columnCouont), "");
							}
						else {
							if (rowCount == 1) {
								headerMap.put(columnCouont, String.valueOf(cell
										.getBooleanCellValue()));
							} else {
								map.put(headerMap.get(columnCouont), String
										.valueOf(cell.getBooleanCellValue()));
							}
						}
					}
					columnCouont++;

				}

				if (rowCount != 1) {
					listMap.add(map);
				}
				rowCount++;

			}

		} catch (Exception e) {

			if (e.getMessage() != null && e.getMessage().isEmpty()) {
				Utils.logger.error(e.getMessage());
			} else {
				Utils.logger.error(" column Num does not exist in file");
			}

		}
		return listMap;

	}

}
