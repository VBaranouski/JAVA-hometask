package com.hometask.calculations;

import java.util.Comparator;

import com.hometask.candies.Confection;

public class SortByCost implements Comparator<Confection> {

			
	public int compare(Confection o1, Confection o2) {
		
		double p1 = o1.getCost();
		double p2 = o2.getCost();
		
		if (p1 < p2){
			return 1;
		}
		else if (p1 > p2){
			return -1;
		}
		else return 0;
	}
}
	

