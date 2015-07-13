/*
 * CakeRelay.java
 */
package com.cakemail.api;

import org.w3c.dom.Node;

public class CakeRelay extends CakeObject {

  private static String className = "ClassRelay";

  /**
   * Retrieves the logs
   * 
   * @returns		true or an exception
   */
  public static CakeDictionary GetLogs(CakeDictionary parameters) throws Exception {
    return GetLogs(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary GetLogs(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "GetLogs";

    Node methodNode = Proccess(className, methodName, parameters, locale);

    return ParseXML(methodNode);
  }

  /**
   * Sends an email
   * 
   * @returns		true or an exception
   */
  public static boolean Send(CakeDictionary parameters) throws Exception {
    return Send(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static boolean Send(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "Send";

    Proccess(className, methodName, parameters, locale);

    return true;
  }
}
