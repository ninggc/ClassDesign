package com.ninggc.encrypt;

import com.ninggc.util.CharAndNumber;

/**
 * Vigenere加密
 */
public class Vigenere implements IEncrypter {
    private final int[] key;

    public Vigenere(int[] k) {
        if (k == null || k.length == 0) {
            throw new IllegalArgumentException("秘钥不能为空");
        }

        this.key = k;
    }

    @Override
    public String encrypt(char[] plaintext) {
        for (int i = 0; i < plaintext.length; ) {
            //按秘钥长度分组加密
            for (int j = 0; j < key.length; j++) {
                if ((i + j) >= plaintext.length) {
                    return String.valueOf(plaintext);
                }
                char c = CharAndNumber.numberToChar((CharAndNumber.charToNumber(plaintext[i + j]) + key[j]) % 26);
                plaintext[i + j] = c;
            }
            //跳过已经加密过的数据
            i += key.length;
        }

        return String.valueOf(plaintext);
    }

    @Override
    public String decode(char[] ciphertext) {
        for (int i = 0; i < ciphertext.length; ) {
            //按秘钥长度分组加密
            for (int j = 0; j < key.length; j++) {
                if ((i + j) >= ciphertext.length) {
                    return String.valueOf(ciphertext);
                }
                int n = (CharAndNumber.charToNumber(ciphertext[i + j]) - key[j]) % 26;
                n = n < 0 ? n + 26 : n;
                char c = CharAndNumber.numberToChar(n);
                ciphertext[i + j] = c;
            }
            //跳过已经加密过的数据
            i += key.length;
        }

        return String.valueOf(ciphertext);
    }

    public static void main(String[] args) {
        String test = "abcdefgabcdefg";
        IEncrypter encrypt = new Vigenere(new int[]{3, 5, 1, 6, 4, 2, 7});
        String ciphertext = encrypt.encrypt(test);
        System.out.println(ciphertext);
        String plaintext = encrypt.decode(ciphertext);
        System.out.println(plaintext);
    }
}
