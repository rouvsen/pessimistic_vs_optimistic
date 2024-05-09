package az.rouvsen.pessimistic_vs_optimistic.controller;

import az.rouvsen.pessimistic_vs_optimistic.model.response.GetAccountBalance;
import az.rouvsen.pessimistic_vs_optimistic.model.response.GetAccountResponse;
import az.rouvsen.pessimistic_vs_optimistic.model.response.GetUsersResponse;
import az.rouvsen.pessimistic_vs_optimistic.service.AccountService;
import az.rouvsen.pessimistic_vs_optimistic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping({"/api/v1"})
public class BankController {

    private final UserService userService;
    private final AccountService accountService;

    @GetMapping(value = "/users")
    public ResponseEntity<List<GetUsersResponse>> getAllUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }
    @GetMapping(value = "/accounts")
    public ResponseEntity<List<GetAccountResponse>> getAllAccounts() {
        return new ResponseEntity<>(accountService.getAccounts(), HttpStatus.OK);
    }

    @GetMapping(value = "/transfer")
    public ResponseEntity<List<GetAccountBalance>> transferBalanceRaceCondition() {
        return new ResponseEntity<>(accountService.transferBalance(), HttpStatus.OK);
    }

}
