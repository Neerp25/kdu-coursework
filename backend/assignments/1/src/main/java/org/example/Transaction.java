package org.example;


public class Transaction {

    private TransactionType type;
    private String coin;
    private double quantity;
    private String walletAddress;
    private double price;
    private double volume;
    private String transactionHash;

    public double getProfitOrLoss() {
        // Placeholder logic to calculate profit or loss for a single transaction
        double transactionAmount = quantity * price;
        if (TransactionType.BUY.equals(type)) {
            return -transactionAmount;  // Negative for buy transactions
        } else {
            return transactionAmount;   // Positive for sell transactions
        }
    }

}
