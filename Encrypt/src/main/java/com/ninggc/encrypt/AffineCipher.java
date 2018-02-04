package com.ninggc.encrypt;

import com.ninggc.util.CharAndNumber;

import java.util.ArrayList;

/**
 * 仿射密码
 */
public class AffineCipher implements IEncrypter {
    private int keyA;
    private int keyB;
    private int gcdKeyA;

    public AffineCipher(int kA, int kB) {
        this.keyA = kA;
        this.keyB = kB;
        gcdKeyA = gcd(kA);
        if (gcdKeyA == 0) {
            throw new IllegalArgumentException("秘钥A不合法");
        }
    }

    @Override
    public String encrypt(char[] text) {
        final int length = text.length;
        ArrayList<Character> list = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            list.add(text[i]);
        }

        for (int i = 0; i < length; i++) {
            //字符转换成0-25的数字
            int plaintext = CharAndNumber.charToNumber(list.get(i));
            int ciphertext  = (keyA * plaintext + keyB) % 26;
            list.set(i, CharAndNumber.numberToChar(ciphertext));
        }

        StringBuilder result = new StringBuilder();
        for (Character character : list) {
            result.append(character);
        }
        return result.toString();
    }

    @Override
    public String decode(char[] text) {
        final int length = text.length;
        ArrayList<Character> list = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            list.add(text[i]);
        }

        for (int i = 0; i < length; i++) {
            //字符转换成0-25的数字
            int plaintext = CharAndNumber.charToNumber(list.get(i));
            int ciphertext  = (plaintext - keyB) * gcdKeyA % 26;
            ciphertext = ciphertext < 0 ? ciphertext + 26 : ciphertext;
            list.set(i, CharAndNumber.numberToChar(ciphertext));
        }

        StringBuilder result = new StringBuilder();
        for (Character character : list) {
            result.append(character);
        }
        return result.toString();
    }

    private int gcd(int keyA) {
        for (int i = 0; i < 26; i++) {
            if (i * keyA % 26 == 1) {
                return i;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        String test = "hot";
        AffineCipher affineCipher = new AffineCipher(7, 3);
        String ciphertext = affineCipher.encrypt(test);
        System.out.println(ciphertext);
        String plaintext = affineCipher.decode(ciphertext);
        System.out.println(plaintext);
    }
}
