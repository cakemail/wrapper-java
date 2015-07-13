package com.cakemail.api;

import java.util.Enumeration;
import java.util.Date;
import java.net.URLEncoder;
import java.net.URL;
import java.net.HttpURLConnection;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.HttpsURLConnection;
import java.io.StringReader;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;

public class CakeXML {

  protected String classes;
  protected boolean closed_xml;
  protected int depth;
  protected Document response;
  protected String x_i;
  protected String x_v;
  protected String xml;

  /**
   * Adds a new class into the xml' structure
   *
   * @class		the class' name
   */
  public void AddClass(String className) throws Exception {
    AddClass(className, "en_US");
  }

  public void AddClass(String className, String locale) throws Exception {
    if (closed_xml) {
      throw new CakeException(CakeErrors.CLOSED_XML);
    }
    Insert("<class type=\"" + Encode(className) + "\" locale=\"" + Encode(locale) + "\">");
    depth += 2;
  }

  /**
   * Adds a new method to a class
   *
   * @method		the method's name
   */
  public void AddMethod(String methodName) throws Exception {
    if (closed_xml) {
      throw new CakeException(CakeErrors.CLOSED_XML);
    }
    Insert("<method type=\"" + Encode(methodName) + "\">");
    depth += 2;
  }

  /**
   * Adds a new parameter into a method
   *
   * @parameters	the parameters
   */
  public void AddParameters(CakeDictionary parameters) throws Exception {
    AddParameters(parameters, false);
  }
  
  public void AddParameters(CakeDictionary parameters, boolean reverseData) throws Exception {
    if (closed_xml) {
      throw new CakeException(CakeErrors.CLOSED_XML);
    }
    Enumeration<String> parameter = parameters.keys();
    while (parameter.hasMoreElements()) {
      String key = parameter.nextElement();
      Object value = parameters.get(key);
      if (value instanceof CakeDictionary) {
        CakeDictionary customParameters = (CakeDictionary) value;
        Enumeration<String> customParameter = customParameters.keys();
        while (customParameter.hasMoreElements()) {
          String customKey = customParameter.nextElement();
          Object customValue = customParameters.get(customKey);
          if (customValue instanceof CakeDictionary) {
            AddTag(key);
            CakeDictionary customParameters2 = (CakeDictionary) customValue;
            Enumeration<String> customParameter2 = customParameters2.keys();
            while (customParameter2.hasMoreElements()) {
              String customKey2 = customParameter2.nextElement();
              AddCustomParameter(customKey2, customParameters2.get(customKey2));
            }
            CloseTag(key);
          } else {
            if (reverseData) AddCustomParameter(customValue.toString(), customKey);
            else AddCustomParameter(customKey, customValue);
          }
        }
      } else {
        AddParameter(key, value);
      }
    }
  }

  /**
   * Adds a new parameter into a method
   *
   * @name		the parameter's name
   * @value		the parameters's value
   */
  public void AddParameter(String name, Object value) throws Exception {
    if (closed_xml) {
      throw new CakeException(CakeErrors.CLOSED_XML);
    }
    
    Insert("<" + name + ">" + Encode(value.toString()) + "</" + name + ">");
  }

  /**
   * Adds a new custom parameter into a method
   *
   * @type		the parameter's type
   * @value		the parameters's value
   */
  public void AddCustomParameter(String type, Object value) throws Exception {
    if (closed_xml) {
      throw new CakeException(CakeErrors.CLOSED_XML);

    }
    Insert("<data type=\"" + Encode(type) + "\">" + Encode(value.toString()) + "</data>");
  }

  /**
   * Adds a new tag
   */
  public void AddTag(String tagName) throws Exception {
    if (closed_xml) {
      throw new CakeException(CakeErrors.CLOSED_XML);
    }
    Insert("<" + tagName + ">");
    depth += 2;
  }

  /**
   * Closes the xml' structure
   */
  public void Close() throws Exception {
    if (closed_xml) {
      throw new CakeException(CakeErrors.CLOSED_XML);
    }
    depth -= 2;
    Insert("</body>");

    closed_xml = true;
  }

  /**
   * Closes a class
   */
  public void CloseClass() throws Exception {
    if (closed_xml) {
      throw new CakeException(CakeErrors.CLOSED_XML);
    }
    depth -= 2;
    Insert("</class>");
  }

