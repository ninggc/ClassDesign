package com.ninggc.encrypt;

public class ErrorEncrypter implements IEncrypter {
    public static final String ERROR_NULL_KEY = "秘钥不合法";

    private String message;

    public ErrorEncrypter(String s) {
        this.message = "异常：" + s;
    }

    @Override
    public String encrypt(char[] plaintext) {
        return message;
    }

    @Override
    public String decode(char[] ciphertext) {
        return message;
    }
}
