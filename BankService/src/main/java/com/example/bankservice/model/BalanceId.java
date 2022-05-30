package com.example.bankservice.model;

import lombok.Data;
import java.math.BigDecimal;
@Data
public class BalanceId {
    private Long from;
    private Long to;
    private BigDecimal amount;
}
