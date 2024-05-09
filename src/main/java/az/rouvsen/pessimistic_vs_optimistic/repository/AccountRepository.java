package az.rouvsen.pessimistic_vs_optimistic.repository;

import az.rouvsen.pessimistic_vs_optimistic.entity.Account;
import az.rouvsen.pessimistic_vs_optimistic.entity.User;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {

    @Lock(value = LockModeType.OPTIMISTIC_FORCE_INCREMENT)
//    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    Optional<Account> findByUser(User user);

}
