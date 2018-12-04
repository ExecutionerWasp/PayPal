package org.model;


import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;


public class SandBox {
    private static SandBox box;

    private SandBox(){
    }

    public SandBox getInstance(){
        if (box != null){
            synchronized (SandBox.class){
                if (box != null){
                    box = new SandBox();
                }
            }
        }
        return box;
    }


}
