package tech.reliab.course.kovalevLab.bank.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import tech.reliab.course.kovalevLab.bank.service.status.BankAtmStatus;

@Entity
@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bank_atms")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class BankAtm {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private boolean cashWithdrawal;

    @Column(nullable = false)
    private boolean cashDeposit;

    @Column(nullable = false)
    private double atmMoney;

    @Column(nullable = false)
    private double maintenanceCost;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BankAtmStatus status;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @JsonIdentityReference(alwaysAsId = true) // Сериализуем офисы только как ID
    private BankOffice office;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true) // Сериализуем офисы только как ID
    private Employee employee;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true) // Сериализуем офисы только как ID
    private Bank bank;

    public BankAtm(String name, String address, Bank bank, BankOffice office, Employee employee, boolean cashWithdrawal, boolean cashDeposit, double maintenanceCost) {
        this.name = name;
        this.address = address;
        this.bank = bank;
        this.office = office;
        this.employee = employee;
        this.cashWithdrawal = cashWithdrawal;
        this.cashDeposit = cashDeposit;
        this.maintenanceCost = maintenanceCost;
    }
}
