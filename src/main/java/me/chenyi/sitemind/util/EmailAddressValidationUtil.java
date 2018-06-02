package me.chenyi.sitemind.util;

import java.util.regex.Pattern;

public class EmailAddressValidationUtil {

    private static Pattern p = null;


    public static boolean isValidEmailAddress(String email) {
        if (p == null) {
            String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
            p = Pattern.compile(ePattern);
        }
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean isValidateEmailList(String emailList) {
        String[] addrs = emailList.split(",");
        int count = 0;
        for (String addr : addrs) {
            if (addr.trim().length() != 0){
                count ++;
                if (!isValidEmailAddress(addr))
                    return false;
            }
        }
        return count > 0;
    }

}
