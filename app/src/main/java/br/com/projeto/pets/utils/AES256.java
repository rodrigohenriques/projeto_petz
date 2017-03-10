package br.com.projeto.pets.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AES256 {

    private static final String password = "feTuC2AfAvu*a&4s";
    private static final String salt = "br3F*r-=rugehUVa";
    private static final int pswdIterations = 65536;
    private static final int keySize = 128;//supporta 256 limite até 320 mas a chave tem que ser maior
    private static byte[] ivBytes;

    private static final String algorithm = "PBKDF2WithHmacSHA1";
    private static final String transformation = "AES/CBC/PKCS7Padding";
    private static final String algorithm_type = "AES";

    public static String encrypt(String plainText) throws UnsupportedEncodingException,
            NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
            InvalidKeyException, InvalidParameterSpecException, BadPaddingException,
            IllegalBlockSizeException {

        //get salt
        byte[] saltBytes = salt.getBytes(StandardCharsets.UTF_8);

        // Derive the key
        SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithm);
        PBEKeySpec spec = new PBEKeySpec(
                password.toCharArray(),
                saltBytes,
                pswdIterations,
                keySize
        );

        SecretKey secretKey = factory.generateSecret(spec);
        SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), algorithm_type);

        //encrypt the message
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.ENCRYPT_MODE, secret);
        AlgorithmParameters params = cipher.getParameters();
        ivBytes = params.getParameterSpec(IvParameterSpec.class).getIV();
        byte[] encryptedTextBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.encodeToString(encryptedTextBytes, Base64.DEFAULT);
    }

    public static String decrypt(String encryptedText) throws UnsupportedEncodingException,
            NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
            InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException {

        byte[] saltBytes = salt.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedTextBytes = Base64.decode(encryptedText, Base64.DEFAULT);

        // Derive the key
        SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithm);
        PBEKeySpec spec = new PBEKeySpec(
                password.toCharArray(),
                saltBytes,
                pswdIterations,
                keySize
        );

        SecretKey secretKey = factory.generateSecret(spec);
        SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), algorithm_type);

        // Decrypt the message
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(ivBytes));


        byte[] decryptedTextBytes = cipher.doFinal(encryptedTextBytes);


        return new String(decryptedTextBytes);

    }

}