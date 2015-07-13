package com.cakemail.api;

import java.io.FileWriter;

public class CakeDebug {

  public static void AddMessage(String message) throws Exception {
    if (CakeGlobals.DEBUG) {
      FileWriter logFile = new FileWriter(CakeGlobals.LOG_FILENAME, true);
      try {
        logFile.write(message + "\r\n");
      } finally {
        logFile.close();
      }
    }
  }
}
