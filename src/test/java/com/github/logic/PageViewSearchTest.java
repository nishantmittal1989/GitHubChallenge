package com.github.logic;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.exception.PageViewSearchException;

public class PageViewSearchTest {

	PageViewSearch pageView = null;
	List<String[]> testRecords = null;

	@Before
	public void setUp() {
		pageView = new PageViewSearch();
		testRecords = new ArrayList<String[]>();

		// test data setup
		String[] record1 = new String[] { "1496217904", "/search?q=millstream", "", "55" };
		String[] record2 = new String[] { "1496217906", "/repository/5289", "/search?q=millstream", "55" };
		String[] record3 = new String[] { "1496217962", "/repository/9800", "/search?q=millstream", "55" };
		String[] record4 = new String[] { "1496217907", "/repository/3913", "/search?q=millstream", "55" };
		String[] record5 = new String[] { "1496248969", "/search?q=pervigilium", "", "33" };
		String[] record6 = new String[] { "1496248970", "/repository/5632", "/search?q=pervigilium", "33" };
		String[] record7 = new String[] { "1496217999", "/search?q=millstream", "", "22" };

		testRecords.add(record1);
		testRecords.add(record2);
		testRecords.add(record3);
		testRecords.add(record4);
		testRecords.add(record5);
		testRecords.add(record6);
		testRecords.add(record7);

	}

	@Test(expected = PageViewSearchException.class)
	public void testGetTopKQueries_NullRecords() throws PageViewSearchException {
		pageView.getTopNQueries(null, 1, 2);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetTopNQueries_topQueriesWithInvalidData() throws PageViewSearchException {
		pageView.getTopNQueries(testRecords, 1, 90);
	}

	@Test
	public void testGetTopNQueries_ResultsClicked() throws PageViewSearchException {
		PriorityQueue<Entry<String, Integer>> pq = pageView.getTopNQueries(testRecords, 1, 2);
		assertEquals(1, pq.size());
		assertEquals("/search?q=millstream", pq.peek().getKey());
	}

	@Test
	public void testGetTopNQueries_topQueries() throws PageViewSearchException {
		PriorityQueue<Entry<String, Integer>> pq = pageView.getTopNQueries(testRecords, 1, 1);
		assertEquals(1, pq.size());
		assertEquals("/search?q=millstream", pq.peek().getKey());
	}

	@Test
	public void testGetAverageSessionTime() throws PageViewSearchException {

		Double avgSessionTime = pageView.getAverageSessionTime(testRecords);
		Assert.assertEquals(Double.valueOf(29.5), avgSessionTime);

	}

}
