package me.chenyi.sitemind.pojo;


public class PojoDataResponse<T> extends BaseResponse {

    private T data;
    
    PojoDataResponse(){
        super();
    }

    //package level access only, use ResponseFactory instead please
    PojoDataResponse(int id, String message, T data) {
        super(id, message);
        this.data = data;
    }
    
    public void setData(T data){
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
