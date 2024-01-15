package org.example;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ExecuteTransaction implements Runnable {
    private JsonNode transactionData;
    private CountDownLatch latch;

    // Assume you have lists of coins and traders
    private List<Coins> coins;
    private List<Trader> traders;

    public ExecuteTransaction(JsonNode transactionData, CountDownLatch latch) {
        this.transactionData = transactionData;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            String transactionType = transactionData.get("type").asText();

            switch (transactionType) {
                case "BUY":
                    executeBuyTransaction();
                    break;
                case "SELL":
                    executeSellTransaction();
                    break;
                case "UPDATE_PRICE":
                    executeUpdatePriceTransaction();
                    break;
                case "ADD_VOLUME":
                    executeAddVolumeTransaction();
                    break;
                default:
                    ConsoleLogger.infoMethod("Unknown transaction type: " + transactionType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
    }

    private void executeBuyTransaction() {
        String coinCode = transactionData.get("data").get("coin").asText();
        int quantity = transactionData.get("data").get("quantity").asInt();
        String walletAddress = transactionData.get("data").get("wallet_address").asText();

        // Placeholder logic for processing a BUY transaction
        Coins coinToBuy = getCoinByCode(coinCode);
        Trader buyer = getTraderByWalletAddress(walletAddress);

        if (coinToBuy != null && buyer != null) {
            double totalPrice = quantity * coinToBuy.getPrice();
            if (buyer.getBalance() >= totalPrice) {
                // Perform the actual buying logic
                buyer.buyCoins(coinToBuy, quantity);

                ConsoleLogger.infoMethod("Buy transaction successful - Coin: " + coinCode + ", Quantity: " + quantity +
                        ", Total Price: " + totalPrice);
            } else {
                ConsoleLogger.infoMethod("Insufficient funds for the BUY transaction.");
            }
        } else {
            ConsoleLogger.infoMethod("Invalid BUY transaction - Coin or trader not found.");
        }
    }

    private void executeSellTransaction() {
        String coinCode = transactionData.get("data").get("coin").asText();
        int quantity = transactionData.get("data").get("quantity").asInt();
        String walletAddress = transactionData.get("data").get("wallet_address").asText();

        // Placeholder logic for processing a SELL transaction
        Coins coinToSell = getCoinByCode(coinCode);
        Trader seller = getTraderByWalletAddress(walletAddress);

        if (coinToSell != null && seller != null) {
            if (seller.hasSufficientCoins(coinToSell, quantity)) {
                // Perform the actual selling logic
                seller.sellCoins(coinToSell, quantity);
                ConsoleLogger.infoMethod("Sell transaction successful - Coin: " + coinCode + ", Quantity: " + quantity);
            } else {
                ConsoleLogger.infoMethod("Insufficient coins for the SELL transaction.");
            }
        } else {

            ConsoleLogger.infoMethod("Invalid SELL transaction - Coin or trader not found.");
        }
    }

    private void executeUpdatePriceTransaction() {
        String coinCode = transactionData.get("data").get("coin").asText();
        double newPrice = transactionData.get("data").get("price").asDouble();

        // Placeholder logic for processing an UPDATE_PRICE transaction
        Coins coinToUpdate = getCoinByCode(coinCode);

        if (coinToUpdate != null) {
            // Perform the actual update price logic
            coinToUpdate.setPrice(newPrice);

            ConsoleLogger.infoMethod("Update Price transaction successful - Coin: " + coinCode + ", New Price: " + newPrice);

        } else {
            ConsoleLogger.infoMethod("Invalid UPDATE_PRICE transaction - Coin not found.");
        }
    }

    private void executeAddVolumeTransaction() {
        String coinCode = transactionData.get("data").get("coin").asText();
        int newVolume = transactionData.get("data").get("volume").asInt();

        // Placeholder logic for processing an ADD_VOLUME transaction
        Coins coinToAddVolume = getCoinByCode(coinCode);

        if (coinToAddVolume != null) {
            // Perform the actual add volume logic
            coinToAddVolume.setVolume(newVolume);

            ConsoleLogger.infoMethod("Add Volume transaction successful - Coin: " + coinCode + ", New Volume: " + newVolume);

        } else {
            ConsoleLogger.infoMethod("Invalid ADD_VOLUME transaction - Coin not found.");
        }
    }

    private Coins getCoinByCode(String coinCode) {
        return coins.stream()
                .filter(coins -> coins.getCode().equals(coinCode))
                .findFirst()
                .orElse(null);
    }

    private Trader getTraderByWalletAddress(String walletAddress) {
        return traders.stream()
                .filter(trader -> trader.getWalletAddress().equals(walletAddress))
                .findFirst()
                .orElse(null);
    }

}
