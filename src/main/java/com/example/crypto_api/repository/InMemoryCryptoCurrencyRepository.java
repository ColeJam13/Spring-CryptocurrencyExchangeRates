package com.example.crypto_api.repository;

import com.example.crypto_api.domain.CryptoCurrency;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryCryptoCurrencyRepository implements CryptoCurrencyRepository {
    
    private final Map<String, CryptoCurrency> cryptoStore = new ConcurrentHashMap<>();
    
    @Override
    public List<CryptoCurrency> findAll() {
        return new ArrayList<>(cryptoStore.values());
    }
    
    @Override
    public Optional<CryptoCurrency> findBySymbol(String symbol) {
        return Optional.ofNullable(cryptoStore.get(symbol.toUpperCase()));
    }
    
    @Override
    public CryptoCurrency save(CryptoCurrency crypto) {
        cryptoStore.put(crypto.getSymbol().toUpperCase(), crypto);
        return crypto;
    }
    
    @Override
    public void deleteBySymbol(String symbol) {
        cryptoStore.remove(symbol.toUpperCase());
    }
}