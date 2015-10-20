package com.hometask.candies;

import org.apache.log4j.Logger;

import com.hometask.giftbox.Gift;

public class Confection {
	
	private	String name;
	private double cost;
	private double weight;
	private String type;
	final static Logger log = Logger.getLogger(Gift.class);
	
	public Confection (String name, double cost, double weight, String type){
		this.name = name;
		this.cost = cost;
		this.weight = weight;
		this.type = type;
	}

	public Confection() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		if (name == "" || name == " ") { 
			log.error("Incorrect name data. Field is empty");
			throw new IllegalArgumentException("Check the Name! Name field cannot be empty");
	    }
		return name;
		
	}

	public double getCost() {
		if (cost < 0) {
			log.error("Incorrect cost data (negative or missing digits)");
			throw new IllegalArgumentException("Check the cost! Cost must be nonnegative");
	    }
		return cost;
	}

	public double getWeight() {
		if (weight <= 0) {
			log.error("Incorrect weight data (negative or missing digits)");
			throw new IllegalArgumentException("Check the weight! Weight must be nonnegative");
	    }
		return weight;
	}
	
	public String getType() {
		if (type == "" || type == " ") {
			log.error("Incorrect name data. Field is empty");
			throw new IllegalArgumentException("Check the type! Type field cannot be empty");
	    }
		return type;
		
	}
	
		
}
