package com.bingqp.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

  /**
   * 加密
   *
   * @param data
   * @return
   */
  public static String md5(String data) {
    String result = "";
    result = transByteArrayToHexString(encryptToByte(data));
    return result;
  }

  /**
   * 加上字符集 默认GBK
   */
  public static byte[] encryptToByte(String data) {
    return encryptToByte(data, "UTF-8");
  }

  /**
   * 加上字符集
   */
  public static byte[] encryptToByte(String data, String charset) {
    byte[] digestInfo = null;
    try {
      MessageDigest digest = MessageDigest.getInstance("MD5");
      digest.update(data.getBytes(charset));
      digestInfo = digest.digest();
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("NoSuchAlgorithmException", e);
    } catch (Exception e) {
      throw new RuntimeException("Exception", e);
    }
    return digestInfo;
  }

  // 二进制转16进制字符串
  public static String transByteArrayToHexString(byte[] byteArray) {
    StringBuffer sb = new StringBuffer();

    for (int i = 0; i < byteArray.length; i++) {
      int b = byteArray[i];
      String s = Integer.toHexString(b);

      if (s.length() > 2) {
        s = s.substring(s.length() - 2, s.length());
      }

      if (s.length() == 1) {
        s = "0" + s;
      }

      sb.append(s);
    }

    return sb.toString();
  }

  public static String MD5EncodeNumber16(String origin) {
    String resultString = null;
    try {
      resultString = origin;
      MessageDigest md = MessageDigest.getInstance("MD5");
      resultString = byteArrayToString(md.digest(resultString.getBytes()));
      resultString = resultString.substring(0, 16);
    } catch (Exception ex) {

    }
    return resultString;
  }

  public static String byteArrayToString(byte[] b) {
    StringBuffer resultSb = new StringBuffer();
    for (int i = 0; i < b.length; i++) {
      resultSb.append(byteToNumString(b[i]));
    }
    return resultSb.toString();
  }

  private static String byteToNumString(byte b) {
    int _b = b;
    if (_b < 0) {
      _b = 256 + _b;
    }
    return String.valueOf(_b);
  }
}
