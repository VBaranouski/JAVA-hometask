package com.hometask.giftbox;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.hometask.calculations.SortByCost;
import com.hometask.candies.Chocolate;
import com.hometask.candies.Confection;
import com.hometask.candies.Cookies;
import com.hometask.candies.Marshmallow;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;


public class Gift implements Giftbox {

	final static Logger log = Logger.getLogger(Gift.class);
	
	
	public static void createBox() {

		///// 0. Logger  ///////
		BasicConfigurator.configure();
		
	
        ///// 1. Creating a collection /////
		Gift gift = new Gift();
		ArrayList<Confection> list = new ArrayList<Confection>();
		Chocolate ch1 = new Chocolate("Snikers ", 2.99, 50, "Chocolate", "Hazelnut");
		list.add(ch1);
		Chocolate ch2 = new Chocolate("Mars    ", 2.51, 45, "Chocolate", "Meadows");
		list.add(ch2);
		Marshmallow ms1 = new Marshmallow("Zephyr   ", 6.10, 80.1, "Marshmallow", "Blueberry");
		list.add(ms1);
		Marshmallow ms2 = new Marshmallow("American ", 8.50, 95.5, "Marshmallow", "Strawberry");
		list.add(ms2);
		Cookies cs1 = new Cookies("Sand    ", 1.99, 30, "Cookie", 5);
		list.add(cs1);
		Cookies cs2 = new Cookies("Cracker ", 3.99, 50.5, "Cookie", 10);
		list.add(cs2);
		log.info("Creating a collection");
		
				
		///// 2. Displaying entered info and saving data to result file /////

		System.out.println("1. Hello! Here is a gift box.\nCollection bellow will be saved to result.txt file.\n");
		gift.displayCollection(list);
		log.info("Saving to file");	
		try { 
			gift.saveToFile(list);
		} catch (IOException e1) {
			e1.printStackTrace();
			log.error("Error during saving to external file", e1);
		}
						
		///// 3. Calculate the final cost of all items ////

		double res = gift.getFinalCost(list, 0);
		System.out.println("\n2. Total cost of all items is: " + res + "\n");
		log.debug("Final cost is calculated: " + res);
		
		
		///// 4. Sorting by cost //////

		System.out.println("3. Sorted list by Cost:\n");
		Collections.sort(list, new SortByCost());
		gift.displayCollection(list);
		log.info("Sorting was successful");	
		
		
		///// 5. Create collection from JSON file /////
		
		System.out.println("\n4. New list from JSON file:");
		JSONParser parser = new JSONParser();
		log.debug("Starting JSONparsing");
		try {
			JSONArray array = (JSONArray) parser.parse(new FileReader("test.json"));
			for (Object arr : array) {
				JSONObject giftbox = (JSONObject) arr;
				String name = (String) giftbox.get("Name");
				Double cost = (Double) giftbox.get("Cost");
				Double weight = (Double) giftbox.get("Weight");
				String type = (String) giftbox.get("Type");
				String other = (String) giftbox.get("Other");

				ArrayList<Confection> newlist = new ArrayList<Confection>();
				Chocolate candy = new Chocolate(name, cost, weight, type, other);
				newlist.add(candy);
				for (Confection vault : newlist) {
					System.out.println(".   " + vault.getName() + "    " + vault.getCost() + "   " + vault.getWeight()
							+ "   " + vault.getType());
				}
			}
			log.info("No errors during parsing");
		} catch (FileNotFoundException e) {
			System.out.println("external file not found!!!");
			log.error("File not found", e);
		} catch (IOException e) {
			System.out.println("unable to read external file");
			log.error("Unable to read extrenal file", e);
		} catch (ParseException e) {
			e.printStackTrace();
			log.error("Parse exception", e);
		}
		
		
		//// 6. Find candy by cost. Type range values in console /////

		System.out.println(
				"\nAnd now let's find some candies from gift-box by thier cost.\nPlease enter first and last numbers of range");
		gift.findElementByCost(list);
		log.info("No errors during searching for candy");	
	}
	
						     		/** METHODS */
	
		
	 /** 
	 * First method. Returns the total cost of all 
	 * items from list (ArrayList<Confection> list)
	 */

	public double getFinalCost(ArrayList<Confection> list, double sum) {
		for (Confection vault : list) {
			sum += vault.getCost();
		}
		return sum;
	}

	
	/** 
	 * Second method. Returns candy from list based on 
	 * users entered info. Cost of this candy will be 
	 * more or equal A and less or equal B. 
	 * A and B values user types in console. 
	 */
		
	public void findElementByCost(ArrayList<Confection> list) {
		Scanner sc = new Scanner(System.in);
		double a = sc.nextDouble();
		double b = sc.nextDouble();
		sc.close();
		StringBuilder item = findCandyByCost(a, b, list);
		System.out.println("\nYour candies are: " + item);
	}

	public StringBuilder findCandyByCost(double a, double b, ArrayList<Confection> list) {
		StringBuilder candy = new StringBuilder();
		for (Confection vault : list) {
			if (vault.getCost() >= a && vault.getCost() <= b) {
				candy.append(vault.getName()).append("(").append(vault.getType()).append("), ");}
			}
			if (b < a || a < 0 || b < 0) { 
				log.error("Numbers cannot be negative. Second one should be bigger"); 
				throw new IllegalArgumentException("Numbers cannot be negative. Second one should be bigger");
			}
		return candy;
				
	}
	
	
	/** 
	 * Third method. Displays collection based on 
	 * entered objects and their parameters. 
	 */

	public void displayCollection(ArrayList<Confection> list) {
		int i = 1;
		System.out.println("ID: " + " NAME:    " + "   COST: " + "   WEIGHT:  " + "  TYPE:");
		for (Confection vault : list) {
			System.out.println(i + ".   " + vault.getName() + "    " + vault.getCost() + "       " + vault.getWeight()
					+ "     " + vault.getType());
			i++;
		}
	}
	
	
	/** 
	 * Fourth method. Saves collection to resut.txt file. 
	 *  
	 */
	
	public void saveToFile(ArrayList<Confection> list) throws IOException {
		FileWriter file = new FileWriter("result.txt");
		Writer output = new BufferedWriter(file);
		int i = 1;
		for (Confection vault : list) {
			output.write(i + ". " + vault.getName() + " " + vault.getCost() + " " + vault.getWeight()
					+ " " + vault.getType() + "\n" );
			i++;
		}
		output.close();
	}

}
