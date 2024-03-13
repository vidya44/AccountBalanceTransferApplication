package com.cg.Model;

public enum TransactionType {
    ADD_MONEY(0),SEND_MONEY(1);

    private final int value;

    private TransactionType(int value){
        this.value=value;
    }
    public int getValue(){
        return value;
    }
}
