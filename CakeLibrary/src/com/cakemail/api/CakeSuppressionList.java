/*
 * CakeSuppressionList.java
 */
package com.cakemail.api;

import org.w3c.dom.Node;

public class CakeSuppressionList extends CakeObject {
    
  private static String className = "ClassSuppressionList";

  /* Imports one or more domains into the suppression list */

  public static CakeDictionary ImportDomains(CakeDictionary parameters) throws Exception {
    return ImportDomains(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary ImportDomains(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "ImportDomains";

    Node methodNode = Proccess(className, methodName, parameters, locale);
    CakeDictionary res = ParseXML(methodNode, new String[]{
              "record"
            });
    ChangeKey(res, "record", "records");

    return res;
  }

  /* Imports one or more emails into the suppression list */

  public static CakeDictionary ImportEmails(CakeDictionary parameters) throws Exception {
    return ImportEmails(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary ImportEmails(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "ImportEmails";

    Node methodNode = Proccess(className, methodName, parameters, locale);
    CakeDictionary res = ParseXML(methodNode, new String[]{
              "record"
            });
    ChangeKey(res, "record", "records");

    return res;
  }

  /* Imports one or more local-parts into the suppression list */

  public static CakeDictionary ImportLocalparts(CakeDictionary parameters) throws Exception {
    return ImportLocalparts(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary ImportLocalparts(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "ImportLocalparts";

    Node methodNode = Proccess(className, methodName, parameters, locale);
    CakeDictionary res = ParseXML(methodNode, new String[]{
              "record"
            });
    ChangeKey(res, "record", "records");

    return res;
  }

  /* Exports the domains from the suppression list */

  public static CakeDictionary ExportDomains(CakeDictionary parameters) throws Exception {
    return ExportDomains(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary ExportDomains(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "ExportDomains";

    Node methodNode = Proccess(className, methodName, parameters, locale);

    return ParseXML(methodNode);
  }

  /* Exports the emails from the suppression list */

  public static CakeDictionary ExportEmails(CakeDictionary parameters) throws Exception {
    return ExportEmails(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary ExportEmails(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "ExportEmails";

    Node methodNode = Proccess(className, methodName, parameters, locale);

    return ParseXML(methodNode);
  }

  /* Exports the local-parts from the suppression list */

  public static CakeDictionary ExportLocalparts(CakeDictionary parameters) throws Exception {
    return ExportLocalparts(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary ExportLocalparts(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "ExportLocalparts";

    Node methodNode = Proccess(className, methodName, parameters, locale);

    return ParseXML(methodNode);
  }

  /* Deletes one or more domains from the suppression list */

  public static CakeDictionary DeleteDomains(CakeDictionary parameters) throws Exception {
    return DeleteDomains(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary DeleteDomains(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "DeleteDomains";

    Node methodNode = Proccess(className, methodName, parameters, locale);
    CakeDictionary res = ParseXML(methodNode, new String[]{
              "record"
            });
    ChangeKey(res, "record", "records");

    return res;
  }

  /* Deletes one or more emails from the suppression list */

  public static CakeDictionary DeleteEmails(CakeDictionary parameters) throws Exception {
    return DeleteEmails(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary DeleteEmails(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "DeleteEmails";

    Node methodNode = Proccess(className, methodName, parameters, locale);
    CakeDictionary res = ParseXML(methodNode, new String[]{
              "record"
            });
    ChangeKey(res, "record", "records");

    return res;
  }

  /* Deletes one or more local-parts from the suppression list */

  public static CakeDictionary DeleteLocalparts(CakeDictionary parameters) throws Exception {
    return DeleteLocalparts(parameters, CakeGlobals.DEFAULT_LOCALE);
  }

  public static CakeDictionary DeleteLocalparts(CakeDictionary parameters, String locale) throws Exception {
    String methodName = "DeleteLocalparts";

    Node methodNode = Proccess(className, methodName, parameters, locale);
    CakeDictionary res = ParseXML(methodNode, new String[]{
              "record"
            });
    ChangeKey(res, "record", "records");

    return res;
  }
}
