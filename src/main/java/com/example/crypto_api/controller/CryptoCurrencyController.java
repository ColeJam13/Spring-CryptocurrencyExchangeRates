package com.example.crypto_api.controller;

import com.example.crypto_api.domain.CryptoCurrency;
import com.example.crypto_api.service.CryptoCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crypto")
public class CryptoCurrencyController {
    
    @Autowired
    private CryptoCurrencyService service;
    
                                                                                        // GET /api/crypto - returns all cryptocurrencies
    @GetMapping
    public ResponseEntity<List<CryptoCurrency>> getAllCryptos() {
        List<CryptoCurrency> cryptos = service.getAllCryptos();
        return ResponseEntity.ok(cryptos);
    }
    
                                                                                        // GET /api/crypto/{symbol} - returns specific cryptocurrency
    @GetMapping("/{symbol}")
    public ResponseEntity<CryptoCurrency> getCryptoBySymbol(@PathVariable String symbol) {
        return service.getCryptoBySymbol(symbol)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}