package org;

import com.paypal.base.ConnectionManager;
import lombok.NonNull;
import org.model.ConfigManager;
import org.model.cnfg.PayPalConfig;
import org.model.PaymentManager;

public final class PayPalApi {
    private static PayPalApi payPal;
    private PaymentManager paymentManager;

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

    public void setConfigurations(@NonNull PayPalConfig payPalConfig){
        ConfigManager.getInstance().init(payPalConfig);
        ConnectionManager
                .getInstance()
                    .configureCustomSslContext(
                            payPalConfig
                                    .configureCustomSslContext());
    }

    public String getConfigureCancelUrl() {
        return ConfigManager.getInstance().configureCancelUrl();
    }

    public String getConfigureReturnUrl() {
        return ConfigManager.getInstance().configureReturnUrl();
    }

    public PaymentManager getPaymentManager() {
        return paymentManager;
    }

    public void setPaymentManager(PaymentManager paymentManager) {
        this.paymentManager = paymentManager;
    }
}