package org.strategies;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

class PayPalStrategy implements PayStrategy {
    private static final Logger log = Logger.getLogger(PayStrategy.class.getName());
    private static final Map<String, String> DATA_BASE = new HashMap<>();

    private String email;
    private String password;
    private boolean signedIn;

    /**
     * Собираем данные от клиента.
     */
    @Override
    public void collectPaymentDetails() {
            while (!signedIn) {
//                email = READER.readLine();
//                password = READER.readLine();
                if (verify()) {
                    log.info("Data verification has been successful.");
                } else {
                    log.info("Wrong email or password!");
                }
            }
    }

    private boolean verify() {
        setSignedIn(email.equals(DATA_BASE.get(password)));
        return signedIn;
    }

    /**
     * Если клиент уже вошел в систему, то для следующей оплаты данные вводить
     * не придется.
     */
    @Override
    public boolean pay(int paymentAmount) {
        if (signedIn) {
            log.info("Paying " + paymentAmount + " using PayPalStrategy.");
            return true;
        } else {
            return false;
        }
    }

    private void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }
}
