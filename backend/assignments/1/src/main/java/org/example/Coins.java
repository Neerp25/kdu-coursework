package org.example;

public class Coins {
    private int rank;
    private String code;
    private String name;
    private double price;
    private long volume;
    public Coins(int rank,String code,String name,double price,long volume){
        this.rank=rank;
        this.code=code;
        this.name=name;
        this.price=price;
        this.volume=volume;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getVolume() {
        return volume;
    }

    // Setters
    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
    public String toString() {
        return "Coins{" +
                "rank=" + rank +
                ", name='" + name + '\'' +
                ", symbol='" + code + '\'' +
                ", price=" + price +
                ", circulatingSupply=" + volume +
                '}';
    }
}
