package com.buba.utils;

import org.springframework.util.DigestUtils;

public class Md5 {
    public static void main(String[] args) {
    String hashedPwd1 = DigestUtils.md5DigestAsHex(("123456").getBytes());
    }
}
