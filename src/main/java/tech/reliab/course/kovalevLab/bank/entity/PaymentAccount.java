package tech.reliab.course.kovalevLab.bank.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment_accounts")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PaymentAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private User user;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Bank bank;

    @Column(nullable = false)
    private double balance;

    public PaymentAccount(User user, Bank bank) {
        this.user = user;
        this.bank = bank;
        this.balance = 0;
    }
}
