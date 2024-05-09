package az.rouvsen.pessimistic_vs_optimistic.service;

import az.rouvsen.pessimistic_vs_optimistic.model.response.AccountBalanceResponse;
import az.rouvsen.pessimistic_vs_optimistic.model.response.AccountResponse;

import java.util.List;

public interface AccountService {
    List<AccountResponse> getAccounts();
    List<AccountBalanceResponse> transferBalance();
}
