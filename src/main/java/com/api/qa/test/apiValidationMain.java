package com.api.qa.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class apiValidationMain {

	public static Properties prop;
	static Workbook book;
	static Sheet sheet;

	public apiValidationMain() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("Config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Object[][] testDataInitialization() {
		String methodName = prop.getProperty("method");
		String testDataFileName = prop.getProperty("testDataFile");

		FileInputStream file = null;
		try {
			file = new FileInputStream(testDataFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet = book.getSheet(methodName);

		Object[][] data = new Object[2][4];

		for (int i = 0; i < 2; i++) {
			for (int k = 0; k < 3; k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}

		return data;

	}

	public static String readPayload(String fileName) {
		String text = "";
		try {
			text = new String(Files.readAllBytes(Paths.get("Payload\\"+fileName+".txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}

}
