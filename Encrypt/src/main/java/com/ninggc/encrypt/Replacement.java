package com.ninggc.encrypt;

import java.util.ArrayList;

/**
 * 置换密码
 */
public class Replacement implements IEncrypter {
    interface Handle {
        /**
         *
         * @param text
         * @param result
         * @param i i是遍历文本
         * @param j j是遍历秘钥, i + j 可取的当前文本字符
         */
        void handle(char[] text, ArrayList<Character> result, int i, int j);
    }

    /**
     * 秘钥中不能有比秘钥长度大的数字
     */
    private final int[] key;

    public Replacement(int[] k) {
        for (int i = 0; i < k.length; i++) {
            if (k[i] < 1 || k[i] > k.length) {
                throw new IllegalArgumentException("秘钥不合法，置换序列小于0或大于秘钥长度；" + k[i]);
            }
        }
        this.key = k;
    }

    public String operateWithHandle(char[] text, Handle encrypt) {
        int multiple = text.length / key.length;
        multiple = text.length % key.length == 0 ? multiple : multiple + 1;
        //length是key长度的整数倍
        int length = multiple * key.length;
        ArrayList<Character> result = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            //多余的位置置0
            result.add(i, (i >= text.length ? 0 : text[i]));
        }

        for (int i = 0; i < text.length; ) {
            for (int j = 0; j < key.length; j++) {
                //操作过程
                encrypt.handle(text, result, i, j);
            }
            //跳过已经加密过的数据
            i += key.length;
        }

        StringBuilder resultString = new StringBuilder();
        for (Character character : result) {
            resultString.append(character);
        }
        return String.valueOf(resultString.toString());
    }

    @Override
    public String encrypt(char[] plaintext) {
        return operateWithHandle(plaintext, new Handle() {
            @Override
            public void handle(char[] text, ArrayList<Character> result, int i, int j) {
                if ((i + j) >= text.length) {
                    result.set(i + key[j] - 1, '0');
                } else {
                    //因为key是从1开始的，所以要减去1
//                    result.set(i + j, text[i + key[j] - 1]);
                    result.set(i + key[j] - 1 , text[i + j]);
                }
            }
        });
    }

    /**
     * 基于置换密码的特性
     * 只要将秘钥倒序后对密文进行加密就可以获取铭文
     * @param ciphertext
     * @return
     */
    @Override
    public String decode(char[] ciphertext) {
//        int length = key.length;
//        int[] inverseKey = new int[length];
//        for (int i = 0; i < length; i++) {
//            inverseKey[length - i - 1] = key[i];
//        }
//        return new Replacement(inverseKey).encrypt(ciphertext);
        return operateWithHandle(ciphertext, new Handle() {
            @Override
            public void handle(char[] text, ArrayList<Character> result, int i, int j) {
                if (i + j >= result.size()) {
                    return;
                }
                if (i == 7) {
                    System.out.println("");
                }
                result.set(i + j, text[i + key[j] - 1]);
            }
        });
    }

    public static void main(String[] args) {
        String test = "123456789";
        IEncrypter encrypt = new Replacement(new int[]{3, 5, 1, 6, 4, 2, 7});
        String ciphertext = encrypt.encrypt(test);
        System.out.println(ciphertext);
        String plaintext = encrypt.decode(ciphertext);
        System.out.println(plaintext);
    }
}
