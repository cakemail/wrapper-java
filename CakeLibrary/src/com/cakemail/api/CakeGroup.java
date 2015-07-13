/*
 * CakeGroup.java
 */
package com.cakemail.api;

import org.w3c.dom.Node;

public class CakeGroup extends CakeObject {

  private static String className = "ClassGroup";

  /**
   * Adds a user to a group
   *
   * @returns		true or an exception
   */
  public static boolean AddUser(CakeDictionary parameters) throws Exception {
    return AddUser(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static boolean AddUser(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "AddUser";

    Proccess(className, methodName, parameters, locale);

    return true;
  }

  /**
   * Creates a new group
   * 
   * @returns		group_id or an exception
   */
  public static int Create(CakeDictionary parameters) throws Exception {
    return Create(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static int Create(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "Create";

    Node methodNode = Proccess(className, methodName, parameters, locale);

    return Integer.parseInt(GetChildNodeByName(methodNode, "id").getTextContent());
  }

  /**
   * Deletes a group
   * 
   * @returns		true or an exception
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
   * Gets the informations of a group
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
              "permission"
            });
    ChangeKey(res, "permission", "permissions");

    return res;
  }

  /**
   * Returns the list of the groups
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
              "group"
            });
    ChangeKey(res, "group", "groups");

    return res;
  }

  /**
   * Removes a user from a group
   *
   * @returns		true or an exception
   */
  public static boolean RemoveUser(CakeDictionary parameters) throws Exception {
    return RemoveUser(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static boolean RemoveUser(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "RemoveUser";

    Proccess(className, methodName, parameters, locale);

    return true;
  }

  /**
   * Sets the informations of a group
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
