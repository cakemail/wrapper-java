/*
 * CakeMailing.java
 */
package com.cakemail.api;

import org.w3c.dom.Node;

public class CakeMailing extends CakeObject {

  private static String className = "ClassMailing";

  /**
   * Creates a mailing
   * 
   * @returns		mailing_id or an exception
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
   * Deletes a mailing
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
   * Gets the formated email message
   *
   * @returns		the message or an exception
   */
  public static CakeDictionary GetEmailMessage(CakeDictionary parameters) throws Exception {
    return GetEmailMessage(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary GetEmailMessage(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "GetEmailMessage";

    Node methodNode = Proccess(className, methodName, parameters, locale);

    return ParseXML(methodNode);
  }

  /**
   * Gets the formated HTML message
   *
   * @returns		the message or an exception
   */
  public static String GetHtmlMessage(CakeDictionary parameters) throws Exception {
    return GetHtmlMessage(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static String GetHtmlMessage(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "GetHtmlMessage";

    Node methodNode = Proccess(className, methodName, parameters, locale);

    return GetChildNodeByName(methodNode, "html_message").getTextContent();
  }

  /**
   * Gets a mailing
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
              "mailing"
            });
    ChangeKey(res, "mailing", "mailings");

    return ParseXML(methodNode);
  }

  /**
   * Returns the list of links for a mailing
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
   * Returns the total and unique counts of openings for mailing's links
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
   * Retrieves the list of mailings
   * 
   * @returns		the new trigger's id or an exception
   */
  public static CakeDictionary GetList(CakeDictionary parameters) throws Exception {
    return GetList(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary GetList(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "GetList";

    Node methodNode = Proccess(className, methodName, parameters, locale);
    CakeDictionary res = ParseXML(methodNode, new String[]{
              "mailing"
            });
    ChangeKey(res, "mailing", "mailings");

    return res;
  }

  /**
   * Returns the logs of a mailing
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
              "count",
              "log"
            });
    ChangeKey(res, "log", "logs");

    return res;
  }

  /**
   * Gets a mailing information
   * 
   * @returns		an array or an exception
   */
  public static CakeDictionary GetStats(CakeDictionary parameters) throws Exception {
    return GetStats(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary GetStats(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "GetStats";

    Node methodNode = Proccess(className, methodName, parameters, locale);

    return ParseXML(methodNode);
  }

  /**
   * Gets the formated TEXT message
   *
   * @returns		the message or an exception
   */
  public static String GetTextMessage(CakeDictionary parameters) throws Exception {
    return GetTextMessage(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static String GetTextMessage(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "GetTextMessage";

    Node methodNode = Proccess(className, methodName, parameters, locale);

    return GetChildNodeByName(methodNode, "text_message").getTextContent();
  }

  /**
   * Resumes a suspended mailing
   * 
   * @returns		true or an exception
   */
  public static boolean Resume(CakeDictionary parameters) throws Exception {
    return Resume(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static boolean Resume(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "Resume";

    Proccess(className, methodName, parameters, locale);

    return true;
  }

  /**
   * Schedules a mailing
   * 
   * @returns		true or an exception
   */
  public static boolean Schedule(CakeDictionary parameters) throws Exception {
    return Schedule(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static boolean Schedule(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "Schedule";

    Proccess(className, methodName, parameters, locale);

    return true;
  }

  /**
   * Suspends a mailing
   * 
   * @returns		true or an exception
   */
  public static boolean Suspend(CakeDictionary parameters) throws Exception {
    return Suspend(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static boolean Suspend(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "Suspend";

    Proccess(className, methodName, parameters, locale);

    return true;
  }

  /**
   * Sends a test email
   * 
   * @returns		true or an exception
   */
  public static boolean SendTestEmail(CakeDictionary parameters) throws Exception {
    return SendTestEmail(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static boolean SendTestEmail(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "SendTestEmail";

    Proccess(className, methodName, parameters, locale);

    return true;
  }

  /**
   * Sets the parameters for a mailing
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
   * Unschedules a mailing
   * 
   * @returns		true or an exception
   */
  public static boolean Unschedule(CakeDictionary parameters) throws Exception {
    return Unschedule(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static boolean Unschedule(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "Unschedule";

    Proccess(className, methodName, parameters, locale);

    return true;
  }
}
