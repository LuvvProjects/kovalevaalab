package tech.reliab.course.kovalevLab.bank.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "banks")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int rating;

    @Column(nullable = false)
    private double totalMoney;

    @Column(nullable = false)
    private double interestRate;

    @OneToMany(mappedBy = "bank")
    // @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIdentityReference(alwaysAsId = true)
    private List<BankOffice> offices;

    @OneToMany(mappedBy = "bank")
    // @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIdentityReference(alwaysAsId = true)
    private List<BankAtm> atms;

    @OneToMany(mappedBy = "bank")
    // @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIdentityReference(alwaysAsId = true)
    private List<Employee> employees;

    @OneToMany(mappedBy = "bank")
    // @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIdentityReference(alwaysAsId = true)
    private List<CreditAccount> creditAccounts;

    @OneToMany(mappedBy = "bank")
    // @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIdentityReference(alwaysAsId = true)
    private List<PaymentAccount> paymentAccounts;

    public Bank(String name) {
        this.name = name;
    }
}
