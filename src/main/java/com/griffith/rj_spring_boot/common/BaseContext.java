package com.griffith.rj_spring_boot.common;

public class BaseContext {
    private static ThreadLocal<Long> threadLocal= new ThreadLocal<>();

    /**
     * 设置值
     * @param id userId
     */
    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    /**
     * 获取值
     * @return Long
     */
    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
