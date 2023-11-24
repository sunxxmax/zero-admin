package com.sunxx.jackson.desensitization;

/**
 * 脱敏类型
 *
 * @author masily
 * @since 2021-6-9 14:49:54
 */
public enum DesensitizationType {
    /**
     * 密码
     */
    PASSWORD,
    /**
     * 地址
     */
    ADDRESS,
    /**
     * 电子邮件
     */
    EMAIL,

    /**
     * 用户id
     */
    USER_ID,
    /**
     * 中文名
     */
    CHINESE_NAME,
    /**
     * 身份证号
     */
    ID_CARD,
    /**
     * 座机号
     */
    FIXED_PHONE,
    /**
     * 手机号
     */
    MOBILE_PHONE,


    ;
}
