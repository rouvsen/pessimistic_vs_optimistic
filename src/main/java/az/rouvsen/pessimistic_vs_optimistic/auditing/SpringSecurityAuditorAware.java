package az.rouvsen.pessimistic_vs_optimistic.auditing;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class SpringSecurityAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || !authentication.isAuthenticated()) {
//            return Optional.empty();
//        }
//        return Optional.of(authentication.getName()); // or return the user ID or any identifier
        return Optional.of("Rovshan Maharramov");
    }
}
