/*
 * CakeClient.java
 */
package com.cakemail.api;

import org.w3c.dom.Node;

public class CakeClient extends CakeObject {

  private static String className = "ClassClient";

  /**
   * Activates a client
   *
   * @returns		an array or an exception
   */
  public static CakeDictionary Activate(CakeDictionary parameters) throws Exception {
    return Activate(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary Activate(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "Activate";

    Node methodNode = Proccess(className, methodName, parameters, locale);

    return ParseXML(methodNode);
  }

  /* Adds credits to a client */
  
  public static boolean AddCredits(CakeDictionary parameters) throws Exception {
    return AddCredits(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static boolean AddCredits(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "AddCredits";

    Proccess(className, methodName, parameters, locale);

    return true;
  }
  
  /**
   * Creates a new client
   *
   * @returns		the confirmation code or an exception
   */
  public static CakeDictionary Create(CakeDictionary parameters) throws Exception {
    return Create(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary Create(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "Create";

    Node methodNode = Proccess(className, methodName, parameters, locale);

    return ParseXML(methodNode);
  }

  /* Gets the credit balance */
  
  public static CakeDictionary GetCreditBalance(CakeDictionary parameters) throws Exception {
    return GetCreditBalance(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary GetCreditBalance(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "GetCreditBalance";

    Node methodNode = Proccess(className, methodName, parameters, locale);

    return ParseXML(methodNode);
  }

  /* Gets the credit transactions */
  
  public static CakeDictionary GetCreditTransactions(CakeDictionary parameters) throws Exception {
    return GetCreditTransactions(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary GetCreditTransactions(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "GetCreditTransactions";

    Node methodNode = Proccess(className, methodName, parameters, locale);

    return ParseXML(methodNode);
  }
  
  /**
   * Retrieves the informations about the client
   * 
   * @returns		an array or an exception
   */
  public static CakeDictionary GetInfo(CakeDictionary parameters) throws Exception {
    return GetInfo(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary GetInfo(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "GetInfo";

    Node methodNode = Proccess(className, methodName, parameters, locale);

    return ParseXML(methodNode);
  }

  /**
   * Gets the list with a specified status
   * 
   * @returns		an array or an exception
   */
  public static CakeDictionary GetList(CakeDictionary parameters) throws Exception {
    return GetList(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary GetList(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "GetList";

    Node methodNode = Proccess(className, methodName, parameters, locale);
    CakeDictionary res = ParseXML(methodNode, new String[]{
              "client"
            });
    ChangeKey(res, "client", "clients");

    return res;
  }

  /**
   * Gets the timezones
   *
   * @returns		an array or an exception
   */
  public static CakeDictionary GetTimezones(CakeDictionary parameters) throws Exception {
    return GetTimezones(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary GetTimezones(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "GetTimezones";

    Node methodNode = Proccess(className, methodName, parameters, locale);
    CakeDictionary res = ParseXML(methodNode, new String[]{
              "timezone"
            });
    ChangeKey(res, "timezone", "timezones");

    return res;
  }

  /* Adds or removes credits to a client for the balance to be 0 at the end of the month */
  
  public static boolean ResetCredits(CakeDictionary parameters) throws Exception {
    return ResetCredits(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static boolean ResetCredits(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "ResetCredits";

    Proccess(className, methodName, parameters, locale);

    return true;
  }
  
  /*
   * Sets the parameters for a user
   * 
   * @returns		true or an exception
   */
  public static boolean SetInfo(CakeDictionary parameters) throws Exception {
    return SetInfo(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static boolean SetInfo(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "SetInfo";

    Proccess(className, methodName, parameters, locale);

    return true;
  }

  /**
   * Searchs for clients based on a query string
   *
   * @returns		an array or an exception
   */
  public static CakeDictionary Search(CakeDictionary parameters) throws Exception {
    return Search(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary Search(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "Search";

    Node methodNode = Proccess(className, methodName, parameters, locale);
    CakeDictionary res = ParseXML(methodNode, new String[]{
              "client"
            });
    ChangeKey(res, "client", "clients");

    return res;
  }
}
