package com.sunxx.util;

import org.springframework.util.StringUtils;

/**
 * 脱敏规则具体实现
 *
 * @author masily
 * @since 2021-6-9 14:34:34
 */
public class DesensitizedUtils {

    /**
     * 【用户id】不对外提供userId
     */
    public static Long userId() {
        return 0L;
    }

    /**
     * 【中文姓名】只显示第一个汉字，其他隐藏为2个星号，比如：李**
     *
     * @param fullName 全名
     */
    public static String chineseName(String fullName) {
        if (!StringUtils.hasText(fullName)) {
            return "";
        }

        // String name = StringUtils.left(fullName, 1);
        // return StringUtils.rightPad(name, StringUtils.length(fullName), "*");
        return "";
    }

    /**
     * 【身份证号】前三位 和后三位
     *
     * @param front 前
     * @param end   后
     */
    public static String idCardNum(String idCardNum, int front, int end) {
        //身份证不能为空
        if (StringUtils.hasText(idCardNum)) {
            return "";
        }
        //需要截取的长度不能大于身份证号长度
        if ((front + end) > idCardNum.length()) {
            return "";
        }
        //需要截取的不能小于0
        if (front < 0 || end < 0) {
            return "";
        }
        //计算*的数量
        int asteriskCount = idCardNum.length() - (front + end);
        String regex = "(\\w*" + front + "})(\\w+)(\\w*" + end + "})";
        return idCardNum.replaceAll(regex, "$1" + "*".repeat(Math.max(0, asteriskCount)) + "$3");
    }

    /**
     * 【固定电话】 前四位，后两位
     *
     * @param num 号码
     * @return 返回值
     */
    public static String fixedPhone(String num) {
        // if (StringUtils.isBlank(num)) {
        //     return "";
        // }
        // return StringUtils.left(num, 4).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(num, 2), StringUtils.length(num), "*"), "****"));
        return "";
    }

    /**
     * 【手机号码】前三位，后两位，其他隐藏，比如135******10
     *
     * @param num 手机号
     * @return 返回值
     */
    public static String mobilePhone(String num) {
        // if (StringUtils.isBlank(num)) {
        //     return "";
        // }
        // return StringUtils.left(num, 3).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(num, 2), StringUtils.length(num), "*"), "***"));
        return "";
    }

    /**
     * 【地址】只显示到地区，不显示详细地址，比如：北京市海淀区****
     *
     * @param address       地址
     * @param sensitiveSize 敏感信息长度
     * @return 地址
     */
    public static String address(String address, int sensitiveSize) {
        // if (StringUtils.isBlank(address)) {
        //     return "";
        // }
        // int length = StringUtils.length(address);
        // return StringUtils.rightPad(StringUtils.left(address, length - sensitiveSize), length, "*");
        return "";
    }

    /**
     * 【电子邮箱 邮箱前缀仅显示第一个字母，前缀其他隐藏，用星号代替，@及后面的地址显示，比如：d**@126.com>
     *
     * @param email 邮箱
     */
    public static String email(String email) {
        if (!StringUtils.hasText(email)) {
            return "";
        }
        // int index = StringUtils.indexOf(email, "@");
        // if (index <= 1) {
        //     return email;
        // } else {
        //     return StringUtils.rightPad(StringUtils.left(email, 1), index, "*").concat(StringUtils.mid(email, index, StringUtils.length(email)));
        // }
        return "";
    }

    /**
     * 【密码】密码的全部字符都用*代替，比如：******
     *
     * @param password 密码
     * @return 转换后的密码
     */
    public static String password(String password) {
        return "******";
    }
}
