package com.rauniyarp.fileHandler;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

public class FileTextTransformation {
    public static final int VERSION = 1;
    private static final String NUMBERS = "0123456789";
    private static final String UPPER_ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_ALPHABETS = "abcdefghijklmnopqrstuvwxyz";
    private static final String SPECIALCHARACTERS = "@#$%&*";
    private static final int MINLENGTHOFPASSWORD = 8;




    public String encrypt(String message, String secretKey) {
        String str;
        try {
            str = DES3._encrypt(message, secretKey);
        } catch (Exception e) {
            str = "";
        }
        return str.trim();
    }


    public String decrypt(String encryptedText, String secretKey) {
        String str;
        try {
            str = DES3._decrypt(encryptedText, secretKey);
        } catch (Exception e) {
            str = "";
        }
        return str.trim();
    }


    public String GetRamdomString(int size) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }


    public String GetRamdomStringCustom(int size, String customCaracter) {
        String ABcustom = customCaracter;
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++)
            sb.append(ABcustom.charAt(rnd.nextInt(ABcustom.length())));
        return sb.toString();
    }


    public String GetRamdomUUID() {
        String uUID = UUID.randomUUID().toString();
        return uUID;
    }


    public String GenerateRandomHexToken(int byteLength) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] token = new byte[byteLength];
        secureRandom.nextBytes(token);
        return (new BigInteger(1, token)).toString(16);
    }

    public static class DES3 {
        public static String ALGO = "DESede/ECB/PKCS5Padding";

        public static String _encrypt(String message, String secretKey) throws Exception {
            Cipher cipher = Cipher.getInstance(ALGO);
            cipher.init(1, getSecreteKey(secretKey));
            byte[] plainTextBytes = message.getBytes(StandardCharsets.UTF_8);
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = java.util.Base64.getEncoder().encode(buf);
            return new String(base64Bytes);
        }

        public static String _decrypt(String encryptedText, String secretKey) throws Exception {
            byte[] message = java.util.Base64.getDecoder().decode(encryptedText.getBytes());
            Cipher decipher = Cipher.getInstance(ALGO);
            decipher.init(2, getSecreteKey(secretKey));
            byte[] plainText = decipher.doFinal(message);
            return new String(plainText, StandardCharsets.UTF_8);
        }

        public static SecretKey getSecreteKey(String secretKey) throws Exception {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digestOfPassword = md.digest(secretKey.getBytes(StandardCharsets.UTF_8));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            return new SecretKeySpec(keyBytes, "DESede");
        }
    }


    public String GetRandomPassword() {
        return getRandomPassword();
    }

    public static String getRandomPassword() {
        StringBuilder password = new StringBuilder();
        int j = 0;
        for (int i = 0; i < 8; i++) {
            password.append(getRandomPasswordCharacters(j));
            j++;
            if (j == 3)
                j = 0;
        }
        return password.toString();
    }

    private static String getRandomPasswordCharacters(int pos) {
        Random randomNum = new Random();
        StringBuilder randomChar = new StringBuilder();
        switch (pos) {
            case 0:
                randomChar.append("0123456789".charAt(randomNum.nextInt("0123456789".length() - 1)));
                break;
            case 1:
                randomChar.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(randomNum.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZ".length() - 1)));
                break;
            case 2:
                randomChar.append("@#$%&*".charAt(randomNum.nextInt("@#$%&*".length() - 1)));
                break;
            case 3:
                randomChar.append("abcdefghijklmnopqrstuvwxyz".charAt(randomNum.nextInt("abcdefghijklmnopqrstuvwxyz".length() - 1)));
                break;
        }
        return randomChar.toString();
    }
}