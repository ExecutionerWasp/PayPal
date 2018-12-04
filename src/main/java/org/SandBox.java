package org;


import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

public class SandBox {



    public Payment sand(Payment payment) throws PayPalRESTException {
        APIContext
                apiContext = new APIContext(
                        "123",
                "123",
                "sandbox"
        );
        return payment.create(apiContext);
    }
}
