package org.example.question3;

import org.example.ConsoleLogger;

public class FactorsCalculator extends Thread{
    private int number;

        public FactorsCalculator(int number) {
            this.number = number;
        }

        @Override
        public void run() {
            calculateFactors(number);

        }

        private void calculateFactors(int n) {

            ConsoleLogger.infoMethod("Factors calculated: ");
            for (int i = 1; i <= n; i++) {
                if (n % i == 0) {

                    ConsoleLogger.infoMethod(i + " ");

                }
            }

        }
}
