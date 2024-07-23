package com.alperkyoruk.hms.core.result;

public class SuccessResult extends Result {

    public SuccessResult(String message) {
        super(true, message);

    }

    public SuccessResult() {
        super(true);
    }

}