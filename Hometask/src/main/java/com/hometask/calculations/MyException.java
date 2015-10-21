package com.hometask.calculations;

public class MyException extends Exception {               
 
    public MyException(String msg) {super(msg);} 
    public MyException() {} 
    

    public static void compareValues(double a, double b) throws MyException { 
        if (a < 0 || b < 0) {
            throw new MyException("Check entered numbers. They cannot be negative");
        }
   
    }
}

