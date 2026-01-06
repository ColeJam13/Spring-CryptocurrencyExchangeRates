package com.example.crypto_api.repository;

import com.example.crypto_api.domain.CryptoCurrency;
import java.util.List;
import java.util.Optional;

public interface CryptoCurrencyRepository {
    List<CryptoCurrency> findAll();
    Optional<CryptoCurrency> findBySymbol(String symbol);
    CryptoCurrency save(CryptoCurrency crypto);
    void deleteBySymbol(String symbol);
}