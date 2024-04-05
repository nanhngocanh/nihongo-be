package com.hedspi.nihongobe.utils;

import org.springframework.security.core.Authentication;

public class BasicInfo {
    public static String getUserIdByAuthentication(Authentication authentication){
        return authentication.getName();
//        TODO
//        hiện đang setup là name, cần phải sửa
    }
}
