package org.strategies;

public interface PayStrategy {
    boolean pay (int paymentAmount);

    void collectPaymentDetails();
}
