/*
 * ParamUtils.java
 */
package cakebench;

import com.cakemail.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;

public class ParamUtils {

  public static CakeDictionary ParamsToCakeDictionary(ParamDictionary collection) {
    return ParamsToCakeDictionary(collection, false);
  }

  public static CakeDictionary ParamsToCakeDictionary(ParamDictionary collection, boolean strict) {
    return ParamsToCakeDictionary(collection, strict, null);
  }

  public static CakeDictionary ParamsToCakeDictionary(ParamDictionary collection, boolean strict, String[] constantKeys) {
    ArrayList<String> constantKeyList = (constantKeys != null ? new ArrayList<String>(Arrays.asList(constantKeys)) : null);

    if (collection == null) {
      return null;
    } else {
      CakeDictionary resCakeDictionary = new CakeDictionary(collection.size());
      Enumeration<String> keys = collection.keys();
      while (keys.hasMoreElements()) {
        String key = keys.nextElement();

        if (strict && (constantKeyList == null || !(constantKeyList.contains(key))) &&
                (collection.get(key) == null || collection.get(key).equals(""))) {
          continue;
        }
        resCakeDictionary.put(key, collection.get(key));
      }

      return resCakeDictionary;
    }

  }

  public static String ParamsToString(ParamDictionary collection) {
    StringBuilder resultString = new StringBuilder();

    if (collection != null) {
      Enumeration<String> keys = collection.keys();
      while (keys.hasMoreElements()) {
        String key = keys.nextElement();
        resultString.append(key + "=" + collection.get(key) + "\n");
      }
    }

    return (resultString.toString());
  }

  public static ParamDictionary StringToParams(String value) {
    ParamDictionary collection;
    String[] stringArray;
    String delimiters = "\r|\n|&";

    collection = new ParamDictionary();
    stringArray = value.split(delimiters);
    for (int i = 0; i < stringArray.length; i++) {
      String str = stringArray[i];

      if (!str.equals("")) {
        int posEgal = str.indexOf("=", 0);
        if (posEgal != -1) {
          collection.put(str.substring(0, posEgal), str.substring(posEgal + 1));
        }
      }
    }

    return (collection);
  }
}
