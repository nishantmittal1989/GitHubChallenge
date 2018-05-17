package com.github.main;

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
		
		CSVParser parser = null;
		PageViewSearch pvsObj = null;

		try {
			if(args==null || args.length<1){
				throw new Exception("Please pass file location in the command line argument");
				
			}
			
			parser = new CSVParser();
			pvsObj = new PageViewSearch();
			// place the file in the resources folder of the Challenge project
			// and mention the file name
			List<String[]> records = parser.readCSV(args[0]);

			System.out.println("\n<-----------------Result of Top 5 Queries-------------------->\n");
			getTopQueries(records, pvsObj);

			System.out.println("\n<----Result of Top 5 Queries, in terms of Results Clicked---->\n");
			getTopQueriesClicked(records, pvsObj);

			System.out.println("\n<-------Result of Average length of Search Session----------->\n");
			getAverageSessionTime(records, pvsObj);

		} catch (Exception e) {
			System.err.println("\n<--------------------------Error------------------------------>\n");
			System.err.println(e.getMessage());
		}
		
		System.out.println("\n<--------------------------Done------------------------------>\n");

	}

	public static void getTopQueries(List<String[]> records, PageViewSearch pvsObj) throws PageViewSearchException {

		// top 5 most frequently issued queries
		PriorityQueue<Entry<String, Integer>> pq = pvsObj.getTopNQueries(records, 5, 1);

		while (!pq.isEmpty()) {
			Entry<String, Integer> entry = pq.poll();
			System.out.println("Query: " + entry.getKey() + "\t, Count: " + entry.getValue());
		}
	}

	public static void getTopQueriesClicked(List<String[]> records, PageViewSearch pvsObj)
			throws PageViewSearchException {

		// top 5 queries in terms of total number of results clicked
		PriorityQueue<Entry<String, Integer>> pq = pvsObj.getTopNQueries(records, 5, 2);

		while (!pq.isEmpty()) {
			Entry<String, Integer> entry = pq.poll();
			System.out.println("Query: " + entry.getKey() + "\t, Count: " + entry.getValue());
		}
	}

	public static void getAverageSessionTime(List<String[]> records, PageViewSearch pvsObj)
			throws PageViewSearchException {

		Double avgTime = pvsObj.getAverageSessionTime(records);
		System.out.println("Average Time of Session in Seconds: " + avgTime);
	}

}
