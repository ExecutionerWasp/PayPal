package org.model;

/**
 * @autor Alvin
 **/
public enum LogMassages {
    INITIALIZE(""),
    MASSAGE("");

    private String massage;
    LogMassages(String massage){
        this.massage = massage;
    }

    public String getMassage(){
        return this.massage;
    }
}
