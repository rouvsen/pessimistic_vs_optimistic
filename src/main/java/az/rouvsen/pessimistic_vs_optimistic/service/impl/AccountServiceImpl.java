package az.rouvsen.pessimistic_vs_optimistic.service.impl;

import az.rouvsen.pessimistic_vs_optimistic.entity.Account;
import az.rouvsen.pessimistic_vs_optimistic.entity.User;
import az.rouvsen.pessimistic_vs_optimistic.model.response.AccountBalanceResponse;
import az.rouvsen.pessimistic_vs_optimistic.model.response.AccountResponse;
import az.rouvsen.pessimistic_vs_optimistic.repository.AccountRepository;
import az.rouvsen.pessimistic_vs_optimistic.repository.UserRepository;
import az.rouvsen.pessimistic_vs_optimistic.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Override
    public List<AccountResponse> getAccounts() {
        List<AccountResponse> responseList = new ArrayList<>();
        accountRepository.findAll().forEach(acc -> responseList.add(new AccountResponse(acc.getUser().getName(), acc.getBalance())));
        return responseList;
    }

    @Override
    @Transactional
    public List<AccountBalanceResponse> transferBalance() {
        List<AccountBalanceResponse> result = new ArrayList<>();

        User eko = userRepository.findById("2c61c8d3-5676-44ac-bc85-b72469eb08a6").orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!")
        );
        Account ekoAccount = accountRepository.findByUser(eko).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found!"));

        User ucok = userRepository.findById("fb203dc3-b641-4dcd-9725-5355b6f0e277").orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!")
        );
        Account ucokAccount = accountRepository.findByUser(ucok).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found!"));

        AccountBalanceResponse ekoBalance = new AccountBalanceResponse();
        ekoBalance.setName(eko.getName());
        ekoBalance.setLastBalance(ekoAccount.getBalance());

        AccountBalanceResponse ucokBalance = new AccountBalanceResponse();
        ucokBalance.setName(ucok.getName());
        ucokBalance.setLastBalance(ucokAccount.getBalance());
        //Transfer balance eko to ucok for 1$
        try {
            BigDecimal amount = BigDecimal.valueOf(1);
            if (ekoAccount.getBalance().compareTo(BigDecimal.valueOf(1)) >= 1) {
                ekoAccount.setBalance(ekoAccount.getBalance().subtract(amount));
                Account ekoSuccessTransfer = accountRepository.saveAndFlush(ekoAccount);
                ekoBalance.setCurrentBalance(ekoSuccessTransfer.getBalance());

                ucokAccount.setBalance(ucokAccount.getBalance().add(amount));
                Account ucokSuccessReceive = accountRepository.saveAndFlush(ucokAccount);
                ucokBalance.setCurrentBalance(ucokSuccessReceive.getBalance());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient Balance!");
            }
        } catch (ObjectOptimisticLockingFailureException exception) {
            log.error("ObjectOptimisticLockingFailureException occurred!");
        } catch (UnexpectedRollbackException exception) {
            log.error("UnexpectedRollbackException occurred!");
        }
        result.add(ekoBalance);
        result.add(ucokBalance);
        System.out.println("Transfer Balance");
        return result;
    }
}
