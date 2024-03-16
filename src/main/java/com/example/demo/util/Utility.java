package com.example.demo.util;

import com.example.demo.enums.StatusCodeEnum;
import com.example.demo.exception.UserException;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

@Component
public class Utility {

    public String encryptPazzW0rdWithSalt(String pazzW0rd) throws Exception {
        String key = "0123456789abcdef"; // AES密钥（16字节）

        byte[] value = pazzW0rd.getBytes("UTF-8");
        byte[] salt = new byte[16];
        new java.security.SecureRandom().nextBytes(salt);

        byte[] result = new byte[salt.length + value.length];
        System.arraycopy(salt, 0, result, 0, salt.length);
        System.arraycopy(value, 0, result, salt.length, value.length);

        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] iv = new byte[16];
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

        String encryptData = Base64.getEncoder().encodeToString(cipher.doFinal(result));
//        System.out.println(encryptData);
        return encryptData;
    }

    public String decrypt(String encryptedPassword) throws Exception {
        byte[] salt = new byte[16];
        // Use a secure random number generator
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        String saltStr = Base64.getEncoder().encodeToString(salt);

        int iterationCount = 10000;
        int keyLength = 256;

        KeySpec keySpec = new PBEKeySpec(encryptedPassword.toCharArray(), Base64.getDecoder().decode(salt), iterationCount, keyLength);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] keyBytes = keyFactory.generateSecret(keySpec).getEncoded();
        SecretKey key = new SecretKeySpec(keyBytes, "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
        return new String(decryptedBytes);
    }

}
