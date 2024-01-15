package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Trader {


    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String walletAddress;


    public Trader(String firstname,String lastname,String phoneNumber, String walletAddress){
      this.firstName=firstname;
      this.lastName=lastname;
      this.phoneNumber=phoneNumber;
      this.walletAddress=walletAddress;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getWalletAddress() {
        return walletAddress;
    }


    private List<Transaction> transactions;  // Assuming a list to store trader's transactions
    private final Lock transactionsLock = new ReentrantLock();


    public double getTotalProfitOrLoss() {
        try {
            transactionsLock.lock();
            // Calculate total profit or loss based on the trader's transactions
            return transactions.stream()
                    .mapToDouble(Transaction::getProfitOrLoss)
                    .sum();
        } finally {
            transactionsLock.unlock();
        }
    }







    private double balance;
    private final Lock balanceLock = new ReentrantLock();

    public double getBalance() {
        // Placeholder implementation for getting the balance
        return balance;
    }
    public void updateBalance(double amount) {
        // Placeholder implementation for updating the balance
        try {
            balanceLock.lock();
            balance += amount;
        } finally {
            balanceLock.unlock();
        }
    }
    public void buyCoins(Coin coin, int quantity) {
        try {
            balanceLock.lock();
            // Check if the trader has enough balance and the coin has enough volume
            if (balance >= coin.getPrice() * quantity && coin.getVolume() >= quantity) {
                // Deduct the cost from the trader's balance
                balance -= coin.getPrice() * quantity;

                // Update the trader's portfolio or perform other necessary actions
                // For example, add the bought coins to the trader's portfolio

                ConsoleLogger.infoMethod("Coins bought successfully - Coin: " + coin.getCode() + ", Quantity: " + quantity);
            } else {

                ConsoleLogger.infoMethod("\"Failed to buy coins - Insufficient balance or insufficient volume.");
            }
        } finally {
            balanceLock.unlock();
        }
    }

    private final Lock portfolioLock = new ReentrantLock();
    private Map<String, Integer> portfolio;
    public boolean hasSufficientCoins(Coin coin, int quantity) {
        try {
            portfolioLock.lock();

            return portfolio.containsKey(coin.getCode()) && portfolio.get(coin.getCode()) >= quantity;
        } finally {
            portfolioLock.unlock();
        }
    }


    public void sellCoins(Coin coin, int quantity) {
        try {
            portfolioLock.lock();
            // Check if the trader has enough of the specified coin in their portfolio
            if (portfolio.containsKey(coin.getCode()) && portfolio.get(coin.getCode()) >= quantity) {
                // Perform the actual selling logic
                portfolio.put(coin.getCode(), portfolio.get(coin.getCode()) - quantity);

                // Add the sale amount to the trader's balance (you might adjust this based on your application)
                double saleAmount = quantity * coin.getPrice();
                balanceLock.lock();
                try {
                    balance += saleAmount;
                } finally {
                    balanceLock.unlock();
                }

                ConsoleLogger.infoMethod("Coins sold successfully - Coin: " + coin.getCode() + ", Quantity: " + quantity);
            } else {
                ConsoleLogger.infoMethod("Failed to sell coins - Insufficient coins in the portfolio.");
            }
        } finally {
            portfolioLock.unlock();
        }
    }

    public String toString() {

        return "Trader{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", walletAddress='" + walletAddress + '\'' +
                '}';
    }

}
