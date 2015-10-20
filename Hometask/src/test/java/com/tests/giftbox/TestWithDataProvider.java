package com.tests.giftbox;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.hometask.candies.Chocolate;
import com.hometask.candies.Confection;
import com.hometask.candies.Cookies;
import com.hometask.candies.Marshmallow;
import com.hometask.giftbox.Gift;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

@RunWith(DataProviderRunner.class)
public class TestWithDataProvider {

    @DataProvider
    public static Object[][] dataProviderAdd() {
       return new Object[][] {
                { 1.2, 2, 6.5, 9.7 },
                { 4, 5, 3, 12 },
                { 0.2, 4, 6, 10.2 },
                { -0.2, 4, 6, 10.2 } //fourth test is failed as expected 
       };            
    }
    
   
    @Test 
    @UseDataProvider("dataProviderAdd")
    public void testGetFinalCost(double a, double b, double c, double result) {
    	Gift g = new Gift();
    	ArrayList<Confection> list = new ArrayList<Confection>();
    	Chocolate ch1 = new Chocolate("S", a , 50.0, "C", "H");
		list.add(ch1);
		Cookies cs1 = new Cookies("S", b , 30, "C", 5);
		list.add(cs1);
    	Marshmallow ms1 = new Marshmallow("Z", c, 80.1,"M","B");
		list.add(ms1);
        
		assertEquals(0, Double.compare(result, g.getFinalCost(list, 0)));
    }
        
}
