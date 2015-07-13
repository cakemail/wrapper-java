/*
 * CakeGlobals.java
 */
package com.cakemail.api;

import java.io.FileInputStream;
import java.util.Properties;
import java.io.InputStream;
        
public class CakeGlobals {

  public static String CAKE_VERSION = "1.0";
  public static String API_CAKE_URL = "http://api.cakemail.com";
  public static String CAKE_MCRYPT_ALG = "Blowfish";

  public static String CAKE_MCRYPT_MODE = "CBC";

  public static String CAKE_INTERFACE_KEY = "";
  public static int CAKE_INTERFACE_ID = 0;
  public static String DEFAULT_LOCALE = "en_US";
  public static boolean DEBUG = false;
  public static String LOG_FILENAME;

	/**
	 * Load the properties from ini file
	 *
	 * Rename CakeGlobals.ini.template to CakeGlobals.ini,
	 * fill each parameter and use CakeGlobals.LoadFromIniFile('CakeGlobals.ini')
	 */
	public static void LoadFromIniFile(String iniFile) throws Exception {
    LoadFromInputStream(new FileInputStream(iniFile));
  }

  public static void LoadFromInputStream(InputStream propsStream) throws Exception {
    Properties props = new Properties();
    props.load(propsStream);
    
    CakeGlobals.CAKE_VERSION = props.getProperty("CAKE_VERSION").trim();
    CakeGlobals.API_CAKE_URL = props.getProperty("API_CAKE_URL").trim();
    CakeGlobals.CAKE_MCRYPT_ALG = props.getProperty("CAKE_MCRYPT_ALG").trim();
    CakeGlobals.CAKE_MCRYPT_MODE = props.getProperty("CAKE_MCRYPT_MODE").trim();
    CakeGlobals.CAKE_INTERFACE_KEY = props.getProperty("CAKE_INTERFACE_KEY").trim();
    CakeGlobals.CAKE_INTERFACE_ID = Integer.valueOf(props.getProperty("CAKE_INTERFACE_ID").trim());
    CakeGlobals.DEFAULT_LOCALE = props.getProperty("DEFAULT_LOCALE").trim();
    CakeGlobals.DEBUG = Boolean.valueOf(props.getProperty("DEBUG").trim());
    CakeGlobals.LOG_FILENAME = props.getProperty("LOG_FILENAME").trim();
  }
}
