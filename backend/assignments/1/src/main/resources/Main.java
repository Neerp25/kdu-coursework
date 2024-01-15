package org.example;


public class Main {
    public static void main(String[] args) {
        CryptoTradingApplication cryptoApp = new CryptoTradingApplication();
        cryptoApp.loadData();
        cryptoApp.displayTopNCoins(5);
        cryptoApp.runTransactions();
        Coin ethcoin = cryptoApp.getCoinDetails("ETH");
        ConsoleLogger.infoMethod("Name:" + ethcoin.getName()+" Code:"+ethcoin.getCode()+" Price:"+ ethcoin.getPrice()+" Volume:"+ethcoin.getVolume());
        Trader Traderone = cryptoApp.getTraderDetails("0xbe3887c02d3d33e16ba49b3607c50e3a");
        ConsoleLogger.infoMethod("fistname:"+Traderone.getFirstName()+" lastname:"+Traderone.getLastName()+" phonenumber:"+Traderone.getPhoneNumber()+" walletAddress:"+Traderone.getWalletAddress());


    }
}