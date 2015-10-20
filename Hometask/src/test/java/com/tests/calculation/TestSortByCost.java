package com.tests.calculation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import com.hometask.calculations.SortByCost;
import com.hometask.candies.Chocolate;
import com.hometask.candies.Confection;
import com.hometask.candies.Cookies;
import com.hometask.candies.Marshmallow;
import com.hometask.giftbox.Gift;

public class TestSortByCost {

	private ArrayList<Confection> list; // for positive tests
	
	
	@Before
	public void setUp() throws Exception {
		new Gift();

		list = new ArrayList<Confection>();
		Chocolate ch1 = new Chocolate("A", 2.00, 15, "A1", "A2");
		list.add(ch1);
		Marshmallow mm1 = new Marshmallow("B", 2.50, 5, "B1", "B2");
		list.add(mm1);
		Cookies ck1 = new Cookies("C", 3.0, 5, "C1", 5);
		list.add(ck1);
		list = new ArrayList<Confection>();
		Chocolate ch2 = new Chocolate("A", 1.20, 15, "A1", "A2");
		list.add(ch2);
		Marshmallow mm2 = new Marshmallow("B", 2.50, 5, "B1", "B2");
		list.add(mm2);
		Cookies ck2 = new Cookies("C", 8.0, 5, "C1", 5);
		list.add(ck2);
		
	}

	@Test
	public void testSortByCostPositive() {
	
		Collections.sort(list, new SortByCost());
		double[] rez = new double[list.size()];
		for (Confection vault : list) {
			int i = 0;
			rez[i] = vault.getCost(); 
			i++;
			assertTrue(vault.getCost() >= rez[i]);
		}
		
	}
	
	@Test (expected=AssertionError.class)
	public void testSortByCostNegative() {
	
		Collections.sort(list, new SortByCost());
		double[] rez = new double[list.size()];
		for (Confection vault : list) {
			int i = 0;
			rez[i] = vault.getCost(); 
			i++;
			assertTrue(vault.getCost() < rez[i]);
		}
		
	}

}