  /**
   * Closes a method
   */
  public void CloseMethod() throws Exception {
    if (closed_xml) {
      throw new CakeException(CakeErrors.CLOSED_XML);
    }
    depth -= 2;
    Insert("</method>");
  }

  /**
   * Closes a tag
   */
  public void CloseTag(String tagName) throws Exception {
    if (closed_xml) {
      throw new CakeException(CakeErrors.CLOSED_XML);
    }
    depth -= 2;
    Insert("</" + tagName + ">");
  }

  /**
   * Executes the xml
   */
  public void ExecXML() throws Exception {
    if (!closed_xml) {
      throw new CakeException(CakeErrors.UNCLOSED_XML);
    }
    Date startTime = new Date();

    CakeDebug.AddMessage(xml);

    CakeCrypt cakeCrypt = new CakeCrypt();

    String xml_req = cakeCrypt.CakeEncryptHex(xml);

    String IV = null;
    if (!CakeGlobals.CAKE_MCRYPT_MODE.equalsIgnoreCase("ECB")) {
      IV = CakeCrypt.ToHex(cakeCrypt.cryptor.getIV());
    }
    if (IV == null) {
      IV = "";
    }
    String data = "id=" + CakeGlobals.CAKE_INTERFACE_ID +
            "&alg=" + CakeGlobals.CAKE_MCRYPT_ALG.toLowerCase() +
            "&mode=" + CakeGlobals.CAKE_MCRYPT_MODE.toLowerCase() +
            "&request=" + IV + xml_req;

		URL url = new URL(CakeGlobals.API_CAKE_URL);
		HttpURLConnection conn = null;
		if (url.getProtocol().equals("https")) {
			TrustManager[] trustAllCerts = new TrustManager[]{
				new X509TrustManager() {
					public java.security.cert.X509Certificate[] getAcceptedIssuers() {
						return null;
					}
					public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
					}
					public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
					}
				}
			};
			try {
				SSLContext sc = SSLContext.getInstance("SSL");
				sc.init(null, trustAllCerts, new java.security.SecureRandom());
				HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			} catch (Exception e) {
				throw new Exception("Can not add TrustManager");
			}

			conn = (HttpsURLConnection) url.openConnection();
		} else {
			conn = (HttpURLConnection) url.openConnection();
		}

    conn.setDoOutput(true);
    conn.setDoInput(true);

    conn.setRequestProperty("User-Agent", "CakeLibrary JAVA " + CakeGlobals.CAKE_VERSION);
    conn.setRequestMethod("POST");

    conn.connect();

    conn.getOutputStream().write(data.getBytes());

    StringBuffer buffer = new StringBuffer();
    InputStream input = conn.getInputStream();
    BufferedReader dataInput = new BufferedReader(new InputStreamReader(input));
    String line;
    while ((line = dataInput.readLine()) != null) {
      buffer.append(line);
      buffer.append("\n");
    }

    String resXML = buffer.toString();

    if (resXML.contains("<?xml")) {
      resXML = resXML.substring(resXML.indexOf("<?xml"));
    } else {
      resXML = cakeCrypt.CakeDecryptHex(resXML);
    }
    CakeDebug.AddMessage(resXML);

    //response = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(resXML.getBytes()));
    response = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(resXML)));

    Date endTime = new Date();

    Date spentTime = new Date(endTime.getTime() - startTime.getTime());
    CakeDebug.AddMessage("Spent time: " + (spentTime.toString()) + " s \r\n");
  }

  /**
   * Returns the xml structure
   */
  public String GetXML() {
    return xml;
  }

  /**
   * Returns the XML response
   */
  public Document GetResponse() {
    return response;
  }

  /**
   * Initialises the xml' structure
   */
  public void Init() throws Exception {
    if (xml == null) {
      xml = "";
      Insert("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
      Insert("<body version=\"" + CakeGlobals.CAKE_VERSION + "\">");
      depth += 2;
    }
  }

  /**
   * Insert a new line with identation
   *
   * @value
   * @returns	an exception in case of error
   */
  protected void Insert(String value) throws Exception {
    if (closed_xml) {
      throw new CakeException(CakeErrors.CLOSED_XML);
    }
    for (int i = 0; i < depth; i++) {
      xml += " ";
    }
    xml += value + "\n";
  }
  
  private String Encode(String str) throws Exception {
    return URLEncoder.encode(str.replaceAll(" ", "__VSH__"), "UTF-8").replaceAll("__VSH__", "%20");
  }
}
