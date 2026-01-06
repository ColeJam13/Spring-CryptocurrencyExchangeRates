package com.example.crypto_api.service;

import com.example.crypto_api.domain.CryptoCurrency;
import com.example.crypto_api.repository.CryptoCurrencyRepository;
import com.example.crypto_api.client.CoinMarketCapClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CryptoCurrencyService {
    
    @Autowired
    private CryptoCurrencyRepository repository;
    
    @Autowired
    private CoinMarketCapClient client;
    
                                                                            // Fetch and update data every 5 minutes (300,000 milliseconds)
    @Scheduled(fixedRate = 300000)
    public void updateCryptoData() {
        try {
                                                                                    // Fetch raw JSON from CoinMarketCap
            String jsonResponse = client.fetchLatestCryptos();
            
                                                                                            // Parse the JSON
            JsonObject root = JsonParser.parseString(jsonResponse).getAsJsonObject();
            JsonArray data = root.getAsJsonArray("data");
            
                                                                                // Clear old data
            List<CryptoCurrency> currentCryptos = repository.findAll();
            for (CryptoCurrency crypto : currentCryptos) {
                repository.deleteBySymbol(crypto.getSymbol());
            }
            
                                                                                    // Process each cryptocurrency in the response
            for (int i = 0; i < data.size(); i++) {
                JsonObject cryptoJson = data.get(i).getAsJsonObject();
                
                                                                                    // Extract fields from JSON
                String symbol = cryptoJson.get("symbol").getAsString();
                String name = cryptoJson.get("name").getAsString();
                
                JsonObject quote = cryptoJson.getAsJsonObject("quote");
                JsonObject usd = quote.getAsJsonObject("USD");
                
                Double priceUsd = usd.get("price").getAsDouble();
                Double percentChange24h = usd.get("percent_change_24h").getAsDouble();
                Long lastUpdated = System.currentTimeMillis();
                
                                                                                        // Create domain object and save
                CryptoCurrency crypto = new CryptoCurrency(
                    symbol, name, priceUsd, percentChange24h, lastUpdated
                );
                repository.save(crypto);
            }
            
            System.out.println("Updated crypto data: " + data.size() + " cryptocurrencies");
            
        } catch (Exception e) {
            System.err.println("Error updating crypto data: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public List<CryptoCurrency> getAllCryptos() {
        return repository.findAll();
    }
    
    public Optional<CryptoCurrency> getCryptoBySymbol(String symbol) {
        return repository.findBySymbol(symbol);
    }
}