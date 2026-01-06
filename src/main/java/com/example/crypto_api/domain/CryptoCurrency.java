package com.example.crypto_api.domain;

public class CryptoCurrency {
    private String symbol;              // e.g., "BTC"
    private String name;                // e.g., "Bitcoin"
    private Double priceUsd;            // Current price in USD
    private Double percentChange24h;    // 24-hour percentage change
    private Long lastUpdated;           // Unix timestamp of last update
    
   
    public CryptoCurrency() {
    }
    
    public CryptoCurrency(String symbol, String name, Double priceUsd, 
                          Double percentChange24h, Long lastUpdated) {
        this.symbol = symbol;
        this.name = name;
        this.priceUsd = priceUsd;
        this.percentChange24h = percentChange24h;
        this.lastUpdated = lastUpdated;
    }
    
    public String getSymbol() {
        return symbol;
    }
    
    public String getName() {
        return name;
    }
    
    public Double getPriceUsd() {
        return priceUsd;
    }
    
    public Double getPercentChange24h() {
        return percentChange24h;
    }
    
    public Long getLastUpdated() {
        return lastUpdated;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setPriceUsd(Double priceUsd) {
        this.priceUsd = priceUsd;
    }
    
    public void setPercentChange24h(Double percentChange24h) {
        this.percentChange24h = percentChange24h;
    }
    
    public void setLastUpdated(Long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}