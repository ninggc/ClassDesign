package com.ninggc.encrypt;

/**
 * 移位密码
 */
public class Shifting implements IEncrypter {

    private int key;

    public Shifting(int k) {
        this.key = k % 26;
        key = key < 0 ? key + 26 : key;
    }

    @Override
    public String encrypt(char[] plaintext) {
        for (int i = 0; i < plaintext.length; i++) {
            plaintext[i] += key;
        }
        return String.valueOf(plaintext);
    }

    @Override
    public String decode(char[] ciphertext) {
        for (int i = 0; i < ciphertext.length; i++) {
            ciphertext[i] -= key;
        }
        return String.valueOf(ciphertext);
    }

    public static void main(String[] args) {
        int i = -10 % 26;
        System.out.println(i < 0 ? i + 26 : i);
        System.out.println(new Shifting(28).encrypt("123"));
    }
}
