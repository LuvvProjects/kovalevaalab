package tech.reliab.course.kovalevLab.bank.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "credit_accounts")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CreditAccount {

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
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private int loanTermMonths;

    @Column(nullable = false)
    private double loanAmount;

    @Column(nullable = false)
    private double monthlyPayment;

    @Column(nullable = false)
    private double interestRate;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Employee employee;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private PaymentAccount paymentAccount;

    public CreditAccount(User user, Bank bank, LocalDate startDate, int loanTermMonths, double interestRate, Employee employee, PaymentAccount paymentAccount) {
        this.user = user;
        this.bank = bank;
        this.startDate = startDate;
        this.loanTermMonths = loanTermMonths;
        this.interestRate = interestRate;
        this.employee = employee;
        this.paymentAccount = paymentAccount;
    }
}

