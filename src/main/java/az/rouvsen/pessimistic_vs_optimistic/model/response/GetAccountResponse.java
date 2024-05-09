package az.rouvsen.pessimistic_vs_optimistic.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAccountResponse {
    private String name;
    private BigDecimal balance;
}
