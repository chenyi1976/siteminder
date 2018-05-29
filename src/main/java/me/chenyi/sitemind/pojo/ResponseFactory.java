package me.chenyi.sitemind.pojo;

import me.chenyi.sitemind.util.ResponseCodeConstant;

public class ResponseFactory {

    public static PojoDataResponse createSuccessfulResponse(Object object){
        return new PojoDataResponse(ResponseCodeConstant.SUCCESS.getCode(), "", object);
    }

    public static PojoDataResponse createErrorResponse(Object object){
        return new PojoDataResponse(ResponseCodeConstant.ERROR.getCode(), "", object);
    }

    public static BaseResponse createSuccessfulResponse(String message){
        return new BaseResponse(ResponseCodeConstant.SUCCESS.getCode(), message);
    }

    public static BaseResponse createErrorResponse(String message){
        return new BaseResponse(ResponseCodeConstant.ERROR.getCode(), message);
    }

    public static PojoDataResponse createCustomResponse(int code, String message, Object object){
        return new PojoDataResponse(code, message, object);
    }

}
