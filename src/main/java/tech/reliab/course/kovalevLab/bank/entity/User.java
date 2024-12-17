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
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String job;

    @Column(nullable = false)
    private double monthlyIncome;

    @Column(nullable = false)
    private int creditRating;

    @ManyToMany
    @JoinTable(
            name = "user_banks",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "bank_id")
    )
    private List<Bank> banks;

    @OneToMany(mappedBy = "user")
    @JsonIdentityReference(alwaysAsId = true)
    // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CreditAccount> creditAccounts;

    @OneToMany(mappedBy = "user")
    @JsonIdentityReference(alwaysAsId = true)
    // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentAccount> paymentAccounts;

    public User(String fullName, LocalDate birthDate, String job) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.job = job;
    }

}
