package org;

import com.paypal.base.ConnectionManager;
import org.cnfg.PayPalConfig;
import org.exception.AccessRequiredFieldsException;
import org.model.PaymentManager;
import org.model.annotation.MetaDate;
import org.reflect.FieldsResolver;

@MetaDate
public final class PayPalApi {
    private final String METHOD = "paypal";
    private final String INTENT = "order";
    private static PayPalApi payPal;
    private PaymentManager paymentManager;
    private FieldsResolver fieldsResolver;

    private PayPalApi(){

    }

    public static PayPalApi getInstance(){
        if (payPal == null){
            synchronized (PayPalApi.class){
                if (payPal == null){
                    payPal = new PayPalApi();
                }
            }
        }
        return payPal;
    }

    public void init(PayPalConfig payPalConfig){
        paymentManager = new PaymentManager(INTENT,METHOD);
        paymentManager.buildRedirectUrls(
                payPalConfig.configureCancelUrl(),
                payPalConfig.configureReturnUrl()
        );

        try {
            fieldsResolver = new FieldsResolver(payPalConfig.configureClientDomain());
        } catch (AccessRequiredFieldsException e) {
            e.printStackTrace();
        }

        ConnectionManager
                .getInstance()
                    .configureCustomSslContext(
                            payPalConfig
                                    .configureCustomSslContext());
    }

    public FieldsResolver getFieldsResolver(){
        return this.fieldsResolver;
    }
}
