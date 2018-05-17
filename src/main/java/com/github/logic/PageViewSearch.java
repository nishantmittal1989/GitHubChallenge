package com.github.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.regex.Pattern;

import com.github.exception.PageViewSearchException;

/*
 * Class containing the functionality to return statistic computation done on page view based on user action
 */
public class PageViewSearch {

	/*
	 * This function returns top n queries for searched or clicked results based
	 * on the column number passed. Time Complexity is O(n), space Complexity is
	 * O(n). records: DataSet passed to the function, n: number of Top queries
	 * needed, filterByColumn: the column used to filter and compute top queries
	 * and top clicked results
	 */
	public PriorityQueue<Entry<String, Integer>> getTopNQueries(List<String[]> records, int n, int filterByColumn)
			throws PageViewSearchException {
		if (records == null || records.isEmpty())
			throw new PageViewSearchException("No Records in the File");

		HashMap<String, Integer> mapOfQueries = new HashMap<>();

		PriorityQueue<Entry<String, Integer>> topQueriesPQ = new PriorityQueue<>(n, (Entry<String, Integer> query1,
				Entry<String, Integer> query2) -> query1.getValue() >= query2.getValue() ? 1 : -1);

		for (String[] record : records) {

			try {
				// using regex pattern ^\/search\?q=.*$ to filter search queries
				// from repository clicks
				String pattern = "^\\/search\\?q=.*$";
				if (Pattern.matches(pattern, record[filterByColumn])) {
					if (mapOfQueries.containsKey(record[filterByColumn]))
						mapOfQueries.put(record[filterByColumn], mapOfQueries.get((record)[filterByColumn]) + 1);
					else
						mapOfQueries.put(record[filterByColumn], 1);
				}
			} catch (Exception ex) {
				throw ex;
			}
		}

		int i = 0;
		if (!mapOfQueries.isEmpty()) {
			for (Entry<String, Integer> entry : mapOfQueries.entrySet()) {
				if (i >= n) {
					topQueriesPQ.add(entry);

					// remove the least priority query
					topQueriesPQ.poll();

				} else
					topQueriesPQ.add(entry);
				i++;
			}
		} else {
			throw new PageViewSearchException("No queries found");
		}

		return topQueriesPQ;
	}

	/*
	 * This function calculate the average length of a search session. Time
	 * Complexity is O(n), space Complexity is O(n), records: DataSet passed to
	 * the function
	 */
	public Double getAverageSessionTime(List<String[]> records) throws PageViewSearchException {
		if (records == null || records.isEmpty())
			throw new PageViewSearchException("No Records in the File");

		Map<String, Integer> minTimePerSessionPerUserMap = new HashMap<>();
		Map<String, Integer> maxTimePerSessionPerUserMap = new HashMap<>();

		for (String[] record : records) {

			try {
				// using regex pattern ^\/search\?q=.*$ to filter search queries
				// from repository clicks
				String pattern = "^\\/search\\?q=.*$";
				if (Pattern.matches(pattern, record[2])) {
					String key = record[2].concat(record[3]);
					if (maxTimePerSessionPerUserMap.containsKey(key))
						maxTimePerSessionPerUserMap.put(key,
								Math.max(Integer.parseInt(record[0]), maxTimePerSessionPerUserMap.get(key)));
					else
						maxTimePerSessionPerUserMap.put(key, Integer.parseInt(record[0]));
				} else {
					String key = record[1].concat(record[3]);
					minTimePerSessionPerUserMap.put(key, Integer.parseInt(record[0]));
				}
			} catch (Exception ex) {
				throw ex;
			}
		}

		int totalTime = 0;
		double avgTime = 0;

		// computing the average session time
		if (maxTimePerSessionPerUserMap != null && !maxTimePerSessionPerUserMap.isEmpty()
				&& minTimePerSessionPerUserMap != null && !minTimePerSessionPerUserMap.isEmpty()) {
			for (String key : maxTimePerSessionPerUserMap.keySet()) {
				totalTime += (maxTimePerSessionPerUserMap.get(key) - minTimePerSessionPerUserMap.get(key));
			}
			avgTime = (double)totalTime / maxTimePerSessionPerUserMap.size();
		}

		return avgTime;
	}

}
