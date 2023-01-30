package com.griffith.rj_spring_boot.utils;

import java.util.Random;

public class ValidateCodeUtils {
    public static Integer generateValidateCode(int length){
        //Integer 默认是 null
        Integer code = null;
        if(length == 4){
            code = new Random().nextInt(9999);
            if(code < 1000){
                code += 1000;
            }
        } else if (length == 6) {
            code = new Random().nextInt(999999);
            if(code < 100000){
                code += 100000;
            }
        } else {
            throw new RuntimeException("only 4/6 length permitted");
        }
        return code;
    }

    /**
     * 随机生成指定长度的字符串验证码
     * @param length int
     * @return String
     */
    public static String generateValidateCode4String(int length){
        Random rdm = new Random();
        String hash1 = Integer.toHexString(rdm.nextInt());
        return hash1.substring(0,length);
    }


}
