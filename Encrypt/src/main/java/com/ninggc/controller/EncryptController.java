package com.ninggc.controller;

import com.ninggc.DAO.Info;
import com.ninggc.encrypt.*;
import com.ninggc.util.IGson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EncryptController implements IGson {

    private IEncrypter encrypter;
    /**
     * 储存加密的集合
     */
    private static List<Info> resultList = new ArrayList<>();
    private static int count = 0;

    /**
     * 转到结果界面
     * @return
     */
    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public ModelAndView show(Model m) {
        ModelAndView mav = new ModelAndView("result");
        mav.addObject("infoList", resultList);
        return mav;
    }

    /**
     * 转到加密界面，加密后转到结果界面
     * @param info 表单信息
     */
    @RequestMapping(value = "/encrypt", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView view(HttpServletRequest request, @ModelAttribute("info") Info info){

        String path_result = "result";
        String path_encrypt = "encrypt";
        ModelAndView mav = new ModelAndView();

        System.out.println("info: " + gson.toJson(info));

        String type = info.getType();
        String key = info.getKey();
        //设置初始状态为成功
        info.setSuccess(true);

        if (type == null || "0".equals(type)) {
            mav.setViewName(path_encrypt);
            mav.addObject("types", EType.types);
            System.out.println("====================");
            return mav;
        }


        try {
            //开始加密过程
            switch (type) {
                case "1":
                    encrypter = new Shifting(Integer.parseInt(key));
                    break;
                case "2":
                    encrypter = new Replacement(parseStringToIntArr(key));
                    break;
                case "3":
                    encrypter = new Vigenere(parseStringToIntArr(key));
                    break;
                case "4":
                    int[] keyArr = parseStringToIntArr(key);
                    if (keyArr == null || keyArr.length < 2) {
                        throw new IllegalArgumentException("秘钥长度非法");
                    } else {
                        encrypter = new AffineCipher(keyArr[0], keyArr[1]);
                    }
                    break;
            }

            if (encrypter == null) {
                encrypter = new ErrorEncrypter(ErrorEncrypter.ERROR_NULL_KEY);
                info.setSuccess(false);
                info.setMsg("秘钥不合法");
            }
        } catch (Exception e) {
//            e.printStackTrace();
            info.setSuccess(false);
            encrypter = new ErrorEncrypter("秘钥不合法");
        }

        String ciphertext = "";
        String plaintext = "";

        if (info.getText() == null || "".equals(info.getText())) {
            info.setSuccess(false);
            encrypter = new ErrorEncrypter("未输入明文");
        }

        try {
            if ("1".equals(info.getActionType())) {
                //解密
                plaintext = encrypter.decode(info.getText());
            } else {
                //加密
                ciphertext = encrypter.encrypt(info.getText());
            }
        } catch (IllegalArgumentException e) {
            plaintext = ciphertext = e.getMessage();
        }

        System.out.println("ciphertext: " + ciphertext);
        info.setPlaintext(plaintext);
        info.setCiphertext(ciphertext);
        info.setId(count++);
        info.seteType(EType.get(Integer.parseInt(info.getType())));
        resultList.add(info);

        mav.addObject("info", info);
        mav.addObject("infoList", resultList);
        System.out.println(gson.toJson(resultList));
        mav.setViewName(path_result);
        System.out.println("====================");
        return mav;
    }

    private int[] parseStringToIntArr(String key) {
        String[] keySplit;
        int[] keyArr;
        keySplit = key.split(" ");
        keyArr = new int[keySplit.length];
        for (int i = 0; i < keySplit.length; i++) {
            try {
                keyArr[i] = Integer.parseInt(keySplit[i]);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return keyArr;
    }
}
