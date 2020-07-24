package com.cheney.gankjava.util;

import android.text.TextUtils;
import android.util.Base64;

public class Base64Util {
    /**
     * Base64 encode
     *
     * @param str
     * @return
     */
    public static String encode(String str) {
        try {
            if (TextUtils.isEmpty(str))
                return "";
            else
                return new String(Base64.encode(str.getBytes(), Base64.DEFAULT), "UTF-8");
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Base64 decode
     *
     * @param str
     * @return
     */
    public static String decode(String str) {
        try {
            if (TextUtils.isEmpty(str))
                return "";
            else
                return new String(Base64.decode(str, Base64.DEFAULT), "UTF-8");
        } catch (Exception e) {
            return "";
        }
    }
}
