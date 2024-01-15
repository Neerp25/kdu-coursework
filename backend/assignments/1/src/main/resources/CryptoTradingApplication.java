package org.example;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class CryptoTradingApplication {
    private List<Coin> coins;
    private List<Trader> traders;

    public void loadData() {
        // Implement loading data from CSV files
        // Load coins
        this.coins = loadCoinsFromCSV("C:\\Users\\Asus\\kdu\\kdu-coursework\\backend\\assignments\\1\\src\\main\\resources\\coins.csv");

        // Load traders
        this.traders = loadTradersFromCSV("C:\\Users\\Asus\\kdu\\kdu-coursework\\backend\\assignments\\1\\src\\main\\resources\\traders.csv");
    }

private List<Coin> loadCoinsFromCSV(String filePath) {
    List<Coin> coins = new ArrayList<>();
    try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
        String[] headers = reader.readNext(); // Read the header row

        // Map column indices for required fields
        int rankIndex = getIndex("Rank", headers);
        int nameIndex = getIndex("Name", headers);
        int symbolIndex = getIndex("Symbol", headers);
        int priceIndex = getIndex("Price", headers);
        int circulatingSupplyIndex = getIndex("Circulating Supply", headers);

        // Iterate over each row in the CSV file
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            // Assuming "Rank", "Name", "Symbol", "Price," and "Circulating Supply" columns exist in the CSV
            int rank = Integer.parseInt(nextLine[rankIndex]);
            String name = nextLine[nameIndex];
            String symbol = nextLine[symbolIndex];
            double price = Double.parseDouble(nextLine[priceIndex]);
            long circulatingSupply = Long.parseLong(nextLine[circulatingSupplyIndex]);

            // Create a new Coin object
            Coin coin = new Coin(rank,symbol, name, price, circulatingSupply);
            coins.add(coin);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } catch (CsvValidationException e) {
        throw new RuntimeException(e);
    }
    return coins;
}

    private int getIndex(String columnName, String[] headers) {
        for (int i = 0; i < headers.length; i++) {
            if (columnName.equals(headers[i])) {
                return i;
            }
        }
        return -1; // Column not found
    }


    private List<Trader> loadTradersFromCSV(String filePath) {
        List<Trader> traders = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] headers = reader.readNext(); // Read the header row

            // Map column indices for required fields
            int firstNameIndex = getIndex("first_name", headers);
            int lastNameIndex = getIndex("last_name", headers);
            int phoneIndex = getIndex("phone", headers);
            int walletAddressIndex = getIndex("Wallet Address", headers);

            // Iterate over each row in the CSV file
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // Assuming "first_name," "last_name," "phone," and "Wallet Address" columns exist in the CSV
                String firstName = nextLine[firstNameIndex];
                String lastName = nextLine[lastNameIndex];
                String phone = nextLine[phoneIndex];
                String walletAddress = nextLine[walletAddressIndex];

                // Create a new Trader object
                Trader trader = new Trader(firstName,lastName,phone, walletAddress);
                traders.add(trader);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return traders;
    }

    public void runTransactions() {
        // Load transaction data from JSON file
        JsonNode jsonTransactions = loadJsonDataFromFile("C:\\Users\\Asus\\kdu\\kdu-coursework\\backend\\assignments\\1\\src\\main\\resources\\small_transaction.json");

        // Create a CountDownLatch with the number of transactions
        CountDownLatch latch = new CountDownLatch(jsonTransactions.size());

        // Create a thread pool to process transactions
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        // Execute transactions
        for (JsonNode transactionData : jsonTransactions) {
            executorService.submit(new ExecuteTransaction(transactionData, latch));
        }

        executorService.shutdown();

        try {
            // Wait for all transactions to complete
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



    public void displayTopNCoins(int n) {
        System.out.println("Displaying top " + n + " coins:");
        List<Coin> topCoins = coins.stream()
                .sorted(Comparator.comparingDouble(Coin::getPrice).reversed())
                .limit(n)
                .collect(Collectors.toList());

        if (topCoins.isEmpty()) {
            ConsoleLogger.infoMethod("No coins available.");
        } else {
            for (Coin coin : topCoins) {
                ConsoleLogger.infoMethod("Code: " + coin.getCode() + ", Name: " + coin.getName() + ", Price: " + coin.getPrice());
            }
        }
    }


    public void displayTopNTraders(int n) {
        System.out.println("Displaying top " + n + " traders:");
        List<Trader> topTraders = traders.stream()
                .sorted(Comparator.comparingDouble(Trader::getTotalProfitOrLoss).reversed())
                .limit(n)
                .collect(Collectors.toList());

        if (topTraders.isEmpty()) {
            ConsoleLogger.infoMethod("No traders available.");
        } else {
            for (Trader trader : topTraders) {

                ConsoleLogger.infoMethod("FirstName: " + trader.getFirstName() + ", LastName: " + trader.getLastName() +
                        ", Wallet Address: " + trader.getWalletAddress() +
                        ", Total Profit/Loss: " + trader.getTotalProfitOrLoss());
            }
        }
    }
    private JsonNode loadJsonDataFromFile(String filePath) {
        // Implement JSON loading logic using Jackson ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readTree(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Coin getCoinDetails(String coinCode) {
        for (Coin coin : coins) {
            if (coin.getCode().equals(coinCode)) {
                return coin;
            }
        }
        return null; // Coin not found
    }
    public Trader getTraderDetails(String walletAddress) {
        for (Trader trader : traders) {
            if (trader.getWalletAddress().equals(walletAddress)) {
                return trader;
            }
        }
        return null; // Trader not found
    }


}
