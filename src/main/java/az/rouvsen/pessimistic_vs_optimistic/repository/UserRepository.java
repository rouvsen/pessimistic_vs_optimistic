package az.rouvsen.pessimistic_vs_optimistic.repository;

import az.rouvsen.pessimistic_vs_optimistic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
