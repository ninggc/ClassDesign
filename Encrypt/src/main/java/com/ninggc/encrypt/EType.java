package com.ninggc.encrypt;

public enum EType {
    SHIFTING("移位加密", 1, "秘钥为需要移动的位数<br>格式：单个数字")
    , REPLACEMENT("置换加密", 2, "秘钥为一组置换序列(数字)<br>说明：数字不得大于序列的长度<br>格式：用空格隔开")
    , VIGENERE("维吉尼亚加密", 3, "请输入秘钥(数字)<br>格式：用空格隔开")
    , AFFINECIPHER("仿射加密", 4, "加密方式：'aX + b'<br>说明：请按序输入a、b(数字)的值<br>格式：用空格隔开");

    public static final EType[] types = new EType[]{SHIFTING, REPLACEMENT, VIGENERE, AFFINECIPHER};

    private final String note;
    private final int order;
    private final String keyTip;

    public String getNote() {
        return note;
    }

    public int getOrder() {
        return order;
    }

    public String getKeyTip() {
        return keyTip;
    }

    EType(String s, int i, String tip) {
        note = s;
        order = i;
        keyTip = tip;
    }

    public static EType get(int i) {
        switch (i) {
            case 1:
                return SHIFTING;
            case 2:
                return REPLACEMENT;
            case 3:
                return VIGENERE;
            case 4:
                return AFFINECIPHER;
            default:return null;
        }
    }
}
