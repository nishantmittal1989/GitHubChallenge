package com.github.main;

import java.io.IOException;
import java.util.Properties;

public class Application {

	public static void main(String args[]) {

		String[] arguments = new String[1];
		Properties prop = new Properties();
		try {
			// load a properties file from class path, inside static method
			prop.load(Application.class.getClassLoader().getResourceAsStream("config.properties"));

			// get the property value and print it out
			arguments[0] = prop.getProperty("FileLocation");
			EntryPoint.main(arguments);

		} catch (IOException ex) {
			System.err.println("\n<--------------------------Error------------------------------>\n");
			System.err.println("Error in reading the properties file");
		}

	}

}
