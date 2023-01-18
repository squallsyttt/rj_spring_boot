package com.griffith.rj_spring_boot.common;

/**
 * 自定义业务异常类
 */
public class CustomException extends RuntimeException{
    /**
     * 构造方法
     * @param message String msg
     */
    public CustomException(String message){
        super(message);
    }
}
