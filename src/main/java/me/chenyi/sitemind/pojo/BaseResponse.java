package me.chenyi.sitemind.pojo;

import java.util.Date;

public class BaseResponse {

    private Date dateTime;
    private int returnCode;
    private String message;

    BaseResponse() {
    }

    //package level access only, use ResponseFactory instead please
    BaseResponse(int code, String message) {
        this.returnCode = code;
        this.message = message;
        this.dateTime = new Date();
    }

    public Date getDateTime() {
        return dateTime;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
