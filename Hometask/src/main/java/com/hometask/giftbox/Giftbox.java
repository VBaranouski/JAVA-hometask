package com.hometask.giftbox;

import java.io.IOException;
import java.util.ArrayList;

import com.hometask.candies.Confection;

public interface Giftbox {

	public double getFinalCost(ArrayList<Confection> list, double sum);
	public void findElementByCost(ArrayList<Confection> list);
	public StringBuilder findCandyByCost(double a, double b, ArrayList<Confection> list);
	public void displayCollection(ArrayList<Confection> list);
	public void saveToFile(ArrayList<Confection> list) throws IOException;
}
