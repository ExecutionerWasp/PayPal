package org.model;

import com.paypal.api.payments.*;
import lombok.NonNull;

import javax.management.ConstructorParameters;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PaymentModel {
    //intent: sale
    private Payer payer;
    //method: paypal
    private Payment payment;
    private RedirectUrls redirectUrls;
    private List<Transaction> transactions = new ArrayList<>();

    @ConstructorParameters({"intent","paypal"})
    public PaymentModel(
            @NonNull String intent,
            @NonNull String method)
    {
        payer = new Payer();
        payer.setPaymentMethod(method);

        payment = this.build(intent);
    }

    public PaymentModel(
            @NonNull String intent,
            @NonNull String method,
            @NonNull String cancelUrl,
            @NonNull String returnUrl)
    {
        payer = new Payer();
        payer.setPaymentMethod(method);

        redirectUrls = this.buildRedirectUrls(cancelUrl, returnUrl);
        payment      = this.build(intent);
    }

    public PaymentModel(
            @NonNull List<Transaction> transactions,
            @NonNull Payer payer,
            @NonNull Payment payment,
            @NonNull RedirectUrls redirectUrls)
    {
        this.transactions = transactions;
        this.payer = payer;
        this.payment = payment;
        this.redirectUrls = redirectUrls;
    }

    public Payment build(@NonNull String intent){
        Objects.requireNonNull(intent);
        Objects.requireNonNull(payer);
        Objects.requireNonNull(transactions);

        payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        payment.setRedirectUrls(redirectUrls);
        return payment;
    }

    public RedirectUrls buildRedirectUrls(
            @NonNull String cancelUrl,
            @NonNull String returnUrl
    )
    {
        redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(returnUrl);
        return redirectUrls;
    }

    public Payer buildPayer(@NonNull String method){
        payer = new Payer();
        payer.setPaymentMethod(method);
        return payer;
    }

    public void addTransaction(@NonNull Amount amount){
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transactions.add(transaction);
    }

    public void addTransaction(@NonNull Transaction transaction){
        transactions.add(transaction);
    }


    public Payer getPayer() {
        return payer;
    }

    public Payment getPayment() {
        return payment;
    }

    public RedirectUrls getRedirectUrls() {
        return redirectUrls;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
