package me.chenyi.sitemind.util;

public enum ResponseCodeConstant {
    ERROR(-1),
    SUCCESS(0);

    private int code;
    private String message;

    ResponseCodeConstant(int code) {
        this.code = code;
        this.message = message;
    }

    public int getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.message;
    }
}
