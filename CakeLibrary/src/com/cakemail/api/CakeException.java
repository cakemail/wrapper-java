package com.cakemail.api;

public class CakeException extends Exception {

  public int Code = 0;

  public CakeException(CakeErrors.CakeError cakeError) {
    super(cakeError.Message);
    Code = cakeError.Code;
  }

  public CakeException(int code, String message) {
    super(message);
    Code = code;
  }

  public CakeException(String code, String message) {
    super(message);
    Code = new Integer(code);
  }

  public CakeException(String message) {
    super(message);
  }
}
