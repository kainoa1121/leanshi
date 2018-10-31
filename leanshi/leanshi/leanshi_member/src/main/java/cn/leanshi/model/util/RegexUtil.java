package cn.leanshi.model.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
    /**
     * 校验身份证号码是否符合格式要求
     * @param idCode
     * @return
     */
    public static boolean isIDCard(String idCode){
        String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";
        return Pattern.matches(REGEX_ID_CARD, idCode);
    }

    /**
     * 校验护照编号是否符合格式要求
     * @param idCode
     * @return
     */
    public static boolean isPassPort(String idCode){
        String PASSPORT1 = "/^[a-zA-Z]{5,17}$/";
        String PASSPORT2 = "/^[a-zA-Z0-9]{5,17}$/";
        boolean matches1 = Pattern.matches(PASSPORT1, idCode);
        boolean matches2 = Pattern.matches(PASSPORT2, idCode);
        if(matches1==true||matches2==true){
            return true;
        }
        return false;
    }

    /**
     * 校验手机号码格式
     * @param mobile
     * @return
     */
    public static boolean isPhoneNum(String mobile){

        boolean flag = false;
        try{
            Pattern regex = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0,5-9]))\\d{8}$");
            Matcher m = regex .matcher(mobile);
            flag = m.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }
}
