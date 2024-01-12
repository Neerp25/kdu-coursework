package org.example.question3;

import org.example.ConsoleLogger;

public class Main {
    public static void main(String[] args){

        int number = 5;

        FactorialCalculator factorialCalculator = new FactorialCalculator(number);
        FactorsCalculator factorsCalculator = new FactorsCalculator(number);

        factorialCalculator.start();

        try{
            Thread.sleep(200);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }

        factorsCalculator.start();

        try {
            factorialCalculator.join();
            factorsCalculator.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//        System.out.println("Main thread finished.");
        ConsoleLogger.infoMethod("Main thread finished.");

    }
}
