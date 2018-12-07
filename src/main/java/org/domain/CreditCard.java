package org.domain;


import lombok.NonNull;

/**
 * Очень наивная реализация кредитной карты.
 */
public class CreditCard {
    private int amount;
    private String number;
    private String date;
    private String cvv;

    CreditCard(
            @NonNull String number,
            @NonNull String date,
            @NonNull String cvv)
    {
        this.amount = 100_000;
        this.number = number;
        this.date = date;
        this.cvv = cvv;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
