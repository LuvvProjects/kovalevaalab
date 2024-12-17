package tech.reliab.course.kovalevLab.bank.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.reliab.course.kovalevLab.bank.service.status.BankOfficeStatus;

import java.util.List;


@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bank_offices")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class BankOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BankOfficeStatus status;

    @Column(nullable = false)
    private boolean canPlaceAtm;

    @Column(nullable = false)
    private boolean canIssueLoan;

    @Column(nullable = false)
    private boolean cashWithdrawal;

    @Column(nullable = false)
    private boolean cashDeposit;

    @Column(nullable = false)
    private double officeMoney;

    @Column(nullable = false)
    private double rentCost;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Bank bank;


    @OneToMany(mappedBy = "bankOffice")
    // @OneToMany(mappedBy = "bankOffice", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIdentityReference(alwaysAsId = true)
    private List<Employee> employees;

    public BankOffice(String name, String address, boolean canPlaceAtm, boolean canIssueLoan, boolean cashWithdrawal, boolean cashDeposit, double rentCost, Bank bank) {
        this.name = name;
        this.address = address;
        this.canPlaceAtm = canPlaceAtm;
        this.canIssueLoan = canIssueLoan;
        this.cashWithdrawal = cashWithdrawal;
        this.cashDeposit = cashDeposit;
        this.rentCost = rentCost;
        this.bank = bank;
    }
}
