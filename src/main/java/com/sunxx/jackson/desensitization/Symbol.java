package com.sunxx.jackson.desensitization;

public class Symbol {

    /**
     * '*'脱敏符
     */
    public static final String STAR = "*";

    private Symbol() {

    }

    /**
     * 获取符号
     *
     * @param number 符号个数
     * @param symbol 符号
     */
    public static String getSymbol(int number, String symbol) {
        return String.valueOf(symbol).repeat(Math.max(0, number));
    }
}
