package org.service;

import com.paypal.api.payments.*;

public interface PayPalService {
    Amount amount(Amount amount);
    Transaction transaction(Transaction amount);
    Payer payer(Payer payer);
    Payment payment(Payment payment);
    RedirectUrls redirectUrls(RedirectUrls redirectUrls);
}
