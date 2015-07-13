/*
 * CakeList.java
 */
package com.cakemail.api;

import org.w3c.dom.Node;

public class CakeList extends CakeObject {

  private static String className = "ClassList";

  /**
   * Adds a test e-mail to list
   *
   * @returns		true or an exception
   */
  public static boolean AddTestEmail(CakeDictionary parameters) throws Exception {
    return AddTestEmail(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static boolean AddTestEmail(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "AddTestEmail";

    Proccess(className, methodName, parameters, locale);

    return true;
  }

  /**
   * Creates a list
   * 
   * @returns		list_id or exception
   */
  public static int Create(CakeDictionary parameters) throws Exception {
    return Create(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static int Create(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "Create";

    Node methodNode = Proccess(className, methodName, parameters, locale, true);

    return Integer.parseInt(GetChildNodeByName(methodNode, "id").getTextContent());
  }

  /**
   * Creates a sublist
   * 
   * @returns		sublist_id or an exception
   */
  public static int CreateSublist(CakeDictionary parameters) throws Exception {
    return CreateSublist(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static int CreateSublist(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "CreateSublist";

    Node methodNode = Proccess(className, methodName, parameters, locale);

    return Integer.parseInt(GetChildNodeByName(methodNode, "sublist_id").getTextContent());
  }

  /**
   * Deletes a list
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
   * Deletes an e-mail from a list
   * 
   * @returns		true or an exception
   */
  public static boolean DeleteEmail(CakeDictionary parameters) throws Exception {
    return DeleteEmail(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static boolean DeleteEmail(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "DeleteEmail";

    Proccess(className, methodName, parameters, locale);

    return true;
  }

  /**
   * Deletes a record from a list
   * 
   * @returns		true or an exception
   */
  public static boolean DeleteRecord(CakeDictionary parameters) throws Exception {
    return DeleteRecord(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static boolean DeleteRecord(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "DeleteRecord";

    Proccess(className, methodName, parameters, locale);

    return true;
  }

  /**
   * Deletes a sublist from a list
   *
   * @returns     true or an exception
   */
  public static boolean DeleteSublist(CakeDictionary parameters) throws Exception {
    return DeleteSublist(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static boolean DeleteSublist(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "DeleteSublist";

    Proccess(className, methodName, parameters, locale);

    return true;
  }

  /**
   * Deletes a test e-mail from a list
   *
   * @return		true or an exception
   */
  public static boolean DeleteTestEmail(CakeDictionary parameters) throws Exception {
    return DeleteTestEmail(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static boolean DeleteTestEmail(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "DeleteTestEmail";

    Proccess(className, methodName, parameters, locale);

    return true;
  }

  /**
   * Edits the structure of the list
   *
   * @return		true or an exception
   */
  public static boolean EditStructure(CakeDictionary parameters) throws Exception {
    return EditStructure(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static boolean EditStructure(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "EditStructure";

    Proccess(className, methodName, parameters, locale);

    return true;
  }

  /**
   * Gets the list's fields
   * 
   * @returns		an array or an exception
   */
  public static CakeDictionary GetFields(CakeDictionary parameters) throws Exception {
    return GetFields(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary GetFields(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "GetFields";

    Node methodNode = Proccess(className, methodName, parameters, locale);

    return ParseXML(methodNode, new String[]{}, true);
  }

  /**
   * Gets the list's informations
   * 
   * @returns		an array or an exception
   */
  public static CakeDictionary GetInfo(CakeDictionary parameters) throws Exception {
    return GetInfo(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary GetInfo(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "GetInfo";

    Node methodNode = Proccess(className, methodName, parameters, locale);

    Node list = GetChildNodeByName(methodNode, "list");
    if (list != null) {
      return ParseXML(list);
    } else {
      return ParseXML(GetChildNodeByName(methodNode, "sublist"));
    }
  }

  /**
   * Gets the lists with a specified status
   * 
   * @returns		an array or an exception
   */
  public static CakeDictionary GetList(CakeDictionary parameters) throws Exception {
    return GetList(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary GetList(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "GetList";

    Node methodNode = Proccess(className, methodName, parameters, locale);

    return ParseXML(methodNode);
  }

  /**
   * Retrieves a single record from a list
   *
   * @returns		an array or an exception
   */
  public static CakeDictionary GetRecord(CakeDictionary parameters) throws Exception {
    return GetRecord(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary GetRecord(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "GetRecord";

    Node methodNode = Proccess(className, methodName, parameters, locale);

    return ParseXML(methodNode);
  }

  /**
   * Gets the sublists of a list
   * 
   * @returns		an array or an exception
   */
  public static CakeDictionary GetSublists(CakeDictionary parameters) throws Exception {
    return GetSublists(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary GetSublists(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "GetSublists";

    Node methodNode = Proccess(className, methodName, parameters, locale);
    CakeDictionary res = ParseXML(methodNode, new String[]{
              "sublist"
            });
    ChangeKey(res, "sublist", "sublists");

    return res;
  }

  /**
   * Gets the list of the test e-mails
   *
   * @returns		an array or an exception
   */
  public static CakeDictionary GetTestEmails(CakeDictionary parameters) throws Exception {
    return GetTestEmails(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary GetTestEmails(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "GetTestEmails";

    Node methodNode = Proccess(className, methodName, parameters, locale);
    CakeDictionary res = ParseXML(methodNode, new String[]{
              "test_email"
            });
    ChangeKey(res, "test_email", "test_emails");

    return res;
  }

  /**
   * Imports records into a list
   *
   * @returns		an array or an exception
   */
  public static CakeDictionary Import(CakeDictionary parameters) throws Exception {
    return Import(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary Import(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "Import";

    Node methodNode = Proccess(className, methodName, parameters, locale);
    CakeDictionary res = ParseXML(methodNode, new String[]{
              "record"
            });
    ChangeKey(res, "record", "records");

    return res;
  }

  /**
   * Sets the parameters for a list
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
   * Searchs a list after a set of conditions
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
              "record"
            });
    ChangeKey(res, "record", "records");

    return res;
  }

  /**
   * Shows a list
   * 
   * @returns		an array or an exception
   */
  public static CakeDictionary Show(CakeDictionary parameters) throws Exception {
    return Show(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary Show(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "Show";

    Node methodNode = Proccess(className, methodName, parameters, locale);
    CakeDictionary res = ParseXML(methodNode, new String[]{
              "record"
            });
    ChangeKey(res, "record", "records");

    return res;
  }

  /**
   * Subscribes an e-mail to a list
   * 
   * @returns		true or an exception
   */
  public static int SubscribeEmail(CakeDictionary parameters) throws Exception {
    return SubscribeEmail(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static int SubscribeEmail(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "SubscribeEmail";

    Node methodNode = Proccess(className, methodName, parameters, locale);

    return Integer.parseInt(GetChildNodeByName(methodNode, "record_id").getTextContent());
  }

  /**
   * Tests a sublist before creation
   *
   * @returns		the results fetching the query or an exception
   */
  public static CakeDictionary TestSublist(CakeDictionary parameters) throws Exception {
    return TestSublist(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary TestSublist(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "TestSublist";

    Node methodNode = Proccess(className, methodName, parameters, locale);
    CakeDictionary res = ParseXML(methodNode, new String[]{
              "record"
            });
    ChangeKey(res, "record", "records");

    return res;
  }

  /**
   * Unsubscribes an e-mail from a list
   * 
   * @returns		true or an exception
   */
  public static boolean UnsubscribeEmail(CakeDictionary parameters) throws Exception {
    return UnsubscribeEmail(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static boolean UnsubscribeEmail(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "UnsubscribeEmail";

    Proccess(className, methodName, parameters, locale);

    return true;
  }

  /**
   * Updates a record into a list
   * 
   * @returns		true or an exception
   */
  public static boolean UpdateRecord(CakeDictionary parameters) throws Exception {
    return UpdateRecord(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static boolean UpdateRecord(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "UpdateRecord";

    Proccess(className, methodName, parameters, locale);

    return true;
  }

  /**
   * Uploads a file into a list
   *
   * @returns		an array or an exception
   */
  public static CakeDictionary Upload(CakeDictionary parameters) throws Exception {
    return Upload(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary Upload(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "Upload";

    Node methodNode = Proccess(className, methodName, parameters, locale);

    return ParseXML(methodNode);
  }
}
