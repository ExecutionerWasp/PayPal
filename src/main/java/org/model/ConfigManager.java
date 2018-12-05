package org.model;

import lombok.NonNull;
import org.model.cnfg.PayPalConfig;

import javax.net.ssl.SSLContext;
import java.util.Objects;

public final class ConfigManager implements PayPalConfig{
    private static ConfigManager configManager;
    private PayPalConfig payPalConfig;

    private ConfigManager() {

    }

    public static ConfigManager getInstance(){
        if (configManager == null){
            synchronized (ConfigManager.class){
                if (configManager == null){
                    configManager = new ConfigManager();
                }
            }
        }
        return configManager;
    }

    public void init(@NonNull PayPalConfig payPalConfig){
        this.payPalConfig = payPalConfig;
    }

    @Override
    public String configureCancelUrl() {
        Objects.requireNonNull(payPalConfig.configureCancelUrl());
        return payPalConfig.configureCancelUrl();
    }

    @Override
    public String configureReturnUrl() {
        Objects.requireNonNull(payPalConfig.configureReturnUrl());
        return payPalConfig.configureReturnUrl();
    }

    @Override
    public Object configureClientDomain() {
        Objects.requireNonNull(payPalConfig.configureClientDomain());
        return payPalConfig.configureClientDomain();
    }

    @Override
    public SSLContext configureCustomSslContext() {
        Objects.requireNonNull(payPalConfig.configureCustomSslContext());
        return payPalConfig.configureCustomSslContext();
    }
}
