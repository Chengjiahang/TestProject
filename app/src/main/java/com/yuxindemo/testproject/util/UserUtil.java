package com.yuxindemo.testproject.util;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/7/19.
 */

public class UserUtil {

    // 判断手机号码是否规则
    public static boolean isPhoneNumber(String input) {
        String regex = "(1[0-9][0-9]|15[0-9]|18[0-9])\\d{8}";
        Pattern p = Pattern.compile(regex);
        return p.matches(regex, input);
    }

}
