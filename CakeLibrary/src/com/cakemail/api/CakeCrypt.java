package com.cakemail.api;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Arrays;

public class CakeCrypt {

  public Cipher cryptor;

  public CakeCrypt() throws Exception {
    cryptor = Cipher.getInstance(CakeGlobals.CAKE_MCRYPT_ALG + "/" + CakeGlobals.CAKE_MCRYPT_MODE + "/NoPadding");
  }

  /**
   * Encrypts some data with a given key
   *
   * @data
   * @key
   * @returns		the encrypted data in binary hex format
   */
  public String CakeEncryptHex(String data) throws Exception {
    return ToHex(CakeEncrypt(data.getBytes()));
  }

  public byte[] CakeEncrypt(byte[] data) throws Exception {
    int len = data.length;
    int lenAligned = AdjustSize(len, cryptor.getBlockSize());

    byte[] workData = new byte[lenAligned];
    for (int i = 0; i < data.length - 1; i++) workData[i] = data[i];

    byte[] raw = CakeGlobals.CAKE_INTERFACE_KEY.getBytes();
    SecretKeySpec skeySpec = new SecretKeySpec(raw, CakeGlobals.CAKE_MCRYPT_ALG);

    if (!CakeGlobals.CAKE_MCRYPT_MODE.equalsIgnoreCase("ECB")) {
      int blockSize = cryptor.getBlockSize();
      byte[] IV = new byte[blockSize];
      Arrays.fill(IV, (byte) 46);
      cryptor.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(IV));
    } else {
      cryptor.init(Cipher.ENCRYPT_MODE, skeySpec);
    }

    byte[] encrypted = cryptor.doFinal(workData);

    return encrypted;
  }

  /**
   * Decrypts some data with a given key
   *
   * @data	in hex encrypted format
   * @key
   * @returns	the decrypted data
   */
  public String CakeDecryptHex(String data) throws Exception {
    return new String(CakeDecrypt(FromHex(data))).replace("\0", "");
  }

  public byte[] CakeDecrypt(byte[] data) throws Exception {
    byte[] raw = CakeGlobals.CAKE_INTERFACE_KEY.getBytes();
    SecretKeySpec skeySpec = new SecretKeySpec(raw, CakeGlobals.CAKE_MCRYPT_ALG);

    int blockSize;
    if (!CakeGlobals.CAKE_MCRYPT_MODE.equalsIgnoreCase("ECB")) {
      blockSize = cryptor.getBlockSize();
      byte[] IV = new byte[blockSize];
      for (int i = 0; i < blockSize; i++) IV[i] = data[i];
      cryptor.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(IV));
    } else {
      blockSize = 0;
      cryptor.init(Cipher.DECRYPT_MODE, skeySpec);
    }

    int len = data.length - blockSize;
    int lenAligned = AdjustSize(len, cryptor.getBlockSize());
    byte[] workData = new byte[lenAligned];
    for (int i = blockSize; i < data.length; i++) workData[i - blockSize] = data[i];

    byte[] dencrypted = cryptor.doFinal(workData);

    return dencrypted;
  }

  public static String ToHex(byte[] b) {
    int size = b.length;
    StringBuffer h = new StringBuffer(size);
    for (int i = 0; i < size; i++) {
      int u = b[i] & 255; // unsigned conversion

      if (u < 16) {
        h.append("0" + Integer.toHexString(u));
      } else {
        h.append(Integer.toHexString(u));
      }
    }
    return h.toString();
  }

  public static byte[] FromHex(String str) {
    if (str == null) {
      return new byte[0];
    }
    int len = str.length();    // probably should check length

    char hex[] = str.toCharArray();
    byte[] buf = new byte[len / 2];

    for (int pos = 0; pos < len / 2; pos++) {
      buf[pos] = (byte) (((toDataNibble(hex[2 * pos]) << 4) & 0xF0) | (toDataNibble(hex[2 * pos + 1]) & 0x0F));
    }
    return buf;
  }

  private static byte toDataNibble(char c) {
    if (('0' <= c) && (c <= '9')) {
      return (byte) ((byte) c - (byte) '0');
    } else if (('a' <= c) && (c <= 'f')) {
      return (byte) ((byte) c - (byte) 'a' + 10);
    } else if (('A' <= c) && (c <= 'F')) {
      return (byte) ((byte) c - (byte) 'A' + 10);
    } else {
      return -1;
    }
  }

  private static int AdjustSize(int arg, int to) {
    int res = arg & ~(to - 1);
    if (res < arg) {
      res += to;
    }
    return res;
  }
}
