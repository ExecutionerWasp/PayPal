package org.strategies;


import org.domain.CreditCard;

import java.util.logging.Logger;

/**
 * Конкретная стратегия. Реализует оплату корзины интернет магазина кредитной
 * картой клиента.
 */
class CreditCardStrategy implements PayStrategy{
    private static final Logger log = Logger.getLogger(CreditCardStrategy.class.getName());
    private CreditCard card;

    /**
     * Собираем данные карты клиента.
     */
    @Override
    public void collectPaymentDetails() {
            log.info("Card validation");
//            String number = READER.readLine();
//            String date = READER.readLine();
//            String cvv = READER.readLine();
//            card = new CreditCard(number, date, cvv);
    }

    /**
     * После проверки карты мы можем совершить оплату. Если клиент продолжает
     * покупки, мы не запрашиваем карту заново.
     */
    @Override
    public boolean pay(int paymentAmount) {
        if (cardIsPresent()) {
            log.info("Paying " + paymentAmount + " using Credit Card.");
            card.setAmount(card.getAmount() - paymentAmount);
            return true;
        } else {
            return false;
        }
    }

    private boolean cardIsPresent() {
        return card != null;
    }
}
