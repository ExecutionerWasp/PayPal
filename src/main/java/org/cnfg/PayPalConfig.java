package org.cnfg;

import lombok.NonNull;

import javax.net.ssl.SSLContext;

public interface PayPalConfig {
    @NonNull
    String configureCancelUrl();

    @NonNull
    String configureReturnUrl();

    @NonNull
    Object configureClientDomain();

    @NonNull
    SSLContext configureCustomSslContext();
}
