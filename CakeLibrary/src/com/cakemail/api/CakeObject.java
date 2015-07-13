package com.cakemail.api;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;

public class CakeObject {

  public static Node Proccess(String className, String methodName, CakeDictionary parameters, String locale) throws Exception {
    return Proccess(className, methodName, parameters, locale, false);
  }
  
  public static Node Proccess(String className, String methodName, CakeDictionary parameters, String locale, boolean reverseData) throws Exception {
    CakeXML cakeXML = new CakeXML();

    cakeXML.Init();
    cakeXML.AddClass(className, locale);
    cakeXML.AddMethod(methodName);

    cakeXML.AddParameters(parameters, reverseData);

    cakeXML.CloseMethod();
    cakeXML.CloseClass();
    cakeXML.Close();

    cakeXML.ExecXML();

    Node resultNode = cakeXML.GetResponse().getElementsByTagName("result").item(0);

    CheckError(resultNode);

    Node methodNode = GetXMLMethod(resultNode, className, methodName);
    CheckError(methodNode);

    return methodNode;
  }

  public static Node GetXMLMethod(Node root, String className, String methodName) {
    NodeList classNodes = root.getChildNodes();
    for (int i = 0; i < classNodes.getLength() - 1; i++) {
      Node classNode = classNodes.item(i);
      if (classNode.getNodeName().equalsIgnoreCase("class") && classNode.getAttributes().getNamedItem("type").getTextContent().equalsIgnoreCase(className)) {
        NodeList methodNodes = classNode.getChildNodes();
        for (int i2 = 0; i < methodNodes.getLength() - 1; i++) {
          Node methodNode = methodNodes.item(i);
          if (methodNode.getNodeName().equalsIgnoreCase("method") && methodNode.getAttributes().getNamedItem("type").getTextContent().equalsIgnoreCase(methodName)) {
            return methodNode;
          }
        }
      }
    }

    return null;
  }

  public static Node GetChildNodeByName(Node node, String name) throws Exception {
    NodeList nodes = node.getChildNodes();
    for (int i = 0; i < nodes.getLength() - 1; i++) {
      Node childNode = nodes.item(i);
      if (childNode.getNodeName().equalsIgnoreCase(name)) {
        return childNode;
      }
    }

    return null;
  }

  public static void CheckError(Node method) throws Exception {
    Node errorCodeNode = GetChildNodeByName(method, "error_code");
    Node errorMessageNode = GetChildNodeByName(method, "error_message");
    ;

    if (errorCodeNode != null && errorMessageNode != null) {
      throw new CakeException(errorCodeNode.getTextContent(), URLDecoder.decode(errorMessageNode.getTextContent(), "UTF-8"));
    }
  }

  public static CakeDictionary ParseXML(Node node) throws Exception {
    return ParseXML(node, new String[]{});
  }

  public static CakeDictionary ParseXML(Node node, String[] subListNames) throws Exception {
    return ParseXML(node, subListNames, false);  
  }
  
  public static CakeDictionary ParseXML(Node node, String[] subListNames, boolean reverseData) throws Exception {
    CakeDictionary res = new CakeDictionary();

    ArrayList<String> subListNamesArrayList = new ArrayList<String>(Arrays.asList(subListNames));

    NodeList itemNodes = node.getChildNodes();
    for (int i = 0; i < itemNodes.getLength() - 1; i++) {
      Node itemNode = itemNodes.item(i);
      if (itemNode.getNodeType() != Node.ELEMENT_NODE) {
        continue;
      }
      String itemName = itemNode.getNodeName();

      if (itemName.equalsIgnoreCase("data")) {
        if (reverseData) res.put(URLDecoder.decode(itemNode.getTextContent(), "UTF-8"), URLDecoder.decode(itemNode.getAttributes().getNamedItem("type").getTextContent(), "UTF-8"));
         else res.put(itemNode.getAttributes().getNamedItem("type").getTextContent(), URLDecoder.decode(itemNode.getTextContent(), "UTF-8"));
      } else {
        if (itemNode.getChildNodes().getLength() > 1) {
          CakeDictionary resList;

          if (res.containsKey(itemName) && (res.get(itemName) instanceof CakeDictionary)) {
            resList = (CakeDictionary) res.get(itemName);
          } else {
            resList = new CakeDictionary();
            res.put(itemName, resList);
          }

          resList.put(String.valueOf(resList.size()), ParseXML(itemNode, subListNames));
        } else {
          CakeDictionary resList;

          if (res.containsKey(itemName) || (subListNamesArrayList.indexOf(itemName) > -1)) {
            if (res.containsKey(itemName) && (res.get(itemName) instanceof CakeDictionary)) {
              resList = (CakeDictionary) res.get(itemName);
            } else {
              resList = new CakeDictionary();
              if (res.containsKey(itemName)) {
                resList.put("0", res.get(itemName));
              }
              res.put(itemName, resList);
            }

            resList.put(String.valueOf(resList.size()), URLDecoder.decode(itemNode.getTextContent(), "UTF-8"));
          } else {
            res.put(itemName, URLDecoder.decode(itemNode.getTextContent(), "UTF-8"));
          }
        }
      }
    }

    return res;
  }

  public static void ChangeKey(CakeDictionary res, String oldName, String newName) {
    if (res.containsKey(oldName)) {
      res.put(newName, res.get(oldName));
      res.remove(oldName);
    }
  }
}
