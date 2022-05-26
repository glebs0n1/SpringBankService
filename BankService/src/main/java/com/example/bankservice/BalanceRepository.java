package com.example.bankservice;

import lombok.val;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository

public class BalanceRepository {
//Read from or save inside HashMap
    private final Map<Long, BigDecimal> storage = new HashMap<>(Map.of(1L, BigDecimal.TEN));

    public BigDecimal getBalanceForId(Long accountId) {
        return storage.get(accountId);
    }

    public void save(Long to, BigDecimal amount) {
        storage.put(to, amount);
    }
}