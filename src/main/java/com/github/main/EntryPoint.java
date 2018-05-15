package com.github.main;

import java.io.File;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;

import com.github.exception.PageViewSearchException;
import com.github.io.CSVParser;
import com.github.logic.PageViewSearch;

/*
 * EntryPoint is the Starting Class for this Project to show the results of all the queries to Question 1 to 3
 */
public class EntryPoint {

	public static void main(String[] args) {

		CSVParser parser = new CSVParser();
		PageViewSearch pvsObj = new PageViewSearch();

		try {
			// place the file in the resources folder of the Challenge project
			// and mention the file name
			List<String[]> records = parser.readCSV("Data.csv");

			System.out.println("\n<-----------------Result of Top Queries-------------------->\n");
			getTopQueries(records, pvsObj);

			System.out.println("\n<-------------Result of Top Queries Clicked---------------->\n");
			getTopQueriesClicked(records, pvsObj);

			System.out.println("\n<-------Result of Average length of Search Session--------->\n");
			getAverageSessionTime(records, pvsObj);

		} catch (Exception e) {
			System.out.println("\n<-----------------------Error------------------------------>\n");
			System.out.println(e.getMessage());
		}

	}

	public static void getTopQueries(List<String[]> records, PageViewSearch pvsObj) throws PageViewSearchException {

		// top 5 most frequently issued queries
		PriorityQueue<Entry<String, Integer>> pq = pvsObj.getTopKQueries(records, 5, 1);

		while (!pq.isEmpty()) {
			Entry<String, Integer> entry = pq.poll();
			System.out.println("Query: " + entry.getKey() + "\t, Count: " + entry.getValue());
		}
	}

	public static void getTopQueriesClicked(List<String[]> records, PageViewSearch pvsObj)
			throws PageViewSearchException {

		// top 5 queries in terms of total number of results clicked
		PriorityQueue<Entry<String, Integer>> pq = pvsObj.getTopKQueries(records, 5, 2);

		while (!pq.isEmpty()) {
			Entry<String, Integer> entry = pq.poll();
			System.out.println("Query: " + entry.getKey() + "\t, Count: " + entry.getValue());
		}
	}

	public static void getAverageSessionTime(List<String[]> records, PageViewSearch pvsObj)
			throws PageViewSearchException {

		int avgTime = pvsObj.getAverageSessionTime(records);
		System.out.println("Average Time of Session in Seconds: " + avgTime);
	}

}
