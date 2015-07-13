/*
 * CakeCountry.java
 */
package com.cakemail.api;

import org.w3c.dom.Node;

public class CakeCountry extends CakeObject {

  private static String className = "ClassCountry";

  /**
   * Gets the list of countries
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
              "country"
            });
    ChangeKey(res, "country", "countries");

    return res;
  }

  /**
   * Gets the list of provinces for a specified country
   * 
   * @returns		an array or an exception
   */
  public static CakeDictionary GetProvinces(CakeDictionary parameters) throws Exception {
    return GetProvinces(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary GetProvinces(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "GetProvinces";

    Node methodNode = Proccess(className, methodName, parameters, locale);
    CakeDictionary res = ParseXML(methodNode, new String[]{
              "province"
            });
    ChangeKey(res, "province", "provinces");

    return res;
  }
}
