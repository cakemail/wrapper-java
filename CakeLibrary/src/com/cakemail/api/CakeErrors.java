package com.cakemail.api;

public class CakeErrors {

  public static CakeError CLOSED_XML = new CakeError(500, "The xml is closed; no further modifications");
  public static CakeError DECRYPTION_ERROR = new CakeError(502, "Decryption error");
  public static CakeError ENCRYPTION_ERROR = new CakeError(503, "Encryption error");
  public static CakeError UNCLOSED_XML = new CakeError(501, "The xml is not closed");
  public static CakeError XML_PARSE_ERROR = new CakeError(504, "Error parsing the xml structure");

  public static class CakeError {

    public int Code;
    public String Message;

    public CakeError(int code, String message) {
      Code = code;
      Message = message;
    }
  }
}
