package com.ninggc.encrypt;

public interface IEncrypter {
    default String encrypt(String plaintext) {
        return encrypt(plaintext.toCharArray());
    }

    String encrypt(char[] plaintext);

    default String decode(String ciphertext) {
        return decode(ciphertext.toCharArray());
    }

    String decode(char[] ciphertext);
}
