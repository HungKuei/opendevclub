package com.opendev.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用户校验数据格式
 *
 */
public class ValidateUtil {

    /**
     * 验证是否为手机号
     * @param mobileNo
     * @return
     */
    public static boolean isMobileNo(String mobileNo) {
        // 1、(13[0-9])|(15[02789])|(18[679])|(17[0-9]) 13段 或者15段 18段17段的匹配
        // 2、\\d{8} 整数出现8次
        boolean flag = false;
        Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");
        Matcher match = p.matcher(mobileNo);
        if (mobileNo != null && !"".equals(mobileNo.trim())) {
            flag = match.matches();
        }
        return flag;
    }


    /**
     * 验证是否为正确的邮箱号
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        // 1、\\w+表示@之前至少要输入一个匹配字母或数字或下划线 \\w 单词字符：[a-zA-Z_0-9]
        // 2、(\\w+\\.)表示域名. 如新浪邮箱域名是sina.com.cn
        // {1,3}表示可以出现一次或两次或者三次.
        String reg = "\\w+@(\\w+\\.){1,3}\\w+";
        Pattern pattern = Pattern.compile(reg);
        boolean flag = false;
        if (email != null && !"".equals(email)) {
            Matcher matcher = pattern.matcher(email);
            flag = matcher.matches();
        }
        return flag;
    }


    /**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        boolean flag = false;
        Pattern pattern = Pattern.compile("[0-9]*");
        if (str != null && !"".equals(str)) {
            Matcher isNum = pattern.matcher(str);
            flag = isNum.matches();
        }
        return flag;
    }


    /**
     * 检验 日期 的格式是否正确 yyyyMMdd
     */
    public static boolean isYYYYMMDDTime(String time){
        boolean flag = false;
        if (null == time || "".equals(time)) {
            return flag;
        }

        if (time.length() != 8) {
            return flag;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            //设置成false,避免宽松的格式校验
            sdf.setLenient(false);

            Date d = sdf.parse(time);
            flag = true;
        } catch (ParseException e) {
            flag = false;
        }
        return flag ;
    }

}
