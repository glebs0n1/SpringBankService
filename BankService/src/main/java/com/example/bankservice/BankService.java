package com.example.bankservice;

import com.example.bankservice.model.BalanceId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class BankService {
    private final BalanceRepository repository;
    public BigDecimal getBalance(Long accountId) {
        BigDecimal balance = repository.getBalanceForId(accountId);
        if (balance == null) {
            throw new IllegalArgumentException();
        }
        else
            return repository.getBalanceForId(accountId);
    }
    public BigDecimal addMoney(Long to, BigDecimal amount) {

        BigDecimal currentBalance = repository.getBalanceForId(to);
        if (currentBalance == null) {
            repository.save(to, amount);
            return amount;
        }
        else {
            final BigDecimal updatedBalance= currentBalance.add(amount);
            repository.save(to, updatedBalance);
            return updatedBalance;
        }
    }
    //Business logic
    public void makeTransfer(BalanceId transferBalance) {
        BigDecimal fromBalance = repository.getBalanceForId(transferBalance.getFrom());
        BigDecimal toBalance = repository.getBalanceForId(transferBalance.getFrom());

        if (fromBalance == null
            || toBalance == null) {
            throw new IllegalArgumentException("No Ids");
        }
        if (transferBalance.getAmount().compareTo(fromBalance) > 0) {
            throw new IllegalArgumentException("Sorry, no money");
        }
        BigDecimal updatedFromBalance = fromBalance.subtract(transferBalance.getAmount());
        BigDecimal updatedToBalance = toBalance.add(transferBalance.getAmount());
        repository.save(transferBalance.getFrom(), updatedFromBalance);
        repository.save(transferBalance.getTo(), 
                        updatedToBalance);
    }
}
