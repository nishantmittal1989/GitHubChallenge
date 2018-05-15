package com.github.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.github.exception.FileParserException;

public class CSVParser {

	/*
	 * function to load data from CSV file into a list of records
	 */
	public List<String[]> readCSV(String inputCSVPath) throws FileParserException, IOException, FileNotFoundException {

		// checks for null or empty locationPath
		if (StringUtils.isBlank(inputCSVPath)) {
			throw new FileParserException("invalid Path");
		}

		// checks if the CSV path is valid
		if (!inputCSVPath.substring(inputCSVPath.length() - 4, inputCSVPath.length()).equalsIgnoreCase(".csv")) {
			throw new FileParserException("invalid File");
		}

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		List<String[]> records = new ArrayList<String[]>();

		try {
			ClassLoader classLoader = ClassLoader.getSystemClassLoader();
			File file = new File(classLoader.getResource(inputCSVPath).getFile());

			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] record = line.split(cvsSplitBy);
				records.add(record);
			}

		} catch (FileNotFoundException ex) {
			throw ex;
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}
		return records;
	}

}
