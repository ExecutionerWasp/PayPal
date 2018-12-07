package org;

import com.paypal.base.ConnectionManager;
import lombok.NonNull;
import org.model.ConfigManager;
import org.model.cnfg.PayPalConfig;
import org.model.PaymentManager;

import java.util.logging.Logger;

public final class PayPalApi {
    private static final Logger log = Logger.getLogger(PayPalApi.class.getName());
    private PaymentManager paymentManager;

    public PayPalApi(@NonNull PayPalConfig payPalConfig){
        ConfigManager.getInstance().init(payPalConfig);
        ConnectionManager
                .getInstance()
                .configureCustomSslContext(
                        payPalConfig
                                .configureCustomSslContext());

        log.info("["+ ConnectionManager.class.getName() +"]" +
                payPalConfig.configureCustomSslContext());

        log.info("[PaiPalApi has been initialized]");
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