package tech.reliab.course.kovalevLab.bank.service.generator;

import org.springframework.stereotype.Service;
import tech.reliab.course.kovalevLab.bank.entity.Bank;

import java.time.LocalDate;
import java.util.Random;

@Service
public class GeneratorService {

    private static final int RATING_BOUND = 101;
    private static final int TOTAL_MONEY_BOUND = 10000001;
    private static final int MAX_RATE = 25;
    private static final double DIVIDER = 10.0;

    private static final int MONTHLY_INCOME_BOUND = 100001;
    private static final double INCOME_DIVIDER = 1000.0;
    private static final int FACTOR = 100;

    /**
     * Генерация случайного рейтинга банка.
     *
     * @return Случайный рейтинг банка.
     */
    public int generateRating() {
        return new Random().nextInt(RATING_BOUND);
    }

    /**
     * Генерация случайного количества денег в банке.
     *
     * @return Случайное количество денег в банке.
     */
    public int generateTotalMoney() {
        return new Random().nextInt(TOTAL_MONEY_BOUND);
    }

    /**
     * Вычисление процентной ставки по кредитам.
     *
     * @param rating Рейтинг банка.
     * @return Процентная ставка.
     */
    public int calculateInterestRate(int rating) {
        return (int) Math.round(MAX_RATE - (rating / DIVIDER));
    }

    /**
     * Генерация случайного месячного дохода пользователя.
     *
     * @return Случайный месячный доход.
     */
    public int generateMonthlyIncome() {
        return new Random().nextInt(MONTHLY_INCOME_BOUND);
    }

    /**
     * Генерация кредитного рейтинга пользователя,
     * основанного на его месячном доходе.
     *
     * @param monthlyIncome Месячный доход пользователя.
     * @return Кредитный рейтинг пользователя.
     */
    public int generateCreditRating(double monthlyIncome) {
        return (int) Math.ceil(monthlyIncome / INCOME_DIVIDER) * FACTOR;
    }

    /**
     * Генерация случайного количества денег в офисе банка.
     *
     * @param bank Банк, которому принадлежит офис.
     * @return Случайное количество денег в офисе банка.
     */
    public int generateOfficeMoney(Bank bank) {
        return (int) Math.round(new Random().nextDouble() * bank.getTotalMoney());
    }

    /**
     * Вычисление даты окончания кредита.
     *
     * @param startDate      Дата начала кредита.
     * @param loanTermMonths Срок кредита в месяцах.
     * @return Дата окончания кредита.
     */
    public LocalDate calculateEndDate(LocalDate startDate, int loanTermMonths) {
        return startDate.plusMonths(loanTermMonths);
    }

    /**
     * Расчет аннуитетного платежа по кредиту.
     *
     * @param interestRate   Процентная ставка по кредиту.
     * @param loanAmount     Сумма кредита.
     * @param loanTermMonths Срок кредита в месяцах.
     * @return Размер аннуитетного платежа.
     */
    public int calculateMonthlyPayment(double interestRate, double loanAmount, int loanTermMonths) {
        double monthlyRate = interestRate / 12 / 100;
        double payment = loanAmount * (monthlyRate / (1 - Math.pow(1 + monthlyRate, -loanTermMonths)));
        return (int) Math.round(payment);
    }

    /**
     * Расчет суммы кредита, не превышающей доступных средств банка.
     *
     * @param loanAmount Сумма кредита, запрошенная пользователем.
     * @param bank       Банк, который предоставляет кредит.
     * @return Сумма кредита, не превышающая доступные средства банка.
     */
    public int calculateLoanAmount(double loanAmount, Bank bank) {
        if (loanAmount > bank.getTotalMoney()) {
            loanAmount = bank.getTotalMoney();
        }
        return (int) Math.round(loanAmount);
    }

    /**
     * Расчет процентной ставки по кредиту, не превышающей процентную ставку банка.
     *
     * @param interestRate Процентная ставка по кредиту, запрошенная пользователем.
     * @param bank         Банк, который предоставляет кредит.
     * @return Процентная ставка по кредиту, не превышающая процентную ставку банка.
     */
    public int calculateInterestRate(double interestRate, Bank bank) {
        if (interestRate > bank.getInterestRate()) {
            System.out.println("Заданная процентная ставка превышает процентную ставку банка. Ставка будет скорректирована.");
            interestRate = bank.getInterestRate();
        }
        return (int) Math.round(interestRate);
    }
}