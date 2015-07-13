/*
 * CakeCampaign.java
 */
package com.cakemail.api;

import org.w3c.dom.Node;

public class CakeCampaign extends CakeObject {

  private static String className = "ClassCampaign";

  /**
   * Creates a new campaign
   *
   * @params		an array of parameters
   * @returns		the new campaign's id or an exception
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
   * Deletes a campaign
   * 
   * @params		an array of parameters
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
   * gets the list of campaigns specified by status
   *
   * @params		an array of parameters
   * @returns		an array with the campaigns or an exception
   */
  public static CakeDictionary GetList(CakeDictionary parameters) throws Exception {
    return GetList(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary GetList(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "GetList";

    Node methodNode = Proccess(className, methodName, parameters, locale);
    CakeDictionary res = ParseXML(methodNode, new String[]{
              "campaign"
            });
    ChangeKey(res, "campaign", "campaigns");

    return res;
  }

  /**
   * retrieves the campaign's infos
   *
   * @params		an array of parameters
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
   * gets the count of mailings assigned to a campaign
   *
   * @params		an array of parameters
   * @returns		an integer or an exception
   */
  public static int GetMailingsCount(CakeDictionary parameters) throws Exception {
    return GetMailingsCount(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static int GetMailingsCount(CakeDictionary parameters, String locale) throws Exception {
    throw new CakeException("Not realized");
  }

  /**
   * Sets the parameters for a campaign
   * 
   * @params		an array of parameters
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
