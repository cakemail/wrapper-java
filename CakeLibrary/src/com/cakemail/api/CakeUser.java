package com.cakemail.api;

import org.w3c.dom.Node;

public class CakeUser extends CakeObject {

  private static String className = "ClassUser";

  /* Checks for a permission on a user */
  
  public static boolean CheckPermission(CakeDictionary parameters) throws Exception {
    return CheckPermission(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static boolean CheckPermission(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "CheckPermission";

    Proccess(className, methodName, parameters, locale);

    return true;
  }

  /**
   * Creates a user
   *
   * @returns		an array or an exception
   */
  public static CakeDictionary Create(CakeDictionary parameters) throws Exception {
    return Create(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary Create(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "Create";

    Node methodNode = Proccess(className, methodName, parameters, locale);

    return ParseXML(methodNode);
  }

  /**
   * Gets the informations about a user
   *
   * @returns		an array or an exception
   */
  public static boolean Delete(CakeDictionary parameters) throws Exception {
    return Delete(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static boolean Delete(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "Delete";

    Proccess(className, methodName, parameters, locale);

    return true;
  }

  /**
   * Gets the informations about a user
   *
   * @returns		an array or an exception
   */
  public static CakeDictionary GetInfo(CakeDictionary parameters) throws Exception {
    return GetInfo(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary GetInfo(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "GetInfo";

    Node methodNode = Proccess(className, methodName, parameters, locale);
    CakeDictionary res = ParseXML(methodNode, new String[]{
              "user"
            });
    ChangeKey(res, "user", "users");

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
              "user",
              "group_id"
            });
    ChangeKey(res, "user", "users");

    return res;
  }

  /**
   * Recovers a password for a user
   *
   * @returns		true or an exception
   */
  public static boolean PasswordRecovery(CakeDictionary parameters) throws Exception {
    return PasswordRecovery(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static boolean PasswordRecovery(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "PasswordRecovery";

    Proccess(className, methodName, parameters, locale);

    return true;
  }

  /**
   * Logs-in a user
   *
   * @returns		an array or an exception
   */
  public static CakeDictionary Login(CakeDictionary parameters) throws Exception {
    return Login(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary Login(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "Login";

    Node methodNode = Proccess(className, methodName, parameters, locale);

    return ParseXML(methodNode);
  }

  /**
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
}
