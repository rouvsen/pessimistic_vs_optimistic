package az.rouvsen.pessimistic_vs_optimistic;

import az.rouvsen.pessimistic_vs_optimistic.entity.Account;
import az.rouvsen.pessimistic_vs_optimistic.entity.User;
import az.rouvsen.pessimistic_vs_optimistic.repository.AccountRepository;
import az.rouvsen.pessimistic_vs_optimistic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@RequiredArgsConstructor
public class PessimisticVsOptimisticApplication implements CommandLineRunner {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(PessimisticVsOptimisticApplication.class, args);
        System.out.println("Hello, World!");
    }

    @Override
    public void run(String... args) {
        User ekoUser = new User("Eko");
        ekoUser.setId("2c61c8d3-5676-44ac-bc85-b72469eb08a6");
        userRepository.save(ekoUser);
        Account ekoAccount = new Account(ekoUser, BigDecimal.valueOf(5000));
        ekoAccount.setId("eko=account=id=1");

        User ucokUser = new User("Ucok");
        ucokUser.setId("fb203dc3-b641-4dcd-9725-5355b6f0e277");
        userRepository.save(ucokUser);
        Account ucokAccount = new Account(ucokUser, BigDecimal.valueOf(4000));
        ucokAccount.setId("ucok=account=id=2");

        accountRepository.saveAll(List.of(ekoAccount, ucokAccount));
    }
}
