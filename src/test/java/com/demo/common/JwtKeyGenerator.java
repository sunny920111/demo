package com.demo.common;

import java.security.SecureRandom;

public class JwtKeyGenerator {
  public static void main(String[] args) {
    SecureRandom random = new SecureRandom();
    byte[] keyBytes = new byte[64]; // 키 길이를 조절할 수 있습니다.

    random.nextBytes(keyBytes);

    String base64EncodedKey = java.util.Base64.getEncoder().encodeToString(keyBytes);
    System.out.println("JWT Key: " + base64EncodedKey);
  }
}
