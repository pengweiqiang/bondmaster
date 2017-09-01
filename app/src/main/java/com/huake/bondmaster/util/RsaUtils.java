package com.huake.bondmaster.util;

/**
 * @author will on 2017/8/31 22:49
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

import android.util.Base64;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAUtils {

    public RSAPublicKey mPublicKey;
    public RSAPrivateKey mPrivateKey;

    public void initKey() {
        KeyPairGenerator keyPairGen = null;
        try {
            //设置使用何种加密算法
            keyPairGen = KeyPairGenerator.getInstance("RSA");
            // 密钥位数
            keyPairGen.initialize(512);
            // 密钥对
            KeyPair keyPair = keyPairGen.generateKeyPair();
            // 公钥
            mPublicKey = (RSAPublicKey) keyPair.getPublic();

            // 私钥
            mPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    /****************************************
     * 函数说明：getPublicKey 取得公钥
     *
     * @param key 公钥字符串
     * @throws Exception
     * @return PublicKey 返回公钥
     * @author zhangmin
     ***************************************/
    public static PublicKey getPublicKey(String key) throws Exception
    {
        byte[] keyBytes = base64Dec(key);

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /****************************************
     * 函数说明：getPrivateKey 取得私钥
     *
     * @param key 私钥字符串
     * @throws Exception
     * @return PrivateKey 返回私钥
     * @author zhangmin
     ***************************************/
    public static PrivateKey getPrivateKey(String key) throws Exception
    {
        byte[] keyBytes = base64Dec(key);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    public static String encrypt(String passwd,RSAPublicKey mPublicKey) {
        String strEncrypt = null;
        try {
            // 实例化加解密类
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

            // 明文
            byte[] plainText = passwd.getBytes();

            // 加密
            cipher.init(Cipher.ENCRYPT_MODE, mPublicKey);
            //将明文转化为根据公钥加密的密文，为byte数组格式
            byte[] enBytes = cipher.doFinal(plainText);
            //为了方便传输我们可以将byte数组转化为base64的编码
            strEncrypt = base64Enc(enBytes);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvalidKeyException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (BadPaddingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            return strEncrypt;
        }
    }

    public String decrypt(String encString) {
        Cipher cipher = null;
        String strDecrypt = null;
        try {
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, mPrivateKey);
            //先将转为base64编码的加密后的数据转化为byte数组
            byte[] enBytes = base64Dec(encString);
            //解密称为byte数组，应该为字符串数组最后转化为字符串
            byte[] deBytes = cipher.doFinal(enBytes);

            strDecrypt = new String(deBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (BadPaddingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvalidKeyException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            return strDecrypt;
        }

    }
    //base64编码
    public static String base64Enc(byte[] enBytes) {
        return Base64.encodeToString(enBytes, Base64.DEFAULT);
    }
    //base64解码
    public static byte[] base64Dec(String str) {
        return Base64.decode(str, Base64.DEFAULT);
    }
}
