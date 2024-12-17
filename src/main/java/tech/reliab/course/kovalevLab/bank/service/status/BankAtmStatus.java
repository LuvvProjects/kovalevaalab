package tech.reliab.course.kovalevLab.bank.service.status;

import java.util.Random;

public enum BankAtmStatus {

    WORKING,
    NOT_WORKING;

    private static final Random
            RANDOM = new Random();

    public static BankAtmStatus randomStatus()  {
        BankAtmStatus[] statuses = values();
        return statuses[RANDOM.nextInt(statuses.length)];
    }
}
