package org.example.question3;

import org.example.ConsoleLogger;

public class FactorialCalculator extends Thread{

      private int number;
        private long factorial;

        public FactorialCalculator(int number) {
            this.number = number;
        }

        public long getFactorial() {
            return factorial;
        }

    public void run() {
            factorial = calculateFactorial(number);
//            System.out.println("Factorial calculated: " + factorial);
        ConsoleLogger.infoMethod("Factorial calculated: " + factorial);


        }

        private long calculateFactorial(int n) {
            if (n == 0 || n == 1) {
                return 1;
            } else {
                return n * calculateFactorial(n - 1);
            }
        }
}
