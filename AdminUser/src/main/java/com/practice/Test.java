package com.practice;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {
public static void main(String[] args) {
	BCryptPasswordEncoder enc=new BCryptPasswordEncoder();
	String encode = enc.encode("123456");
	System.out.println(encode);
}
}