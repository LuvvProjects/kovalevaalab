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
@Table(name = "employees")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String position;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Bank bank;

    @Column(nullable = false)
    private boolean remoteWork;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private BankOffice bankOffice;

    @Column(nullable = false)
    private boolean canIssueLoans;

    @Column(nullable = false)
    private double salary;

    @OneToMany(mappedBy = "employee")
    @JsonIdentityReference(alwaysAsId = true)
    // @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BankAtm> bankAtm;

    public Employee(String fullName, LocalDate birthDate, String position, Bank bank, boolean remoteWork, BankOffice bankOffice, boolean canIssueLoans, double salary) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.position = position;
        this.bank = bank;
        this.remoteWork = remoteWork;
        this.bankOffice = bankOffice;
        this.canIssueLoans = canIssueLoans;
        this.salary = salary;
    }
}
