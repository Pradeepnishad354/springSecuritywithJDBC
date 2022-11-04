package com.security.jdbc;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {
public static void main(String[] args) {
	BCryptPasswordEncoder enc=new BCryptPasswordEncoder();
	String encode = enc.encode("abhi");
	System.out.println(encode);
}
}
