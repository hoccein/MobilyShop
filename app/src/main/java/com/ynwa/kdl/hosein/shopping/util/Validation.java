package com.ynwa.kdl.hosein.shopping.util;

import android.text.TextUtils;
import android.util.Patterns;

public class Validation {



    public static boolean name(String name){
        if (TextUtils.isEmpty(name))
            return false;
        else
            return true;
    }

    public static boolean email(String email){
        if (TextUtils.isEmpty(email))
            return false;
        else
            return true;
    }

    public static boolean emailPattern(String email){
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return false;
        else
            return true;
    }

    public static boolean password(String pass){
        if (TextUtils.isEmpty(pass))
            return false;
        else
            return true;
    }

    public static boolean passwordLength(String pass){
        if (pass.length()<6)
            return false;
        else
            return true;
    }

    public static boolean mobileValid(String mobile){
        return false;
    }

    public static boolean addressValid(String address){
        return false;
    }
}
