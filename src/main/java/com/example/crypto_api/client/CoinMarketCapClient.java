package com.example.crypto_api.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@Component
public class CoinMarketCapClient {
    
    @Value("${coinmarketcap.api.key}")
    private String apiKey;
    
    private final RestTemplate restTemplate = new RestTemplate();
    
    public String fetchLatestCryptos() {
        String url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?limit=10";
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-CMC_PRO_API_KEY", apiKey);
        headers.set("Accept", "application/json");
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        ResponseEntity<String> response = restTemplate.exchange(
            url, 
            HttpMethod.GET, 
            entity, 
            String.class
        );
        
        return response.getBody();
    }
}