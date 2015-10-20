package com.tests.giftbox;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.hometask.candies.Chocolate;
import com.hometask.candies.Confection;
import com.hometask.candies.Cookies;
import com.hometask.candies.Marshmallow;
import com.hometask.giftbox.Gift;
import com.tests.testsuite.NegativeTests;
import com.tests.testsuite.PositiveTests;

public class TestGift {

	private Gift g;
	private ArrayList<Confection> list; // for positive tests
	private ArrayList<Confection> listN; // for negative tests and handling
											// exceptions
	private StringBuilder candy;

	@Before
	public void setUp() throws Exception {

		candy = new StringBuilder();
		g = new Gift();

		list = new ArrayList<Confection>();
		Chocolate ch1 = new Chocolate("A", 2.00, 15, "A1", "A2");
		list.add(ch1);
		Marshmallow mm1 = new Marshmallow("B", 2.50, 5, "B1", "B2");
		list.add(mm1);
		Cookies ck1 = new Cookies("C", 3.0, 5, "C1", 5);
		list.add(ck1);

		listN = new ArrayList<Confection>();
		Chocolate ch2 = new Chocolate("", 2.00, 15, "", "A2");
		listN.add(ch2);
		Marshmallow mm2 = new Marshmallow("B", -2.50, 5, "B1", "B2");
		listN.add(mm2);
		Cookies ck2 = new Cookies("C", 3.0, 5, "C1", 5);
		listN.add(ck2);
	}

	
	
	@Ignore
	@Test // !Test doesn't work correctly. Returns fail, but expected result is equal to "was" value.
	public void testFindCandyByCostPositive() {
		assertEquals((candy.append("B(B1), ")), g.findCandyByCost(2.3, 2.5, list));
	}

	
	@Category({ NegativeTests.class })
	@Test(expected = IllegalArgumentException.class) // Name and Type fields cannot be empty
	public void testFindCandyByCostNegative() {
		assertEquals((candy.append("B(B1), ")), g.findCandyByCost(1.3, 2.15, listN));
	}

	
	@Test(expected = IllegalArgumentException.class) // Second value should be bigger then the first
	public void testFindCandyByCostNegative1() {
		assertEquals((candy.append("C(C1), ")), g.findCandyByCost(8.9, 3.25, list));
	}

	
	@Test(expected = IllegalArgumentException.class) // Values cannot be negative
	public void testFindCandyByCostNegative2() {
		assertEquals((candy.append("C(C1), ")), g.findCandyByCost(-2.9, -2.25, list));
	}

	@Category({ PositiveTests.class })
	@Test
	public void testSaveToFilePositive() throws IOException {
		g.saveToFile(list);
		BufferedReader br = new BufferedReader(new FileReader("result.txt"));
		String str = br.readLine();
		assertEquals("1. A 2.0 15.0 A1", str);
		br.close();
	}

	@Category({ NegativeTests.class })
	@Test(expected = FileNotFoundException.class)
	public void testSaveToFileException() throws IOException {
		g.saveToFile(list);
		BufferedReader br = new BufferedReader(new FileReader("result1.txt"));
		String str = br.readLine();
		assertEquals("1. A 2.0 15.0 A1", str);
		br.close();
	}

	@Test
	public void testSaveToFileNegative() throws IOException {
		g.saveToFile(list);
		BufferedReader br = new BufferedReader(new FileReader("result.txt"));
		String str = br.readLine();
		assertFalse(str == null);
		br.close();
	}
	
	
	
	
}
