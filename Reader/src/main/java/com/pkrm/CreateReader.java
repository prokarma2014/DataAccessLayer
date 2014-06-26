package com.pkrm;

/**
 * 
 * @author Prokarma
 *
 */
public class CreateReader {

	/**
	 * 
	 * @param type
	 * @return Reader
	 */
	public static Reader createReader(String type) {

		if (type.equalsIgnoreCase("CSV")) {

			return new Reader();

		} else if (type.equalsIgnoreCase("XML")) {

			return new Reader();

		} else if (type.equalsIgnoreCase("EXCEL")) {

			return new Reader();

		} else if (type.equalsIgnoreCase("DB")) {

			return new Reader();

		}

		return null;

	}
}
