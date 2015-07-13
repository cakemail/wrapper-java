/*
 * CakeTrigger.java
 */
package com.cakemail.api;

import org.w3c.dom.Node;

public class CakeTrigger extends CakeObject {

  private static String className = "ClassTrigger";

  /**
   * Creates a new trigger
   * 
   * @returns		the new trigger's id or an exception
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
   * Gets a trigger
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
   * Returns the list of links for a trigger
   *
   * @returns		an array or an exception
   */
  public static CakeDictionary GetLinks(CakeDictionary parameters) throws Exception {
    return GetLinks(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary GetLinks(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "GetLinks";

    Node methodNode = Proccess(className, methodName, parameters, locale);
    CakeDictionary res = ParseXML(methodNode, new String[]{
              "link"
            });
    ChangeKey(res, "link", "links");

    return res;
  }

  /**
   * Returns the total and unique counts of openings for triger's links
   *
   * @returns		an array or an exception
   */
  public static CakeDictionary GetLinksLog(CakeDictionary parameters) throws Exception {
    return GetLinksLog(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary GetLinksLog(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "GetLinksLog";

    Node methodNode = Proccess(className, methodName, parameters, locale);
    CakeDictionary res = ParseXML(methodNode, new String[]{
              "link"
            });
    ChangeKey(res, "link", "links");

    return res;
  }

  /**
   * Retrieves the list of triggers
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
              "trigger"
            });
    ChangeKey(res, "trigger", "triggers");

    return res;
  }

  /**
   * Returns the logs of a trigger
   *
   * @returns		an array or an exception
   */
  public static CakeDictionary GetLog(CakeDictionary parameters) throws Exception {
    return GetLog(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary GetLog(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "GetLog";

    Node methodNode = Proccess(className, methodName, parameters, locale);
    CakeDictionary res = ParseXML(methodNode, new String[]{
              "count"
              ,"daily"
              ,"hourly"
              ,"log"
            });
    ChangeKey(res, "log", "logs");

    return res;
  }

  /**
   * Sets the parameters for a trigger
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
   * Unleashes a trigger
   * 
   * @returns		true or an exception
   */
  public static boolean Unleash(CakeDictionary parameters) throws Exception {
    return Unleash(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static boolean Unleash(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "Unleash";

    Proccess(className, methodName, parameters, locale);

    return true;
  }
}
