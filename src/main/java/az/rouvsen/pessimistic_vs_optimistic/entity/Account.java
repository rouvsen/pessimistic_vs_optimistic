package az.rouvsen.pessimistic_vs_optimistic.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private BigDecimal balance;

}
