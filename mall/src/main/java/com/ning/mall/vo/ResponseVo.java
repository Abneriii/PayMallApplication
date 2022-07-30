package com.ning.mall.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.ning.mall.enums.ResponseEnum;
import lombok.Data;

/**
 *
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)//如果域为空，则不返回
public class  ResponseVo<T> {

    Integer status;

    String msg;

    T  data;

    public ResponseVo(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResponseVo(Integer status, T data) {
        this.status = status;
        this.data = data;
    }


    public static <T> ResponseVo<T> successByMsg(Integer status,String msg){
            return new ResponseVo<>(status,msg);
    }


    public static <T> ResponseVo<T> success(Integer status,T data){
        return new ResponseVo<>(status,data);
    }

    public static <T> ResponseVo<T> success() {
        return new ResponseVo<>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getDesc());
    }

    /**
     *
     *
     */
    public static <T> ResponseVo<T> error(ResponseEnum responseEnum, String msg){
        return new ResponseVo<>(responseEnum.getCode(),msg);

    }











}
