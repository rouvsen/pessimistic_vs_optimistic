package az.rouvsen.pessimistic_vs_optimistic.service;

import az.rouvsen.pessimistic_vs_optimistic.model.response.GetAccountBalance;
import az.rouvsen.pessimistic_vs_optimistic.model.response.GetAccountResponse;

import java.util.List;

public interface AccountService {
    List<GetAccountResponse> getAccounts();
    List<GetAccountBalance> transferBalance();
}
